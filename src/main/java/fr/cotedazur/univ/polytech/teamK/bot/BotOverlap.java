package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.PaquetVideException;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerOwnedMap;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotOverlap extends Bot {
    private class DestDestValue {
        private DestinationCard destCard1;
        private DestinationCard destCard2;
        private Integer value;
        public DestDestValue(DestinationCard destCard1, DestinationCard destCard2, Integer value) {
            this.destCard1 = destCard1;
            this.destCard2 = destCard2;
            this.value = value;
        }
        public DestinationCard getDestCard1() {
            return destCard1;
        }
        public DestinationCard getDestCard2() {
            return destCard2;
        }
        public Integer getValue() {
            return value;
        }
    }
    @Override
    public boolean drawDestinationCard(Integer number_of_short_dests) throws PaquetVideException, WrongPlayerException {
        if (gameEngine.getRound() == 1)
        {
            List<DestinationCard> destCardDrawn = drawDestFromNumber(number_of_short_dests);
            List<DestDestValue> chosenCommonCardsWithValue = new ArrayList<DestDestValue>();
            List<DestDestValue> chosenSeperateCardsWithValue = new ArrayList<DestDestValue>();
            int combinedValue = 0;
            for (DestinationCard destCardOne : destCardDrawn)
            {
                for (DestinationCard destCardTwo : destCardDrawn)
                {
                    DestDestValue comboToAdd = new DestDestValue(destCardOne, destCardTwo, combinedValue);
                    if (cityInCommon(destCardOne, destCardTwo))
                    {
                        chosenCommonCardsWithValue.add(comboToAdd);
                    }
                    else
                    if (!destCardOne.equals(destCardTwo))
                    {
                        chosenSeperateCardsWithValue.add(comboToAdd);
                    }
                }
            }
            if (chosenCommonCardsWithValue.size() > 0)
            {
                DestDestValue maxValueDestCombo = DestDestValue.stream().mapToInt(DestDestValue::getValue).max()
            }

        }
    }

    private Boolean cityInCommon (DestinationCard dest1, DestinationCard dest2)
    {
        City dest1StartCity = dest1.getStartCity();
        City dest2StartCity = dest2.getStartCity();
        City dest1EndCity = dest1.getEndCity();
        City dest2EndCity = dest2.getEndCity();
        if (!dest1.equals(dest2) && (dest1EndCity.equals(dest2EndCity) || dest1EndCity.equals(dest2StartCity) || dest1StartCity.equals(dest2StartCity) || dest1StartCity.equals(dest2EndCity)))
        {
            return true;
        }
        return false;
    }

    private Integer combinedValue (DestinationCard dest1, DestinationCard dest2)
    {
        return dest1.getValue() + dest2.getValue();
    }

    private DestDestValue findMaWithinList(List<DestDestValue> tupleList)
    {
        if (tupleList.size() == 0)
        {
            return null;
        }
        DestDestValue maxValueTuple = tupleList.get(0);
        Integer maxValue = maxValueTuple.getValue();

        for (DestDestValue tuple : tupleList)
        {
            if(tuple.getValue() > maxValue)
            {
                maxValueTuple = tuple;
            }
        }
        return maxValueTuple;.0
    }
    @Override
    public boolean drawWagonCard(Colors toFocus) throws PaquetVideException, WrongPlayerException {
        return false;
    }

    @Override
    public boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException {
        return false;
    }

    @Override
    public boolean playTurn(GameView gameView) throws WrongPlayerException {
        return false;
    }
    /*
    private class CityIntegerTuple
    {
        private int integer;
        private City city;
        private Boolean visitied;

        public Boolean getVisitied() {
            return visitied;
        }
        public void setVisitied(Boolean visitied) {
            this.visitied = visitied;
        }

        public int getInteger() {
            return integer;
        }
        public void setInteger(int integer) {
            this.integer = integer;
        }
        public City getCity() {
            return city;
        }
        public void setCity(City city) {
            this.city = city;
        }
        public CityIntegerTuple(int integer, City city) {
            this.integer = integer;
            this.city = city;
        }
    }
    private List<City> dijkstraPreOwned (City startCity, City endCity, PlayerOwnedMap alreadyOwnedCities)
    {

        * i preload distances between start city and all other cities using already owned cities
        * then i recursively go off of that
        *
        Map<City, Integer> distances = new HashMap<City, Integer>();
        for (City city : gameEngine.getGameMap().getCity().values())
        {
            if (alreadyOwnedCities.isNeighbour(startCity.getName(), city.getName()))
            {
                distances.put(city, 0);
            }
            else
            {
                distances.put(city, -1);
            }
        }
    }

    private List<CityIntegerTuple> auxDijkstra(List<CityIntegerTuple> currentDistances, City currentCity)
    {
        for
    }
    */

    private


}
