package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;

import java.util.List;

/**
 * The {@code WagonDrawManager} class is responsible for managing the drawing of wagon cards
 * it helps the bot determine the best strategy to draw wagon cards based on the current game state and available visible cards.
 * The manager provides methods to look for specific colors of wagon cards in the visible card and the deck
 * and to draw them accordingly.
 */

public class WagonDrawManager{
    private GameView gameView;
    private Bot owner;
    private GameEngine gameEngine;
    public WagonDrawManager(GameView gameView, Bot owner) {
        this.gameView = gameView;
        this.owner = owner;
        this.gameEngine = owner.getGameEngine();
    }

    /**
     * Looks for a single wagon card of the specified color in the visible wagon cards.
     * If found, it draws the card and adds it to the bot's collection.
     *
     * @param colorWanted the color of the wagon card to search for
     * @return {@code true} if a matching card was found and drawn; {@code false} otherwise
     * @throws WrongPlayerException if an invalid player performs the action
     */
    private boolean lookForSingleColorInVisible(Colors colorWanted) throws WrongPlayerException {
        for (int i = 0; i < gameView.getVisibleWagonCards().size(); i++) {
            WagonCard wagonCard = gameView.getVisibleWagonCards().get(i);
            if (wagonCard.getColor().equals(colorWanted)) {
                WagonCard wagonCardToDraw = gameEngine.drawVisibleWagonCard(i);
                gameEngine.addWagonCard(owner, wagonCardToDraw);
                owner.displayDrawWagonCardAction();
                return true;
            }
        }
        return false;
    }

    /**
     * Looks for two matching wagon cards of the specified color in the visible cards.
     * If two matching cards are not found, it attempts to draw from the deck.
     *
     * @param colorWanted the color of the wagon cards to search for
     * @return {@code true} if the cards are found and drawn; {@code false} otherwise
     * @throws WrongPlayerException if an invalid player performs the action
     */
    private boolean lookForTwoOfSame(Colors colorWanted) throws WrongPlayerException {
        if (gameView.getWagonDeck().getRemainingCards() < 8)
        {
            String hooooooolyShit = "yup";
        }




        //try to find the color once
        if (lookForSingleColorInVisible(colorWanted)) {
            //found one, try to find it again
            if (lookForSingleColorInVisible(colorWanted))
                return true;
                //did not find it, draw facedown
            else {
                WagonCard wagoncard = gameEngine.drawWagonCard();
                gameEngine.addWagonCard(owner, wagoncard);
                owner.displayDrawWagonCardAction();
                return true;
            }
        } else {
            //try to draw rainbow
            if (lookForSingleColorInVisible(Colors.RAINBOW))
                return true;
            else {
                //draw two hidden
                for (int nbdrawn = 0; nbdrawn < 2; nbdrawn++) {
                    WagonCard wagonCard = gameEngine.drawWagonCard();
                    gameEngine.addWagonCard(owner, wagonCard);
                    owner.displayDrawWagonCardAction();
                    wagonCard = gameEngine.drawWagonCard();
                    gameEngine.addWagonCard(owner, wagonCard);
                    owner.displayDrawWagonCardAction();
                }
                return true;
            }
        }
    }

    /**
     * Looks for two different colored wagon cards, one of the primary color and one of the secondary color.
     * It searches for the primary color first, then attempts to find a rainbow or secondary color.
     * If no visible cards match, it will draw cards from the deck.
     *
     * @param primary the primary color of the wagon card
     * @param secondary the secondary color of the wagon card
     * @return {@code true} if the cards are found and drawn; {@code false} otherwise
     * @throws WrongPlayerException if an invalid player performs the action
     */
    private boolean lookForTwoDifferentColors(Colors primary, Colors secondary) throws WrongPlayerException {
        //look for the primary first
        //if unfound look for rainbow
        //if still unfound look for secondary
        //if a secondary was found, maybe the new visible card is a primary, so try that again
        //it could also be another secondary, try that again
        //then draw the remainder in hidden
        Integer cardsToDraw = 2;
        boolean keepLooping = true;
        boolean foundPrimary = false;
        while (keepLooping && cardsToDraw > 0) {
            keepLooping = false;
            if (!foundPrimary && lookForSingleColorInVisible(primary)) {
                cardsToDraw--;
                keepLooping = true;
                foundPrimary = true;
            } else if (cardsToDraw == 2 && lookForSingleColorInVisible(Colors.RAINBOW)) {
                cardsToDraw = 0;
            } else if (lookForSingleColorInVisible(secondary)) {
                keepLooping = true;
                cardsToDraw--;
            }
        }
        for (int nbDrawn = 0; nbDrawn < cardsToDraw; nbDrawn++) {

            WagonCard wagoncard = gameEngine.drawWagonCard();
            gameEngine.addWagonCard(owner, wagoncard);
            owner.displayDrawWagonCardAction();
        }
        return true;
    }

    /**
     * Draws a set of wagon cards based on the provided list of colors.
     * The method can draw either two cards of the same color or two cards of different colors.
     *
     * @param listOfColors the list of colors representing the desired wagon cards to draw
     * @return {@code true} if the cards are successfully drawn; {@code false} if the input is invalid
     * @throws WrongPlayerException if an invalid player performs the action
     */
    public boolean drawWagonsOnList(List<Colors> listOfColors) throws WrongPlayerException {
        int nbColors = listOfColors.size();
        if (nbColors > 2 || nbColors == 0)
        {
            String wtf = "what the fuck";
            return false;
        }
        else if (nbColors == 1)
        {
            return lookForTwoOfSame(listOfColors.get(0));
        }
        else
        {
            return lookForTwoDifferentColors(listOfColors.get(0), listOfColors.get(1));
        }
    }
}