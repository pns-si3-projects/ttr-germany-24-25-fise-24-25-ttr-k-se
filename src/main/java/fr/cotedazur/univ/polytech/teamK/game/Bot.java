package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.PaquetPleinException;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.PhysicalConnection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.*;

public class Bot extends Player {
    private Integer ID;
    public Bot(Integer ID, MapHash gameMap)
    {
        super("temp name)", gameMap);
        this.ID = ID;
        if (ID == 0)
        {
            super.setName("Dumb");
        }
    }

    public boolean playTurn(MapHash currentMap,Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck, Deck<WagonCard> wagonDeck)
    {
        if (this.ID == 0)
        {
            //look at city 0 and purchase a connection. if not possible, look at a random neighbor etc
            //once you find yourself on a city you've already seen, pull cards.
            HashSet<String> seenCities = new HashSet<String>();
            //find the city with the ID corresponding to player ID
            String currentCityID = "";
            currentCityID = findCityWithID(currentMap, currentCityID);
            while (!seenCities.contains(currentCityID))
            {
                List<PhysicalConnection> connections = currentMap.getCity().get(currentCityID).getPhysicalConnectionList();

                for(PhysicalConnection connection : connections){
                    if (this.buyRail(connection)){
                        takeMeeples(connection.getCityOne(),Colors.RED);

                    }

                }
                for (int connectionIndex = 0; connectionIndex < connections.size(); connectionIndex++)

                {

                    if (this.buyRail(connections.get(connectionIndex)))
                    {
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
                if (Objects.equals(oldID, currentCityID))
                {
                    currentCityID = connections.get(rand_int).getCityTwo().getName();
                }
            }
            Random rand = new Random();
            int rand_int = rand.nextInt(100);
            if (rand_int < 20)
            {
                //draw from destination
                DestinationCard destCardDrawn = shortDestinationDeck.draw();
                if (destCardDrawn != null)
                {
                    super.addCardDestination(destCardDrawn);
                    return true;
                }

            }
            else
            {
                //draw from wagons
                WagonCard wagonCardDrawn1 = wagonDeck.draw();
                WagonCard wagonCardDrawn2 = wagonDeck.draw();
                if (wagonCardDrawn1 != null && wagonCardDrawn2 != null)
                {
                    super.addCardWagon(wagonCardDrawn1);
                    super.addCardWagon(wagonCardDrawn2);
                    return true;
                }
            }

        }
        return false;
    }

    private String findCityWithID(MapHash currentMap, String currentCityID) {
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

    public List<DestinationCard> drawDestCard (Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck, int number_short) {
        List<DestinationCard> res = new ArrayList<>(4) ;
        for (int i = 0 ; i < 4 ; i++) {
            if (i < number_short) {
                res.add(shortDestinationDeck.draw());
            } else {
                res.add(longDestinationDeck.draw());
            }
        }
        return res;
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


    public void printStatus()
    {
        System.out.println(super.toString());
    }


}
