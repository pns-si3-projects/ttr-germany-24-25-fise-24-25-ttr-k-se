package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.Board;
import fr.cotedazur.univ.polytech.teamK.game.Game;

import javax.lang.model.type.NullType;
import java.util.*;

public class DumbBot extends  Bot {
    HashSet<String> seenCities ;
    public DumbBot(String name, Board gameMap)
    {
        super(name);
        seenCities = new HashSet<String>();
    }

    public boolean playTurn(Board currentMap, Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck, Deck<WagonCard> wagonDeck)
    {
        //look at city 0 and purchase a connection. if not possible, look at a random neighbor etc
        //once you find yourself on a city you've already seen, pull cards.

        //find the city with the ID corresponding to player ID

        if(buyConnection(currentMap)) {
            return true;
        }

        Random rand = new Random();
        int rand_int = rand.nextInt(100);
        if (rand_int < 20) {
            try {
                drawDestinationCard(shortDestinationDeck, longDestinationDeck);
            } catch (PaquetVideException e) {
                try {
                    drawWagonCard(wagonDeck);
                } catch (PaquetVideException ex) {
                    return false;
                }

            }
            return true;
        } else {
            try {
                drawWagonCard(wagonDeck);
            } catch (PaquetVideException e) {
                try {
                    drawDestinationCard(shortDestinationDeck, longDestinationDeck);
                } catch (PaquetVideException ex) {
                    return false;
                }
            }
            return true;
        }
    }

    private String findCityWithID(Board currentMap, String currentCityID) {
        for (Map.Entry<String, City> entry : currentMap.getCity().entrySet())
        {
            if (currentCityID.equals(""))
            {
                currentCityID = entry.getValue().getName();
            }
            if (entry.getValue().getId() == super.getId())
            {
                currentCityID = entry.getValue().getName();
            }
        }
        return currentCityID;
    }

    @Override
    public boolean buyConnection(Board currentMap) {
        String currentCityID = "";
        currentCityID = findCityWithID(currentMap,currentCityID);
        while (!seenCities.contains(currentCityID)) {
            List<Connection> connections = currentMap.getCity().get(currentCityID).getConnectionList();
            for (Connection connection : connections) {
/*ATTENTION CHANGER LE NOMBRE DE JOUEUR*/
                if (this.buyRail(connection,currentMap, 10)) {
                    super.takeMeeples(connection.getCityOne(), Colors.RED);
                    super.takeMeeples(connection.getCityTwo(), Colors.RED);
                    return true;
                }
            }
            seenCities.add(currentCityID);
            Random rand = new Random();
            int rand_int = rand.nextInt(connections.size());
            String oldID = currentCityID;
            currentCityID = connections.get(rand_int).getCityOne().getName();
            if (Objects.equals(oldID, currentCityID)) {
                currentCityID = connections.get(rand_int).getCityTwo().getName();
            }
        }
        return false;
    }

    @Override
    public void drawDestinationCard(Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck) throws PaquetVideException {
        if(shortDestinationDeck.getRemainingCards() <=0 && longDestinationDeck.getRemainingCards() <= 0) {
            throw new PaquetVideException("The 2 deck is empty");
        }
        int number_short = 2;
        List<DestinationCard> destCardDrawn = drawDestFromNumber(shortDestinationDeck,longDestinationDeck,number_short);
        for (int i=0 ; i<4 ; i++) {
            super.addCardDestination(destCardDrawn.removeFirst());
        }
    }

    @Override
    public void drawWagonCard(Deck<WagonCard> wagonDeck) throws PaquetVideException
    {
        if(wagonDeck.getRemainingCards() <= 0) {
            throw new PaquetVideException("The deck is empty");
        }
        super.addCardWagon(wagonDeck.draw());
        super.addCardWagon(wagonDeck.draw());
    }

    public void printStatus()
    {
        System.out.println(super.toString());
    }


}
