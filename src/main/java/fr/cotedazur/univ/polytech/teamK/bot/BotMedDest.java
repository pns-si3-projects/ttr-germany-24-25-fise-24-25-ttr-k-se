package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.PaquetVideException;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.*;

public class BotMedDest extends Bot{

    public BotMedDest(String name, GameEngine<Bot> gameEngine) {
        super(name, gameEngine);
    }

    @Override
    public boolean drawDestinationCard() throws PaquetVideException, WrongPlayerException {
        if(gameEngine.getShortDestinationDeck().getRemainingCards() <=0 && gameEngine.getLongDestinationDeck().getRemainingCards() <= 0) {
            throw new PaquetVideException("The 2 deck is empty");
        }
        int number_short = 2;
        List<DestinationCard> destCardDrawn = drawDestFromNumber(number_short);
        for(DestinationCard card : destCardDrawn){
            gameEngine.addDestinationCard(this,card);
        }
        return true;
    }

    @Override
    public boolean drawWagonCard(Colors toFocus) throws PaquetVideException, WrongPlayerException {
        try {
            if (gameEngine.getWagonDeck().getRemainingCards() <= 0) {
                throw new PaquetVideException("The deck is empty");
            }
            gameEngine.addWagonCard(this,gameEngine.getWagonDeck().draw());
            gameEngine.addWagonCard(this,gameEngine.getWagonDeck().draw());
            return true;
        } catch (PaquetVideException e) {
            return false;
        }
    }

    @Override
    public boolean buyConnection(ArrayList<Connection> path) throws PaquetVideException, WrongPlayerException {
        try {
            List<DestinationCard> destinationCards = gameEngine.getDestinationCard(this);
            for (DestinationCard card : destinationCards) {
                City startCity = card.getStartCity();
                City endCity = card.getEndCity();
                List<Connection> way = djikstra(startCity, endCity);
                for (Connection connection : way) {
                    if (gameEngine.buyRail(this, connection, gameEngine.getGameMap(), gameEngine.getNumberPlayer())) {
                        return true;
                    }
                }
            }
        } catch (PaquetVideException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean playTurn() throws PaquetVideException, WrongPlayerException {
        try {
            if (buyConnection(null)) {
                return true;
            }
        } catch (WrongPlayerException e) {
            throw new RuntimeException(e);
        }
        try {
            drawDestinationCard();
        } catch (PaquetVideException e) {
            try {
                drawWagonCard(null);
            } catch (PaquetVideException ex) {
                return false;
            } catch (WrongPlayerException e1) {
                throw new RuntimeException(e1);
            }
        }
        return true;
    }
/*
    private List<Connection> findShortestPath(Board currentMap, String startCity, String endCity) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, Connection> previous = new HashMap<>();
        PriorityQueue<City> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String cityName : currentMap.getCity().keySet()) {
            distances.put(cityName, Integer.MAX_VALUE);
        }
        distances.put(startCity, 0);
        queue.add(currentMap.getCity().get(startCity));

        while (!queue.isEmpty()) {
            City currentCity = queue.poll();
            if (currentCity.getName().equals(endCity)) {
                break;
            }
            for (Connection connection : currentCity.getConnectionList()) {
                City neighbor = connection.getOtherCity(currentCity);
                int newDist = distances.get(currentCity.getName()) + connection.getLength();
                if (newDist < distances.get(neighbor.getName())) {
                    distances.put(neighbor.getName(), newDist);
                    previous.put(neighbor.getName(), connection);
                    queue.add(neighbor);
                }
            }
        }

        List<Connection> path = new ArrayList<>();
        for (Connection connection = previous.get(endCity); connection != null; connection = previous.get(connection.getOtherCity(currentMap.getCity().get(connection.getCityOne().getName())))) {
            path.add(connection);
        }
        Collections.reverse(path);
        return path;
    }*/
}
