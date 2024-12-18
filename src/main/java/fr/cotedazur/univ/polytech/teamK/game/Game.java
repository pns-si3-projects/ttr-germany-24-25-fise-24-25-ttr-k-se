package fr.cotedazur.univ.polytech.teamK.game;
import fr.cotedazur.univ.polytech.teamK.board.*;
import fr.cotedazur.univ.polytech.teamK.board.Cards.Deck;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Connections;
import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    /*
    STEP ONE: BUILD THE BOARD
     */
    //matrice d'adjacence:
    private MapSimple gameMap = new MapSimple("Reich");
    /*
    STEP TWO: INITIALISE PLAYERS
     */
    private Players gamePlayers = new Players(2);

    /*
    STEP THREE: CARDS + PLAYABLE OBJECTS
    initialise the decks of cards (wagon + destination)
    initialise Meeples when done
     */

    /*
    STEP 4: ROUNDS
    run rounds until the end condition is tested or a certain number of rounds
    on each round: each 'player' acts on their turn
     */

    public void runGame()
    {
        
    }
}
