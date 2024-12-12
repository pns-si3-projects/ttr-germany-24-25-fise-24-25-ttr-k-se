package fr.cotedazur.univ.polytech.teamK.board.map;

public enum Cities {
    DANEMARK(0, new Neeples(1)),
    KIEL(1, new Neeples(1)),
    ROSTOCK(2, new Neeples(1)),
    EMDEN(3, new Neeples(1)),
    BREMERHAVEN(4, new Neeples(1)),
    HAMBURG(5, new Neeples(4)),
    SCHWERIN(6, new Neeples(1)),
    MUNSTER(7, new Neeples(1)),
    HANNOVER(8, new Neeples(1)),
    BERLIN(9, new Neeples(5)),
    DUSSELDORF(10, new Neeples(1)),
    DORTMUND(11, new Neeples(1)),
    KASSEL(12, new Neeples(1)),
    MAGDEBURG(13, new Neeples(1)),
    LEIPZIG(14, new Neeples(3)),
    ERFURT(15, new Neeples(1)),
    DRESDEN(16, new Neeples(1)),
    CHEMNITZ(17, new Neeples(1)),
    KOLN(18, new Neeples(4)),
    KOBLENZ(19, new Neeples(1)),
    FRANKFURT(20, new Neeples(4)),
    MAINZ(21, new Neeples(1)),
    WURZBURG(22, new Neeples(1)),
    NURNBERG(23, new Neeples(1)),
    REGENSBURG(24, new Neeples(1)),
    MUNCHEN(25, new Neeples(4)),
    AUGSBURG(26, new Neeples(1)),
    ULM(27, new Neeples(1)),
    STUTTGART(28, new Neeples(3)),
    KARLSRUHE(29, new Neeples(1)),
    MANNHEIM(30, new Neeples(1)),
    SAARBRUCKEN(31, new Neeples(1)),
    FREIBURG(32, new Neeples(1)),
    KONSTANZ(33, new Neeples(1)),
    LINDAU(34, new Neeples(1)),
    BREMEN(35, new Neeples(1)),
    FRANKREICH(36, new Neeples(1)),
    SCHWEIZ(37, new Neeples(1)),
    NIEDERLANDE(38, new Neeples(1)),
    OSTERREICH(39, new Neeples(1));

    final int id;

    final Neeples neeples;

    Cities(int id, Neeples neeples) {
        this.id = id;
        this.neeples = neeples;
    }

    public int getId() {
        return id;
    }

    public Neeples getNeeples() {
        return neeples;
    }
}
