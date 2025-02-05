package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.Meeple;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;

import java.util.HashMap;
import java.util.List;

public class Board {
    private static final String KIEL = "Kiel";
    private static final String DANEMARK = "Danemark";
    private static final String BREMERHAVEN = "Bremerhaven";
    private static final String HAMBURG = "Hamburg";
    private static final String SCHWERIN = "Schwerin";
    private static final String BERLIN = "Berlin";
    private static final String HANNOVER = "Hannover";
    private static final String BREMEN = "Bremen";
    private static final String EMDEN = "Emden";
    private static final String NIEDERLANDE = "Niederlande";
    private static final String MUNSTER = "Munster";
    private static final String DUSSELDORF = "Dusseldorf";
    private static final String MAGDEBURG = "Magdeburg";
    private static final String LEIPZIG = "Leipzig";
    private static final String DRESDEN = "Dresden";
    private static final String ERFURT = "Erfurt";
    private static final String KASSEL = "Kassel";
    private static final String DORTMUND = "Dortmund";
    private static final String KOLN = "Koln";
    private static final String CHEMNITZ = "Chemnitz";
    private static final String FRANKFURT = "Frankfurt";
    private static final String KOBLENZ = "Koblenz";
    private static final String NURNBERG = "Nurnberg";
    private static final String REGENSBURG = "Regensburg";
    private static final String MAINZ = "Mainz";
    private static final String SAARBRUCKEN = "Saarbrucken";
    private static final String MANNHEIM = "Mannheim";
    private static final String WURZBURG = "Wurzburg";
    private static final String AUGSBURG = "Augsburg";
    private static final String MUNCHEN = "Munchen";
    private static final String KARLSRUHE = "Karlsruhe";
    private static final String STUTTGART = "Stuttgart";
    private static final String FRANKREICH = "Frankreich";
    private static final String FREIBURG = "Freiburg";
    private static final String KONSTANZ = "Konstanz";
    private static final String ULM = "Ulm";
    private static final String OSTERREICH = "Osterreich";
    private static final String LINDAU = "Lindau";
    private static final String SCHWEIZ = "Schweiz";
    private static final String ROSTOCK = "Rostock";

    private HashMap<String, City> cities;
    public Board(String name) {
        Meeple.resetMeeples();
        if (name.equals("Reich"))
        {
            cities = buildReichMap();
        }
        if (name.equals("testMap")){
            cities = buildTestMap();
        }
    }

    public HashMap<String, City> getCity()
    {
        return cities;
    }

    /**
     * Build the map for the TTR in germany
     * @return the map of the game
     */
    private HashMap<String,City> buildReichMap()
    {
        HashMap<String, City> reichMap = new HashMap<>();

        reichMap.put(DANEMARK, new City(DANEMARK, 1, true));
        reichMap.put(KIEL, new City(KIEL, 1));
        reichMap.put(ROSTOCK, new City(ROSTOCK, 1));
        reichMap.put(EMDEN, new City(EMDEN, 1));
        reichMap.put(BREMERHAVEN, new City(BREMERHAVEN, 1));
        reichMap.put(HAMBURG, new City(HAMBURG, 4));
        reichMap.put(SCHWERIN, new City(SCHWERIN, 1));
        reichMap.put(MUNSTER, new City(MUNSTER, 1));
        reichMap.put(HANNOVER, new City(HANNOVER, 1));
        reichMap.put(BERLIN, new City(BERLIN, 5));
        reichMap.put(DUSSELDORF, new City(DUSSELDORF, 1));
        reichMap.put(DORTMUND, new City(DORTMUND, 1));
        reichMap.put(KASSEL, new City(KASSEL, 1));
        reichMap.put(MAGDEBURG, new City(MAGDEBURG, 1));
        reichMap.put(LEIPZIG, new City(LEIPZIG, 3));
        reichMap.put(ERFURT, new City(ERFURT, 1));
        reichMap.put(DRESDEN, new City(DRESDEN, 1));
        reichMap.put(CHEMNITZ, new City(CHEMNITZ, 1));
        reichMap.put(KOLN, new City(KOLN, 4));
        reichMap.put(KOBLENZ, new City(KOBLENZ, 1));
        reichMap.put(FRANKFURT, new City(FRANKFURT, 4));
        reichMap.put(MAINZ, new City(MAINZ, 1));
        reichMap.put(WURZBURG, new City(WURZBURG, 1));
        reichMap.put(NURNBERG, new City(NURNBERG, 1));
        reichMap.put(REGENSBURG, new City(REGENSBURG, 1));
        reichMap.put(MUNCHEN, new City(MUNCHEN, 4));
        reichMap.put(AUGSBURG, new City(AUGSBURG, 1));
        reichMap.put(ULM, new City(ULM, 1));
        reichMap.put(STUTTGART, new City(STUTTGART, 3));
        reichMap.put(KARLSRUHE, new City(KARLSRUHE, 1));
        reichMap.put(MANNHEIM, new City(MANNHEIM, 1));
        reichMap.put(SAARBRUCKEN, new City(SAARBRUCKEN, 1));
        reichMap.put(FREIBURG, new City(FREIBURG, 1));
        reichMap.put(KONSTANZ, new City(KONSTANZ, 1));
        reichMap.put(LINDAU, new City(LINDAU, 1));
        reichMap.put(BREMEN, new City(BREMEN, 1));
        reichMap.put(FRANKREICH, new City(FRANKREICH, 1, true));
        reichMap.put(SCHWEIZ, new City(SCHWEIZ, 1, true));
        reichMap.put(NIEDERLANDE, new City(NIEDERLANDE, 1, true));
        reichMap.put(OSTERREICH, new City(OSTERREICH, 1, true));

// DANEMARK to BREMERHAVEN
        Connection danemarkToBremerhaven = new Connection(reichMap.get(DANEMARK), reichMap.get(BREMERHAVEN), 5, Colors.BLACK);
        reichMap.get(DANEMARK).addConnection(danemarkToBremerhaven);
        reichMap.get(BREMERHAVEN).addConnection(danemarkToBremerhaven);

// DANEMARK to KIEL
        Connection danemarkToKiel = new Connection(reichMap.get(DANEMARK), reichMap.get(KIEL), 2, Colors.GRAY);
        reichMap.get(DANEMARK).addConnection(danemarkToKiel);
        reichMap.get(KIEL).addConnection(danemarkToKiel);

// KIEL to BREMERHAVEN
        Connection kielToBremerhaven = new Connection(reichMap.get(KIEL), reichMap.get(BREMERHAVEN), 3, Colors.GRAY);
        reichMap.get(KIEL).addConnection(kielToBremerhaven);
        reichMap.get(BREMERHAVEN).addConnection(kielToBremerhaven);
// KIEL TO HAMBURG
        Connection kielToHamburg = new Connection(reichMap.get(KIEL), reichMap.get(HAMBURG), 2, Colors.BLACK);
        reichMap.get(KIEL).addConnection(kielToHamburg);
        reichMap.get(HAMBURG).addConnection(kielToHamburg);
        kielToHamburg = new Connection(reichMap.get(KIEL), reichMap.get(HAMBURG), 2, Colors.PINK);
        reichMap.get(KIEL).addConnection(kielToHamburg);
        reichMap.get(HAMBURG).addConnection(kielToHamburg);
// KIEL TO SCHWERIN
        Connection kielToSchwerin = new Connection(reichMap.get(KIEL), reichMap.get(SCHWERIN), 3, Colors.YELLOW);
        reichMap.get(KIEL).addConnection(kielToSchwerin);
        reichMap.get(SCHWERIN).addConnection(kielToSchwerin);

        Connection kielToRostock = new Connection(reichMap.get(KIEL), reichMap.get(ROSTOCK), 4, Colors.ORANGE);
        reichMap.get(KIEL).addConnection(kielToRostock);
        reichMap.get(ROSTOCK).addConnection(kielToRostock);

        Connection rostockToSchwerin = new Connection(reichMap.get(ROSTOCK), reichMap.get(SCHWERIN), 2, Colors.RED);
        reichMap.get(ROSTOCK).addConnection(rostockToSchwerin);
        reichMap.get(SCHWERIN).addConnection(rostockToSchwerin);

        Connection rostockToBerlin = new Connection(reichMap.get(ROSTOCK), reichMap.get(BERLIN), 6, Colors.PINK);
        reichMap.get(ROSTOCK).addConnection(rostockToBerlin);
        reichMap.get(BERLIN).addConnection(rostockToBerlin);

        Connection schwerinToBerlin = new Connection(reichMap.get(SCHWERIN), reichMap.get(BERLIN), 5, Colors.WHITE);
        reichMap.get(SCHWERIN).addConnection(schwerinToBerlin);
        reichMap.get(BERLIN).addConnection(schwerinToBerlin);

        Connection schwerinToHamburg = new Connection(reichMap.get(SCHWERIN), reichMap.get(HAMBURG), 2, Colors.RED);
        reichMap.get(SCHWERIN).addConnection(schwerinToHamburg);
        reichMap.get(HAMBURG).addConnection(schwerinToHamburg);

        Connection hamburgToBerlin = new Connection(reichMap.get(HAMBURG), reichMap.get(BERLIN), 7, Colors.BLUE);
        reichMap.get(HAMBURG).addConnection(hamburgToBerlin);
        reichMap.get(BERLIN).addConnection(hamburgToBerlin);
        hamburgToBerlin = new Connection(reichMap.get(HAMBURG), reichMap.get(BERLIN), 7, Colors.YELLOW);
        reichMap.get(HAMBURG).addConnection(hamburgToBerlin);
        reichMap.get(BERLIN).addConnection(hamburgToBerlin);

        Connection hamburgToHannover = new Connection(reichMap.get(HAMBURG), reichMap.get(HANNOVER), 4, Colors.RED);
        reichMap.get(HAMBURG).addConnection(hamburgToHannover);
        reichMap.get(HANNOVER).addConnection(hamburgToHannover);
        hamburgToHannover = new Connection(reichMap.get(HAMBURG), reichMap.get(HANNOVER), 4, Colors.WHITE);
        reichMap.get(HAMBURG).addConnection(hamburgToHannover);
        reichMap.get(HANNOVER).addConnection(hamburgToHannover);

        Connection hamburgToBremerhaven = new Connection(reichMap.get(HAMBURG), reichMap.get(BREMERHAVEN), 3, Colors.GRAY);
        reichMap.get(HAMBURG).addConnection(hamburgToBremerhaven);
        reichMap.get(BREMERHAVEN).addConnection(hamburgToBremerhaven);

        Connection hamburgToBremen = new Connection(reichMap.get(HAMBURG), reichMap.get(BREMEN), 3, Colors.ORANGE);
        reichMap.get(HAMBURG).addConnection(hamburgToBremen);
        reichMap.get(BREMEN).addConnection(hamburgToBremen);

        Connection bremerhavenToBremen = new Connection(reichMap.get(BREMERHAVEN), reichMap.get(BREMEN), 1, Colors.WHITE);
        reichMap.get(BREMERHAVEN).addConnection(bremerhavenToBremen);
        reichMap.get(BREMEN).addConnection(bremerhavenToBremen);

        Connection bremerhavenToEmden = new Connection(reichMap.get(BREMERHAVEN), reichMap.get(EMDEN), 3, Colors.GRAY);
        reichMap.get(BREMERHAVEN).addConnection(bremerhavenToEmden);
        reichMap.get(EMDEN).addConnection(bremerhavenToEmden);

        Connection emdenToNiederlande = new Connection(reichMap.get(EMDEN), reichMap.get(NIEDERLANDE), 2, Colors.WHITE);
        reichMap.get(EMDEN).addConnection(emdenToNiederlande);
        reichMap.get(NIEDERLANDE).addConnection(emdenToNiederlande);

        Connection emdenToMunster = new Connection(reichMap.get(EMDEN), reichMap.get(MUNSTER), 4, Colors.RED);
        reichMap.get(EMDEN).addConnection(emdenToMunster);
        reichMap.get(MUNSTER).addConnection(emdenToMunster);

        Connection emdenToBremen = new Connection(reichMap.get(EMDEN), reichMap.get(BREMEN), 3, Colors.BLUE);
        reichMap.get(EMDEN).addConnection(emdenToBremen);
        reichMap.get(BREMEN).addConnection(emdenToBremen);

        Connection bremenToHannover = new Connection(reichMap.get(BREMEN), reichMap.get(HANNOVER), 3, Colors.PINK);
        reichMap.get(BREMEN).addConnection(bremenToHannover);
        reichMap.get(HANNOVER).addConnection(bremenToHannover);

        Connection bremenToMunster = new Connection(reichMap.get(BREMEN), reichMap.get(MUNSTER), 3, Colors.BLACK);
        reichMap.get(BREMEN).addConnection(bremenToMunster);
        reichMap.get(MUNSTER).addConnection(bremenToMunster);

        Connection niederlandeToMunster = new Connection(reichMap.get(NIEDERLANDE), reichMap.get(MUNSTER), 2, Colors.ORANGE);
        reichMap.get(NIEDERLANDE).addConnection(niederlandeToMunster);
        reichMap.get(MUNSTER).addConnection(niederlandeToMunster);

        Connection niederlandeToDusseldorf = new Connection(reichMap.get(NIEDERLANDE), reichMap.get(DUSSELDORF), 3, Colors.PINK);
        reichMap.get(NIEDERLANDE).addConnection(niederlandeToDusseldorf);
        reichMap.get(DUSSELDORF).addConnection(niederlandeToDusseldorf);

        Connection berlinToHannover = new Connection(reichMap.get(BERLIN), reichMap.get(HANNOVER), 7, Colors.YELLOW);
        reichMap.get(BERLIN).addConnection(berlinToHannover);
        reichMap.get(HANNOVER).addConnection(berlinToHannover);

        Connection berlinToMagdeburg = new Connection(reichMap.get(BERLIN), reichMap.get(MAGDEBURG), 3, Colors.RED);
        reichMap.get(BERLIN).addConnection(berlinToMagdeburg);
        reichMap.get(MAGDEBURG).addConnection(berlinToMagdeburg);

        Connection berlinToLeipzig = new Connection(reichMap.get(BERLIN), reichMap.get(LEIPZIG), 4, Colors.PINK);
        reichMap.get(BERLIN).addConnection(berlinToLeipzig);
        reichMap.get(LEIPZIG).addConnection(berlinToLeipzig);

        Connection berlinToDresden = new Connection(reichMap.get(BERLIN), reichMap.get(DRESDEN), 5, Colors.GREEN);
        reichMap.get(BERLIN).addConnection(berlinToDresden);
        reichMap.get(DRESDEN).addConnection(berlinToDresden);

        Connection hannoverToMagdeburg = new Connection(reichMap.get(HANNOVER), reichMap.get(MAGDEBURG), 4, Colors.BLUE);
        reichMap.get(HANNOVER).addConnection(hannoverToMagdeburg);
        reichMap.get(MAGDEBURG).addConnection(hannoverToMagdeburg);

        Connection hannoverToErfurt = new Connection(reichMap.get(HANNOVER), reichMap.get(ERFURT), 5, Colors.ORANGE);
        reichMap.get(HANNOVER).addConnection(hannoverToErfurt);
        reichMap.get(ERFURT).addConnection(hannoverToErfurt);
        hannoverToErfurt = new Connection(reichMap.get(HANNOVER), reichMap.get(ERFURT), 5, Colors.GREEN);
        reichMap.get(HANNOVER).addConnection(hannoverToErfurt);
        reichMap.get(ERFURT).addConnection(hannoverToErfurt);

        Connection hannoverToKassel = new Connection(reichMap.get(HANNOVER), reichMap.get(KASSEL), 3, Colors.GRAY);
        reichMap.get(HANNOVER).addConnection(hannoverToKassel);
        reichMap.get(KASSEL).addConnection(hannoverToKassel);
        hannoverToKassel = new Connection(reichMap.get(HANNOVER), reichMap.get(KASSEL), 3, Colors.GRAY);
        reichMap.get(HANNOVER).addConnection(hannoverToKassel);
        reichMap.get(KASSEL).addConnection(hannoverToKassel);

        Connection hannoverToMunster = new Connection(reichMap.get(HANNOVER), reichMap.get(MUNSTER), 4, Colors.YELLOW);
        reichMap.get(HANNOVER).addConnection(hannoverToMunster);
        reichMap.get(MUNSTER).addConnection(hannoverToMunster);

        Connection munsterToDortmund = new Connection(reichMap.get(MUNSTER), reichMap.get(DORTMUND), 1, Colors.GRAY);
        reichMap.get(MUNSTER).addConnection(munsterToDortmund);
        reichMap.get(DORTMUND).addConnection(munsterToDortmund);
        munsterToDortmund = new Connection(reichMap.get(MUNSTER), reichMap.get(DORTMUND), 1, Colors.GRAY);
        reichMap.get(MUNSTER).addConnection(munsterToDortmund);
        reichMap.get(DORTMUND).addConnection(munsterToDortmund);

// MAGDEBURG to LEIPZIG
        Connection magdeburgToLeipzig = new Connection(reichMap.get(MAGDEBURG), reichMap.get(LEIPZIG), 2, Colors.YELLOW);
        reichMap.get(MAGDEBURG).addConnection(magdeburgToLeipzig);
        reichMap.get(LEIPZIG).addConnection(magdeburgToLeipzig);

// DORTMUND to DUSSELDORF
        Connection dortmundToDusseldorf = new Connection(reichMap.get(DORTMUND), reichMap.get(DUSSELDORF), 1, Colors.GRAY);
        reichMap.get(DORTMUND).addConnection(dortmundToDusseldorf);
        reichMap.get(DUSSELDORF).addConnection(dortmundToDusseldorf);
        dortmundToDusseldorf = new Connection(reichMap.get(DORTMUND), reichMap.get(DUSSELDORF), 1, Colors.GRAY);
        reichMap.get(DORTMUND).addConnection(dortmundToDusseldorf);
        reichMap.get(DUSSELDORF).addConnection(dortmundToDusseldorf);
        dortmundToDusseldorf = new Connection(reichMap.get(DORTMUND), reichMap.get(DUSSELDORF), 1, Colors.GRAY);
        reichMap.get(DORTMUND).addConnection(dortmundToDusseldorf);
        reichMap.get(DUSSELDORF).addConnection(dortmundToDusseldorf);

// DORTMUND to KASSEL
        Connection dortmundToKassel = new Connection(reichMap.get(DORTMUND), reichMap.get(KASSEL), 4, Colors.GREEN);
        reichMap.get(DORTMUND).addConnection(dortmundToKassel);
        reichMap.get(KASSEL).addConnection(dortmundToKassel);

// DUSSELDORF to KOLN
        Connection dusseldorfToKoln = new Connection(reichMap.get(DUSSELDORF), reichMap.get(KOLN), 1, Colors.GRAY);
        reichMap.get(DUSSELDORF).addConnection(dusseldorfToKoln);
        reichMap.get(KOLN).addConnection(dusseldorfToKoln);
        dusseldorfToKoln = new Connection(reichMap.get(DUSSELDORF), reichMap.get(KOLN), 1, Colors.GRAY);
        reichMap.get(DUSSELDORF).addConnection(dusseldorfToKoln);
        reichMap.get(KOLN).addConnection(dusseldorfToKoln);dusseldorfToKoln = new Connection(reichMap.get(DUSSELDORF), reichMap.get(KOLN), 1, Colors.GRAY);
        reichMap.get(DUSSELDORF).addConnection(dusseldorfToKoln);
        reichMap.get(KOLN).addConnection(dusseldorfToKoln);

// LEIPZIG to DRESDEN
        Connection leipzigToDresden = new Connection(reichMap.get(LEIPZIG), reichMap.get(DRESDEN), 3, Colors.BLACK);
        reichMap.get(LEIPZIG).addConnection(leipzigToDresden);
        reichMap.get(DRESDEN).addConnection(leipzigToDresden);

// LEIPZIG to CHEMNITZ
        Connection leipzigToChemnitz = new Connection(reichMap.get(LEIPZIG), reichMap.get(CHEMNITZ), 2, Colors.BLUE);
        reichMap.get(LEIPZIG).addConnection(leipzigToChemnitz);
        reichMap.get(CHEMNITZ).addConnection(leipzigToChemnitz);

// LEIPZIG to ERFURT
        Connection leipzigToErfurt = new Connection(reichMap.get(LEIPZIG), reichMap.get(ERFURT), 3, Colors.YELLOW);
        reichMap.get(LEIPZIG).addConnection(leipzigToErfurt);
        reichMap.get(ERFURT).addConnection(leipzigToErfurt);

// KASSEL to ERFURT
        Connection kasselToErfurt = new Connection(reichMap.get(KASSEL), reichMap.get(ERFURT), 3, Colors.GRAY);
        reichMap.get(KASSEL).addConnection(kasselToErfurt);
        reichMap.get(ERFURT).addConnection(kasselToErfurt);

// KASSEL to FRANKFURT
        Connection kasselToFrankfurt = new Connection(reichMap.get(KASSEL), reichMap.get(FRANKFURT), 4, Colors.BLUE);
        reichMap.get(KASSEL).addConnection(kasselToFrankfurt);
        reichMap.get(FRANKFURT).addConnection(kasselToFrankfurt);
        kasselToFrankfurt = new Connection(reichMap.get(KASSEL), reichMap.get(FRANKFURT), 4, Colors.GRAY);
        reichMap.get(KASSEL).addConnection(kasselToFrankfurt);
        reichMap.get(FRANKFURT).addConnection(kasselToFrankfurt);

// KOLN to FRANKFURT
        Connection kolnToFrankfurt = new Connection(reichMap.get(KOLN), reichMap.get(FRANKFURT), 4, Colors.GRAY);
        reichMap.get(KOLN).addConnection(kolnToFrankfurt);
        reichMap.get(FRANKFURT).addConnection(kolnToFrankfurt);
        kolnToFrankfurt = new Connection(reichMap.get(KOLN), reichMap.get(FRANKFURT), 4, Colors.WHITE);
        reichMap.get(KOLN).addConnection(kolnToFrankfurt);
        reichMap.get(FRANKFURT).addConnection(kolnToFrankfurt);

// KOLN to KOBLENZ
        Connection kolnToKoblenz = new Connection(reichMap.get(KOLN), reichMap.get(KOBLENZ), 1, Colors.GRAY);
        reichMap.get(KOLN).addConnection(kolnToKoblenz);
        reichMap.get(KOBLENZ).addConnection(kolnToKoblenz);
        kolnToKoblenz = new Connection(reichMap.get(KOLN), reichMap.get(KOBLENZ), 1, Colors.GRAY);
        reichMap.get(KOLN).addConnection(kolnToKoblenz);
        reichMap.get(KOBLENZ).addConnection(kolnToKoblenz);

// ERFURT to NURNBERG
        Connection erfurtToNurnberg = new Connection(reichMap.get(ERFURT), reichMap.get(NURNBERG), 4, Colors.RED);
        reichMap.get(ERFURT).addConnection(erfurtToNurnberg);
        reichMap.get(NURNBERG).addConnection(erfurtToNurnberg);
        erfurtToNurnberg = new Connection(reichMap.get(ERFURT), reichMap.get(NURNBERG), 4, Colors.BLUE);
        reichMap.get(ERFURT).addConnection(erfurtToNurnberg);
        reichMap.get(NURNBERG).addConnection(erfurtToNurnberg);

// ERFURT to REGENSBURG
        Connection erfurtToRegensburg = new Connection(reichMap.get(ERFURT), reichMap.get(REGENSBURG), 7, Colors.WHITE);
        reichMap.get(ERFURT).addConnection(erfurtToRegensburg);
        reichMap.get(REGENSBURG).addConnection(erfurtToRegensburg);

// ERFURT to CHEMNITZ
        Connection erfurtToChemnitz = new Connection(reichMap.get(ERFURT), reichMap.get(CHEMNITZ), 3, Colors.BLACK);
        reichMap.get(ERFURT).addConnection(erfurtToChemnitz);
        reichMap.get(CHEMNITZ).addConnection(erfurtToChemnitz);

// DRESDEN to CHEMNITZ
        Connection dresdenToChemnitz = new Connection(reichMap.get(DRESDEN), reichMap.get(CHEMNITZ), 1, Colors.YELLOW);
        reichMap.get(DRESDEN).addConnection(dresdenToChemnitz);
        reichMap.get(CHEMNITZ).addConnection(dresdenToChemnitz);

// DRESDEN to REGENSBURG
        Connection dresdenToRegensburg = new Connection(reichMap.get(DRESDEN), reichMap.get(REGENSBURG), 7, Colors.RED);
        reichMap.get(DRESDEN).addConnection(dresdenToRegensburg);
        reichMap.get(REGENSBURG).addConnection(dresdenToRegensburg);

// CHEMNITZ to REGENSBURG
        Connection chemnitzToRegensburg = new Connection(reichMap.get(CHEMNITZ), reichMap.get(REGENSBURG), 6, Colors.PINK);
        reichMap.get(CHEMNITZ).addConnection(chemnitzToRegensburg);
        reichMap.get(REGENSBURG).addConnection(chemnitzToRegensburg);

// KOBLENZ to MAINZ
        Connection koblenzToMainz = new Connection(reichMap.get(KOBLENZ), reichMap.get(MAINZ), 2, Colors.GRAY);
        reichMap.get(KOBLENZ).addConnection(koblenzToMainz);
        reichMap.get(MAINZ).addConnection(koblenzToMainz);
        koblenzToMainz = new Connection(reichMap.get(KOBLENZ), reichMap.get(MAINZ), 2, Colors.GRAY);
        reichMap.get(KOBLENZ).addConnection(koblenzToMainz);
        reichMap.get(MAINZ).addConnection(koblenzToMainz);

// KOBLENZ to SAARBRUCKEN
        Connection koblenzToSaarbrucken = new Connection(reichMap.get(KOBLENZ), reichMap.get(SAARBRUCKEN), 3, Colors.GRAY);
        reichMap.get(KOBLENZ).addConnection(koblenzToSaarbrucken);
        reichMap.get(SAARBRUCKEN).addConnection(koblenzToSaarbrucken);

// FRANKFURT to MAINZ
        Connection frankfurtToMainz = new Connection(reichMap.get(FRANKFURT), reichMap.get(MAINZ), 1, Colors.WHITE);
        reichMap.get(FRANKFURT).addConnection(frankfurtToMainz);
        reichMap.get(MAINZ).addConnection(frankfurtToMainz);
        frankfurtToMainz = new Connection(reichMap.get(FRANKFURT), reichMap.get(MAINZ), 1, Colors.GRAY);
        reichMap.get(FRANKFURT).addConnection(frankfurtToMainz);
        reichMap.get(MAINZ).addConnection(frankfurtToMainz);

// FRANKFURT to MANNHEIM
        Connection frankfurtToMannheim = new Connection(reichMap.get(FRANKFURT), reichMap.get(MANNHEIM), 2, Colors.GRAY);
        reichMap.get(FRANKFURT).addConnection(frankfurtToMannheim);
        reichMap.get(MANNHEIM).addConnection(frankfurtToMannheim);
        frankfurtToMannheim = new Connection(reichMap.get(FRANKFURT), reichMap.get(MANNHEIM), 2, Colors.GRAY);
        reichMap.get(FRANKFURT).addConnection(frankfurtToMannheim);
        reichMap.get(MANNHEIM).addConnection(frankfurtToMannheim);

// FRANKFURT to WURZBURG
        Connection frankfurtToWurzburg = new Connection(reichMap.get(FRANKFURT), reichMap.get(WURZBURG), 2, Colors.GRAY);
        reichMap.get(FRANKFURT).addConnection(frankfurtToWurzburg);
        reichMap.get(WURZBURG).addConnection(frankfurtToWurzburg);
        frankfurtToWurzburg = new Connection(reichMap.get(FRANKFURT), reichMap.get(WURZBURG), 2, Colors.BLACK);
        reichMap.get(FRANKFURT).addConnection(frankfurtToWurzburg);
        reichMap.get(WURZBURG).addConnection(frankfurtToWurzburg);
// MAINZ to SAARBRUCKEN
        Connection mainzToSaarbrucken = new Connection(reichMap.get(MAINZ), reichMap.get(SAARBRUCKEN), 3, Colors.GRAY);
        reichMap.get(MAINZ).addConnection(mainzToSaarbrucken);
        reichMap.get(SAARBRUCKEN).addConnection(mainzToSaarbrucken);

// MAINZ to MANNHEIM
        Connection mainzToMannheim = new Connection(reichMap.get(MAINZ), reichMap.get(MANNHEIM), 1, Colors.GRAY);
        reichMap.get(MAINZ).addConnection(mainzToMannheim);
        reichMap.get(MANNHEIM).addConnection(mainzToMannheim);
        mainzToMannheim = new Connection(reichMap.get(MAINZ), reichMap.get(MANNHEIM), 1,  Colors.YELLOW);
        reichMap.get(MAINZ).addConnection(mainzToMannheim);
        reichMap.get(MANNHEIM).addConnection(mainzToMannheim);

// WURZBURG to NURNBERG
        Connection wurzburgToNurnberg = new Connection(reichMap.get(WURZBURG), reichMap.get(NURNBERG), 2, Colors.GRAY);
        reichMap.get(WURZBURG).addConnection(wurzburgToNurnberg);
        reichMap.get(NURNBERG).addConnection(wurzburgToNurnberg);
        wurzburgToNurnberg = new Connection(reichMap.get(WURZBURG), reichMap.get(NURNBERG), 2, Colors.YELLOW);
        reichMap.get(WURZBURG).addConnection(wurzburgToNurnberg);
        reichMap.get(NURNBERG).addConnection(wurzburgToNurnberg);

// NURNBERG to AUGSBURG
        Connection nurnbergToAugsburg = new Connection(reichMap.get(NURNBERG), reichMap.get(AUGSBURG), 4, Colors.BLACK);
        reichMap.get(NURNBERG).addConnection(nurnbergToAugsburg);
        reichMap.get(AUGSBURG).addConnection(nurnbergToAugsburg);

// NURNBERG to MUNCHEN
        Connection nurnbergToMunchen = new Connection(reichMap.get(NURNBERG), reichMap.get(MUNCHEN), 5, Colors.BLUE);
        reichMap.get(NURNBERG).addConnection(nurnbergToMunchen);
        reichMap.get(MUNCHEN).addConnection(nurnbergToMunchen);
        nurnbergToMunchen = new Connection(reichMap.get(NURNBERG), reichMap.get(MUNCHEN), 5, Colors.GRAY);
        reichMap.get(NURNBERG).addConnection(nurnbergToMunchen);
        reichMap.get(MUNCHEN).addConnection(nurnbergToMunchen);

// NURNBERG to REGENSBURG
        Connection nurnbergToRegensburg = new Connection(reichMap.get(NURNBERG), reichMap.get(REGENSBURG), 3, Colors.GREEN);
        reichMap.get(NURNBERG).addConnection(nurnbergToRegensburg);
        reichMap.get(REGENSBURG).addConnection(nurnbergToRegensburg);

// MANNHEIM to SAARBRUCKEN
        Connection mannheimToSaarbrucken = new Connection(reichMap.get(MANNHEIM), reichMap.get(SAARBRUCKEN), 3, Colors.GRAY);
        reichMap.get(MANNHEIM).addConnection(mannheimToSaarbrucken);
        reichMap.get(SAARBRUCKEN).addConnection(mannheimToSaarbrucken);

// MANNHEIM to KARLSRUHE
        Connection mannheimToKarlsruhe = new Connection(reichMap.get(MANNHEIM), reichMap.get(KARLSRUHE), 1, Colors.BLUE);
        reichMap.get(MANNHEIM).addConnection(mannheimToKarlsruhe);
        reichMap.get(KARLSRUHE).addConnection(mannheimToKarlsruhe);

// MANNHEIM to STUTTGART
        Connection mannheimToStuttgart = new Connection(reichMap.get(MANNHEIM), reichMap.get(STUTTGART), 2, Colors.GRAY);
        reichMap.get(MANNHEIM).addConnection(mannheimToStuttgart);
        reichMap.get(STUTTGART).addConnection(mannheimToStuttgart);
        mannheimToStuttgart = new Connection(reichMap.get(MANNHEIM), reichMap.get(STUTTGART), 2, Colors.GRAY);
        reichMap.get(MANNHEIM).addConnection(mannheimToStuttgart);
        reichMap.get(STUTTGART).addConnection(mannheimToStuttgart);

// SAARBRUCKEN to FRANKREICH
        Connection saarbruckenToFrankreich = new Connection(reichMap.get(SAARBRUCKEN), reichMap.get(FRANKREICH), 1, Colors.GREEN);
        reichMap.get(SAARBRUCKEN).addConnection(saarbruckenToFrankreich);
        reichMap.get(FRANKREICH).addConnection(saarbruckenToFrankreich);

// SAARBRUCKEN to KARLSRUHE
        Connection saarbruckenToKarlsruhe = new Connection(reichMap.get(SAARBRUCKEN), reichMap.get(KARLSRUHE), 3, Colors.BLACK);
        reichMap.get(SAARBRUCKEN).addConnection(saarbruckenToKarlsruhe);
        reichMap.get(KARLSRUHE).addConnection(saarbruckenToKarlsruhe);

// KARLSRUHE to FRANKREICH
        Connection karlsruheToFrankreich = new Connection(reichMap.get(KARLSRUHE), reichMap.get(FRANKREICH), 2, Colors.BLACK);
        reichMap.get(KARLSRUHE).addConnection(karlsruheToFrankreich);
        reichMap.get(FRANKREICH).addConnection(karlsruheToFrankreich);

// KARLSRUHE to FREIBURG
        Connection karlsruheToFreiburg = new Connection(reichMap.get(KARLSRUHE), reichMap.get(FREIBURG), 3, Colors.WHITE);
        reichMap.get(KARLSRUHE).addConnection(karlsruheToFreiburg);
        reichMap.get(FREIBURG).addConnection(karlsruheToFreiburg);

// KARLSRUHE to STUTTGART
        Connection karlsruheToStuttgart = new Connection(reichMap.get(KARLSRUHE), reichMap.get(STUTTGART), 1, Colors.PINK);
        reichMap.get(KARLSRUHE).addConnection(karlsruheToStuttgart);
        reichMap.get(STUTTGART).addConnection(karlsruheToStuttgart);


        // STUTTGART to FREIBURG
        Connection stuttgartToFreiburg = new Connection(reichMap.get(STUTTGART), reichMap.get(FREIBURG), 3, Colors.RED);
        reichMap.get(STUTTGART).addConnection(stuttgartToFreiburg);
        reichMap.get(FREIBURG).addConnection(stuttgartToFreiburg);

// STUTTGART to KONSTANZ
        Connection stuttgartToKonstanz = new Connection(reichMap.get(STUTTGART), reichMap.get(KONSTANZ), 3, Colors.GREEN);
        reichMap.get(STUTTGART).addConnection(stuttgartToKonstanz);
        reichMap.get(KONSTANZ).addConnection(stuttgartToKonstanz);

// STUTTGART to ULM
        Connection stuttgartToUlm = new Connection(reichMap.get(STUTTGART), reichMap.get(ULM), 2, Colors.GRAY);
        reichMap.get(STUTTGART).addConnection(stuttgartToUlm);
        reichMap.get(ULM).addConnection(stuttgartToUlm);
        stuttgartToUlm = new Connection(reichMap.get(STUTTGART), reichMap.get(ULM), 2, Colors.GRAY);
        reichMap.get(STUTTGART).addConnection(stuttgartToUlm);
        reichMap.get(ULM).addConnection(stuttgartToUlm);

// REGENSBURG to MUNCHEN
        Connection regensburgToMunchen = new Connection(reichMap.get(REGENSBURG), reichMap.get(MUNCHEN), 3, Colors.ORANGE);
        reichMap.get(REGENSBURG).addConnection(regensburgToMunchen);
        reichMap.get(MUNCHEN).addConnection(regensburgToMunchen);

// REGENSBURG to OSTERREICH
        Connection regensburgToOsterreich = new Connection(reichMap.get(REGENSBURG), reichMap.get(OSTERREICH), 4, Colors.YELLOW);
        reichMap.get(REGENSBURG).addConnection(regensburgToOsterreich);
        reichMap.get(OSTERREICH).addConnection(regensburgToOsterreich);

// FRANKREICH to FREIBURG
        Connection frankreichToFreiburg = new Connection(reichMap.get(FRANKREICH), reichMap.get(FREIBURG), 2, Colors.YELLOW);
        reichMap.get(FRANKREICH).addConnection(frankreichToFreiburg);
        reichMap.get(FREIBURG).addConnection(frankreichToFreiburg);

// ULM to AUGSBURG
        Connection ulmToAugsburg = new Connection(reichMap.get(ULM), reichMap.get(AUGSBURG), 1, Colors.GRAY);
        reichMap.get(ULM).addConnection(ulmToAugsburg);
        reichMap.get(AUGSBURG).addConnection(ulmToAugsburg);
        ulmToAugsburg = new Connection(reichMap.get(ULM), reichMap.get(AUGSBURG), 1, Colors.GRAY);
        reichMap.get(ULM).addConnection(ulmToAugsburg);
        reichMap.get(AUGSBURG).addConnection(ulmToAugsburg);

// ULM to LINDAU
        Connection ulmToLindau = new Connection(reichMap.get(ULM), reichMap.get(LINDAU), 2, Colors.RED);
        reichMap.get(ULM).addConnection(ulmToLindau);
        reichMap.get(LINDAU).addConnection(ulmToLindau);

// AUGSBURG to MUNCHEN
        Connection augsburgToMunchen = new Connection(reichMap.get(AUGSBURG), reichMap.get(MUNCHEN), 2, Colors.WHITE);
        reichMap.get(AUGSBURG).addConnection(augsburgToMunchen);
        reichMap.get(MUNCHEN).addConnection(augsburgToMunchen);
        augsburgToMunchen = new Connection(reichMap.get(AUGSBURG), reichMap.get(MUNCHEN), 2, Colors.GRAY);
        reichMap.get(AUGSBURG).addConnection(augsburgToMunchen);
        reichMap.get(MUNCHEN).addConnection(augsburgToMunchen);

// MUNCHEN to OSTERREICH
        Connection munchenToOsterreich = new Connection(reichMap.get(MUNCHEN), reichMap.get(OSTERREICH), 3, Colors.RED);
        reichMap.get(MUNCHEN).addConnection(munchenToOsterreich);
        reichMap.get(OSTERREICH).addConnection(munchenToOsterreich);

// MUNCHEN to LINDAU
        Connection munchenToLindau = new Connection(reichMap.get(MUNCHEN), reichMap.get(LINDAU), 5, Colors.GRAY);
        reichMap.get(MUNCHEN).addConnection(munchenToLindau);
        reichMap.get(LINDAU).addConnection(munchenToLindau);

// FREIBURG to SCHWEIZ
        Connection freiburgToSchweiz = new Connection(reichMap.get(FREIBURG), reichMap.get(SCHWEIZ), 2, Colors.ORANGE);
        reichMap.get(FREIBURG).addConnection(freiburgToSchweiz);
        reichMap.get(SCHWEIZ).addConnection(freiburgToSchweiz);

// FREIBURG to KONSTANZ
        Connection freiburgToKonstanz = new Connection(reichMap.get(FREIBURG), reichMap.get(KONSTANZ), 2, Colors.BLACK);
        reichMap.get(FREIBURG).addConnection(freiburgToKonstanz);
        reichMap.get(KONSTANZ).addConnection(freiburgToKonstanz);

// KONSTANZ to SCHWEIZ
        Connection konstanzToSchweiz = new Connection(reichMap.get(KONSTANZ), reichMap.get(SCHWEIZ), 1, Colors.RED);
        reichMap.get(KONSTANZ).addConnection(konstanzToSchweiz);
        reichMap.get(SCHWEIZ).addConnection(konstanzToSchweiz);

// KONSTANZ to LINDAU
        Connection konstanzToLindau = new Connection(reichMap.get(KONSTANZ), reichMap.get(LINDAU), 1, Colors.YELLOW);
        reichMap.get(KONSTANZ).addConnection(konstanzToLindau);
        reichMap.get(LINDAU).addConnection(konstanzToLindau);

// LINDAU to SCHWEIZ
        Connection lindauToSchweiz = new Connection(reichMap.get(LINDAU), reichMap.get(SCHWEIZ), 2, Colors.BLUE);
        reichMap.get(LINDAU).addConnection(lindauToSchweiz);
        reichMap.get(SCHWEIZ).addConnection(lindauToSchweiz);

// LINDAU to OSTERREICH
        Connection lindauToOsterreich = new Connection(reichMap.get(LINDAU), reichMap.get(OSTERREICH), 2, Colors.PINK);
        reichMap.get(LINDAU).addConnection(lindauToOsterreich);
        reichMap.get(OSTERREICH).addConnection(lindauToOsterreich);

        return reichMap;
    }

    /**
     * Calculate the number of connection between two cities
     * @param cityOne the first city
     * @param cityTwo the second city
     * @return a int for the number of connections
     */
    public int countConnectionsBetweenCities(City cityOne, City cityTwo){
        int count = 0;
        List<Connection> connections = cities.get(cityOne.getName()).getConnectionList();
        for(Connection connection : connections){
            if(connection.getCityTwo().equals(cityTwo)){
                count++;
            }
        }
        return count;
    }

    /**
     * Give the connection between two neighbour cities
     * @param cityOne the first one
     * @param cityTwo the second
     * @return the connection
     */
    public Connection getNeighbourConnection(City cityOne, City cityTwo) {
        List<Connection> connections = cityOne.getConnectionList();
        for(Connection connection : connections) {
            if(connection.getOtherCity(cityOne) == cityTwo) {
                return connection;
            }
        }
        return null;
    }

    /**
     * Get a city from is name
     * @param cityName the name of the city
     * @return the city
     */
    public City getCity(String cityName)
    {
        return cities.get(cityName);
    }

    /**
     * Give all the connection of a city from it name
     * @param cityName the name of the city
     * @return all the connection
     */
    public List<Connection> getCitiesConnections(String cityName){
        City inputCity = cities.get(cityName);
        return inputCity.getConnectionList();
    }

    private HashMap<String,City> buildTestMap() {
        HashMap<String, City> testMap = new HashMap<String, City>();

        testMap.put(DANEMARK, new City(DANEMARK, 1, true));
        testMap.put(KIEL, new City(KIEL, 1));
        testMap.put(ROSTOCK, new City(ROSTOCK, 1));

        testMap.put(MUNCHEN, new City(MUNCHEN, 1));


        Connection freiburgToSchweiz = new Connection(testMap.get(DANEMARK), testMap.get(KIEL), 1,Colors.ORANGE);
        testMap.get(DANEMARK).addConnection(freiburgToSchweiz);
        testMap.get(KIEL).addConnection(freiburgToSchweiz);

        Connection freiburgToKonstanz = new Connection(testMap.get(KIEL), testMap.get(ROSTOCK), 1, Colors.BLACK);
        testMap.get(KIEL).addConnection(freiburgToKonstanz);
        testMap.get(ROSTOCK).addConnection(freiburgToKonstanz);

        return testMap;
    }
}
