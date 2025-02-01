package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.ArrayList;
import java.util.List;

public class MidBot extends Bot {

    public MidBot(String name, GameEngine gameEngine) {
        super(name, gameEngine);
    }

    @Override
    public boolean playTurn() {
        try {
            if (gameView.getMyDestinationCards().isEmpty()) {
                return drawDestinationCard();
            }
            ArrayList<DestinationCard> list = gameView.getMyDestinationCards();
            DestinationCard toAchieve = list.getFirst();
            ArrayList<Connection> path = super.djikstra(toAchieve.getStartCity(), toAchieve.getEndCity());
            if (buyConnection(path)) return true;
            if (drawWagonCard(path.getFirst().getColor())) return true;
        } catch (WrongPlayerException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean drawDestinationCard() throws PaquetVideException, WrongPlayerException {
        try {
            List<DestinationCard> draw = drawDestFromNumber(4);
            List<DestinationCard> selected = new ArrayList<>();

                selected.add(draw.get(0).getValue() < draw.get(1).getValue() ? draw.get(1) : draw.get(0));
                selected.add(draw.get(2).getValue() < draw.get(3).getValue() ? draw.get(3) : draw.get(2));

            for (DestinationCard card : selected) {
                gameEngine.addDestinationCard(this,card);
            }

            return true;
        } catch (PaquetVideException e) {
            return false;
        }
    }

    @Override
    public boolean drawWagonCard(Colors toFocus) throws PaquetVideException, WrongPlayerException {
        try {
            Deck<WagonCard> wagonDeck = gameEngine.getWagonDeck();
            for (int i = 0; i < wagonDeck.getVisibleCard().size(); i++) {
                if (wagonDeck.getVisibleCard().get(i).getColor() == toFocus) {
                    gameEngine.addWagonCard(this, gameEngine.getWagonDeck().drawVisibleCard(i));
                    return true;
                }
            }
            gameEngine.addWagonCard(this, gameEngine.getWagonDeck().draw());
            return true;
        } catch (PaquetVideException e) {
            return false;
        } catch (WrongPlayerException e) {
            throw e;
        }
    }

    @Override
    public boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException {
        try {
            for(Connection connection : path) {
                if(connection.claimAttempt(gameEngine.getNumberColor(this, connection.getColor()),gameEngine.getPlayerByBot(this),gameEngine.getGameMap(), gameEngine.getNumberPlayer())) {
                    return true;
                }
            }
        } catch (WrongPlayerException e) {
            throw e;
        }
        return false;
    }
}

