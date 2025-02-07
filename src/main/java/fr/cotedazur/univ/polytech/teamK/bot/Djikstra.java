package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Djikstra {

    private Djikstra() {}
    /**
     * Dijkstra algorithm who find the shortest path between two cities
     * @param cityOne the first cities
     * @param cityTwo the second cities
     * @return a arrayList with the path
     */
    public static ArrayList<Connection> djikstra(City cityOne, City cityTwo, Bot owner, Boolean findNeightbor) {
        ArrayList<HashMap<City,Integer>> djikstraTable = new ArrayList<>();
        GameView gameView = owner.gameView;
        HashMap<City,Integer> djikstraLine = new HashMap<>();
        ArrayList<City> seen = new ArrayList<>();
        for (City c : gameView.getGameMap().getCity().values()) {
            djikstraLine.put(c,Integer.MAX_VALUE);
        }

        City actual = cityOne;
        int lenght = 0;
        djikstraLine.replace(cityOne,0);

        //Algo
        while (djikstraLine.get(cityTwo) > lenght) {
            for(Connection connection : actual.getConnectionList()) {
                int i1 = djikstraLine.get(actual)+connection.getLength();
                int i2 = djikstraLine.get(connection.getOtherCity(actual));
                if(connection.getOwner() == gameView.getPlayerByBot(owner)) {
                    djikstraLine.replace(connection.getOtherCity(actual),djikstraLine.get(actual));
                }
                else if (i1< i2 && connection.getIsFree())
                    djikstraLine.replace(connection.getOtherCity(actual),djikstraLine.get(actual)+connection.getLength());
            }
            HashMap<City,Integer> djikstraLineToAdd = new HashMap<>();
            djikstraLineToAdd.putAll(djikstraLine);
            if (!seen.isEmpty())
                djikstraLine.replace(seen.getLast(),-1);
            seen.add(actual);
            lenght = Integer.MAX_VALUE;
            for(City city : djikstraLine.keySet()) {
                if(djikstraLine.get(city) < lenght && !seen.contains(city) && !city.isCountry()) {
                    lenght = djikstraLine.get(city);
                    actual = city;
                }
            }
            djikstraTable.addFirst(djikstraLineToAdd);
        }

        //Récupération résultat
        ArrayList<City> resCity = new ArrayList<>();
        resCity.add(cityTwo);
        for (HashMap<City,Integer> line : djikstraTable) {
            if(line.get(resCity.getLast()) == -1);
            else if(line.get(resCity.getLast()) == Integer.MAX_VALUE ||line.get(resCity.getLast()) > lenght ) {
                City min = resCity.getLast();
                int value = Integer.MAX_VALUE;
                for (City city : line.keySet()) {
                    if(line.get(city) <= line.get(min) && line.get(city) != -1 && !city.isCountry()) {
                        Connection connection = gameView.getGameMap().getNeighbourConnection(resCity.getLast() , city);
                        if(connection != null && line.get(city) + connection.getLength() < value && line.get(city)!= Integer.MAX_VALUE) {
                            min = city;
                            value = line.get(city) + connection.getLength();
                        }
                    }
                }
                resCity.add(min);
            }
        }
        resCity.addLast(cityOne);
        //COnvertion city -> Connection

        ArrayList<Connection> res = new ArrayList<>();
        for(int i=0 ; i<resCity.size()-1 ; i++) {
            List<Connection> cityConnection = gameView.getGameMap().getCitiesConnections(resCity.get(i).getName());
            for(Connection connection : cityConnection) {
                if(connection.getOtherCity(resCity.get(i)) == resCity.get(i+1)){
                    res.add(connection);
                    break;
                }
            }
        }
        if(lenght == 0 && findNeightbor) {
            return null;
        }

        return res;
    }

    public static ArrayList<Connection> djikstra(City cityOne, City cityTwo, Bot owner) {
        return djikstra(cityOne,cityTwo,owner,true);
    }
}
