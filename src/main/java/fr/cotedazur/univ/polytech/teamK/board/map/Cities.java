package fr.cotedazur.univ.polytech.teamK.board.map;

public enum Cities {
    DANEMARK(0, new Meeples(1)),
    KIEL(1, new Meeples(1)),
    ROSTOCK(2, new Meeples(1)),
    EMDEN(3, new Meeples(1)),
    BREMERHAVEN(4, new Meeples(1)),
    HAMBURG(5, new Meeples(4)),
    SCHWERIN(6, new Meeples(1)),
    MUNSTER(7, new Meeples(1)),
    HANNOVER(8, new Meeples(1)),
    BERLIN(9, new Meeples(5)),
    DUSSELDORF(10, new Meeples(1)),
    DORTMUND(11, new Meeples(1)),
    KASSEL(12, new Meeples(1)),
    MAGDEBURG(13, new Meeples(1)),
    LEIPZIG(14, new Meeples(3)),
    ERFURT(15, new Meeples(1)),
    DRESDEN(16, new Meeples(1)),
    CHEMNITZ(17, new Meeples(1)),
    KOLN(18, new Meeples(4)),
    KOBLENZ(19, new Meeples(1)),
    FRANKFURT(20, new Meeples(4)),
    MAINZ(21, new Meeples(1)),
    WURZBURG(22, new Meeples(1)),
    NURNBERG(23, new Meeples(1)),
    REGENSBURG(24, new Meeples(1)),
    MUNCHEN(25, new Meeples(4)),
    AUGSBURG(26, new Meeples(1)),
    ULM(27, new Meeples(1)),
    STUTTGART(28, new Meeples(3)),
    KARLSRUHE(29, new Meeples(1)),
    MANNHEIM(30, new Meeples(1)),
    SAARBRUCKEN(31, new Meeples(1)),
    FREIBURG(32, new Meeples(1)),
    KONSTANZ(33, new Meeples(1)),
    LINDAU(34, new Meeples(1)),
    BREMEN(35, new Meeples(1)),
    FRANKREICH(36, new Meeples(1)),
    SCHWEIZ(37, new Meeples(1)),
    NIEDERLANDE(38, new Meeples(1)),
    OSTERREICH(39, new Meeples(1));

    final int id;

    final Meeples meeples;

    Cities(int id, Meeples meeples) {
        this.id = id;
        this.meeples = meeples;
    }

    public int getId() {
        return id;
    }

    public Meeples getMeeples() {
        return meeples;
    }
}
