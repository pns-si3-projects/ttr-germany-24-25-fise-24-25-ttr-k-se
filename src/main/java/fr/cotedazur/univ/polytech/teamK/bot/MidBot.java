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
        ArrayList<Connection> path;
        ArrayList<DestinationCard> list = gameView.getMyDestinationCards();
        DestinationCard toAchieve;
        do {
            toAchieve = list.getFirst();
            list.removeFirst();
            path = super.djikstra(toAchieve.getStartCity(), toAchieve.getEndCity());
        } while (path.isEmpty() && !list.isEmpty());
        if (list.isEmpty()) {
            return drawDestinationCard();
        }
        if (buyConnection(path)) {
            gameView.getPlayerByBot(this).validDestinationCard(toAchieve);
            return true;
        }
        return drawWagonCard(path.getFirst().getColor()) && drawWagonCard(path.getFirst().getColor());
    }

    @Override
    public boolean drawDestinationCard() throws DeckEmptyException, WrongPlayerException {
        try {
            List<DestinationCard> draw = drawDestFromNumber(2);
            List<DestinationCard> selected = new ArrayList<>();

                selected.add(draw.get(0).getValue() < draw.get(1).getValue() ? draw.remove(1) : draw.remove(0));
                selected.add(draw.get(1).getValue() < draw.get(2).getValue() ? draw.remove(2) : draw.remove(1));

            for (DestinationCard card : selected) {
                gameEngine.addDestinationCard(this,card);
            }
            displayDrawDestinationCardAction();
            giveBackCard(draw);
            return true;
        } catch (DeckEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean drawWagonCard(Colors toFocus) throws DeckEmptyException, WrongPlayerException {
        try {
            for (int i = 0; i < gameView.getWagonDeck().getVisibleCard().size(); i++) {
                if (gameView.getWagonDeck().getVisibleCard().get(i).getColor() == toFocus) {
                    return gameEngine.addWagonCard(this, gameEngine.drawVisibleWagonCard(i));
                }
            }
            gameEngine.addWagonCard(this, gameEngine.drawWagonCard());
            displayDrawWagonCardAction();
            return true;
        } catch (DeckEmptyException e) {
            return false;
        }
    }

    @Override
    public boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException {
        for(Connection connection : path) {
            if(gameEngine.buyRail(this,connection)) {
                int index;
                Colors meepleColor;
                SecureRandom rand = new SecureRandom();
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

