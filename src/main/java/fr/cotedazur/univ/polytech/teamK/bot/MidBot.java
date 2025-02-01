package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.ArrayList;
import java.util.List;

public class MidBot extends Bot{
    public MidBot(String name,GameEngine gameEngine) {
        super(name, gameEngine);
    }

    @Override
    public boolean playTurn(GameView gameView) {
        try {
            if (gameEngine.getDestinationCard(this).isEmpty()) {
                return drawDestinationCard();
            }
            ArrayList<DestinationCard> list = gameEngine.getDestinationCard(this);
            DestinationCard toArchieve = gameEngine.getDestinationCard(this).getFirst();
            ArrayList<Connection> path = super.djikstra(toArchieve.getStartCity(), toArchieve.getEndCity());
            if (buyConnection(path))
                return true;
            if (drawWagonCard(path.getFirst().getColor()))
                return true;
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
            if (draw.get(0).getValue() < draw.get(1).getValue())
                selected.add(draw.get(1));
            else
                selected.add(draw.get(0));
            if (draw.get(2).getValue() < draw.get(3).getValue())
                selected.add(draw.get(3));
            else
                selected.add(draw.get(2));
            for (DestinationCard card : selected) {
                gameEngine.addDestinationCard(this, card);
            }
            return true;
        } catch (PaquetVideException e) {
            return false;
        } catch (WrongPlayerException e) {
            throw e;
        }
    }

    @Override
    public boolean drawWagonCard(Colors toFocus) throws PaquetVideException, WrongPlayerException {
        boolean found = false;
        try {
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < gameEngine.getWagonDeck().getVisibleCard().size(); i++) {
                    if (gameEngine.getWagonDeck().getVisibleCard().get(i).getColor() == toFocus) {
                        Deck<WagonCard> wagondeck = gameEngine.getWagonDeck();
                        gameEngine.addWagonCard(this, gameEngine.getWagonDeck().drawVisibleCard(i));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    gameEngine.addWagonCard(this, gameEngine.getWagonDeck().draw());
                }
                found = false;
            }
        } catch (PaquetVideException e) {
            return false;
        } catch (WrongPlayerException e) {
            throw e;
        }
        return true;
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
