package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.*;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public abstract class Bot{

    private static int COUNT = 1;
    public GameView gameView;
    public final GameEngine gameEngine;
    public String name;
    public final int id;

    public Bot(String name, GameEngine gameEngine)
    {
        this.name = name;
        this.id = COUNT++;
        this.gameEngine = gameEngine;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setGameView(GameView gameView){
        this.gameView = gameView;
    }
    /**
     * Method who will draw the 4 dest Cards with the number of short dest the player chose
     * @param number_short the number of short dest to draw
     * @return a list of the cards
     */
    public List<DestinationCard> drawDestFromNumber (int number_short) {
        List<DestinationCard> destCardDrawn = new ArrayList<>(4) ;
        DestinationCard toAddCard;
        Deck<DestinationCard> shortDestinationDeck = gameEngine.getShortDestinationDeck();
        Deck<DestinationCard> longDestinationDeck = gameEngine.getLongDestinationDeck();

        for (int i = 0 ; i < 4 ; i++) {
            if (i < number_short) {
                toAddCard = shortDestinationDeck.draw();
                if(toAddCard != null)
                    destCardDrawn.add(toAddCard);
                else
                    destCardDrawn.add(longDestinationDeck.draw());
            } else {
                toAddCard = longDestinationDeck.draw();
                if (toAddCard != null)
                    destCardDrawn.add(toAddCard);
                else
                    destCardDrawn.add(shortDestinationDeck.draw());
            }
        }
        return destCardDrawn;
    }

    /**
     The player give a list of the dest Card he doesn't want
     @param cards the cards he doesn't want
     @return true if he could give back cards
     */
    public boolean giveBackCard (List<DestinationCard> cards) {
        try {
            for (DestinationCard card : cards) {
                gameEngine.addDestinationCardToDeck(this,card);
            }
        } catch (PaquetPleinException e) {
            System.out.println("you gave too much cards");
            return false;
        }
        return true;
    }

    /**
     * Djikstra algorithm who find the shortest path between two cities
     *
     * @param cityOne the first cities
     * @param cityTwo the second cities
     * @return a arrayList with the path
     */
    public ArrayList<Connection> djikstra(City cityOne, City cityTwo) {
        ArrayList<HashMap<City,Integer>> djikstraTable = new ArrayList<>();
        HashMap<City,Integer> djikstraLine = new HashMap<>();
        ArrayList<City> seen = new ArrayList<>();
        for (City c : gameEngine.getGameMap().getCity().values()) {
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
                if (i1< i2)
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
                for (City city : line.keySet()) {
                    Integer value = Integer.MAX_VALUE;
                    if(line.get(city) <= line.get(min) && line.get(city) != -1 && !city.isCountry()) {
                        Connection connection = gameEngine.getGameMap().getNeighbourConnection(resCity.getLast() , city);
                        if(connection != null && connection.getLength() < value) {
                            min = city;
                            value = connection.getLength();
                        }
                    }
                }
                resCity.add(min);
            }
        }
        resCity.addLast(cityOne);
        //COnvertion city -> Connection

        ArrayList<Connection> res = new ArrayList<>();
        for(int i=0 ; i<resCity.size()-2 ; i++) {
            List<Connection> cityConnection = gameEngine.getGameMap().getCitiesConnections(resCity.get(i).getName());
            for(Connection connection : cityConnection) {
                if(connection.getOtherCity(resCity.get(i)) == resCity.get(i+1)){
                    res.add(connection);
                    break;
                }
            }
        }

        return res;
    }

    /**
     * The Bot will choose the number of short dest card to draw and give back the one he doesn't want
     * @return true if the draw succeed
     * @throws PaquetVideException if the destination deck is empty
     */
    public abstract boolean drawDestinationCard() throws PaquetVideException, WrongPlayerException;

    /**
     * The bot will choose the wagon card he want in the deck
     * @return true if the draw succeed
     * @throws PaquetVideException if the wagon deck is empty
     */
    public abstract boolean drawWagonCard(Colors toFocus) throws PaquetVideException, WrongPlayerException ;

    /**
     * The bot will choose to buy a connection or not
     */
    public abstract boolean buyConnection(ArrayList<Connection> path) throws WrongPlayerException;

    /**
     * The main fonction who run the bot
     * @return true if the bot did something
     */
    public abstract boolean playTurn() throws WrongPlayerException ;



}
