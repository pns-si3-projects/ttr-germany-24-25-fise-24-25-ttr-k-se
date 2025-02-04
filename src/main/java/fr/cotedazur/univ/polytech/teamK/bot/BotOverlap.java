package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
//import fr.cotedazur.univ.polytech.teamK.board.cards.PaquetVideException;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.nio.file.Path;
import java.util.*;

public class BotOverlap extends Bot {
    private PathValues currentPathAndDest = null;

    private class DestDestValue {
        private DestinationCard destCard1;
        private DestinationCard destCard2;
        private Integer value;
        public DestDestValue(DestinationCard destCard1, DestinationCard destCard2, Integer value) {
            this.destCard1 = destCard1;
            this.destCard2 = destCard2;
            this.value = value;
        }
        public DestinationCard getDestCard1() {
            return destCard1;
        }
        public DestinationCard getDestCard2() {
            return destCard2;
        }
        public Integer getValue() {
            return value;
        }

        public boolean contains(DestinationCard destCard) {
            return destCard1.equals(destCard) || destCard2.equals(destCard);
        }
    }
    private class PathValues {
        private Colors primaryColor;
        private Integer cost;
        private HashMap<Colors, Integer> colorsRequired;
        private DestinationCard destCardOfpath;
        private List<Connection> connectionsForCurrentDestCard;
        public PathValues(Colors primaryColor, Integer cost, HashMap<Colors, Integer> colorsRequired)
        {
            this.primaryColor = primaryColor;
            this.cost = cost;
            this.colorsRequired = colorsRequired;
        }
        public Colors getPrimaryColor() {
            return primaryColor;
        }
        public Integer getCost() {
            return cost;
        }
        public HashMap<Colors, Integer> getColorsRequired() {
            return colorsRequired;
        }
        public DestinationCard getDestCardOfpath() {
            return destCardOfpath;
        }
        public List<Connection> getConnectionsForCurrentDestCard() {
            return connectionsForCurrentDestCard;
        }

        public void setDestCardOfpath(DestinationCard destCardOfpath) {
            this.destCardOfpath = destCardOfpath;
        }
        public void setConnectionsForCurrentDestCard(List<Connection> connectionsForCurrentDestCard) {
            this.connectionsForCurrentDestCard = connectionsForCurrentDestCard;
        }
    }

    public BotOverlap(String name, GameEngine gameEngine) {
        super(name, gameEngine);
    }




    public boolean drawDestinationCard()
    {
        return drawDestinationCardWithNumber(0);
    }

    private boolean drawDestinationCardWithNumber(Integer number_of_short_dests) {
        List<DestinationCard> destCardDrawn = drawDestFromNumber(number_of_short_dests);
        if (gameView.getRound() == 1)
        {
            List<DestDestValue> chosenCommonCardsWithValue = new ArrayList<DestDestValue>();
            List<DestDestValue> chosenSeperateCardsWithValue = new ArrayList<DestDestValue>();
            for (DestinationCard destCardOne : destCardDrawn)
            {
                for (DestinationCard destCardTwo : destCardDrawn)
                {
                    DestDestValue comboToAdd = new DestDestValue(destCardOne, destCardTwo, combinedValue(destCardOne, destCardTwo));
                    if (cityInCommon(destCardOne, destCardTwo))
                    {
                        chosenCommonCardsWithValue.add(comboToAdd);
                    }
                    else
                    if (!destCardOne.equals(destCardTwo))
                    {
                        chosenSeperateCardsWithValue.add(comboToAdd);
                    }
                }
            }
            DestDestValue maxValueDestCombo;
            if (chosenCommonCardsWithValue.size() > 0)
            {
                maxValueDestCombo = findMaxWithinList(chosenCommonCardsWithValue);
            }
            else
            {
                maxValueDestCombo = findMaxWithinList(chosenSeperateCardsWithValue);
            }

            for (DestinationCard destCard : destCardDrawn)
            {
                if (!maxValueDestCombo.contains(destCard))
                {
                    giveBackCard(destCard);
                }
            }
            return true;
        }
        //----------------------------------------------------------------------------------------------------------
        else
        {
            //iterate over destCardDrawn: choose the one with smallest cost
            PathValues bestPathValues = null;
            PathValues secondBestPathValues = null;
            for (DestinationCard destCard : destCardDrawn)
            {
                PathValues pathValues = costOfPath(djikstra(destCard.getEndCity(), destCard.getStartCity()));
                pathValues.setDestCardOfpath(destCard);
                if ((bestPathValues == null) || (pathValues.getCost() < bestPathValues.getCost()))
                {
                    bestPathValues = pathValues;

                }
                else if ((pathValues.getCost() < 5) && ((secondBestPathValues == null) || (pathValues.getCost() < secondBestPathValues.getCost())))
                {
                    secondBestPathValues = pathValues;
                }
            }
            if (bestPathValues == null) {
                System.out.println("No path found, as if no destination cards were drawn");
                return false;
            }

            for (DestinationCard destCard : destCardDrawn)
            {
                if (!destCard.equals(bestPathValues.getDestCardOfpath()))
                {
                    giveBackCard(destCard);
                }
            }
            return true;



        }
    }

    private Boolean cityInCommon (DestinationCard dest1, DestinationCard dest2)
    {
        City dest1StartCity = dest1.getStartCity();
        City dest2StartCity = dest2.getStartCity();
        City dest1EndCity = dest1.getEndCity();
        City dest2EndCity = dest2.getEndCity();
        if (!dest1.equals(dest2) && (dest1EndCity.equals(dest2EndCity) || dest1EndCity.equals(dest2StartCity) || dest1StartCity.equals(dest2StartCity) || dest1StartCity.equals(dest2EndCity)))
        {
            return true;
        }
        return false;
    }

    private Integer combinedValue (DestinationCard dest1, DestinationCard dest2)
    {
        return dest1.getValue() + dest2.getValue();
    }

    private DestDestValue findMaxWithinList(List<DestDestValue> tupleList)
    {
        if (tupleList.size() == 0)
        {
            return null;
        }
        DestDestValue maxValueTuple = tupleList.get(0);
        for (DestDestValue tuple : tupleList)
        {
            if(tuple.getValue() > maxValueTuple.getValue())
            {
                maxValueTuple = tuple;
            }
        }
        return maxValueTuple;
    }

    private PathValues costOfPath(List<Connection> connectionsInPath)
    {
        HashMap<Colors, Integer> costOfPath = new HashMap<Colors, Integer>();
        for (Colors color : Colors.values())
        {
            costOfPath.put(color, 0);
        }
        for (Connection connection : connectionsInPath)
        {
            Integer previousColorCost = costOfPath.get(connection.getColor());
            costOfPath.put(connection.getColor(), previousColorCost + connection.getLength());
            //costOfPath.compute(connection.getColor(), (k, previousColorCost) -> previousColorCost + connection.getLength());
        }

        Integer cost = 0;
        Colors maximalColor = null;
        for (Colors color : Colors.values())
        {
            if ((maximalColor == null) || (costOfPath.get(color) > costOfPath.get(maximalColor)))
            {
                maximalColor = color;
            }
            cost += costOfPath.get(color);
        }
        if (maximalColor == null)
        {
            throw new NoSuchElementException("no colors in the colors enum");
        }
        return new PathValues(maximalColor, cost, costOfPath);
    }
    //---------------------------------------------------------------------------------------------------------------


    private PathValues djikstraPathValues(DestinationCard destCard)
    {
        List<Connection> djikstraPath = djikstra(destCard.getEndCity(), destCard.getStartCity());
        PathValues bestPathValues = costOfPath(djikstraPath);
        bestPathValues.setDestCardOfpath(destCard);
        bestPathValues.setConnectionsForCurrentDestCard(djikstraPath);
        return bestPathValues;
    }

    private PathValues nextDestCardToDo ()
    {
        List<DestinationCard> allDestCardOwned = gameView.getMyDestinationCards();
        PathValues cheapestPath = null;
        for (DestinationCard destCard : allDestCardOwned)
        {
            if (!gameView.getPlayerByBot(this).validDestinationCard(destCard))
            {
                PathValues currentPath = djikstraPathValues(destCard);
                if ((cheapestPath == null) || (currentPath.getCost() < cheapestPath.getCost()))
                {
                    cheapestPath = currentPath;
                }
            }
        }
        return cheapestPath;
    }

    private List<Colors> colorToDrawForPath(PathValues path) throws WrongPlayerException {
        List<Colors> colorsWanted = new ArrayList<Colors>();
        for (Map.Entry <Colors, Integer> entry: path.getColorsRequired().entrySet())
        {
            Colors currentColor = entry.getKey();
            if (colorsWanted.size() < 2)
            {
                colorsWanted.add(currentColor);
            }
            else
            {
                Colors primaryColor = colorsWanted.get(0);
                Colors secondaryColor = colorsWanted.get(1);
                Integer minColorNeeded = path.getColorsRequired().get(primaryColor) - gameEngine.getNumberColor(this, primaryColor);
                Integer secMinColorNeeded = path.getColorsRequired().get(secondaryColor) - gameEngine.getNumberColor(this, secondaryColor);

                Integer currentColorNeeded = entry.getValue() - gameEngine.getNumberColor(this, currentColor);
                if (minColorNeeded > currentColorNeeded)
                {
                    colorsWanted.set(0, currentColor);
                }
                else if (secMinColorNeeded > currentColorNeeded)
                {
                    colorsWanted.set(1, currentColor);
                }
            }
        }

        Colors primaryColorWanted = colorsWanted.get(0);
        Integer amountNeeded = path.getColorsRequired().get(primaryColorWanted) - gameEngine.getNumberColor(this, primaryColorWanted);
        if ((amountNeeded > 1) && (colorsWanted.size() == 2))
        {
            colorsWanted.remove(1);
        }
        return colorsWanted;
    }

    public boolean drawWagonCard(Colors colorWanted)
    {
        return false;
    }

    private boolean drawVisibleSingleColorSingleCard(Colors colorWanted)
    {
        for (int visibleWagoncardIndex = 0; visibleWagoncardIndex < gameView.getWagonDeck().getVisibleCard().size(); visibleWagoncardIndex++)
        {
            Colors currentVisibleCardColor = gameView.getWagonDeck().getVisibleCard().get(visibleWagoncardIndex).getColor();
            if (colorWanted.equals(currentVisibleCardColor))
            {
                WagonCard drawnCard = gameView.getWagonDeck().drawVisibleCard(visibleWagoncardIndex);
                gameView.getPlayerByBot(this).addCardWagon(drawnCard);
                return true;
            }
        }
        return false;
    }
    private boolean drawVisibleRainbow()
    {
        return drawVisibleSingleColorSingleCard(Colors.RAINBOW);
    }
    private Integer drawTwoFromVisibleWithColor(Colors colorWanted)
    {
        boolean foundColorOnce = drawVisibleSingleColorSingleCard(colorWanted);
        boolean foundColorTwice = drawVisibleSingleColorSingleCard(colorWanted);
        if (foundColorTwice)
        {
            return 2;
        }
        else if (foundColorOnce)
        {
            return 1;
        }
        return 0;

    }
    private boolean drawFromWagonDeck(Integer numberToDraw)
    {
        for (int cardsDrawn = 0; cardsDrawn < numberToDraw; cardsDrawn++)
        {
            WagonCard drawnCard = gameView.getWagonDeck().draw();
            gameView.getPlayerByBot(this).addCardWagon(drawnCard);
        }
        return true;
    }
    private boolean drawWagonSingleColorFocus(Colors colorWanted)
    {
        Integer correctFound = drawTwoFromVisibleWithColor(colorWanted);
        boolean foundRainbow = false;
        if (correctFound == 0)
        {
            foundRainbow = drawVisibleRainbow();
        }
        if (!foundRainbow)
        {
            drawFromWagonDeck(2-correctFound);
        }
        return true;
    }
    private boolean drawWagonDualColorFocus(Colors firstColor, Colors secondColor)
    {
        boolean foundPrimary = drawVisibleSingleColorSingleCard(firstColor);
        boolean foundRainbow = false;
        if (!foundPrimary)
        {
            foundRainbow = drawVisibleRainbow();
        }

        if (!foundRainbow)
        {
            boolean foundSecondary = drawVisibleSingleColorSingleCard(secondColor);
            boolean retryPrimary = drawVisibleSingleColorSingleCard(firstColor);
            boolean foundTwoSecondary = false;
            if (!retryPrimary)
            {
                foundTwoSecondary = drawVisibleSingleColorSingleCard(secondColor);
            }

            int cardsToDraw;
            if (!foundSecondary)
            {
                cardsToDraw = 2;
            }
            if ((foundSecondary) && !(foundPrimary || foundTwoSecondary))
            {
                cardsToDraw = 1;
            }
            else
            {
                cardsToDraw = 0;
            }
            boolean drewFromHidden = drawFromWagonDeck(cardsToDraw);
        }
        return true;
    }
    private boolean drawWagonCardsForPath(PathValues currentpath) throws WrongPlayerException {
        List<Colors> colorsWanted = colorToDrawForPath(currentpath);
        if (colorsWanted.size() < 1 || colorsWanted.size() > 2)
        {
            //no colors in path, weird, or too many
            return false;
        }
        else if (colorsWanted.size() == 1)
        {
            drawWagonSingleColorFocus(colorsWanted.get(0));
        }
        else if (colorsWanted.size() == 2)
        {
            drawWagonDualColorFocus(colorsWanted.get(0), colorsWanted.get(1));
        }
        return true;
    }

    public boolean drawWagonCard() throws WrongPlayerException {
        if ((this.currentPathAndDest == null) || (gameView.getWagonDeck().isEmpty()))
        {
            return false;
        }
        DestinationCard currentDest = this.currentPathAndDest.destCardOfpath;
        if (!gameView.getPlayerByBot(this).validDestinationCard(currentDest))
        {
            return false;
        }
        //PathValues nextPath = nextCardToDo();
        drawWagonCardsForPath(this.currentPathAndDest);
        return true;
    }

    private Integer verifyUnchangedPath()
    {
        if (this.currentPathAndDest == null)
        {
            return -1;
        }
        List<Connection> currentConnectionsInPath = this.currentPathAndDest.getConnectionsForCurrentDestCard();
        if (currentConnectionsInPath == null)
        {
            return -1; //no current path, error
        }
        for (Connection connection : currentConnectionsInPath)
        {
            if (!(connection.getOwner() == null || connection.getOwner().equals(gameView.getPlayerByBot(this))))
            {
                return 1; //a path has been bought by someone else
            }
        }
        return 0;
    }

    private Connection findConnectionToBuy(PathValues currentpath) throws WrongPlayerException {
        List<Connection> connectionsInPath = currentpath.getConnectionsForCurrentDestCard();
        for (Connection connection : connectionsInPath)
        {
            Colors connectionColor = connection.getColor();
            Integer cost = connection.getLength();
            Integer myCardsOfColor = gameEngine.getNumberColor(this, connectionColor);
            if ((connection.getOwner() == null) && (myCardsOfColor >= cost))
            {
                return connection;
            }
        }
        return null;
    }
    public boolean buyRail() throws WrongPlayerException {
        Integer pathChanged = verifyUnchangedPath();
        PathValues valuesForPath = this.currentPathAndDest;
        if ((valuesForPath == null) || (pathChanged == -1))
        {
            return false;
            //path nonexistant: draw new destination cards
        }
        DestinationCard currentDestCard = valuesForPath.destCardOfpath;
        if (pathChanged == 1)
        {
            //recalc djikstra
            valuesForPath = djikstraPathValues(currentDestCard);
        }

        Connection connectionToBuy = findConnectionToBuy(valuesForPath);
        boolean pathPurchased = false;
        if (connectionToBuy != null)
        {
            pathPurchased =  gameEngine.buyRail(this, connectionToBuy);
            pickMeeplesFromConnection(connectionToBuy);
            if (gameView.getPlayerByBot(this).validDestinationCard(currentDestCard))
            {
                this.currentPathAndDest = nextDestCardToDo();
            }
        }
        return pathPurchased;
    }

    public boolean buyConnection(ArrayList<Connection> connections) throws WrongPlayerException {
        return false;
    }

    private List<Colors> sortedMeepleColors()
    {
        List<Colors> sortedMeeplesList = new ArrayList<Colors>();
        Player myPlayer = gameView.getPlayerByBot(this);
        for (Colors newColor : sortedMeeplesList)
        {
            int amountOfNew = myPlayer.getMeeples().getNumberOfAColor(newColor);
            for (int addedColorIndex = 0; addedColorIndex < sortedMeeplesList.size(); addedColorIndex++)
            {
                int oldAmount = myPlayer.getMeeples().getNumberOfAColor(sortedMeeplesList.get(addedColorIndex));
                if (amountOfNew > oldAmount)
                {
                    sortedMeeplesList.add(addedColorIndex, newColor);
                    break;
                }
            }
        }
        return sortedMeeplesList;
    }

    private void pickMeeplesFromConnection(Connection connection) throws WrongPlayerException
    {
        City cityOne = connection.getCityOne();
        City cityTwo = connection.getCityTwo();
        List<Colors> sortedMeeplesList = sortedMeepleColors();

        for (Colors newColor : sortedMeeplesList)
        {
            if (this.gameView.getPlayerByBot(this).takeMeeples(cityOne, newColor))
            {
                break;
            }
        }
        for (Colors newColor : sortedMeeplesList)
        {
            if (this.gameView.getPlayerByBot(this).takeMeeples(cityTwo, newColor))
            {
                break;
            }
        }

    }




    public boolean playTurn() throws WrongPlayerException {
        //this should work, otherwise code in comments might be clearer
        if (buyRail() || drawWagonCard() || drawDestinationCard())
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
