package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.board.player.PlayerSeenException;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the selection and retrieval of meeples for a bot player.
 * It prioritizes selecting meeples based on their availability and attempts
 * to pick the most abundant ones first.
 */
public class MeepleSelectorManager {
    private Bot owner;
    private GameView gameView;
    private GameEngine gameEngine;
    public MeepleSelectorManager(Bot owner, GameView gameView) {
        this.owner = owner;
        this.gameView = gameView;
        this.gameEngine = owner.getGameEngine();
    }



    /**
     * sorts the meeples by color, so we know which ones we have the most of
     * then, chooses that one if possible, descending until either no meeples or we found one
     */
    private List<Colors> sortedMeepleColors()
    {
        List<Colors> sortedMeepleColorsBis = new ArrayList<>();
        Meeple myMeeple = gameView.getMyMeeples();
        for (Colors currentColor : Colors.values())
        {
            if (sortedMeepleColorsBis.isEmpty())
            {
                sortedMeepleColorsBis.add(currentColor);
            }
            else
            {
                Colors comparedColor = sortedMeepleColorsBis.getFirst();
                int indxToAdd = 0;
                while (indxToAdd < sortedMeepleColorsBis.size() && myMeeple.getNumberOfAColor(comparedColor) > myMeeple.getNumberOfAColor(currentColor))
                {

                    comparedColor = sortedMeepleColorsBis.get(indxToAdd);
                    indxToAdd++;
                }
                sortedMeepleColorsBis.add(indxToAdd, currentColor);
            }
        }
        return sortedMeepleColorsBis;

        /*
        List<Colors> sortedMeeplesList = new ArrayList<Colors>();
        Player myPlayer = gameView.getPlayerByBot(owner);
        for (Colors newColor : Colors.values())
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
        */
    }

    /**
     * Picks the best available meeples from the given connection.
     * It selects the most abundant meeples first to ensure efficient usage.
     *
     * @param connection The connection from which meeples are to be picked.
     * @throws WrongPlayerException If an action is performed by the wrong player.
     */
    public void pickMeeplesFromConnection(Connection connection) throws WrongPlayerException
    {
        City cityOne = connection.getCityOne();
        City cityTwo = connection.getCityTwo();
        List<Colors> sortedMeeplesList = sortedMeepleColors();

        for (Colors newColor : sortedMeeplesList)
        {
            try
            {
                if (this.gameEngine.takeMeeples(owner, cityOne, newColor))
                {
                    break;
                }
            }
            catch (PlayerSeenException ignored){}

        }
        for (Colors newColor : sortedMeeplesList)
        {
            try
            {
                if (this.gameEngine.takeMeeples(owner, cityTwo, newColor))
                {
                    break;
                }
            }
            catch (PlayerSeenException ignored){}
        }

    }

}