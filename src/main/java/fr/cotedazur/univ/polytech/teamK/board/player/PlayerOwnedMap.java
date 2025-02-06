package fr.cotedazur.univ.polytech.teamK.board.player;

import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.Board;

import java.util.HashMap;
import java.util.Map;

public class PlayerOwnedMap {

    private Map<String, Map<String, Integer>> virtualConnectionsCreated;
    public PlayerOwnedMap() {
        createVirtualConnectionMap();
    }
    /**
     * getter for the map
     */
    public Map<String, Map<String, Integer>> getVirtualConnectionsCreated() {
        return virtualConnectionsCreated;
    }

    /**
     * creates the map of cities the player has managed to tie together
     */
    public void createVirtualConnectionMap(){
        virtualConnectionsCreated = new HashMap<String, Map<String, Integer>>();
    }
    /**
     * adds a city to the map of cities the player has manged to tie together
     */
    private void addCityToHashmap(City city) {
        virtualConnectionsCreated.put(city.getName(), new HashMap<String, Integer>());
    }

    /**
     * Ads cityOne as a neighbor of cityTwo and vise versa
     * @param cityOneName the first city
     * @param cityTwoName the second city
     * @param length the length of the connection
     */
    private void addNeighborToCity(String cityOneName, String cityTwoName, int length) {
        virtualConnectionsCreated.get(cityOneName).put(cityTwoName, length);
        virtualConnectionsCreated.get(cityOneName).put(cityOneName, length);
        virtualConnectionsCreated.get(cityTwoName).put(cityTwoName, length);
        virtualConnectionsCreated.get(cityTwoName).put(cityOneName, length);
    }

    /**
     * connects two cities together on the map of connected cities:
     * @param cityOneName name of the first city
     * @param cityTwoName name of the second city
     * @param length length of the connection between the two cities
     * @param gameMap the board of the current game
     */
    public void connectTwoCities(String cityOneName, String cityTwoName, Integer length, Board gameMap) {
        if (!virtualConnectionsCreated.containsKey(cityOneName)) {
            addCityToHashmap(gameMap.getCity(cityOneName));
        }
        if (!virtualConnectionsCreated.containsKey(cityTwoName)) {
            addCityToHashmap(gameMap.getCity(cityTwoName));
        }
        if ((!isNeighbour(cityOneName, cityTwoName)) || (distance(cityOneName, cityTwoName) <= length)) {
            addNeighborToCity(cityOneName, cityTwoName, length);
        }
    }

    /**
     * updates the map of connected cities
     * @param c the connection that was just purchased
     * @param gameMap the board of the game
     */
    public void updateMap(Connection c, Board gameMap) {
        City cityA = c.getCityOne();
        String cityAName = cityA.getName();
        City cityB = c.getCityTwo();
        String cityBName = cityB.getName();
        Integer lengthToAdd = c.getLength();
        if (foundCity(cityAName) || (!isNeighbour(cityAName, cityBName)) || (lengthToAdd > distance(cityAName, cityBName))) {
            connectTwoCities(cityA.getName(), cityB.getName(), lengthToAdd, gameMap);
            if (cityA.isCountry() && !cityB.isCountry())
            {
                connectCountryAndCity(cityAName, cityBName, lengthToAdd, gameMap);
            }
            else if (cityB.isCountry() && !cityA.isCountry())
            {
                connectCountryAndCity(cityBName, cityAName, lengthToAdd, gameMap);
            }
            else if (!cityA.isCountry() && !cityB.isCountry())
            {
                connectCityAndCity(cityAName, cityBName, lengthToAdd, gameMap);
            }
        }
    }

    /**
     * have we found the city yet
     * @param cityName the city we want to see if we have found yet
     *
     */
    public boolean foundCity(String cityName) {
        return virtualConnectionsCreated.containsKey(cityName);
    }

    /**
     * test if two cities are connected
     * @param cityTwoName the first city
     * @param cityOneName the other city
     */
    public boolean isNeighbour(String cityOneName, String cityTwoName) {
        if (!virtualConnectionsCreated.containsKey(cityOneName))
        {
            return false;
        }
        else {
            return virtualConnectionsCreated.get(cityOneName).containsKey(cityTwoName);
        }
    }

    /**
     * distance between two cities
     * @param cityTwoName first city
     * @param cityOneName other city
     *
     */
    public int distance(String cityOneName, String cityTwoName) {
        return virtualConnectionsCreated.get(cityOneName).get(cityTwoName);
    }

    /**
     * connects a country and city
     * countries cannot be a link in a chain, they are dead ends
     * @param countryName name of the country
     * @param cityName name of the city
     * @param lengthToAdd distance between the two
     * @param gameMap board
     */

    private void connectCountryAndCity(String countryName, String cityName, int lengthToAdd, Board gameMap)
    {
        for (var cityTwo : (virtualConnectionsCreated.get(cityName)).entrySet()) {
            String cityTwoName = cityTwo.getKey();
            if (!countryName.equals(cityTwoName)) {
                lengthToAdd += virtualConnectionsCreated.get(cityTwoName).get(cityName);
                connectTwoCities(countryName, cityTwoName, lengthToAdd, gameMap);
            }
        }
    }

    /**
     * Give the length between two cities
     * @param cityOneName first city
     * @param cityTwoName second city
     * @return the length
     */
    private int lengthBetweenCities(String cityOneName, String cityTwoName) {
        return virtualConnectionsCreated.get(cityOneName).get(cityTwoName);
    }

    /**
     * connects a city and city
     * countries cannot be a link in a chain, they are dead ends
     * @param cityBName name of the country
     * @param cityAName name of the city
     * @param lengthToAdd distance between the two
     * @param gameMap board
     */
    private void connectCityAndCity(String cityAName, String cityBName, int lengthToAdd, Board gameMap)
    {
        for (var cityOne : (virtualConnectionsCreated.get(cityAName)).entrySet()) {
            String cityOneName = cityOne.getKey();
            //loop over all neighbors of B
            if (!cityOneName.equals(cityBName)) {
                for (var cityTwo : (virtualConnectionsCreated.get(cityBName)).entrySet()) {
                    String cityTwoName = cityTwo.getKey();
                    //ensure I dont connect cityOne to cityTwo.
                    //otherwise, connect cityOne to cityTwo with length(1-A) + length (A-B) + length(B-2)
                    if (!cityOneName.equals(cityTwoName) && !cityTwoName.equals(cityAName)) {
                        lengthToAdd += lengthBetweenCities(cityOneName, cityAName);
                        lengthToAdd += lengthBetweenCities(cityTwoName, cityBName);
                        connectTwoCities(cityOneName, cityTwoName, lengthToAdd, gameMap);
                    }
                }
            }
        }
    }




}
