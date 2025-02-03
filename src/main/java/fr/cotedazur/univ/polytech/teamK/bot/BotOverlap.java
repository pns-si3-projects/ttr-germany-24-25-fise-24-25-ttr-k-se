package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
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
    @Override
    public boolean drawDestinationCard() throws PaquetVideException, WrongPlayerException {
        if (gameEngine.getRound())

        return false;
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
