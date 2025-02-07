package fr.cotedazur.univ.polytech.teamK.bot.overlap;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;
import fr.cotedazur.univ.polytech.teamK.game.GameEngine;
import fr.cotedazur.univ.polytech.teamK.game.GameView;
import fr.cotedazur.univ.polytech.teamK.game.WrongPlayerException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;
import java.util.ArrayList;
import java.util.List;

public class MeepleSelectorManager {
    private Bot owner;
    private GameView gameView;
    private GameEngine gameEngine;
    public MeepleSelectorManager(Bot owner, GameView gameView) {
        this.owner = owner;
        this.gameView = gameView;
        this.gameEngine = owner.getGameEngine();
    }

    /*
    sorts the meeples by color, so we know which ones we have the most of
    then, chooses that one if possible, descending until either no meeples or we found one
     */
    private List<Colors> sortedMeepleColors()
    {
        List<Colors> sortedMeeplesList = new ArrayList<Colors>();
        Player myPlayer = gameView.getPlayerByBot(owner);
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
            if (this.gameEngine.takeMeeples(owner, cityOne, newColor))
            {
                break;
            }
        }
        for (Colors newColor : sortedMeeplesList)
        {
            if (this.gameEngine.takeMeeples(owner, cityTwo, newColor))
            {
                break;
            }
        }

    }

}




