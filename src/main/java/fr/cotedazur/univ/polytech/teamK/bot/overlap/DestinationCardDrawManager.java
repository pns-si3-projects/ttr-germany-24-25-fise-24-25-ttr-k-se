package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages the process of drawing and selecting destination cards for the bot.
 * It evaluates which destination cards to keep based on their value and feasibility.
 */
public class DestinationCardDrawManager {
    private Djikstra djikstra;
    private Bot owner;
    private GameView gameView;
    public DestinationCardDrawManager(Bot owner, GameView gameView) {
        this.owner = owner;
        this.gameView = gameView;
        this.djikstra = new Djikstra(gameView, owner);
    }



    /**
     * Represents a tuple of two destination cards and their combined value.
     * Used to determine the best destination cards to keep.
     */
    private class DestDestValueTuple
    {
        DestinationCard dest1;
        DestinationCard dest2;
        Integer combinedValue;
        public DestDestValueTuple(DestinationCard dest1, DestinationCard dest2) {
            this.dest1 = dest1;
            this.dest2 = dest2;
            this.combinedValue = dest1.getValue() + dest2.getValue();
        }

        public DestinationCard getDest1() {
            return dest1;
        }
        public DestinationCard getDest2() {
            return dest2;
        }
        public Integer getCombinedValue() {
            return combinedValue;
        }

        /**
         * Compares two tuples and returns the one with the higher combined value.
         *
         * @param other The other tuple to compare against.
         * @return The tuple with the higher combined value.
         */
        public DestDestValueTuple compareTo(DestDestValueTuple other) {
            if (other.getCombinedValue() > this.getCombinedValue()) {
                return other;
            }
            return this;
        }
        /**
         * Finds the maximum tuple from a list.
         *
         * @param others The list of tuples to compare against.
         * @return The tuple with the highest combined value.
         */
        public DestDestValueTuple findMax(List<DestDestValueTuple> others) {
            DestDestValueTuple max = this;
            for (DestDestValueTuple other : others) {
                max = max.compareTo(other);
            }
            return max;
        }
        /**
         * Checks if the two destination cards in this tuple share any common cities.
         *
         * @return True if the cards share a city, false otherwise.
         */
        public boolean citiesInCommon()
        {
            List<City> dest1Cities = new ArrayList<>(Arrays.asList(dest1.getStartCity(), dest1.getEndCity()));
            List<City> dest2Cities = new ArrayList<>(Arrays.asList(dest2.getStartCity(), dest2.getEndCity()));

            for (City city : dest1Cities) {
                if (dest2Cities.contains(city)) {
                    return true;
                }
            }
            return false;
        }
    }
    /**
     * Chooses a single destination card to keep and returns the others.
     *
     * @param destKeep   The destination card to keep.
     * @param totalDrawn The list of drawn destination cards.
     * @throws WrongPlayerException If an action is performed by the wrong player.
     */
    private void chooseSingleDestCard(DestinationCard destKeep, List<DestinationCard> totalDrawn) throws WrongPlayerException {
        for (DestinationCard destCard : totalDrawn) {
            if (destCard.equals(destKeep)) {
                owner.gameEngine.addDestinationCard(owner, destCard);
            }
            else
            {
                owner.giveBackCard(destCard);
            }
        }
    }
    /**
     * Draws destination cards and selects the best one to keep based on cost and feasibility.
     *
     * @param numberOfShortDests The number of destination cards to draw.
     * @return The selected PathManager for the best destination card.
     * @throws WrongPlayerException If an action is performed by the wrong player.
     */
    public PathManager drawDestinationsFromNumber(Integer numberOfShortDests) throws WrongPlayerException {
        List<DestinationCard> destCardsDrawn = owner.drawDestFromNumber(numberOfShortDests);
        if (destCardsDrawn.isEmpty()) {
            //System.out.println("No destination card drawn: round is " + gameView.getRound());
            owner.drawDestFromNumber(numberOfShortDests);
            return null;
        }
        Integer minimalRoadCost = Integer.MAX_VALUE;
        PathManager bestDestinationCardPathmanager = null;
        PathManager cheapestNullDest = null;
        for (DestinationCard destCard : destCardsDrawn) {
            PathManager currentDestPathManager = new PathManager(destCard, owner, gameView);
            Integer currentDestCost = currentDestPathManager.findTotalCostRemaining();
            //as long as we havent found a doable destination, we track the lowest value destination card (to lose less points)
            if (currentDestCost == null && ((cheapestNullDest == null) || cheapestNullDest.getDestCardOfpath().getValue() > currentDestPathManager.getDestCardOfpath().getValue()))
            {
                cheapestNullDest = currentDestPathManager;
            }
            else if (currentDestCost != null && currentDestCost < minimalRoadCost) {
                minimalRoadCost = currentDestCost;
                bestDestinationCardPathmanager = currentDestPathManager;
            }
        }
        if (bestDestinationCardPathmanager == null) {
            //no destination cards pulled are possible: take the one of least value
            assert cheapestNullDest != null;
            chooseSingleDestCard(cheapestNullDest.getDestCardOfpath(), destCardsDrawn);
        }

        else
        {
            chooseSingleDestCard(bestDestinationCardPathmanager.getDestCardOfpath(), destCardsDrawn);
        }
        return bestDestinationCardPathmanager;
    }

    private void chooseListDestCards(List<DestinationCard> toKeep, List<DestinationCard> totalDrawn) throws WrongPlayerException {
        for (DestinationCard destCard : toKeep) {
            owner.gameEngine.addDestinationCard(owner, destCard);
            totalDrawn.remove(destCard);
            owner.displayDrawDestinationCardAction();
        }
        for (DestinationCard destCard : totalDrawn) {
            owner.giveBackCard(destCard);
        }
    }
    /**
     * Draws and selects the original set of destination cards at the start of the game.
     *
     * @return The PathManager for the best starting destination card.
     * @throws WrongPlayerException If an action is performed by the wrong player.
     */
    public PathManager chooseOriginalDestCards() throws WrongPlayerException {
        List<DestinationCard> destCardDrawn = owner.drawDestFromNumber(0);
        List<DestDestValueTuple> tupleListCommonCities = new ArrayList<DestDestValueTuple>();
        List<DestDestValueTuple> tupleListSeperateCities = new ArrayList<DestDestValueTuple>();
        Integer n = destCardDrawn.size();
        for (int firstPassIndex = 0; firstPassIndex < n; firstPassIndex++) {
            for (int secondPassIndex = firstPassIndex + 1; secondPassIndex < n; secondPassIndex++) {
                DestinationCard destCard1 = destCardDrawn.get(firstPassIndex);
                DestinationCard destCard2 = destCardDrawn.get(secondPassIndex);
                DestDestValueTuple currentTuple = new DestDestValueTuple(destCard1, destCard2);
                if (currentTuple.citiesInCommon())
                {
                    tupleListCommonCities.add(currentTuple);
                }
                else
                {
                    tupleListSeperateCities.add(currentTuple);
                }
            }
        }

        if (!tupleListCommonCities.isEmpty()) {
            DestDestValueTuple chose = tupleListCommonCities.getFirst().findMax(tupleListCommonCities);
            List<DestinationCard> toKeep = Arrays.asList(chose.getDest1(), chose.getDest2());
            PathManager firstPathToDo = new PathManager(chose.getDest1(), owner, gameView);
            chooseListDestCards(toKeep, destCardDrawn);
            return firstPathToDo;
        }
        else
        {
            DestDestValueTuple chose = tupleListSeperateCities.getFirst().findMax(tupleListSeperateCities);
            List<DestinationCard> toKeep = Arrays.asList(chose.getDest1(), chose.getDest2());
            PathManager firstPathToDo = new PathManager(chose.getDest1(), owner, gameView);
            chooseListDestCards(toKeep, destCardDrawn);
            return firstPathToDo;
        }

    }

}