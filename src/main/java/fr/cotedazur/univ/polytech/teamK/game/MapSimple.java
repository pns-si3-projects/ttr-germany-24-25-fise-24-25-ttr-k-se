package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Connections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapSimple {
    private List<Connections> connectionsInMap;

    public MapSimple(String Chosen) {
        if (Chosen.equals("Reich"))
        {
            connectionsInMap = buildReich();
        }
    }

    public List<Connections> getConnectionsInMap() {
        return connectionsInMap;
    }

    private List buildReich() {
        ArrayList<Connections> railsGermanyReich = new ArrayList<Connections>();
        railsGermanyReich.add(new Connections(Cities.DANEMARK, Cities.BREMERHAVEN, 5, new ArrayList<>(Arrays.asList(Colors.BLACK))));
        railsGermanyReich.add(new Connections(Cities.DANEMARK, Cities.KIEL, 2, new ArrayList<>(Arrays.asList(Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.KIEL, Cities.BREMERHAVEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.KIEL, Cities.HAMBURG, 2, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK))));
        railsGermanyReich.add(new Connections(Cities.KIEL, Cities.SCHWERIN, 3, new ArrayList<>(Arrays.asList(Colors.YELLOW))));
        railsGermanyReich.add(new Connections(Cities.KIEL, Cities.ROSTOCK, 4, new ArrayList<>(Arrays.asList(Colors.ORANGE))));

        railsGermanyReich.add(new Connections(Cities.ROSTOCK, Cities.SCHWERIN, 2, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connections(Cities.ROSTOCK, Cities.BERLIN, 6, new ArrayList<>(Arrays.asList(Colors.PINK))));

        railsGermanyReich.add(new Connections(Cities.SCHWERIN, Cities.BERLIN, 5, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connections(Cities.SCHWERIN, Cities.HAMBURG, 2, new ArrayList<>(Arrays.asList(Colors.RED))));

        railsGermanyReich.add(new Connections(Cities.HAMBURG, Cities.BERLIN, 7, new ArrayList<>(Arrays.asList(Colors.BLUE, Colors.YELLOW))));
        railsGermanyReich.add(new Connections(Cities.HAMBURG, Cities.HANNOVER, 4, new ArrayList<>(Arrays.asList(Colors.RED, Colors.WHITE))));
        railsGermanyReich.add(new Connections(Cities.HAMBURG, Cities.BREMERHAVEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.HAMBURG, Cities.BREMEN, 3, new ArrayList<>(Arrays.asList(Colors.ORANGE))));

        railsGermanyReich.add(new Connections(Cities.BREMERHAVEN, Cities.BREMEN, 1, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connections(Cities.BREMERHAVEN, Cities.EMDEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.EMDEN, Cities.NETHERLANDS, 2, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connections(Cities.EMDEN, Cities.MUNSTER, 4, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connections(Cities.EMDEN, Cities.BREMEN, 3, new ArrayList<>(Arrays.asList(Colors.BLUE))));

        railsGermanyReich.add(new Connections(Cities.BREMEN, Cities.HANNOVER, 3, new ArrayList<>(Arrays.asList(Colors.PINK)))); //VERIFY
        railsGermanyReich.add(new Connections(Cities.BREMEN, Cities.MUNSTER, 3, new ArrayList<>(Arrays.asList(Colors.BLACK))));

        railsGermanyReich.add(new Connections(Cities.NETHERLANDS, Cities.MUNSTER, 2, new ArrayList<>(Arrays.asList(Colors.ORANGE))));
        railsGermanyReich.add(new Connections(Cities.NETHERLANDS, Cities.DUSSELDORF, 3, new ArrayList<>(Arrays.asList(Colors.PINK))));

        railsGermanyReich.add(new Connections(Cities.BERLIN, Cities.HANNOVER, 7, new ArrayList<>(Arrays.asList(Colors.YELLOW))));
        railsGermanyReich.add(new Connections(Cities.BERLIN, Cities.MAGDEBURG, 3, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connections(Cities.BERLIN, Cities.LEIPZIG, 4, new ArrayList<>(Arrays.asList(Colors.PINK)))); //VERIFY
        railsGermanyReich.add(new Connections(Cities.BERLIN, Cities.DRESDEN, 5, new ArrayList<>(Arrays.asList(Colors.GREEN))));

        railsGermanyReich.add(new Connections(Cities.HANNOVER, Cities.MAGDEBURG, 4, new ArrayList<>(Arrays.asList(Colors.BLUE))));
        railsGermanyReich.add(new Connections(Cities.HANNOVER, Cities.ERFURT, 5, new ArrayList<>(Arrays.asList(Colors.ORANGE, Colors.GREEN))));
        railsGermanyReich.add(new Connections(Cities.HANNOVER, Cities.KASSEL, 3, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.HANNOVER, Cities.MUNSTER, 4, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connections(Cities.MUNSTER, Cities.DORTMUND, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.MAGDEBURG, Cities.LEIPZIG, 2, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connections(Cities.DORTMUND, Cities.DUSSELDORF, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.DORTMUND, Cities.KASSEL, 4, new ArrayList<>(Arrays.asList(Colors.GREEN))));

        railsGermanyReich.add(new Connections(Cities.DUSSELDORF, Cities.KOLN, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.LEIPZIG, Cities.DRESDEN, 3, new ArrayList<>(Arrays.asList(Colors.BLACK))));
        railsGermanyReich.add(new Connections(Cities.LEIPZIG, Cities.CHEMNITZ, 2, new ArrayList<>(Arrays.asList(Colors.BLUE))));
        railsGermanyReich.add(new Connections(Cities.LEIPZIG, Cities.ERFURT, 3, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connections(Cities.KASSEL, Cities.ERFURT, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.KASSEL, Cities.FRANKFURT, 4, new ArrayList<>(Arrays.asList(Colors.BLUE, Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.KOLN, Cities.FRANKFURT, 4, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.WHITE))));
        railsGermanyReich.add(new Connections(Cities.KOLN, Cities.KOBLENZ, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.ERFURT, Cities.NURNBERG, 4, new ArrayList<>(Arrays.asList(Colors.RED, Colors.BLUE))));
        railsGermanyReich.add(new Connections(Cities.ERFURT, Cities.REGENSBURG, 7, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connections(Cities.ERFURT, Cities.CHEMNITZ, 3, new ArrayList<>(Arrays.asList(Colors.BLACK))));

        railsGermanyReich.add(new Connections(Cities.DRESDEN, Cities.CHEMNITZ, 1, new ArrayList<>(Arrays.asList(Colors.YELLOW))));
        railsGermanyReich.add(new Connections(Cities.DRESDEN, Cities.REGENSBURG, 7, new ArrayList<>(Arrays.asList(Colors.RED))));

        railsGermanyReich.add(new Connections(Cities.CHEMNITZ, Cities.REGENSBURG, 6, new ArrayList<>(Arrays.asList(Colors.PINK))));

        railsGermanyReich.add(new Connections(Cities.KOBLENZ, Cities.MAINZ, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.KOBLENZ, Cities.SAARBRUCKEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.FRANKFURT, Cities.MAINZ, 1, new ArrayList<>(Arrays.asList(Colors.WHITE, Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.FRANKFURT, Cities.MANNHEIM, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.FRANKFURT, Cities.WURZBURG, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.BLACK))));

        railsGermanyReich.add(new Connections(Cities.MAINZ, Cities.SAARBRUCKEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.MAINZ, Cities.MANNHEIM, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.YELLOW))));

        railsGermanyReich.add(new Connections(Cities.WURZBURG, Cities.NURNBERG, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.YELLOW))));

        railsGermanyReich.add(new Connections(Cities.NURNBERG, Cities.AUGSBURG, 4, new ArrayList<>(Arrays.asList(Colors.BLACK))));
        railsGermanyReich.add(new Connections(Cities.NURNBERG, Cities.MUNCHEN, 5, new ArrayList<>(Arrays.asList(Colors.BLUE, Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.NURNBERG, Cities.REGENSBURG, 3, new ArrayList<>(Arrays.asList(Colors.GREEN))));

        railsGermanyReich.add(new Connections(Cities.MANNHEIM, Cities.SAARBRUCKEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.MANNHEIM, Cities.KARLSRUHE, 1, new ArrayList<>(Arrays.asList(Colors.BLUE))));
        railsGermanyReich.add(new Connections(Cities.MANNHEIM, Cities.STUTTGART, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.SAARBRUCKEN, Cities.STRASBOURG, 1, new ArrayList<>(Arrays.asList(Colors.GREEN))));
        railsGermanyReich.add(new Connections(Cities.SAARBRUCKEN, Cities.KARLSRUHE, 3, new ArrayList<>(Arrays.asList(Colors.BLACK))));

        railsGermanyReich.add(new Connections(Cities.KARLSRUHE, Cities.STRASBOURG, 2, new ArrayList<>(Arrays.asList(Colors.BLACK))));
        railsGermanyReich.add(new Connections(Cities.KARLSRUHE, Cities.FREIBURG, 3, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connections(Cities.KARLSRUHE, Cities.STUTTGART, 1, new ArrayList<>(Arrays.asList(Colors.PINK))));

        railsGermanyReich.add(new Connections(Cities.STUTTGART, Cities.FREIBURG, 3, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connections(Cities.STUTTGART, Cities.KONSTANZ, 3, new ArrayList<>(Arrays.asList(Colors.GREEN))));
        railsGermanyReich.add(new Connections(Cities.STUTTGART, Cities.ULM, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.REGENSBURG, Cities.MUNCHEN, 3, new ArrayList<>(Arrays.asList(Colors.ORANGE))));
        railsGermanyReich.add(new Connections(Cities.REGENSBURG, Cities.OSTERREICH, 4, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connections(Cities.STRASBOURG, Cities.FREIBURG, 2, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connections(Cities.ULM, Cities.AUGSBURG, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connections(Cities.ULM, Cities.LINDAU, 2, new ArrayList<>(Arrays.asList(Colors.RED))));

        railsGermanyReich.add(new Connections(Cities.AUGSBURG, Cities.MUNCHEN, 2, new ArrayList<>(Arrays.asList(Colors.WHITE, Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.MUNCHEN, Cities.OSTERREICH, 3, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connections(Cities.MUNCHEN, Cities.LINDAU, 5, new ArrayList<>(Arrays.asList(Colors.GRAY))));

        railsGermanyReich.add(new Connections(Cities.FREIBURG, Cities.SCHWEIZ, 2, new ArrayList<>(Arrays.asList(Colors.ORANGE))));
        railsGermanyReich.add(new Connections(Cities.FREIBURG, Cities.KONSTANZ, 2, new ArrayList<>(Arrays.asList(Colors.BLACK))));

        railsGermanyReich.add(new Connections(Cities.KONSTANZ, Cities.SCHWEIZ, 1, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connections(Cities.KONSTANZ, Cities.LINDAU, 1, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connections(Cities.LINDAU, Cities.SCHWEIZ, 2, new ArrayList<>(Arrays.asList(Colors.BLUE))));
        railsGermanyReich.add(new Connections(Cities.LINDAU, Cities.OSTERREICH2, 2, new ArrayList<>(Arrays.asList(Colors.PINK))));

        return railsGermanyReich;
    }
}
