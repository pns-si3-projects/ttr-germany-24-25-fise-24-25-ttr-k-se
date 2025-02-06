package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DeckEmptyException;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerSeenException;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.*;

public class Steve extends Bot {

    public Steve(String name, GameEngine gameEngine) {
        super(name, gameEngine);
    }

    @Override
    public boolean playTurn() throws WrongPlayerException {
        try{
            if(gameView.getMyDestinationCards().isEmpty()){
                return drawDestinationCard();
            }
            ArrayList<Connection> path = null;
            List<DestinationCard> list = gameView.getMyDestinationCards();
            DestinationCard toAchieve = null;
            int highestValue = 0;

            for(DestinationCard card : list){
                if(card.getValue() > highestValue){
                    highestValue = card.getValue();
                    toAchieve = card;
                }
            }

            if(toAchieve != null){
                path = super.djikstra(toAchieve.getStartCity(), toAchieve.getEndCity());
                if(!path.isEmpty() && buyConnection(path)){
                    gameView.getPlayerByBot(this).validDestinationCard(toAchieve);
                    return true;
                }
            }

            if(drawWagonCard(path.get(0).getColor())){
                return true;
            }
        }catch(DeckEmptyException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean drawDestinationCard() {
        try {
            List<DestinationCard> draw = drawDestFromNumber(4);
            List<DestinationCard> selected = new ArrayList<>();

            draw.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
            selected.add(draw.get(0));
            selected.add(draw.get(1));

            for (DestinationCard card : selected) {
                gameEngine.addDestinationCard(this, card);
            }
            displayDrawDestinationCardAction();
            return true;
        } catch (DeckEmptyException | WrongPlayerException e){
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
                gameEngine.addWagonCard(this, gameView.getWagonDeck().drawVisibleCard(i));
                displayDrawWagonCardAction();
                return true;
            }
        }catch (DeckEmptyException e){
            return false;
        }catch(WrongPlayerException z){
            throw z;
        }
        return false;
    }

    @Override
    public boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException {
        for (Connection connection : path) {
            if (gameEngine.buyRail(this, connection)) {
                int index;
                Colors meepleColor;
                Random rand = new Random();
                try {
                    do {
                        index = rand.nextInt(6);
                        meepleColor = Colors.GRAY.getColorById(index);
                    } while (!gameEngine.takeMeeples(this, connection.getCityOne(), meepleColor));
                } catch (PlayerSeenException ignored) {
                }
                try {
                    do {
                        index = rand.nextInt(6);
                        meepleColor = Colors.GRAY.getColorById(index);
                    } while (!gameEngine.takeMeeples(this, connection.getCityTwo(), meepleColor));
                } catch (PlayerSeenException ignored) {
                    throw new PlayerSeenException("Player already passed by this city. Can no longer take Meeples.");
                }
                displayBuyConnectionAction();
                return true;
            }
        }
        return false;
    }


}