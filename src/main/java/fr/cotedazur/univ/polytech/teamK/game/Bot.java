package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.Cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.Cards.WagonCard;
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

    public boolean playTurn(MapHash currentMap, Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck, Deck<WagonCard> wagonDeck)
    {
        if (this.ID == 0)
        {
            //look at city 0 and purchase a connection. if not possible, look at a random neighbor etc
            //once you find yourself on a city you've already seen, pull cards.
            HashSet<String> seenCities = new HashSet<String>();
            //find the city with the ID corresponding to player ID
            String currentCityID = "";
            for (Map.Entry<String, City> entry : currentMap.getCities().entrySet())
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
            while (!seenCities.contains(currentCityID))
            {
                List<PhysicalConnection> connections = currentMap.getCities().get(currentCityID).getPhysicalConnectionList();
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


    public void printStatus()
    {
        System.out.println(super.toString());
    }


}
