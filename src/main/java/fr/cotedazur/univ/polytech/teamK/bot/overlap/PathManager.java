package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.bot.Djikstra;
import fr.cotedazur.univ.polytech.teamK.game.GameView;

import java.util.*;

public class PathManager{
    private DestinationCard destCardOfpath;
    private List<Connection> connectionsForCurrentDestCard;
    private boolean cardDoable;
    private Bot owner;
    private HashMap<Colors, List<Integer>> costPerColorPerConnection = null;
    private GameView gameView;

    /**
     * Manages the paths for the bot's destination card and helps determine the best connections to purchase,
     * draw wagon cards for, or compute the total cost remaining for a particular path.
     * This class utilizes a Dijkstra algorithm to find the shortest path between the cities
     * specified in the bot's destination card.
     */
    public PathManager(DestinationCard destCard, Bot owner, GameView gameView) {
        this.destCardOfpath = destCard;
        this.owner = owner;
        this.gameView = gameView;
        resetpath();
    }

    public List<Connection> getConnectionsForCurrentDestCard() {
        return connectionsForCurrentDestCard;
    }
    public DestinationCard getDestCardOfpath() {
        return destCardOfpath;
    }

    /**
     * Resets the path by running the Dijkstra algorithm to find the shortest path
     * between the start and end cities of the current destination card.
     */
    private void resetpath() {
        this.connectionsForCurrentDestCard = Djikstra.djikstra(destCardOfpath.getEndCity(), destCardOfpath.getStartCity(),owner,false);
        if (connectionsForCurrentDestCard.size() == 0) {
            cardDoable = false;
        } else {
            cardDoable = true;
            this.costPerColorPerConnection = generateValuesFromPath();
        }
    }

    /**
     * Generates a map of colors to a list of integers representing the length of connections
     * for each color, sorted in ascending order.
     *
     * @return A map of colors to lists of integers representing the connection lengths.
     */
    private HashMap<Colors, List<Integer>> generateValuesFromPath() {
        //the goal is to generate a hashmap of <Color: ArrayList<Integer>> so that I know, per color, hdrawWagonCardow many i need (seperated by connection
        //ie if i have two rails in the path of color blue, of length 3 and 5 resp, then <Blue: [3,5]> is the entry: 3 and 5 are sorted asc.
        HashMap<Colors, List<Integer>> purchaseCostPerColorSortedDescending = new HashMap<Colors, List<Integer>>();
        for (Connection c : connectionsForCurrentDestCard) {
            Colors currentColor = c.getColor();
            Integer lengthOfCurrent = c.getLength();
            if (purchaseCostPerColorSortedDescending.containsKey(currentColor)) {
                purchaseCostPerColorSortedDescending.get(currentColor).add(lengthOfCurrent);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(lengthOfCurrent);
                purchaseCostPerColorSortedDescending.put(currentColor, newList);
            }
        }
        //sort the values ascending
        for (List<Integer> railsCost : purchaseCostPerColorSortedDescending.values()) {
            Collections.sort(railsCost);
        }
        return purchaseCostPerColorSortedDescending;
    }
    private boolean pathStillFree() {
        Player currentPlayer = gameView.getPlayerByBot(owner);
        for (Connection c : connectionsForCurrentDestCard) {
            if (!c.getIsFree() && !c.getOwner().equals(currentPlayer)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Finds the maximum number of cards of a single color owned by the bot.
     *
     * @return The maximum number of cards of a single color that the bot owns.
     */
    private Integer findMaximumSingleColorOwned() {
        HashMap<Colors, Integer> amountOwnedPerColor = cardsOwnedPerColor();
        Integer maximalSingleColor = Collections.max(amountOwnedPerColor.values());
        return maximalSingleColor + amountOwnedPerColor.get(Colors.RAINBOW);
    }
    private HashMap<Colors, Integer> cardsOwnedPerColor() {
        ArrayList<WagonCard> myWagonCards = gameView.getMyWagonCards();
        HashMap<Colors, Integer> cardsOwnedPerColor = new HashMap<>();
        for (Colors currentColor : Colors.values()) {
            cardsOwnedPerColor.put(currentColor, 0);
        }
        for (WagonCard wagonCard : myWagonCards) {
            Colors currentColor = wagonCard.getColor();
            if (cardsOwnedPerColor.containsKey(currentColor)) {
                Integer previousAmount = cardsOwnedPerColor.get(currentColor);
                cardsOwnedPerColor.put(currentColor, previousAmount + 1);
            } /*else {
                cardsOwnedPerColor.put(currentColor, 1);
            }*/
        }
        return cardsOwnedPerColor;
    }
    /**
     * Determines which connection the bot can afford to purchase based on the path and its owned cards.
     *
     * @return The connection the bot can purchase, or null if no connection can be purchased.
     */
    public Connection connectionToPurchase() {
        if (!pathStillFree()) {
            resetpath();
        }
        if (!cardDoable) {
            return null;
        }
        HashMap<Colors, Integer> amountOwnedPerColor = cardsOwnedPerColor();
        //loop over all connections to find the first we can purchase
            for (Connection c : connectionsForCurrentDestCard) {
                if (c.getIsFree()) {
                    Colors currentColor = c.getColor();
                    Integer currentCost = c.getLength();
                    Integer amountOwned;
                    if (currentColor.equals(Colors.GRAY)) {
                        amountOwned = findMaximumSingleColorOwned() + amountOwnedPerColor.get(Colors.RAINBOW);
                    }
                    else {
                        amountOwned = amountOwnedPerColor.get(currentColor) + amountOwnedPerColor.get(Colors.RAINBOW);
                    }
                    if (amountOwned - currentCost >= 0) {
                        return c;
                    }
                }

            }
            return null;
    }
    /**
     * Determines which colors the bot should draw to fulfill the connection requirements.
     *
     * @return A list of colors that the bot needs to draw cards for to fulfill the path requirements.
     */
    public List<Colors> colorsToDraw() {
        if (!pathStillFree()) {
            resetpath();
        }
        if (!cardDoable) {
            return null;
        }
        HashMap<Colors, Integer> amountOwnedPerColor = cardsOwnedPerColor();
        Integer minimalReq = Integer.MAX_VALUE;
        Colors minColor = null;
        //find the amount needed per color, and the min color with its amount
        for (Colors color : Colors.values()) {
            if (costPerColorPerConnection.containsKey(color) && costPerColorPerConnection.get(color).size() > 0) {
                Integer amountNeededForShortestConnectionOfThisColor = costPerColorPerConnection.get(color).get(0) - amountOwnedPerColor.get(color);
                if (amountNeededForShortestConnectionOfThisColor < 0) {
                    //TODO SOMEHOW GO TO PURCHASE A RAIL INSTEAD OF DRAW A WAGON
                } else if (amountNeededForShortestConnectionOfThisColor < minimalReq) {
                    minimalReq = amountNeededForShortestConnectionOfThisColor;
                    minColor = color;
                }
            }

        }
        if (minimalReq > 1) {
            return Arrays.asList(minColor);
        } else {
            //we only need one of said color, so we find the other color who is least needed and try to draw that
            costPerColorPerConnection.get(minColor).remove(0);
            Integer secondMin = Integer.MAX_VALUE;
            Colors secondMinColor = null;
            for (Colors color : Colors.values()) {
                if ((costPerColorPerConnection.containsKey(color)) && (costPerColorPerConnection.get(color).size() > 0)) {
                    Integer amountNeededForShortestConnectionOfThisColor = costPerColorPerConnection.get(color).get(0) - amountOwnedPerColor.get(color);
                    if ((amountNeededForShortestConnectionOfThisColor < minimalReq) && (amountNeededForShortestConnectionOfThisColor > 0)) {
                        secondMin = amountNeededForShortestConnectionOfThisColor;
                        secondMinColor = color;
                    }
                }
            }
            return Arrays.asList(minColor, secondMinColor);
        }
    }

    /**
     * Calculates the total remaining cost of the path for the destination card.
     *
     * @return The total cost remaining for the path, or null if the path is not doable.
     */
    public Integer findTotalCostRemaining() {
        if (!pathStillFree()) {
            resetpath();
        }
        if (!cardDoable) {
            return null;
        }
        Integer totalCost = 0;
        for (Connection c : connectionsForCurrentDestCard)
        {
            //all connections on path should be ours or free
            assert(c.getIsFree() || c.getOwner().equals(gameView.getPlayerByBot(owner)));
            if (c.getIsFree() || !c.getOwner().equals(gameView.getPlayerByBot(owner))) {
                totalCost += c.getLength();
            }
        }
        return totalCost;
    }

}

