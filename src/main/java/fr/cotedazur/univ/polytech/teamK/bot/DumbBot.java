package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.security.SecureRandom;
import java.util.*;

public class DumbBot extends Bot {
    HashSet<String> seenCities ;
    public DumbBot(String name, GameEngine gameEngine)
    {
        super(name, gameEngine);
        seenCities = new HashSet<String>();
    }

    @Override
    public boolean playTurn() throws WrongPlayerException
    {
        //look at city 0 and purchase a connection. if not possible, look at a random neighbor etc
        //once you find yourself on a city you've already seen, pull cards.

        //find the city with the ID corresponding to player ID

        if(buyConnection(new ArrayList<>())) {
            return true;
        }


        SecureRandom rand = new SecureRandom();
        int rand_int = rand.nextInt(100);
        if (rand_int < 20) {
            try {
                drawDestinationCard();
            } catch (DeckEmptyException e) {
                try {
                    drawWagonCard(Colors.GRAY);
                } catch (DeckEmptyException ex) {
                    return false;
                }

            }
            return true;
        } else {
            try {
                drawWagonCard(Colors.GRAY);
            } catch (DeckEmptyException e) {
                try {
                    drawDestinationCard();
                } catch (DeckEmptyException ex) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public boolean drawDestinationCard() throws DeckEmptyException, WrongPlayerException {
        displayDrawDestinationCardAction();
        return false;
    }

    @Override
    public boolean drawWagonCard(Colors toFocus) throws DeckEmptyException, WrongPlayerException {
        displayDrawWagonCardAction();
        return false;
    }

    @Override
    public boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException {
        displayBuyConnectionAction();
        return false;
    }
/*
    @Override
    public boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException {
        String currentCityID = "";
        currentCityID = findCityWithID(gameEngine.getGameMap(),currentCityID);
        while (!seenCities.contains(currentCityID)) {
            List<Connection> connections = gameEngine.getGameMap().getCity().get(currentCityID).getConnectionList();
            for (Connection connection : connections) {
/*ATTENTION CHANGER LE NOMBRE DE JOUEUR
                if (buyConnection(connection)) {
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
    public boolean drawDestinationCard(Deck<DestinationCard> shortDestinationDeck, Deck<DestinationCard> longDestinationDeck) throws DeckEmptyException {
        if(shortDestinationDeck.getRemainingCards() <=0 && longDestinationDeck.getRemainingCards() <= 0) {
            throw new DeckEmptyException("The 2 deck is empty");
        }
        int number_short = 2;
        List<DestinationCard> destCardDrawn = drawDestFromNumber(shortDestinationDeck,longDestinationDeck,number_short);
        for (int i=0 ; i<4 ; i++) {
            super.addCardDestination(destCardDrawn.removeFirst());
        }
        return true;
    }


    @Override
    public boolean drawWagonCard(Deck<WagonCard> wagonDeck, Colors toFocus) throws DeckEmptyException
    {
        if(wagonDeck.getRemainingCards() <= 0) {
            throw new DeckEmptyException("The deck is empty");
        }
        super.addCardWagon(wagonDeck.draw());
        super.addCardWagon(wagonDeck.draw());
        return true;
    }

    public void printStatus()
    {
        System.out.println(super.toString());
    }*/


}
