package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.Cities;
import fr.cotedazur.univ.polytech.teamK.board.map.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapSimple {
    private List<Connection> connectionsInMap;

    public MapSimple(String Chosen) {
        if (Chosen.equals("Reich"))
        {
            connectionsInMap = buildReich();
        }
    }

    public List<Connection> getConnectionsInMap() {
        return connectionsInMap;
    }

    private List buildReich() {
        ArrayList<Connection> railsGermanyReich = new ArrayList<Connection>();
        railsGermanyReich.add(new Connection(Cities.DANEMARK, Cities.BREMERHAVEN, 5, new ArrayList<>(Arrays.asList(Colors.BLACK))));
        railsGermanyReich.add(new Connection(Cities.DANEMARK, Cities.KIEL, 2, new ArrayList<>(Arrays.asList(Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.KIEL, Cities.BREMERHAVEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.KIEL, Cities.HAMBURG, 2, new ArrayList<>(Arrays.asList(Colors.BLACK, Colors.PINK))));
        railsGermanyReich.add(new Connection(Cities.KIEL, Cities.SCHWERIN, 3, new ArrayList<>(Arrays.asList(Colors.YELLOW))));
        railsGermanyReich.add(new Connection(Cities.KIEL, Cities.ROSTOCK, 4, new ArrayList<>(Arrays.asList(Colors.ORANGE))));

        railsGermanyReich.add(new Connection(Cities.ROSTOCK, Cities.SCHWERIN, 2, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connection(Cities.ROSTOCK, Cities.BERLIN, 6, new ArrayList<>(Arrays.asList(Colors.PINK))));

        railsGermanyReich.add(new Connection(Cities.SCHWERIN, Cities.BERLIN, 5, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connection(Cities.SCHWERIN, Cities.HAMBURG, 2, new ArrayList<>(Arrays.asList(Colors.RED))));

        railsGermanyReich.add(new Connection(Cities.HAMBURG, Cities.BERLIN, 7, new ArrayList<>(Arrays.asList(Colors.BLUE, Colors.YELLOW))));
        railsGermanyReich.add(new Connection(Cities.HAMBURG, Cities.HANNOVER, 4, new ArrayList<>(Arrays.asList(Colors.RED, Colors.WHITE))));
        railsGermanyReich.add(new Connection(Cities.HAMBURG, Cities.BREMERHAVEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.HAMBURG, Cities.BREMEN, 3, new ArrayList<>(Arrays.asList(Colors.ORANGE))));

        railsGermanyReich.add(new Connection(Cities.BREMERHAVEN, Cities.BREMEN, 1, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connection(Cities.BREMERHAVEN, Cities.EMDEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.EMDEN, Cities.NIEDERLANDE, 2, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connection(Cities.EMDEN, Cities.MUNSTER, 4, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connection(Cities.EMDEN, Cities.BREMEN, 3, new ArrayList<>(Arrays.asList(Colors.BLUE))));

        railsGermanyReich.add(new Connection(Cities.BREMEN, Cities.HANNOVER, 3, new ArrayList<>(Arrays.asList(Colors.PINK)))); //VERIFY
        railsGermanyReich.add(new Connection(Cities.BREMEN, Cities.MUNSTER, 3, new ArrayList<>(Arrays.asList(Colors.BLACK))));

        railsGermanyReich.add(new Connection(Cities.NIEDERLANDE, Cities.MUNSTER, 2, new ArrayList<>(Arrays.asList(Colors.ORANGE))));
        railsGermanyReich.add(new Connection(Cities.NIEDERLANDE, Cities.DUSSELDORF, 3, new ArrayList<>(Arrays.asList(Colors.PINK))));

        railsGermanyReich.add(new Connection(Cities.BERLIN, Cities.HANNOVER, 7, new ArrayList<>(Arrays.asList(Colors.YELLOW))));
        railsGermanyReich.add(new Connection(Cities.BERLIN, Cities.MAGDEBURG, 3, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connection(Cities.BERLIN, Cities.LEIPZIG, 4, new ArrayList<>(Arrays.asList(Colors.PINK)))); //VERIFY
        railsGermanyReich.add(new Connection(Cities.BERLIN, Cities.DRESDEN, 5, new ArrayList<>(Arrays.asList(Colors.GREEN))));

        railsGermanyReich.add(new Connection(Cities.HANNOVER, Cities.MAGDEBURG, 4, new ArrayList<>(Arrays.asList(Colors.BLUE))));
        railsGermanyReich.add(new Connection(Cities.HANNOVER, Cities.ERFURT, 5, new ArrayList<>(Arrays.asList(Colors.ORANGE, Colors.GREEN))));
        railsGermanyReich.add(new Connection(Cities.HANNOVER, Cities.KASSEL, 3, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.HANNOVER, Cities.MUNSTER, 4, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connection(Cities.MUNSTER, Cities.DORTMUND, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.MAGDEBURG, Cities.LEIPZIG, 2, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connection(Cities.DORTMUND, Cities.DUSSELDORF, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.DORTMUND, Cities.KASSEL, 4, new ArrayList<>(Arrays.asList(Colors.GREEN))));

        railsGermanyReich.add(new Connection(Cities.DUSSELDORF, Cities.KOLN, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.LEIPZIG, Cities.DRESDEN, 3, new ArrayList<>(Arrays.asList(Colors.BLACK))));
        railsGermanyReich.add(new Connection(Cities.LEIPZIG, Cities.CHEMNITZ, 2, new ArrayList<>(Arrays.asList(Colors.BLUE))));
        railsGermanyReich.add(new Connection(Cities.LEIPZIG, Cities.ERFURT, 3, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connection(Cities.KASSEL, Cities.ERFURT, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.KASSEL, Cities.FRANKFURT, 4, new ArrayList<>(Arrays.asList(Colors.BLUE, Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.KOLN, Cities.FRANKFURT, 4, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.WHITE))));
        railsGermanyReich.add(new Connection(Cities.KOLN, Cities.KOBLENZ, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.ERFURT, Cities.NURNBERG, 4, new ArrayList<>(Arrays.asList(Colors.RED, Colors.BLUE))));
        railsGermanyReich.add(new Connection(Cities.ERFURT, Cities.REGENSBURG, 7, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connection(Cities.ERFURT, Cities.CHEMNITZ, 3, new ArrayList<>(Arrays.asList(Colors.BLACK))));

        railsGermanyReich.add(new Connection(Cities.DRESDEN, Cities.CHEMNITZ, 1, new ArrayList<>(Arrays.asList(Colors.YELLOW))));
        railsGermanyReich.add(new Connection(Cities.DRESDEN, Cities.REGENSBURG, 7, new ArrayList<>(Arrays.asList(Colors.RED))));

        railsGermanyReich.add(new Connection(Cities.CHEMNITZ, Cities.REGENSBURG, 6, new ArrayList<>(Arrays.asList(Colors.PINK))));

        railsGermanyReich.add(new Connection(Cities.KOBLENZ, Cities.MAINZ, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.KOBLENZ, Cities.SAARBRUCKEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.FRANKFURT, Cities.MAINZ, 1, new ArrayList<>(Arrays.asList(Colors.WHITE, Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.FRANKFURT, Cities.MANNHEIM, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.FRANKFURT, Cities.WURZBURG, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.BLACK))));

        railsGermanyReich.add(new Connection(Cities.MAINZ, Cities.SAARBRUCKEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.MAINZ, Cities.MANNHEIM, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.YELLOW))));

        railsGermanyReich.add(new Connection(Cities.WURZBURG, Cities.NURNBERG, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.YELLOW))));

        railsGermanyReich.add(new Connection(Cities.NURNBERG, Cities.AUGSBURG, 4, new ArrayList<>(Arrays.asList(Colors.BLACK))));
        railsGermanyReich.add(new Connection(Cities.NURNBERG, Cities.MUNCHEN, 5, new ArrayList<>(Arrays.asList(Colors.BLUE, Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.NURNBERG, Cities.REGENSBURG, 3, new ArrayList<>(Arrays.asList(Colors.GREEN))));

        railsGermanyReich.add(new Connection(Cities.MANNHEIM, Cities.SAARBRUCKEN, 3, new ArrayList<>(Arrays.asList(Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.MANNHEIM, Cities.KARLSRUHE, 1, new ArrayList<>(Arrays.asList(Colors.BLUE))));
        railsGermanyReich.add(new Connection(Cities.MANNHEIM, Cities.STUTTGART, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.SAARBRUCKEN, Cities.FRANKREICH, 1, new ArrayList<>(Arrays.asList(Colors.GREEN))));
        railsGermanyReich.add(new Connection(Cities.SAARBRUCKEN, Cities.KARLSRUHE, 3, new ArrayList<>(Arrays.asList(Colors.BLACK))));

        railsGermanyReich.add(new Connection(Cities.KARLSRUHE, Cities.FRANKREICH, 2, new ArrayList<>(Arrays.asList(Colors.BLACK))));
        railsGermanyReich.add(new Connection(Cities.KARLSRUHE, Cities.FREIBURG, 3, new ArrayList<>(Arrays.asList(Colors.WHITE))));
        railsGermanyReich.add(new Connection(Cities.KARLSRUHE, Cities.STUTTGART, 1, new ArrayList<>(Arrays.asList(Colors.PINK))));

        railsGermanyReich.add(new Connection(Cities.STUTTGART, Cities.FREIBURG, 3, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connection(Cities.STUTTGART, Cities.KONSTANZ, 3, new ArrayList<>(Arrays.asList(Colors.GREEN))));
        railsGermanyReich.add(new Connection(Cities.STUTTGART, Cities.ULM, 2, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.REGENSBURG, Cities.MUNCHEN, 3, new ArrayList<>(Arrays.asList(Colors.ORANGE))));
        railsGermanyReich.add(new Connection(Cities.REGENSBURG, Cities.OSTERREICH, 4, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connection(Cities.FRANKREICH, Cities.FREIBURG, 2, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connection(Cities.ULM, Cities.AUGSBURG, 1, new ArrayList<>(Arrays.asList(Colors.GRAY, Colors.GRAY))));
        railsGermanyReich.add(new Connection(Cities.ULM, Cities.LINDAU, 2, new ArrayList<>(Arrays.asList(Colors.RED))));

        railsGermanyReich.add(new Connection(Cities.AUGSBURG, Cities.MUNCHEN, 2, new ArrayList<>(Arrays.asList(Colors.WHITE, Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.MUNCHEN, Cities.OSTERREICH, 3, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connection(Cities.MUNCHEN, Cities.LINDAU, 5, new ArrayList<>(Arrays.asList(Colors.GRAY))));

        railsGermanyReich.add(new Connection(Cities.FREIBURG, Cities.SCHWEIZ, 2, new ArrayList<>(Arrays.asList(Colors.ORANGE))));
        railsGermanyReich.add(new Connection(Cities.FREIBURG, Cities.KONSTANZ, 2, new ArrayList<>(Arrays.asList(Colors.BLACK))));

        railsGermanyReich.add(new Connection(Cities.KONSTANZ, Cities.SCHWEIZ, 1, new ArrayList<>(Arrays.asList(Colors.RED))));
        railsGermanyReich.add(new Connection(Cities.KONSTANZ, Cities.LINDAU, 1, new ArrayList<>(Arrays.asList(Colors.YELLOW))));

        railsGermanyReich.add(new Connection(Cities.LINDAU, Cities.SCHWEIZ, 2, new ArrayList<>(Arrays.asList(Colors.BLUE))));
        railsGermanyReich.add(new Connection(Cities.LINDAU, Cities.OSTERREICH, 2, new ArrayList<>(Arrays.asList(Colors.PINK))));

        return railsGermanyReich;
    }
}
