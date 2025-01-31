package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.connection.Connection;

import java.util.HashMap;
import java.util.List;

public class Board {
    private HashMap<String, City> cities;
    public Board(String name) {
        if (name.equals("Reich"))
        {
            cities = buildReichMap();
        }
    }

    public HashMap<String, City> getCity()
    {
        return cities;
    }

    private HashMap buildReichMap()
    {
        HashMap<String, City> reichMap = new HashMap<String, City>();

        reichMap.put("Danemark", new City("Danemark", 1, true));
        reichMap.put("Kiel", new City("Kiel", 1));
        reichMap.put("Rostock", new City("Rostock", 1));
        reichMap.put("Emden", new City("Emden", 1));
        reichMap.put("Bremerhaven", new City("Bremerhaven", 1));
        reichMap.put("Hamburg", new City("Hamburg", 4));
        reichMap.put("Schwerin", new City("Schwerin", 1));
        reichMap.put("Munster", new City("Munster", 1));
        reichMap.put("Hannover", new City("Hannover", 1));
        reichMap.put("Berlin", new City("Berlin", 5));
        reichMap.put("Dusseldorf", new City("Dusseldorf", 1));
        reichMap.put("Dortmund", new City("Dortmund", 1));
        reichMap.put("Kassel", new City("Kassel", 1));
        reichMap.put("Magdeburg", new City("Magdeburg", 1));
        reichMap.put("Leipzig", new City("Leipzig", 3));
        reichMap.put("Erfurt", new City("Erfurt", 1));
        reichMap.put("Dresden", new City("Dresden", 1));
        reichMap.put("Chemnitz", new City("Chemnitz", 1));
        reichMap.put("Koln", new City("Koln", 4));
        reichMap.put("Koblenz", new City("Koblenz", 1));
        reichMap.put("Frankfurt", new City("Frankfurt", 4));
        reichMap.put("Mainz", new City("Mainz", 1));
        reichMap.put("Wurzburg", new City("Wurzburg", 1));
        reichMap.put("Nurnberg", new City("Nurnberg", 1));
        reichMap.put("Regensburg", new City("Regensburg", 1));
        reichMap.put("Munchen", new City("Munchen", 4));
        reichMap.put("Augsburg", new City("Augsburg", 1));
        reichMap.put("Ulm", new City("Ulm", 1));
        reichMap.put("Stuttgart", new City("Stuttgart", 3));
        reichMap.put("Karlsruhe", new City("Karlsruhe", 1));
        reichMap.put("Mannheim", new City("Mannheim", 1));
        reichMap.put("Saarbrucken", new City("Saarbrucken", 1));
        reichMap.put("Freiburg", new City("Freiburg", 1));
        reichMap.put("Konstanz", new City("Konstanz", 1));
        reichMap.put("Lindau", new City("Lindau", 1));
        reichMap.put("Bremen", new City("Bremen", 1));
        reichMap.put("Frankreich", new City("Frankreich", 1, true));
        reichMap.put("Schweiz", new City("Schweiz", 1, true));
        reichMap.put("Niederlande", new City("Niederlande", 1, true));
        reichMap.put("Osterreich", new City("Osterreich", 1, true));

// DANEMARK to BREMERHAVEN
        Connection danemarkToBremerhaven = new Connection(reichMap.get("Danemark"), reichMap.get("Bremerhaven"), 5, Colors.BLACK);
        reichMap.get("Danemark").addConnection(danemarkToBremerhaven);
        reichMap.get("Bremerhaven").addConnection(danemarkToBremerhaven);

// DANEMARK to KIEL
        Connection danemarkToKiel = new Connection(reichMap.get("Danemark"), reichMap.get("Kiel"), 2, Colors.GRAY);
        reichMap.get("Danemark").addConnection(danemarkToKiel);
        reichMap.get("Kiel").addConnection(danemarkToKiel);

// KIEL to BREMERHAVEN
        Connection kielToBremerhaven = new Connection(reichMap.get("Kiel"), reichMap.get("Bremerhaven"), 3, Colors.GRAY);
        reichMap.get("Kiel").addConnection(kielToBremerhaven);
        reichMap.get("Bremerhaven").addConnection(kielToBremerhaven);
// KIEL TO HAMBURG
        Connection kielToHamburg = new Connection(reichMap.get("Kiel"), reichMap.get("Hamburg"), 2, Colors.BLACK);
        reichMap.get("Kiel").addConnection(kielToHamburg);
        reichMap.get("Hamburg").addConnection(kielToHamburg);
        kielToHamburg = new Connection(reichMap.get("Kiel"), reichMap.get("Hamburg"), 2, Colors.PINK);
        reichMap.get("Kiel").addConnection(kielToHamburg);
        reichMap.get("Hamburg").addConnection(kielToHamburg);
// KIEL TO SCHWERIN
        Connection kielToSchwerin = new Connection(reichMap.get("Kiel"), reichMap.get("Schwerin"), 3, Colors.YELLOW);
        reichMap.get("Kiel").addConnection(kielToSchwerin);
        reichMap.get("Schwerin").addConnection(kielToSchwerin);

        Connection kielToRostock = new Connection(reichMap.get("Kiel"), reichMap.get("Rostock"), 4, Colors.ORANGE);
        reichMap.get("Kiel").addConnection(kielToRostock);
        reichMap.get("Rostock").addConnection(kielToRostock);

        Connection rostockToSchwerin = new Connection(reichMap.get("Rostock"), reichMap.get("Schwerin"), 2, Colors.RED);
        reichMap.get("Rostock").addConnection(rostockToSchwerin);
        reichMap.get("Schwerin").addConnection(rostockToSchwerin);

        Connection rostockToBerlin = new Connection(reichMap.get("Rostock"), reichMap.get("Berlin"), 6, Colors.PINK);
        reichMap.get("Rostock").addConnection(rostockToBerlin);
        reichMap.get("Berlin").addConnection(rostockToBerlin);

        Connection schwerinToBerlin = new Connection(reichMap.get("Schwerin"), reichMap.get("Berlin"), 5, Colors.WHITE);
        reichMap.get("Schwerin").addConnection(schwerinToBerlin);
        reichMap.get("Berlin").addConnection(schwerinToBerlin);

        Connection schwerinToHamburg = new Connection(reichMap.get("Schwerin"), reichMap.get("Hamburg"), 2, Colors.RED);
        reichMap.get("Schwerin").addConnection(schwerinToHamburg);
        reichMap.get("Hamburg").addConnection(schwerinToHamburg);

        Connection hamburgToBerlin = new Connection(reichMap.get("Hamburg"), reichMap.get("Berlin"), 7, Colors.BLUE);
        reichMap.get("Hamburg").addConnection(hamburgToBerlin);
        reichMap.get("Berlin").addConnection(hamburgToBerlin);
        hamburgToBerlin = new Connection(reichMap.get("Hamburg"), reichMap.get("Berlin"), 7, Colors.YELLOW);
        reichMap.get("Hamburg").addConnection(hamburgToBerlin);
        reichMap.get("Berlin").addConnection(hamburgToBerlin);

        Connection hamburgToHannover = new Connection(reichMap.get("Hamburg"), reichMap.get("Hannover"), 4, Colors.RED);
        reichMap.get("Hamburg").addConnection(hamburgToHannover);
        reichMap.get("Hannover").addConnection(hamburgToHannover);
        hamburgToHannover = new Connection(reichMap.get("Hamburg"), reichMap.get("Hannover"), 4, Colors.WHITE);
        reichMap.get("Hamburg").addConnection(hamburgToHannover);
        reichMap.get("Hannover").addConnection(hamburgToHannover);

        Connection hamburgToBremerhaven = new Connection(reichMap.get("Hamburg"), reichMap.get("Bremerhaven"), 3, Colors.GRAY);
        reichMap.get("Hamburg").addConnection(hamburgToBremerhaven);
        reichMap.get("Bremerhaven").addConnection(hamburgToBremerhaven);

        Connection hamburgToBremen = new Connection(reichMap.get("Hamburg"), reichMap.get("Bremen"), 3, Colors.ORANGE);
        reichMap.get("Hamburg").addConnection(hamburgToBremen);
        reichMap.get("Bremen").addConnection(hamburgToBremen);

        Connection bremerhavenToBremen = new Connection(reichMap.get("Bremerhaven"), reichMap.get("Bremen"), 1, Colors.WHITE);
        reichMap.get("Bremerhaven").addConnection(bremerhavenToBremen);
        reichMap.get("Bremen").addConnection(bremerhavenToBremen);

        Connection bremerhavenToEmden = new Connection(reichMap.get("Bremerhaven"), reichMap.get("Emden"), 3, Colors.GRAY);
        reichMap.get("Bremerhaven").addConnection(bremerhavenToEmden);
        reichMap.get("Emden").addConnection(bremerhavenToEmden);

        Connection emdenToNiederlande = new Connection(reichMap.get("Emden"), reichMap.get("Niederlande"), 2, Colors.WHITE);
        reichMap.get("Emden").addConnection(emdenToNiederlande);
        reichMap.get("Niederlande").addConnection(emdenToNiederlande);

        Connection emdenToMunster = new Connection(reichMap.get("Emden"), reichMap.get("Munster"), 4, Colors.RED);
        reichMap.get("Emden").addConnection(emdenToMunster);
        reichMap.get("Munster").addConnection(emdenToMunster);

        Connection emdenToBremen = new Connection(reichMap.get("Emden"), reichMap.get("Bremen"), 3, Colors.BLUE);
        reichMap.get("Emden").addConnection(emdenToBremen);
        reichMap.get("Bremen").addConnection(emdenToBremen);

        Connection bremenToHannover = new Connection(reichMap.get("Bremen"), reichMap.get("Hannover"), 3, Colors.PINK);
        reichMap.get("Bremen").addConnection(bremenToHannover);
        reichMap.get("Hannover").addConnection(bremenToHannover);

        Connection bremenToMunster = new Connection(reichMap.get("Bremen"), reichMap.get("Munster"), 3, Colors.BLACK);
        reichMap.get("Bremen").addConnection(bremenToMunster);
        reichMap.get("Munster").addConnection(bremenToMunster);

        Connection niederlandeToMunster = new Connection(reichMap.get("Niederlande"), reichMap.get("Munster"), 2, Colors.ORANGE);
        reichMap.get("Niederlande").addConnection(niederlandeToMunster);
        reichMap.get("Munster").addConnection(niederlandeToMunster);

        Connection niederlandeToDusseldorf = new Connection(reichMap.get("Niederlande"), reichMap.get("Dusseldorf"), 3, Colors.PINK);
        reichMap.get("Niederlande").addConnection(niederlandeToDusseldorf);
        reichMap.get("Dusseldorf").addConnection(niederlandeToDusseldorf);

        Connection berlinToHannover = new Connection(reichMap.get("Berlin"), reichMap.get("Hannover"), 7, Colors.YELLOW);
        reichMap.get("Berlin").addConnection(berlinToHannover);
        reichMap.get("Hannover").addConnection(berlinToHannover);

        Connection berlinToMagdeburg = new Connection(reichMap.get("Berlin"), reichMap.get("Magdeburg"), 3, Colors.RED);
        reichMap.get("Berlin").addConnection(berlinToMagdeburg);
        reichMap.get("Magdeburg").addConnection(berlinToMagdeburg);

        Connection berlinToLeipzig = new Connection(reichMap.get("Berlin"), reichMap.get("Leipzig"), 4, Colors.PINK);
        reichMap.get("Berlin").addConnection(berlinToLeipzig);
        reichMap.get("Leipzig").addConnection(berlinToLeipzig);

        Connection berlinToDresden = new Connection(reichMap.get("Berlin"), reichMap.get("Dresden"), 5, Colors.GREEN);
        reichMap.get("Berlin").addConnection(berlinToDresden);
        reichMap.get("Dresden").addConnection(berlinToDresden);

        Connection hannoverToMagdeburg = new Connection(reichMap.get("Hannover"), reichMap.get("Magdeburg"), 4, Colors.BLUE);
        reichMap.get("Hannover").addConnection(hannoverToMagdeburg);
        reichMap.get("Magdeburg").addConnection(hannoverToMagdeburg);

        Connection hannoverToErfurt = new Connection(reichMap.get("Hannover"), reichMap.get("Erfurt"), 5, Colors.ORANGE);
        reichMap.get("Hannover").addConnection(hannoverToErfurt);
        reichMap.get("Erfurt").addConnection(hannoverToErfurt);
        hannoverToErfurt = new Connection(reichMap.get("Hannover"), reichMap.get("Erfurt"), 5, Colors.GREEN);
        reichMap.get("Hannover").addConnection(hannoverToErfurt);
        reichMap.get("Erfurt").addConnection(hannoverToErfurt);

        Connection hannoverToKassel = new Connection(reichMap.get("Hannover"), reichMap.get("Kassel"), 3, Colors.GRAY);
        reichMap.get("Hannover").addConnection(hannoverToKassel);
        reichMap.get("Kassel").addConnection(hannoverToKassel);
        hannoverToKassel = new Connection(reichMap.get("Hannover"), reichMap.get("Kassel"), 3, Colors.GRAY);
        reichMap.get("Hannover").addConnection(hannoverToKassel);
        reichMap.get("Kassel").addConnection(hannoverToKassel);

        Connection hannoverToMunster = new Connection(reichMap.get("Hannover"), reichMap.get("Munster"), 4, Colors.YELLOW);
        reichMap.get("Hannover").addConnection(hannoverToMunster);
        reichMap.get("Munster").addConnection(hannoverToMunster);

        Connection munsterToDortmund = new Connection(reichMap.get("Munster"), reichMap.get("Dortmund"), 1, Colors.GRAY);
        reichMap.get("Munster").addConnection(munsterToDortmund);
        reichMap.get("Dortmund").addConnection(munsterToDortmund);
        munsterToDortmund = new Connection(reichMap.get("Munster"), reichMap.get("Dortmund"), 1, Colors.GRAY);
        reichMap.get("Munster").addConnection(munsterToDortmund);
        reichMap.get("Dortmund").addConnection(munsterToDortmund);

// MAGDEBURG to LEIPZIG
        Connection magdeburgToLeipzig = new Connection(reichMap.get("Magdeburg"), reichMap.get("Leipzig"), 2, Colors.YELLOW);
        reichMap.get("Magdeburg").addConnection(magdeburgToLeipzig);
        reichMap.get("Leipzig").addConnection(magdeburgToLeipzig);

// DORTMUND to DUSSELDORF
        Connection dortmundToDusseldorf = new Connection(reichMap.get("Dortmund"), reichMap.get("Dusseldorf"), 1, Colors.GRAY);
        reichMap.get("Dortmund").addConnection(dortmundToDusseldorf);
        reichMap.get("Dusseldorf").addConnection(dortmundToDusseldorf);
        dortmundToDusseldorf = new Connection(reichMap.get("Dortmund"), reichMap.get("Dusseldorf"), 1, Colors.GRAY);
        reichMap.get("Dortmund").addConnection(dortmundToDusseldorf);
        reichMap.get("Dusseldorf").addConnection(dortmundToDusseldorf);
        dortmundToDusseldorf = new Connection(reichMap.get("Dortmund"), reichMap.get("Dusseldorf"), 1, Colors.GRAY);
        reichMap.get("Dortmund").addConnection(dortmundToDusseldorf);
        reichMap.get("Dusseldorf").addConnection(dortmundToDusseldorf);

// DORTMUND to KASSEL
        Connection dortmundToKassel = new Connection(reichMap.get("Dortmund"), reichMap.get("Kassel"), 4, Colors.GREEN);
        reichMap.get("Dortmund").addConnection(dortmundToKassel);
        reichMap.get("Kassel").addConnection(dortmundToKassel);

// DUSSELDORF to KOLN
        Connection dusseldorfToKoln = new Connection(reichMap.get("Dusseldorf"), reichMap.get("Koln"), 1, Colors.GRAY);
        reichMap.get("Dusseldorf").addConnection(dusseldorfToKoln);
        reichMap.get("Koln").addConnection(dusseldorfToKoln);
        dusseldorfToKoln = new Connection(reichMap.get("Dusseldorf"), reichMap.get("Koln"), 1, Colors.GRAY);
        reichMap.get("Dusseldorf").addConnection(dusseldorfToKoln);
        reichMap.get("Koln").addConnection(dusseldorfToKoln);dusseldorfToKoln = new Connection(reichMap.get("Dusseldorf"), reichMap.get("Koln"), 1, Colors.GRAY);
        reichMap.get("Dusseldorf").addConnection(dusseldorfToKoln);
        reichMap.get("Koln").addConnection(dusseldorfToKoln);

// LEIPZIG to DRESDEN
        Connection leipzigToDresden = new Connection(reichMap.get("Leipzig"), reichMap.get("Dresden"), 3, Colors.BLACK);
        reichMap.get("Leipzig").addConnection(leipzigToDresden);
        reichMap.get("Dresden").addConnection(leipzigToDresden);

// LEIPZIG to CHEMNITZ
        Connection leipzigToChemnitz = new Connection(reichMap.get("Leipzig"), reichMap.get("Chemnitz"), 2, Colors.BLUE);
        reichMap.get("Leipzig").addConnection(leipzigToChemnitz);
        reichMap.get("Chemnitz").addConnection(leipzigToChemnitz);

// LEIPZIG to ERFURT
        Connection leipzigToErfurt = new Connection(reichMap.get("Leipzig"), reichMap.get("Erfurt"), 3, Colors.YELLOW);
        reichMap.get("Leipzig").addConnection(leipzigToErfurt);
        reichMap.get("Erfurt").addConnection(leipzigToErfurt);

// KASSEL to ERFURT
        Connection kasselToErfurt = new Connection(reichMap.get("Kassel"), reichMap.get("Erfurt"), 3, Colors.GRAY);
        reichMap.get("Kassel").addConnection(kasselToErfurt);
        reichMap.get("Erfurt").addConnection(kasselToErfurt);

// KASSEL to FRANKFURT
        Connection kasselToFrankfurt = new Connection(reichMap.get("Kassel"), reichMap.get("Frankfurt"), 4, Colors.BLUE);
        reichMap.get("Kassel").addConnection(kasselToFrankfurt);
        reichMap.get("Frankfurt").addConnection(kasselToFrankfurt);
        kasselToFrankfurt = new Connection(reichMap.get("Kassel"), reichMap.get("Frankfurt"), 4, Colors.GRAY);
        reichMap.get("Kassel").addConnection(kasselToFrankfurt);
        reichMap.get("Frankfurt").addConnection(kasselToFrankfurt);

// KOLN to FRANKFURT
        Connection kolnToFrankfurt = new Connection(reichMap.get("Koln"), reichMap.get("Frankfurt"), 4, Colors.GRAY);
        reichMap.get("Koln").addConnection(kolnToFrankfurt);
        reichMap.get("Frankfurt").addConnection(kolnToFrankfurt);
        kolnToFrankfurt = new Connection(reichMap.get("Koln"), reichMap.get("Frankfurt"), 4, Colors.WHITE);
        reichMap.get("Koln").addConnection(kolnToFrankfurt);
        reichMap.get("Frankfurt").addConnection(kolnToFrankfurt);

// KOLN to KOBLENZ
        Connection kolnToKoblenz = new Connection(reichMap.get("Koln"), reichMap.get("Koblenz"), 1, Colors.GRAY);
        reichMap.get("Koln").addConnection(kolnToKoblenz);
        reichMap.get("Koblenz").addConnection(kolnToKoblenz);
        kolnToKoblenz = new Connection(reichMap.get("Koln"), reichMap.get("Koblenz"), 1, Colors.GRAY);
        reichMap.get("Koln").addConnection(kolnToKoblenz);
        reichMap.get("Koblenz").addConnection(kolnToKoblenz);

// ERFURT to NURNBERG
        Connection erfurtToNurnberg = new Connection(reichMap.get("Erfurt"), reichMap.get("Nurnberg"), 4, Colors.RED);
        reichMap.get("Erfurt").addConnection(erfurtToNurnberg);
        reichMap.get("Nurnberg").addConnection(erfurtToNurnberg);
        erfurtToNurnberg = new Connection(reichMap.get("Erfurt"), reichMap.get("Nurnberg"), 4, Colors.BLUE);
        reichMap.get("Erfurt").addConnection(erfurtToNurnberg);
        reichMap.get("Nurnberg").addConnection(erfurtToNurnberg);

// ERFURT to REGENSBURG
        Connection erfurtToRegensburg = new Connection(reichMap.get("Erfurt"), reichMap.get("Regensburg"), 7, Colors.WHITE);
        reichMap.get("Erfurt").addConnection(erfurtToRegensburg);
        reichMap.get("Regensburg").addConnection(erfurtToRegensburg);

// ERFURT to CHEMNITZ
        Connection erfurtToChemnitz = new Connection(reichMap.get("Erfurt"), reichMap.get("Chemnitz"), 3, Colors.BLACK);
        reichMap.get("Erfurt").addConnection(erfurtToChemnitz);
        reichMap.get("Chemnitz").addConnection(erfurtToChemnitz);

// DRESDEN to CHEMNITZ
        Connection dresdenToChemnitz = new Connection(reichMap.get("Dresden"), reichMap.get("Chemnitz"), 1, Colors.YELLOW);
        reichMap.get("Dresden").addConnection(dresdenToChemnitz);
        reichMap.get("Chemnitz").addConnection(dresdenToChemnitz);

// DRESDEN to REGENSBURG
        Connection dresdenToRegensburg = new Connection(reichMap.get("Dresden"), reichMap.get("Regensburg"), 7, Colors.RED);
        reichMap.get("Dresden").addConnection(dresdenToRegensburg);
        reichMap.get("Regensburg").addConnection(dresdenToRegensburg);

// CHEMNITZ to REGENSBURG
        Connection chemnitzToRegensburg = new Connection(reichMap.get("Chemnitz"), reichMap.get("Regensburg"), 6, Colors.PINK);
        reichMap.get("Chemnitz").addConnection(chemnitzToRegensburg);
        reichMap.get("Regensburg").addConnection(chemnitzToRegensburg);

// KOBLENZ to MAINZ
        Connection koblenzToMainz = new Connection(reichMap.get("Koblenz"), reichMap.get("Mainz"), 2, Colors.GRAY);
        reichMap.get("Koblenz").addConnection(koblenzToMainz);
        reichMap.get("Mainz").addConnection(koblenzToMainz);
        koblenzToMainz = new Connection(reichMap.get("Koblenz"), reichMap.get("Mainz"), 2, Colors.GRAY);
        reichMap.get("Koblenz").addConnection(koblenzToMainz);
        reichMap.get("Mainz").addConnection(koblenzToMainz);

// KOBLENZ to SAARBRUCKEN
        Connection koblenzToSaarbrucken = new Connection(reichMap.get("Koblenz"), reichMap.get("Saarbrucken"), 3, Colors.GRAY);
        reichMap.get("Koblenz").addConnection(koblenzToSaarbrucken);
        reichMap.get("Saarbrucken").addConnection(koblenzToSaarbrucken);

// FRANKFURT to MAINZ
        Connection frankfurtToMainz = new Connection(reichMap.get("Frankfurt"), reichMap.get("Mainz"), 1, Colors.WHITE);
        reichMap.get("Frankfurt").addConnection(frankfurtToMainz);
        reichMap.get("Mainz").addConnection(frankfurtToMainz);
        frankfurtToMainz = new Connection(reichMap.get("Frankfurt"), reichMap.get("Mainz"), 1, Colors.GRAY);
        reichMap.get("Frankfurt").addConnection(frankfurtToMainz);
        reichMap.get("Mainz").addConnection(frankfurtToMainz);

// FRANKFURT to MANNHEIM
        Connection frankfurtToMannheim = new Connection(reichMap.get("Frankfurt"), reichMap.get("Mannheim"), 2, Colors.GRAY);
        reichMap.get("Frankfurt").addConnection(frankfurtToMannheim);
        reichMap.get("Mannheim").addConnection(frankfurtToMannheim);
        frankfurtToMannheim = new Connection(reichMap.get("Frankfurt"), reichMap.get("Mannheim"), 2, Colors.GRAY);
        reichMap.get("Frankfurt").addConnection(frankfurtToMannheim);
        reichMap.get("Mannheim").addConnection(frankfurtToMannheim);

// FRANKFURT to WURZBURG
        Connection frankfurtToWurzburg = new Connection(reichMap.get("Frankfurt"), reichMap.get("Wurzburg"), 2, Colors.GRAY);
        reichMap.get("Frankfurt").addConnection(frankfurtToWurzburg);
        reichMap.get("Wurzburg").addConnection(frankfurtToWurzburg);
        frankfurtToWurzburg = new Connection(reichMap.get("Frankfurt"), reichMap.get("Wurzburg"), 2, Colors.BLACK);
        reichMap.get("Frankfurt").addConnection(frankfurtToWurzburg);
        reichMap.get("Wurzburg").addConnection(frankfurtToWurzburg);
// MAINZ to SAARBRUCKEN
        Connection mainzToSaarbrucken = new Connection(reichMap.get("Mainz"), reichMap.get("Saarbrucken"), 3, Colors.GRAY);
        reichMap.get("Mainz").addConnection(mainzToSaarbrucken);
        reichMap.get("Saarbrucken").addConnection(mainzToSaarbrucken);

// MAINZ to MANNHEIM
        Connection mainzToMannheim = new Connection(reichMap.get("Mainz"), reichMap.get("Mannheim"), 1, Colors.GRAY);
        reichMap.get("Mainz").addConnection(mainzToMannheim);
        reichMap.get("Mannheim").addConnection(mainzToMannheim);
        mainzToMannheim = new Connection(reichMap.get("Mainz"), reichMap.get("Mannheim"), 1,  Colors.YELLOW);
        reichMap.get("Mainz").addConnection(mainzToMannheim);
        reichMap.get("Mannheim").addConnection(mainzToMannheim);

// WURZBURG to NURNBERG
        Connection wurzburgToNurnberg = new Connection(reichMap.get("Wurzburg"), reichMap.get("Nurnberg"), 2, Colors.GRAY);
        reichMap.get("Wurzburg").addConnection(wurzburgToNurnberg);
        reichMap.get("Nurnberg").addConnection(wurzburgToNurnberg);
        wurzburgToNurnberg = new Connection(reichMap.get("Wurzburg"), reichMap.get("Nurnberg"), 2, Colors.YELLOW);
        reichMap.get("Wurzburg").addConnection(wurzburgToNurnberg);
        reichMap.get("Nurnberg").addConnection(wurzburgToNurnberg);

// NURNBERG to AUGSBURG
        Connection nurnbergToAugsburg = new Connection(reichMap.get("Nurnberg"), reichMap.get("Augsburg"), 4, Colors.BLACK);
        reichMap.get("Nurnberg").addConnection(nurnbergToAugsburg);
        reichMap.get("Augsburg").addConnection(nurnbergToAugsburg);

// NURNBERG to MUNCHEN
        Connection nurnbergToMunchen = new Connection(reichMap.get("Nurnberg"), reichMap.get("Munchen"), 5, Colors.BLUE);
        reichMap.get("Nurnberg").addConnection(nurnbergToMunchen);
        reichMap.get("Munchen").addConnection(nurnbergToMunchen);
        nurnbergToMunchen = new Connection(reichMap.get("Nurnberg"), reichMap.get("Munchen"), 5, Colors.GRAY);
        reichMap.get("Nurnberg").addConnection(nurnbergToMunchen);
        reichMap.get("Munchen").addConnection(nurnbergToMunchen);

// NURNBERG to REGENSBURG
        Connection nurnbergToRegensburg = new Connection(reichMap.get("Nurnberg"), reichMap.get("Regensburg"), 3, Colors.GREEN);
        reichMap.get("Nurnberg").addConnection(nurnbergToRegensburg);
        reichMap.get("Regensburg").addConnection(nurnbergToRegensburg);

// MANNHEIM to SAARBRUCKEN
        Connection mannheimToSaarbrucken = new Connection(reichMap.get("Mannheim"), reichMap.get("Saarbrucken"), 3, Colors.GRAY);
        reichMap.get("Mannheim").addConnection(mannheimToSaarbrucken);
        reichMap.get("Saarbrucken").addConnection(mannheimToSaarbrucken);

// MANNHEIM to KARLSRUHE
        Connection mannheimToKarlsruhe = new Connection(reichMap.get("Mannheim"), reichMap.get("Karlsruhe"), 1, Colors.BLUE);
        reichMap.get("Mannheim").addConnection(mannheimToKarlsruhe);
        reichMap.get("Karlsruhe").addConnection(mannheimToKarlsruhe);

// MANNHEIM to STUTTGART
        Connection mannheimToStuttgart = new Connection(reichMap.get("Mannheim"), reichMap.get("Stuttgart"), 2, Colors.GRAY);
        reichMap.get("Mannheim").addConnection(mannheimToStuttgart);
        reichMap.get("Stuttgart").addConnection(mannheimToStuttgart);
        mannheimToStuttgart = new Connection(reichMap.get("Mannheim"), reichMap.get("Stuttgart"), 2, Colors.GRAY);
        reichMap.get("Mannheim").addConnection(mannheimToStuttgart);
        reichMap.get("Stuttgart").addConnection(mannheimToStuttgart);

// SAARBRUCKEN to FRANKREICH
        Connection saarbruckenToFrankreich = new Connection(reichMap.get("Saarbrucken"), reichMap.get("Frankreich"), 1, Colors.GREEN);
        reichMap.get("Saarbrucken").addConnection(saarbruckenToFrankreich);
        reichMap.get("Frankreich").addConnection(saarbruckenToFrankreich);

// SAARBRUCKEN to KARLSRUHE
        Connection saarbruckenToKarlsruhe = new Connection(reichMap.get("Saarbrucken"), reichMap.get("Karlsruhe"), 3, Colors.BLACK);
        reichMap.get("Saarbrucken").addConnection(saarbruckenToKarlsruhe);
        reichMap.get("Karlsruhe").addConnection(saarbruckenToKarlsruhe);

// KARLSRUHE to FRANKREICH
        Connection karlsruheToFrankreich = new Connection(reichMap.get("Karlsruhe"), reichMap.get("Frankreich"), 2, Colors.BLACK);
        reichMap.get("Karlsruhe").addConnection(karlsruheToFrankreich);
        reichMap.get("Frankreich").addConnection(karlsruheToFrankreich);

// KARLSRUHE to FREIBURG
        Connection karlsruheToFreiburg = new Connection(reichMap.get("Karlsruhe"), reichMap.get("Freiburg"), 3, Colors.WHITE);
        reichMap.get("Karlsruhe").addConnection(karlsruheToFreiburg);
        reichMap.get("Freiburg").addConnection(karlsruheToFreiburg);

// KARLSRUHE to STUTTGART
        Connection karlsruheToStuttgart = new Connection(reichMap.get("Karlsruhe"), reichMap.get("Stuttgart"), 1, Colors.PINK);
        reichMap.get("Karlsruhe").addConnection(karlsruheToStuttgart);
        reichMap.get("Stuttgart").addConnection(karlsruheToStuttgart);


        // STUTTGART to FREIBURG
        Connection stuttgartToFreiburg = new Connection(reichMap.get("Stuttgart"), reichMap.get("Freiburg"), 3, Colors.RED);
        reichMap.get("Stuttgart").addConnection(stuttgartToFreiburg);
        reichMap.get("Freiburg").addConnection(stuttgartToFreiburg);

// STUTTGART to KONSTANZ
        Connection stuttgartToKonstanz = new Connection(reichMap.get("Stuttgart"), reichMap.get("Konstanz"), 3, Colors.GREEN);
        reichMap.get("Stuttgart").addConnection(stuttgartToKonstanz);
        reichMap.get("Konstanz").addConnection(stuttgartToKonstanz);

// STUTTGART to ULM
        Connection stuttgartToUlm = new Connection(reichMap.get("Stuttgart"), reichMap.get("Ulm"), 2, Colors.GRAY);
        reichMap.get("Stuttgart").addConnection(stuttgartToUlm);
        reichMap.get("Ulm").addConnection(stuttgartToUlm);
        stuttgartToUlm = new Connection(reichMap.get("Stuttgart"), reichMap.get("Ulm"), 2, Colors.GRAY);
        reichMap.get("Stuttgart").addConnection(stuttgartToUlm);
        reichMap.get("Ulm").addConnection(stuttgartToUlm);

// REGENSBURG to MUNCHEN
        Connection regensburgToMunchen = new Connection(reichMap.get("Regensburg"), reichMap.get("Munchen"), 3, Colors.ORANGE);
        reichMap.get("Regensburg").addConnection(regensburgToMunchen);
        reichMap.get("Munchen").addConnection(regensburgToMunchen);

// REGENSBURG to OSTERREICH
        Connection regensburgToOsterreich = new Connection(reichMap.get("Regensburg"), reichMap.get("Osterreich"), 4, Colors.YELLOW);
        reichMap.get("Regensburg").addConnection(regensburgToOsterreich);
        reichMap.get("Osterreich").addConnection(regensburgToOsterreich);

// FRANKREICH to FREIBURG
        Connection frankreichToFreiburg = new Connection(reichMap.get("Frankreich"), reichMap.get("Freiburg"), 2, Colors.YELLOW);
        reichMap.get("Frankreich").addConnection(frankreichToFreiburg);
        reichMap.get("Freiburg").addConnection(frankreichToFreiburg);

// ULM to AUGSBURG
        Connection ulmToAugsburg = new Connection(reichMap.get("Ulm"), reichMap.get("Augsburg"), 1, Colors.GRAY);
        reichMap.get("Ulm").addConnection(ulmToAugsburg);
        reichMap.get("Augsburg").addConnection(ulmToAugsburg);
        ulmToAugsburg = new Connection(reichMap.get("Ulm"), reichMap.get("Augsburg"), 1, Colors.GRAY);
        reichMap.get("Ulm").addConnection(ulmToAugsburg);
        reichMap.get("Augsburg").addConnection(ulmToAugsburg);

// ULM to LINDAU
        Connection ulmToLindau = new Connection(reichMap.get("Ulm"), reichMap.get("Lindau"), 2, Colors.RED);
        reichMap.get("Ulm").addConnection(ulmToLindau);
        reichMap.get("Lindau").addConnection(ulmToLindau);

// AUGSBURG to MUNCHEN
        Connection augsburgToMunchen = new Connection(reichMap.get("Augsburg"), reichMap.get("Munchen"), 2, Colors.WHITE);
        reichMap.get("Augsburg").addConnection(augsburgToMunchen);
        reichMap.get("Munchen").addConnection(augsburgToMunchen);
        augsburgToMunchen = new Connection(reichMap.get("Augsburg"), reichMap.get("Munchen"), 2, Colors.GRAY);
        reichMap.get("Augsburg").addConnection(augsburgToMunchen);
        reichMap.get("Munchen").addConnection(augsburgToMunchen);

// MUNCHEN to OSTERREICH
        Connection munchenToOsterreich = new Connection(reichMap.get("Munchen"), reichMap.get("Osterreich"), 3, Colors.RED);
        reichMap.get("Munchen").addConnection(munchenToOsterreich);
        reichMap.get("Osterreich").addConnection(munchenToOsterreich);

// MUNCHEN to LINDAU
        Connection munchenToLindau = new Connection(reichMap.get("Munchen"), reichMap.get("Lindau"), 5, Colors.GRAY);
        reichMap.get("Munchen").addConnection(munchenToLindau);
        reichMap.get("Lindau").addConnection(munchenToLindau);

// FREIBURG to SCHWEIZ
        Connection freiburgToSchweiz = new Connection(reichMap.get("Freiburg"), reichMap.get("Schweiz"), 2, Colors.ORANGE);
        reichMap.get("Freiburg").addConnection(freiburgToSchweiz);
        reichMap.get("Schweiz").addConnection(freiburgToSchweiz);

// FREIBURG to KONSTANZ
        Connection freiburgToKonstanz = new Connection(reichMap.get("Freiburg"), reichMap.get("Konstanz"), 2, Colors.BLACK);
        reichMap.get("Freiburg").addConnection(freiburgToKonstanz);
        reichMap.get("Konstanz").addConnection(freiburgToKonstanz);

// KONSTANZ to SCHWEIZ
        Connection konstanzToSchweiz = new Connection(reichMap.get("Konstanz"), reichMap.get("Schweiz"), 1, Colors.RED);
        reichMap.get("Konstanz").addConnection(konstanzToSchweiz);
        reichMap.get("Schweiz").addConnection(konstanzToSchweiz);

// KONSTANZ to LINDAU
        Connection konstanzToLindau = new Connection(reichMap.get("Konstanz"), reichMap.get("Lindau"), 1, Colors.YELLOW);
        reichMap.get("Konstanz").addConnection(konstanzToLindau);
        reichMap.get("Lindau").addConnection(konstanzToLindau);

// LINDAU to SCHWEIZ
        Connection lindauToSchweiz = new Connection(reichMap.get("Lindau"), reichMap.get("Schweiz"), 2, Colors.BLUE);
        reichMap.get("Lindau").addConnection(lindauToSchweiz);
        reichMap.get("Schweiz").addConnection(lindauToSchweiz);

// LINDAU to OSTERREICH
        Connection lindauToOsterreich = new Connection(reichMap.get("Lindau"), reichMap.get("Osterreich"), 2, Colors.PINK);
        reichMap.get("Lindau").addConnection(lindauToOsterreich);
        reichMap.get("Osterreich").addConnection(lindauToOsterreich);

        return reichMap;
    }

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

    public Boolean isNeighbour(City cityOne, City cityTwo){
        return (cities.get(cityOne.getName()).getConnectionList().contains(cityTwo));
    }

    public Connection getNeighbourConnection(City cityOne, City cityTwo) {
        List<Connection> connections = cityOne.getConnectionList();
        for(Connection connection : connections) {
            if(connection.getOtherCity(cityOne) == cityTwo) {
                return connection;
            }
        }
        return null;
    }

    public City getCity(String cityName)
    {
        return cities.get(cityName);
    }

    public List<Connection> getCitiesConnections(String cityName){
        City inputCity = cities.get(cityName);
        return inputCity.getConnectionList();
    }
}
