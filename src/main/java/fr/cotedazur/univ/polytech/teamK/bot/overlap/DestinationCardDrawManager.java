package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DestinationCardDrawManager {
    private Djikstra djikstra;
    private Bot owner;
    private GameView gameView;
    public DestinationCardDrawManager(Bot owner, GameView gameView) {
        this.owner = owner;
        this.gameView = gameView;
        this.djikstra = new Djikstra(gameView, owner);
    }




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


        public DestDestValueTuple compareTo(DestDestValueTuple other) {
            if (other.getCombinedValue() > this.getCombinedValue()) {
                return other;
            }
            return this;
        }
        public DestDestValueTuple findMax(List<DestDestValueTuple> others) {
            DestDestValueTuple max = this;
            for (DestDestValueTuple other : others) {
                max = max.compareTo(other);
            }
            return max;
        }
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
    public PathManager drawDestinationsFromNumber(Integer numberOfShortDests) throws WrongPlayerException {
        List<DestinationCard> destCardsDrawn = owner.drawDestFromNumber(numberOfShortDests);
        if (destCardsDrawn.isEmpty()) {
            System.out.println("No destination card drawn: round is " + gameView.getRound());
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
            else if (currentDestCost < minimalRoadCost) {
                minimalRoadCost = currentDestCost;
                bestDestinationCardPathmanager = currentDestPathManager;
            }
        }
        if (bestDestinationCardPathmanager == null) {
            //no destination cards pulled are possible: take the one of least value
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
        }
        for (DestinationCard destCard : totalDrawn) {
            owner.giveBackCard(destCard);
        }
    }
    public PathManager chooseOriginalDestCards() throws WrongPlayerException {
        List<DestinationCard> destCardDrawn = owner.drawDestFromNumber(4);
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