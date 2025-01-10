package fr.cotedazur.univ.polytech.teamK.board.map;

import fr.cotedazur.univ.polytech.teamK.board.player.Player;

import java.util.ArrayList;
import java.util.List;

public enum Cities {
    DANEMARK(0, new Meeple(1)),
    KIEL(1, new Meeple(1)),
    ROSTOCK(2, new Meeple(1)),
    EMDEN(3, new Meeple(1)),
    BREMERHAVEN(4, new Meeple(1)),
    HAMBURG(5, new Meeple(4)),
    SCHWERIN(6, new Meeple(1)),
    MUNSTER(7, new Meeple(1)),
    HANNOVER(8, new Meeple(1)),
    BERLIN(9, new Meeple(5)),
    DUSSELDORF(10, new Meeple(1)),
    DORTMUND(11, new Meeple(1)),
    KASSEL(12, new Meeple(1)),
    MAGDEBURG(13, new Meeple(1)),
    LEIPZIG(14, new Meeple(3)),
    ERFURT(15, new Meeple(1)),
    DRESDEN(16, new Meeple(1)),
    CHEMNITZ(17, new Meeple(1)),
    KOLN(18, new Meeple(4)),
    KOBLENZ(19, new Meeple(1)),
    FRANKFURT(20, new Meeple(4)),
    MAINZ(21, new Meeple(1)),
    WURZBURG(22, new Meeple(1)),
    NURNBERG(23, new Meeple(1)),
    REGENSBURG(24, new Meeple(1)),
    MUNCHEN(25, new Meeple(4)),
    AUGSBURG(26, new Meeple(1)),
    ULM(27, new Meeple(1)),
    STUTTGART(28, new Meeple(3)),
    KARLSRUHE(29, new Meeple(1)),
    MANNHEIM(30, new Meeple(1)),
    SAARBRUCKEN(31, new Meeple(1)),
    FREIBURG(32, new Meeple(1)),
    KONSTANZ(33, new Meeple(1)),
    LINDAU(34, new Meeple(1)),
    BREMEN(35, new Meeple(1)),
    FRANKREICH(36, new Meeple(1)),
    SCHWEIZ(37, new Meeple(1)),
    NIEDERLANDE(38, new Meeple(1)),
    OSTERREICH(39, new Meeple(1));

    final int id;

    final Meeple meeples;
    private List<Player> playersThatPickedUpMeeples;

    Cities(int id, Meeple meeples) {
        this.id = id;
        this.meeples = meeples;
        this.playersThatPickedUpMeeples = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Meeple getMeeples() {
        return meeples;
    }

    public List<Player> getPlayersThatPickedUpMeeples () {return playersThatPickedUpMeeples;}

    public void addPlayer(Player player) {
        playersThatPickedUpMeeples.add(player);
    }
}
