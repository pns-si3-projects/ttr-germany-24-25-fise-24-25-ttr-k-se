package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.cards.WagonCard;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import fr.cotedazur.univ.polytech.teamK.game.GameView;

import java.util.List;


public class WagonDrawManager{
    private GameView gameView;
    private Bot owner;
    public WagonDrawManager(GameView gameView, Bot owner) {
        this.gameView = gameView;
        this.owner = owner;
    }

    private boolean lookForSingleColorInVisible(Colors colorWanted) {
        for (int i = 0; i < gameView.getVisibleWagonCards().size(); i++) {
            WagonCard wagonCard = gameView.getVisibleWagonCards().get(i);
            if (wagonCard.getColor().equals(colorWanted)) {
                gameView.getWagonDeck().drawVisibleCard(i);
                gameView.getPlayerByBot(owner).addCardWagon(wagonCard);
                return true;
            }
        }
        return false;
    }

    private boolean lookForTwoOfSame(Colors colorWanted) {
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
                WagonCard wagoncard = gameView.getWagonDeck().draw();
                gameView.getPlayerByBot(owner).addCardWagon(wagoncard);
                return true;
            }
        } else {
            //try to draw rainbow
            if (lookForSingleColorInVisible(Colors.RAINBOW))
                return true;
            else {
                //draw two hidden
                for (int nbdrawn = 0; nbdrawn < 2; nbdrawn++) {
                    WagonCard wagoncard = gameView.getWagonDeck().draw();
                    gameView.getPlayerByBot(owner).addCardWagon(wagoncard);
                }
                return true;
            }
        }
    }

    private boolean lookForTwoDifferentColors(Colors primary, Colors secondary) {
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
            WagonCard wagoncard = gameView.getWagonDeck().draw();
            gameView.getPlayerByBot(owner).addCardWagon(wagoncard);
        }
        return true;
    }

    public boolean drawWagonsOnList(List<Colors> listOfColors)
    {
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