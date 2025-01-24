package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Cards.*;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.PhysicalConnection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.MapHash;

import javax.lang.model.type.NullType;
import java.util.*;

public class DumbBot extends  Bot {
    HashSet<String> seenCities ;
    public DumbBot(String name, MapHash gameMap)
    {
        super(name, gameMap);
        seenCities = new HashSet<String>();
    }

    public boolean playTurn(MapHash currentMap, Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck, Deck<WagonCard> wagonDeck)
    {
        //look at city 0 and purchase a connection. if not possible, look at a random neighbor etc
        //once you find yourself on a city you've already seen, pull cards.

        //find the city with the ID corresponding to player ID
        String currentCityID = "";
        for (Map.Entry<String, City> entry : currentMap.getCities().entrySet()) {
            if (currentCityID.equals("")) {
                currentCityID = entry.getValue().getName();
            }
            if (entry.getValue().getId() == super.getId()) {
                currentCityID = entry.getValue().getName();
            }
        }
        while (!seenCities.contains(currentCityID)) {
            List<PhysicalConnection> connections = currentMap.getCities().get(currentCityID).getPhysicalConnectionList();
            for (int connectionIndex = 0; connectionIndex < connections.size(); connectionIndex++) {

                if (this.buyRail(connections.get(connectionIndex))) {
                    super.takeMeeples(connections.get(connectionIndex).getCityOne(), Colors.RED);
                    super.takeMeeples(connections.get(connectionIndex).getCityTwo(), Colors.RED);
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

    public boolean giveBackCard (List<DestinationCard> cards, Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck) {
        try {
            for (DestinationCard card : cards) {
                if (card.getValue() > 11) {
                    longDestinationDeck.addCard(card);
                } else {
                    shortDestinationDeck.addCard(card);
                }
            }
        } catch (PaquetPleinException e) {
            System.out.println("you gave too much cards");
            return false;
        }
        return true;
    }

    @Override
    public void buyRail(MapHash currentMap) {

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
