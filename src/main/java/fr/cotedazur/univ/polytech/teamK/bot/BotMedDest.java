package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.PaquetVideException;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.Board;

import java.util.*;

public class BotMedDest extends Bot{

    public BotMedDest(String name) {
        super(name);
    }

    @Override
    public void drawDestinationCard(Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck) throws PaquetVideException {
        if(shortDestinationDeck.getRemainingCards() <=0 && longDestinationDeck.getRemainingCards() <= 0) {
            throw new PaquetVideException("The 2 deck is empty");
        }
        int number_short = 2;
        List<DestinationCard> destCardDrawn = drawDestFromNumber(shortDestinationDeck,longDestinationDeck,number_short);
        for(DestinationCard card : destCardDrawn){
            super.addCardDestination(card);
        }
    }

    @Override
    public void drawWagonCard(Deck<WagonCard> wagonDeck) throws PaquetVideException {
        if(wagonDeck.getRemainingCards() <= 0) {
            throw new PaquetVideException("The deck is empty");
        }
        super.addCardWagon(wagonDeck.draw());
        super.addCardWagon(wagonDeck.draw());
    }

    @Override
    public boolean buyConnection(Board currentMap) {
        List<DestinationCard> destinationCards = super.getCartesDestination();
        for (DestinationCard card : destinationCards){
            String startCity = card.getStartCity().getName();
            String endCity = card.getEndCity().getName();
            List<Connection> path = findShortestPath(currentMap, startCity, endCity);
            for (Connection connection : path){
                if(super.buyRail(connection, currentMap, 10)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean playTurn(Board currentMap, Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck, Deck<WagonCard> wagonDeck) {
        if (buyConnection(currentMap)) {
            return true;
        }
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
    }

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
    }
}
