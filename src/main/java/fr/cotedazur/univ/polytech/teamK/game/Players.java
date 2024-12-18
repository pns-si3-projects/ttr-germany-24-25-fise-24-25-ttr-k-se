package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.ArrayList;

public class Players {

    private ArrayList<Player> players;
    public Players(Integer numberOfPlayers) {
        players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = "Player " + (i + 1);
            players.add(new Player(name));

        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
