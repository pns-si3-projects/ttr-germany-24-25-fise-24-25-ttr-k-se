package fr.cotedazur.univ.polytech.teamK.bot;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.DeckFullException;
import fr.cotedazur.univ.polytech.teamK.board.cards.DestinationCard;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.nio.file.Path;
import java.util.*;

public class BotOverlap extends Bot {
    private PathValues currentPathAndDest = null;

    //stores two destination cards and their combined value
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
    /*stored information on a path to complete a destination card:
    has the primary color to purchase (need to fix)
    has the connection in the shortest path
    has the destination card itself
    has the cost of all the path combined, and the cost per color
     */
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



    //the move in which the bot draws destination cards: it always tries to draw 4 long destinations
    public boolean drawDestinationCard() throws WrongPlayerException {
        return drawDestinationCardWithNumber(0);
    }
    //the logic for choosing the first 2 dest cards:
    /*
    the logic is: we create DestDestValue tuples, so we can compare the values of them
     */
    private boolean drawBeginningDest() throws WrongPlayerException {
        List<DestinationCard> destCardDrawn = drawDestFromNumber(0);
        List<DestDestValue> chosenCommonCardsWithValue = new ArrayList<DestDestValue>();
        List<DestDestValue> chosenSeperateCardsWithValue = new ArrayList<DestDestValue>();
        //loop over all combos of destination cards (contains duplicates) to create the tuples
        for (DestinationCard destCardOne : destCardDrawn)
        {
            for (DestinationCard destCardTwo : destCardDrawn)
            {
                DestDestValue comboToAdd = new DestDestValue(destCardOne, destCardTwo, combinedValue(destCardOne, destCardTwo));
                //adds the tuple to either the list that indicates it has cities in common, or the other list
                //for two identical destination cards, the cityInCommon method treats that case and returns false
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
        //finds the maximum value tuple, either with cities in common if they exist or in the other list if no paired cities
        if (chosenCommonCardsWithValue.size() > 0)
        {
            maxValueDestCombo = findMaxWithinList(chosenCommonCardsWithValue);
        }
        else
        {
            maxValueDestCombo = findMaxWithinList(chosenSeperateCardsWithValue);
        }
        //loops over the destination cards, to remove the ones we dont want to choose, and keep the others
        for (DestinationCard destCard : destCardDrawn)
        {
            assert maxValueDestCombo != null;
            if (!maxValueDestCombo.contains(destCard))
            {
                giveBackCard(destCard);
            }
            else
            {
                gameEngine.addDestinationCard(this, destCard);
            }
        }
        this.currentPathAndDest = nextDestCardToDo();
        return true;
    }

    //destination card draw logic
    /*
    draws 4 long cards preferably
    chooses the card with the least remaining rails to buy
    if a second card is also quite cheap, choose that one (not implemented: the commented lines choose that card, but we still return it
    only ever choose maximum 2 cards
     */
    private boolean drawDestinationCardWithNumber(Integer number_of_short_dests) throws WrongPlayerException {
        List<DestinationCard> destCardDrawn = drawDestFromNumber(number_of_short_dests);
        if (destCardDrawn.isEmpty())
        {
            System.out.println("No destination card drawn");
            drawDestFromNumber(number_of_short_dests);

        }
        //iterate over destCardDrawn: choose the one with smallest cost
        PathValues bestPathValues = null;
        //PathValues secondBestPathValues = null;
        for (DestinationCard destCard : destCardDrawn)
        {
            PathValues pathValues = costOfPath(djikstra(destCard.getEndCity(), destCard.getStartCity()));
            pathValues.setDestCardOfpath(destCard);
            if ((bestPathValues == null) || (pathValues.getCost() < bestPathValues.getCost()))
            {
                bestPathValues = pathValues;
            }
           //else if ((pathValues.getCost() < 5) && ((secondBestPathValues == null) || (pathValues.getCost() < secondBestPathValues.getCost())))
           //{
           //    secondBestPathValues = pathValues;
           //}
        }
        //test, in case no dest cards were found:
        if (bestPathValues == null) {
            //System.out.println("No path found, as if no destination cards were drawn");
            return false;
        }

        //returns all the other destination cards
        for (DestinationCard destCard : destCardDrawn)
        {
            if (!destCard.equals(bestPathValues.getDestCardOfpath()))
            {
                giveBackCard(destCard);
            }
            else
            {
                gameEngine.addDestinationCard(this, destCard);
            }
        }
        return true;
    }

    /*
    function to determine if two destination cards have a city in common
     */
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

    /*
    function to sum the point value of two destination cards
     */
    private Integer combinedValue (DestinationCard dest1, DestinationCard dest2)
    {
        return dest1.getValue() + dest2.getValue();
    }

    /*
    function to find the maximal tuple (based on its value element) in a list of destdestvalues
     */
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

    /*
    determines the total cost of a path to complete a destination card:
    it returns a PathValues tuple, which also stores the colors needed, how many of them, etc
     */
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

    /*
    calls djikstra on a destination card, and returns the pathvalues tuple associated to it
     */
    private PathValues djikstraPathValues(DestinationCard destCard)
    {
        List<Connection> djikstraPath = djikstra(destCard.getEndCity(), destCard.getStartCity());
        PathValues bestPathValues = costOfPath(djikstraPath);
        bestPathValues.setDestCardOfpath(destCard);
        bestPathValues.setConnectionsForCurrentDestCard(djikstraPath);
        return bestPathValues;
    }

    /*
    loops over all the dest cards someone has: finds the next noncomplete one to do
    does the one that is cheapest to do next
     */
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

    /*
    looks at all connections in a path
    returns a list of maximum size 2 of colors
    if the size is two, we need 1 card of the first color, and an unknown amount of the second color
    if the size is 1, we need at least 2 of the first color
     */
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

    /*
    this is because of bad abstract class making
     */
    public boolean drawWagonCard(Colors colorWanted)
    {
        return false;
    }

    /*
    looks over the visible cards, for a single color we want, and draws a single version of it
     */
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
    /*
    looks for a rainbow
     */
    private boolean drawVisibleRainbow()
    {
        return drawVisibleSingleColorSingleCard(Colors.RAINBOW);
    }
    /*
    looks over visible for two versions of the same color
     */
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

    /*
    draws from face down wagon deck
     */
    private boolean drawFromWagonDeck(Integer numberToDraw)
    {
        for (int cardsDrawn = 0; cardsDrawn < numberToDraw; cardsDrawn++)
        {
            WagonCard drawnCard = gameView.getWagonDeck().draw();
            gameView.getPlayerByBot(this).addCardWagon(drawnCard);
        }
        return true;
    }

    /*
    method to draw two of a focused color: we try to draw 2 of the proper color
    if we find none, we try for a rainbow
    if there arent any, we draw two from facedown
     */
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

    /*
    we want to try and find two colors
    we look for the primary color: if we find it cool
    if not we look for rainbow

    if no rainbow, we look for secondary color
    finding one might unveil a new primary color card, so we look for that again
    if none, we look for another secondary color
    if none, we draw the remaining cards needed from the deck

    could probably be rewritten with a while
     */
    private boolean drawWagonDualColorFocus(Colors firstColor, Colors secondColor)
    {
        int cardsToDraw = 2;
        boolean foundPrimary = drawVisibleSingleColorSingleCard(firstColor);
        if (foundPrimary)
        {
            cardsToDraw -= 1;
        }
        boolean foundRainbow = false;
        if (cardsToDraw == 2)
        {
            foundRainbow = drawVisibleRainbow();
        }
        if (foundRainbow)
        {
            cardsToDraw = 0;
        }
        if (cardsToDraw > 0)
        {
            boolean foundSecondary = drawVisibleSingleColorSingleCard(secondColor);
            if (foundSecondary)
            {
                cardsToDraw -= 1;
            }
            boolean retryPrimary = false;
            if (cardsToDraw > 0)
            {
                retryPrimary = drawVisibleSingleColorSingleCard(firstColor);
            }
            if (retryPrimary)
            {
                cardsToDraw -= 1;
            }
            boolean foundTwoSecondary = false;
            if (cardsToDraw > 0)
            {
                foundTwoSecondary = drawVisibleSingleColorSingleCard(secondColor);
            }
            if (foundTwoSecondary)
            {
                cardsToDraw -= 1;
            }
            drawFromWagonDeck(cardsToDraw);
        }
        return true;
    }

    /*
    combination method: draws the cards required for the path
    returns true if the draw was successful (ie we drew cards)
     */
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

    /*
    the method used to draw cards: combines the logic of the other stuff
    if we have no path we are looking for, or no wagondeck cards left to draw, we dont draw
    otherwise we draw the cards wanted for the current deck
     */
    public boolean drawWagonCard() throws WrongPlayerException {
        if ((this.currentPathAndDest == null) || (gameView.getWagonDeck().isEmpty()))
        {
            return false;
        }
        DestinationCard currentDest = this.currentPathAndDest.destCardOfpath;
        if (gameView.getPlayerByBot(this).validDestinationCard(currentDest))
        {
            return false;
        }
        drawWagonCardsForPath(this.currentPathAndDest);
        return true;
    }

    /*
    verifies that a path is unchanged (avoids recalculating djikstra every time someone else plays
     */
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

    /*
    looks at all the connections in a path, and finds the connection we want to buy
    ie the one we are the closest to successfully purchasing
     */
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

    /*
    the combination method that buys a rail
     */
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

    /*
    bad abstract class creation
     */
    public boolean buyConnection(ArrayList<Connection> connections) throws WrongPlayerException {
        return false;
    }

    /*
    sorts the meeples by color, so we know which ones we have the most of
    then, chooses that one if possible, descending until either no meeples or we found one
     */
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

    /*
    combination method: finds the meeples we want, and then picks the best color possible
     */
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

    /*
    combination method: tries to purchase a connection wanted: if impossible draws a wagon for the connection we want
    if there are no more colors we want, that means we have no more unfinished destinations, so we draw a new destination card
     */
    public boolean playTurn() throws WrongPlayerException {
        //this should work, otherwise code in comments might be clearer
        if (gameView.getRound() == 0)
        {
            drawBeginningDest();
        }
        boolean value =  buyRail() || drawWagonCard() || drawDestinationCard();
        String test = "test";
        return value;
    }
}
