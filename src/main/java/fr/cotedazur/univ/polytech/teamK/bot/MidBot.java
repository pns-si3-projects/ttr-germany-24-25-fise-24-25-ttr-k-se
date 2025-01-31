package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.PaquetVideException;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.Board;

import java.util.ArrayList;
import java.util.List;

public class MidBot extends Bot{
    public MidBot() {
        super("midBot");
    }

    @Override
    public boolean drawDestinationCard(Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck) throws PaquetVideException {
        try {
            List<DestinationCard> draw = drawDestFromNumber(shortDestinationDeck, longDestinationDeck, 2);
            if (draw.get(0).getValue() < draw.get(1).getValue())
                super.addCardDestination(draw.remove(1));
            else
                super.addCardDestination(draw.removeFirst());
            if (draw.get(2).getValue() < draw.get(3).getValue())
                super.addCardDestination(draw.remove(3));
            else
                super.addCardDestination(draw.remove(2));
        } catch (PaquetVideException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean drawWagonCard(Deck<WagonCard> wagonDeck, Colors toFocus) throws PaquetVideException {
        boolean found = false;
        try {
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < wagonDeck.getVisibleCard().size(); i++) {
                    if (wagonDeck.getVisibleCard().get(i).getColor() == toFocus) {
                        super.addCardWagon(wagonDeck.drawVisibleCard(i));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    super.addCardWagon(wagonDeck.draw());
                }
                found = false;
            }
        } catch (PaquetVideException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean buyConnection(Board currentMap, ArrayList<Connection> path) {
        /*for(Connection connection : path) {
            if(connection.claimAttempt(super.getNumberColor(connection.getColor()),super,currentMap,4)) {
                return true;
            }
        }*/
        return false;
    }

    @Override
    public boolean playTurn(Board currentMap, Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck, Deck<WagonCard> wagonDeck) {
        if(super.getCartesDestination().isEmpty()) {
            return drawDestinationCard(shortDestinationDeck,longDestinationDeck);
        }/*
        DestinationCard toArchieve= super.getCartesDestination().getFirst();
        ArrayList<Connection> path = super.djikstra(toArchieve.getStartCity(), toArchieve.getEndCity(),currentMap);
        if(buyConnection(currentMap, path))
            return true;
        if(drawWagonCard(wagonDeck,path.getFirst().getColor()))
            return true;*/
        return false;
    }
}
