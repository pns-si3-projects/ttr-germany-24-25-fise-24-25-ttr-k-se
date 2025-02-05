package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerSeenException;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MidBot extends Bot {

    public MidBot(String name, GameEngine gameEngine) {
        super(name, gameEngine);
    }

    @Override
    public boolean playTurn() throws WrongPlayerException {
        if (gameView.getMyDestinationCards().isEmpty()) {
            return drawDestinationCard();
        }
        ArrayList<DestinationCard> list = gameView.getMyDestinationCards();
        DestinationCard toAchieve = list.getFirst();
        ArrayList<Connection> path = super.djikstra(toAchieve.getStartCity(), toAchieve.getEndCity());
        if (buyConnection(path)) return true;
        if (drawWagonCard(path.getFirst().getColor())) {
            return true;
        }
        //else{
                //lui donner une alternative, ici il ne peut plus récupérer de cartes wagons.
        //}
        return false;
    }

    @Override
    public boolean drawDestinationCard() throws DeckEmptyException, WrongPlayerException {
        try {
            List<DestinationCard> draw = drawDestFromNumber(4);
            List<DestinationCard> selected = new ArrayList<>();

                selected.add(draw.get(0).getValue() < draw.get(1).getValue() ? draw.get(1) : draw.get(0));
                selected.add(draw.get(2).getValue() < draw.get(3).getValue() ? draw.get(3) : draw.get(2));

            for (DestinationCard card : selected) {
                gameEngine.addDestinationCard(this,card);
            }
            displayDrawDestinationCardAction();
            return true;
        } catch (DeckEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean drawWagonCard(Colors toFocus) throws DeckEmptyException, WrongPlayerException {
        try {
            Deck<WagonCard> wagonDeck = gameView.getWagonDeck();
            List<WagonCard> visibleCard = wagonDeck.getVisibleCard();
            for (int i = 0; i < visibleCard.size(); i++) {
                if (wagonDeck.getVisibleCard().get(i).getColor() == toFocus) {
                    return gameEngine.addWagonCard(this, gameView.getWagonDeck().drawVisibleCard(i));
                }
            }
            gameEngine.addWagonCard(this, gameView.getWagonDeck().draw());
            displayDrawWagonCardAction();
            return true;
        } catch (DeckEmptyException e) {
            return false;
        } catch (WrongPlayerException e) {
            throw e;
        }
    }

    @Override
    public boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException {
        for(Connection connection : path) {
            if(gameEngine.buyRail(this,connection)) {
                int index;
                Colors meepleColor;
                SecureRandom rand = new SecureRandom();
                int[] res = gameView.getMyMeeples().getListOfOwnedMeeples();
                try {
                    do {
                        index = rand.nextInt(6);
                        meepleColor = Colors.GRAY.getColorById(index);
                    } while (!gameEngine.takeMeeples(this,connection.getCityOne(),meepleColor));
                } catch (PlayerSeenException ignored){
                }
                try {
                    do {
                        index = rand.nextInt(6);
                        meepleColor = Colors.GRAY.getColorById(index);
                    } while (!gameEngine.takeMeeples(this,connection.getCityTwo(),meepleColor));
                } catch (PlayerSeenException ignored) {}
                displayBuyConnectionAction();
                return true;
            }
        }
        return false;
    }
}

