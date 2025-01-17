package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.PhysicalConnection;

import java.util.HashMap;

public class MapHash {
    private HashMap<String, City> cities;
    public MapHash(String name) {
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

        reichMap.put("Danemark", new City("Danemark", 1));
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
        reichMap.put("Frankreich", new City("Frankreich", 1));
        reichMap.put("Schweiz", new City("Schweiz", 1));
        reichMap.put("Niederlande", new City("Niederlande", 1));
        reichMap.put("Osterreich", new City("Osterreich", 1));

// DANEMARK to BREMERHAVEN
        PhysicalConnection danemarkToBremerhaven = new PhysicalConnection(reichMap.get("Danemark"), reichMap.get("Bremerhaven"), 5, Colors.BLACK);
        reichMap.get("Danemark").addPhysicalConnection(danemarkToBremerhaven);
        reichMap.get("Bremerhaven").addPhysicalConnection(danemarkToBremerhaven);

// DANEMARK to KIEL
        PhysicalConnection danemarkToKiel = new PhysicalConnection(reichMap.get("Danemark"), reichMap.get("Kiel"), 2, Colors.GRAY);
        reichMap.get("Danemark").addPhysicalConnection(danemarkToKiel);
        reichMap.get("Kiel").addPhysicalConnection(danemarkToKiel);

// KIEL to BREMERHAVEN
        PhysicalConnection kielToBremerhaven = new PhysicalConnection(reichMap.get("Kiel"), reichMap.get("Bremerhaven"), 3, Colors.GRAY);
        reichMap.get("Kiel").addPhysicalConnection(kielToBremerhaven);
        reichMap.get("Bremerhaven").addPhysicalConnection(kielToBremerhaven);
// KIEL TO HAMBURG
        PhysicalConnection kielToHamburg = new PhysicalConnection(reichMap.get("Kiel"), reichMap.get("Hamburg"), 2, Colors.BLACK);
        reichMap.get("Kiel").addPhysicalConnection(kielToHamburg);
        reichMap.get("Hamburg").addPhysicalConnection(kielToHamburg);
        kielToHamburg = new PhysicalConnection(reichMap.get("Kiel"), reichMap.get("Hamburg"), 2, Colors.PINK);
        reichMap.get("Kiel").addPhysicalConnection(kielToHamburg);
        reichMap.get("Hamburg").addPhysicalConnection(kielToHamburg);
// KIEL TO SCHWERIN
        PhysicalConnection kielToSchwerin = new PhysicalConnection(reichMap.get("Kiel"), reichMap.get("Schwerin"), 3, Colors.YELLOW);
        reichMap.get("Kiel").addPhysicalConnection(kielToSchwerin);
        reichMap.get("Schwerin").addPhysicalConnection(kielToSchwerin);

        PhysicalConnection kielToRostock = new PhysicalConnection(reichMap.get("Kiel"), reichMap.get("Rostock"), 4, Colors.ORANGE);
        reichMap.get("Kiel").addPhysicalConnection(kielToRostock);
        reichMap.get("Rostock").addPhysicalConnection(kielToRostock);

        PhysicalConnection rostockToSchwerin = new PhysicalConnection(reichMap.get("Rostock"), reichMap.get("Schwerin"), 2, Colors.RED);
        reichMap.get("Rostock").addPhysicalConnection(rostockToSchwerin);
        reichMap.get("Schwerin").addPhysicalConnection(rostockToSchwerin);

        PhysicalConnection rostockToBerlin = new PhysicalConnection(reichMap.get("Rostock"), reichMap.get("Berlin"), 6, Colors.PINK);
        reichMap.get("Rostock").addPhysicalConnection(rostockToBerlin);
        reichMap.get("Berlin").addPhysicalConnection(rostockToBerlin);

        PhysicalConnection schwerinToBerlin = new PhysicalConnection(reichMap.get("Schwerin"), reichMap.get("Berlin"), 5, Colors.WHITE);
        reichMap.get("Schwerin").addPhysicalConnection(schwerinToBerlin);
        reichMap.get("Berlin").addPhysicalConnection(schwerinToBerlin);

        PhysicalConnection schwerinToHamburg = new PhysicalConnection(reichMap.get("Schwerin"), reichMap.get("Hamburg"), 2, Colors.RED);
        reichMap.get("Schwerin").addPhysicalConnection(schwerinToHamburg);
        reichMap.get("Hamburg").addPhysicalConnection(schwerinToHamburg);

        PhysicalConnection hamburgToBerlin = new PhysicalConnection(reichMap.get("Hamburg"), reichMap.get("Berlin"), 7, Colors.BLUE);
        reichMap.get("Hamburg").addPhysicalConnection(hamburgToBerlin);
        reichMap.get("Berlin").addPhysicalConnection(hamburgToBerlin);
        hamburgToBerlin = new PhysicalConnection(reichMap.get("Hamburg"), reichMap.get("Berlin"), 7, Colors.YELLOW);
        reichMap.get("Hamburg").addPhysicalConnection(hamburgToBerlin);
        reichMap.get("Berlin").addPhysicalConnection(hamburgToBerlin);

        PhysicalConnection hamburgToHannover = new PhysicalConnection(reichMap.get("Hamburg"), reichMap.get("Hannover"), 4, Colors.RED);
        reichMap.get("Hamburg").addPhysicalConnection(hamburgToHannover);
        reichMap.get("Hannover").addPhysicalConnection(hamburgToHannover);
        hamburgToHannover = new PhysicalConnection(reichMap.get("Hamburg"), reichMap.get("Hannover"), 4, Colors.WHITE);
        reichMap.get("Hamburg").addPhysicalConnection(hamburgToHannover);
        reichMap.get("Hannover").addPhysicalConnection(hamburgToHannover);

        PhysicalConnection hamburgToBremerhaven = new PhysicalConnection(reichMap.get("Hamburg"), reichMap.get("Bremerhaven"), 3, Colors.GRAY);
        reichMap.get("Hamburg").addPhysicalConnection(hamburgToBremerhaven);
        reichMap.get("Bremerhaven").addPhysicalConnection(hamburgToBremerhaven);

        PhysicalConnection hamburgToBremen = new PhysicalConnection(reichMap.get("Hamburg"), reichMap.get("Bremen"), 3, Colors.ORANGE);
        reichMap.get("Hamburg").addPhysicalConnection(hamburgToBremen);
        reichMap.get("Bremen").addPhysicalConnection(hamburgToBremen);

        PhysicalConnection bremerhavenToBremen = new PhysicalConnection(reichMap.get("Bremerhaven"), reichMap.get("Bremen"), 1, Colors.WHITE);
        reichMap.get("Bremerhaven").addPhysicalConnection(bremerhavenToBremen);
        reichMap.get("Bremen").addPhysicalConnection(bremerhavenToBremen);

        PhysicalConnection bremerhavenToEmden = new PhysicalConnection(reichMap.get("Bremerhaven"), reichMap.get("Emden"), 3, Colors.GRAY);
        reichMap.get("Bremerhaven").addPhysicalConnection(bremerhavenToEmden);
        reichMap.get("Emden").addPhysicalConnection(bremerhavenToEmden);

        PhysicalConnection emdenToNiederlande = new PhysicalConnection(reichMap.get("Emden"), reichMap.get("Niederlande"), 2, Colors.WHITE);
        reichMap.get("Emden").addPhysicalConnection(emdenToNiederlande);
        reichMap.get("Niederlande").addPhysicalConnection(emdenToNiederlande);

        PhysicalConnection emdenToMunster = new PhysicalConnection(reichMap.get("Emden"), reichMap.get("Munster"), 4, Colors.RED);
        reichMap.get("Emden").addPhysicalConnection(emdenToMunster);
        reichMap.get("Munster").addPhysicalConnection(emdenToMunster);

        PhysicalConnection emdenToBremen = new PhysicalConnection(reichMap.get("Emden"), reichMap.get("Bremen"), 3, Colors.BLUE);
        reichMap.get("Emden").addPhysicalConnection(emdenToBremen);
        reichMap.get("Bremen").addPhysicalConnection(emdenToBremen);

        PhysicalConnection bremenToHannover = new PhysicalConnection(reichMap.get("Bremen"), reichMap.get("Hannover"), 3, Colors.PINK);
        reichMap.get("Bremen").addPhysicalConnection(bremenToHannover);
        reichMap.get("Hannover").addPhysicalConnection(bremenToHannover);

        PhysicalConnection bremenToMunster = new PhysicalConnection(reichMap.get("Bremen"), reichMap.get("Munster"), 3, Colors.BLACK);
        reichMap.get("Bremen").addPhysicalConnection(bremenToMunster);
        reichMap.get("Munster").addPhysicalConnection(bremenToMunster);

        PhysicalConnection niederlandeToMunster = new PhysicalConnection(reichMap.get("Niederlande"), reichMap.get("Munster"), 2, Colors.ORANGE);
        reichMap.get("Niederlande").addPhysicalConnection(niederlandeToMunster);
        reichMap.get("Munster").addPhysicalConnection(niederlandeToMunster);

        PhysicalConnection niederlandeToDusseldorf = new PhysicalConnection(reichMap.get("Niederlande"), reichMap.get("Dusseldorf"), 3, Colors.PINK);
        reichMap.get("Niederlande").addPhysicalConnection(niederlandeToDusseldorf);
        reichMap.get("Dusseldorf").addPhysicalConnection(niederlandeToDusseldorf);

        PhysicalConnection berlinToHannover = new PhysicalConnection(reichMap.get("Berlin"), reichMap.get("Hannover"), 7, Colors.YELLOW);
        reichMap.get("Berlin").addPhysicalConnection(berlinToHannover);
        reichMap.get("Hannover").addPhysicalConnection(berlinToHannover);

        PhysicalConnection berlinToMagdeburg = new PhysicalConnection(reichMap.get("Berlin"), reichMap.get("Magdeburg"), 3, Colors.RED);
        reichMap.get("Berlin").addPhysicalConnection(berlinToMagdeburg);
        reichMap.get("Magdeburg").addPhysicalConnection(berlinToMagdeburg);

        PhysicalConnection berlinToLeipzig = new PhysicalConnection(reichMap.get("Berlin"), reichMap.get("Leipzig"), 4, Colors.PINK);
        reichMap.get("Berlin").addPhysicalConnection(berlinToLeipzig);
        reichMap.get("Leipzig").addPhysicalConnection(berlinToLeipzig);

        PhysicalConnection berlinToDresden = new PhysicalConnection(reichMap.get("Berlin"), reichMap.get("Dresden"), 5, Colors.GREEN);
        reichMap.get("Berlin").addPhysicalConnection(berlinToDresden);
        reichMap.get("Dresden").addPhysicalConnection(berlinToDresden);

        PhysicalConnection hannoverToMagdeburg = new PhysicalConnection(reichMap.get("Hannover"), reichMap.get("Magdeburg"), 4, Colors.BLUE);
        reichMap.get("Hannover").addPhysicalConnection(hannoverToMagdeburg);
        reichMap.get("Magdeburg").addPhysicalConnection(hannoverToMagdeburg);

        PhysicalConnection hannoverToErfurt = new PhysicalConnection(reichMap.get("Hannover"), reichMap.get("Erfurt"), 5, Colors.ORANGE);
        reichMap.get("Hannover").addPhysicalConnection(hannoverToErfurt);
        reichMap.get("Erfurt").addPhysicalConnection(hannoverToErfurt);
        hannoverToErfurt = new PhysicalConnection(reichMap.get("Hannover"), reichMap.get("Erfurt"), 5, Colors.GREEN);
        reichMap.get("Hannover").addPhysicalConnection(hannoverToErfurt);
        reichMap.get("Erfurt").addPhysicalConnection(hannoverToErfurt);

        PhysicalConnection hannoverToKassel = new PhysicalConnection(reichMap.get("Hannover"), reichMap.get("Kassel"), 3, Colors.GRAY);
        reichMap.get("Hannover").addPhysicalConnection(hannoverToKassel);
        reichMap.get("Kassel").addPhysicalConnection(hannoverToKassel);
        hannoverToKassel = new PhysicalConnection(reichMap.get("Hannover"), reichMap.get("Kassel"), 3, Colors.GRAY);
        reichMap.get("Hannover").addPhysicalConnection(hannoverToKassel);
        reichMap.get("Kassel").addPhysicalConnection(hannoverToKassel);

        PhysicalConnection hannoverToMunster = new PhysicalConnection(reichMap.get("Hannover"), reichMap.get("Munster"), 4, Colors.YELLOW);
        reichMap.get("Hannover").addPhysicalConnection(hannoverToMunster);
        reichMap.get("Munster").addPhysicalConnection(hannoverToMunster);

        PhysicalConnection munsterToDortmund = new PhysicalConnection(reichMap.get("Munster"), reichMap.get("Dortmund"), 1, Colors.GRAY);
        reichMap.get("Munster").addPhysicalConnection(munsterToDortmund);
        reichMap.get("Dortmund").addPhysicalConnection(munsterToDortmund);
        munsterToDortmund = new PhysicalConnection(reichMap.get("Munster"), reichMap.get("Dortmund"), 1, Colors.GRAY);
        reichMap.get("Munster").addPhysicalConnection(munsterToDortmund);
        reichMap.get("Dortmund").addPhysicalConnection(munsterToDortmund);

// MAGDEBURG to LEIPZIG
        PhysicalConnection magdeburgToLeipzig = new PhysicalConnection(reichMap.get("Magdeburg"), reichMap.get("Leipzig"), 2, Colors.YELLOW);
        reichMap.get("Magdeburg").addPhysicalConnection(magdeburgToLeipzig);
        reichMap.get("Leipzig").addPhysicalConnection(magdeburgToLeipzig);

// DORTMUND to DUSSELDORF
        PhysicalConnection dortmundToDusseldorf = new PhysicalConnection(reichMap.get("Dortmund"), reichMap.get("Dusseldorf"), 1, Colors.GRAY);
        reichMap.get("Dortmund").addPhysicalConnection(dortmundToDusseldorf);
        reichMap.get("Dusseldorf").addPhysicalConnection(dortmundToDusseldorf);
        dortmundToDusseldorf = new PhysicalConnection(reichMap.get("Dortmund"), reichMap.get("Dusseldorf"), 1, Colors.GRAY);
        reichMap.get("Dortmund").addPhysicalConnection(dortmundToDusseldorf);
        reichMap.get("Dusseldorf").addPhysicalConnection(dortmundToDusseldorf);
        dortmundToDusseldorf = new PhysicalConnection(reichMap.get("Dortmund"), reichMap.get("Dusseldorf"), 1, Colors.GRAY);
        reichMap.get("Dortmund").addPhysicalConnection(dortmundToDusseldorf);
        reichMap.get("Dusseldorf").addPhysicalConnection(dortmundToDusseldorf);

// DORTMUND to KASSEL
        PhysicalConnection dortmundToKassel = new PhysicalConnection(reichMap.get("Dortmund"), reichMap.get("Kassel"), 4, Colors.GREEN);
        reichMap.get("Dortmund").addPhysicalConnection(dortmundToKassel);
        reichMap.get("Kassel").addPhysicalConnection(dortmundToKassel);

// DUSSELDORF to KOLN
        PhysicalConnection dusseldorfToKoln = new PhysicalConnection(reichMap.get("Dusseldorf"), reichMap.get("Koln"), 1, Colors.GRAY);
        reichMap.get("Dusseldorf").addPhysicalConnection(dusseldorfToKoln);
        reichMap.get("Koln").addPhysicalConnection(dusseldorfToKoln);
        dusseldorfToKoln = new PhysicalConnection(reichMap.get("Dusseldorf"), reichMap.get("Koln"), 1, Colors.GRAY);
        reichMap.get("Dusseldorf").addPhysicalConnection(dusseldorfToKoln);
        reichMap.get("Koln").addPhysicalConnection(dusseldorfToKoln);dusseldorfToKoln = new PhysicalConnection(reichMap.get("Dusseldorf"), reichMap.get("Koln"), 1, Colors.GRAY);
        reichMap.get("Dusseldorf").addPhysicalConnection(dusseldorfToKoln);
        reichMap.get("Koln").addPhysicalConnection(dusseldorfToKoln);

// LEIPZIG to DRESDEN
        PhysicalConnection leipzigToDresden = new PhysicalConnection(reichMap.get("Leipzig"), reichMap.get("Dresden"), 3, Colors.BLACK);
        reichMap.get("Leipzig").addPhysicalConnection(leipzigToDresden);
        reichMap.get("Dresden").addPhysicalConnection(leipzigToDresden);

// LEIPZIG to CHEMNITZ
        PhysicalConnection leipzigToChemnitz = new PhysicalConnection(reichMap.get("Leipzig"), reichMap.get("Chemnitz"), 2, Colors.BLUE);
        reichMap.get("Leipzig").addPhysicalConnection(leipzigToChemnitz);
        reichMap.get("Chemnitz").addPhysicalConnection(leipzigToChemnitz);

// LEIPZIG to ERFURT
        PhysicalConnection leipzigToErfurt = new PhysicalConnection(reichMap.get("Leipzig"), reichMap.get("Erfurt"), 3, Colors.YELLOW);
        reichMap.get("Leipzig").addPhysicalConnection(leipzigToErfurt);
        reichMap.get("Erfurt").addPhysicalConnection(leipzigToErfurt);

// KASSEL to ERFURT
        PhysicalConnection kasselToErfurt = new PhysicalConnection(reichMap.get("Kassel"), reichMap.get("Erfurt"), 3, Colors.GRAY);
        reichMap.get("Kassel").addPhysicalConnection(kasselToErfurt);
        reichMap.get("Erfurt").addPhysicalConnection(kasselToErfurt);

// KASSEL to FRANKFURT
        PhysicalConnection kasselToFrankfurt = new PhysicalConnection(reichMap.get("Kassel"), reichMap.get("Frankfurt"), 4, Colors.BLUE);
        reichMap.get("Kassel").addPhysicalConnection(kasselToFrankfurt);
        reichMap.get("Frankfurt").addPhysicalConnection(kasselToFrankfurt);
        kasselToFrankfurt = new PhysicalConnection(reichMap.get("Kassel"), reichMap.get("Frankfurt"), 4, Colors.GRAY);
        reichMap.get("Kassel").addPhysicalConnection(kasselToFrankfurt);
        reichMap.get("Frankfurt").addPhysicalConnection(kasselToFrankfurt);

// KOLN to FRANKFURT
        PhysicalConnection kolnToFrankfurt = new PhysicalConnection(reichMap.get("Koln"), reichMap.get("Frankfurt"), 4, Colors.GRAY);
        reichMap.get("Koln").addPhysicalConnection(kolnToFrankfurt);
        reichMap.get("Frankfurt").addPhysicalConnection(kolnToFrankfurt);
        kolnToFrankfurt = new PhysicalConnection(reichMap.get("Koln"), reichMap.get("Frankfurt"), 4, Colors.WHITE);
        reichMap.get("Koln").addPhysicalConnection(kolnToFrankfurt);
        reichMap.get("Frankfurt").addPhysicalConnection(kolnToFrankfurt);

// KOLN to KOBLENZ
        PhysicalConnection kolnToKoblenz = new PhysicalConnection(reichMap.get("Koln"), reichMap.get("Koblenz"), 1, Colors.GRAY);
        reichMap.get("Koln").addPhysicalConnection(kolnToKoblenz);
        reichMap.get("Koblenz").addPhysicalConnection(kolnToKoblenz);
        kolnToKoblenz = new PhysicalConnection(reichMap.get("Koln"), reichMap.get("Koblenz"), 1, Colors.GRAY);
        reichMap.get("Koln").addPhysicalConnection(kolnToKoblenz);
        reichMap.get("Koblenz").addPhysicalConnection(kolnToKoblenz);

// ERFURT to NURNBERG
        PhysicalConnection erfurtToNurnberg = new PhysicalConnection(reichMap.get("Erfurt"), reichMap.get("Nurnberg"), 4, Colors.RED);
        reichMap.get("Erfurt").addPhysicalConnection(erfurtToNurnberg);
        reichMap.get("Nurnberg").addPhysicalConnection(erfurtToNurnberg);
        erfurtToNurnberg = new PhysicalConnection(reichMap.get("Erfurt"), reichMap.get("Nurnberg"), 4, Colors.BLUE);
        reichMap.get("Erfurt").addPhysicalConnection(erfurtToNurnberg);
        reichMap.get("Nurnberg").addPhysicalConnection(erfurtToNurnberg);

// ERFURT to REGENSBURG
        PhysicalConnection erfurtToRegensburg = new PhysicalConnection(reichMap.get("Erfurt"), reichMap.get("Regensburg"), 7, Colors.WHITE);
        reichMap.get("Erfurt").addPhysicalConnection(erfurtToRegensburg);
        reichMap.get("Regensburg").addPhysicalConnection(erfurtToRegensburg);

// ERFURT to CHEMNITZ
        PhysicalConnection erfurtToChemnitz = new PhysicalConnection(reichMap.get("Erfurt"), reichMap.get("Chemnitz"), 3, Colors.BLACK);
        reichMap.get("Erfurt").addPhysicalConnection(erfurtToChemnitz);
        reichMap.get("Chemnitz").addPhysicalConnection(erfurtToChemnitz);

// DRESDEN to CHEMNITZ
        PhysicalConnection dresdenToChemnitz = new PhysicalConnection(reichMap.get("Dresden"), reichMap.get("Chemnitz"), 1, Colors.YELLOW);
        reichMap.get("Dresden").addPhysicalConnection(dresdenToChemnitz);
        reichMap.get("Chemnitz").addPhysicalConnection(dresdenToChemnitz);

// DRESDEN to REGENSBURG
        PhysicalConnection dresdenToRegensburg = new PhysicalConnection(reichMap.get("Dresden"), reichMap.get("Regensburg"), 7, Colors.RED);
        reichMap.get("Dresden").addPhysicalConnection(dresdenToRegensburg);
        reichMap.get("Regensburg").addPhysicalConnection(dresdenToRegensburg);

// CHEMNITZ to REGENSBURG
        PhysicalConnection chemnitzToRegensburg = new PhysicalConnection(reichMap.get("Chemnitz"), reichMap.get("Regensburg"), 6, Colors.PINK);
        reichMap.get("Chemnitz").addPhysicalConnection(chemnitzToRegensburg);
        reichMap.get("Regensburg").addPhysicalConnection(chemnitzToRegensburg);

// KOBLENZ to MAINZ
        PhysicalConnection koblenzToMainz = new PhysicalConnection(reichMap.get("Koblenz"), reichMap.get("Mainz"), 2, Colors.GRAY);
        reichMap.get("Koblenz").addPhysicalConnection(koblenzToMainz);
        reichMap.get("Mainz").addPhysicalConnection(koblenzToMainz);
        koblenzToMainz = new PhysicalConnection(reichMap.get("Koblenz"), reichMap.get("Mainz"), 2, Colors.GRAY);
        reichMap.get("Koblenz").addPhysicalConnection(koblenzToMainz);
        reichMap.get("Mainz").addPhysicalConnection(koblenzToMainz);

// KOBLENZ to SAARBRUCKEN
        PhysicalConnection koblenzToSaarbrucken = new PhysicalConnection(reichMap.get("Koblenz"), reichMap.get("Saarbrucken"), 3, Colors.GRAY);
        reichMap.get("Koblenz").addPhysicalConnection(koblenzToSaarbrucken);
        reichMap.get("Saarbrucken").addPhysicalConnection(koblenzToSaarbrucken);

// FRANKFURT to MAINZ
        PhysicalConnection frankfurtToMainz = new PhysicalConnection(reichMap.get("Frankfurt"), reichMap.get("Mainz"), 1, Colors.WHITE);
        reichMap.get("Frankfurt").addPhysicalConnection(frankfurtToMainz);
        reichMap.get("Mainz").addPhysicalConnection(frankfurtToMainz);
        frankfurtToMainz = new PhysicalConnection(reichMap.get("Frankfurt"), reichMap.get("Mainz"), 1, Colors.GRAY);
        reichMap.get("Frankfurt").addPhysicalConnection(frankfurtToMainz);
        reichMap.get("Mainz").addPhysicalConnection(frankfurtToMainz);

// FRANKFURT to MANNHEIM
        PhysicalConnection frankfurtToMannheim = new PhysicalConnection(reichMap.get("Frankfurt"), reichMap.get("Mannheim"), 2, Colors.GRAY);
        reichMap.get("Frankfurt").addPhysicalConnection(frankfurtToMannheim);
        reichMap.get("Mannheim").addPhysicalConnection(frankfurtToMannheim);
        frankfurtToMannheim = new PhysicalConnection(reichMap.get("Frankfurt"), reichMap.get("Mannheim"), 2, Colors.GRAY);
        reichMap.get("Frankfurt").addPhysicalConnection(frankfurtToMannheim);
        reichMap.get("Mannheim").addPhysicalConnection(frankfurtToMannheim);

// FRANKFURT to WURZBURG
        PhysicalConnection frankfurtToWurzburg = new PhysicalConnection(reichMap.get("Frankfurt"), reichMap.get("Wurzburg"), 2, Colors.GRAY);
        reichMap.get("Frankfurt").addPhysicalConnection(frankfurtToWurzburg);
        reichMap.get("Wurzburg").addPhysicalConnection(frankfurtToWurzburg);
        frankfurtToWurzburg = new PhysicalConnection(reichMap.get("Frankfurt"), reichMap.get("Wurzburg"), 2, Colors.BLACK);
        reichMap.get("Frankfurt").addPhysicalConnection(frankfurtToWurzburg);
        reichMap.get("Wurzburg").addPhysicalConnection(frankfurtToWurzburg);

// MAINZ to SAARBRUCKEN
        PhysicalConnection mainzToSaarbrucken = new PhysicalConnection(reichMap.get("Mainz"), reichMap.get("Saarbrucken"), 3, Colors.GRAY);
        reichMap.get("Mainz").addPhysicalConnection(mainzToSaarbrucken);
        reichMap.get("Saarbrucken").addPhysicalConnection(mainzToSaarbrucken);

// MAINZ to MANNHEIM
        PhysicalConnection mainzToMannheim = new PhysicalConnection(reichMap.get("Mainz"), reichMap.get("Mannheim"), 1, Colors.GRAY);
        reichMap.get("Mainz").addPhysicalConnection(mainzToMannheim);
        reichMap.get("Mannheim").addPhysicalConnection(mainzToMannheim);
        mainzToMannheim = new PhysicalConnection(reichMap.get("Mainz"), reichMap.get("Mannheim"), 1,  Colors.YELLOW);
        reichMap.get("Mainz").addPhysicalConnection(mainzToMannheim);
        reichMap.get("Mannheim").addPhysicalConnection(mainzToMannheim);

// WURZBURG to NURNBERG
        PhysicalConnection wurzburgToNurnberg = new PhysicalConnection(reichMap.get("Wurzburg"), reichMap.get("Nurnberg"), 2, Colors.GRAY);
        reichMap.get("Wurzburg").addPhysicalConnection(wurzburgToNurnberg);
        reichMap.get("Nurnberg").addPhysicalConnection(wurzburgToNurnberg);
        wurzburgToNurnberg = new PhysicalConnection(reichMap.get("Wurzburg"), reichMap.get("Nurnberg"), 2, Colors.YELLOW);
        reichMap.get("Wurzburg").addPhysicalConnection(wurzburgToNurnberg);
        reichMap.get("Nurnberg").addPhysicalConnection(wurzburgToNurnberg);

// NURNBERG to AUGSBURG
        PhysicalConnection nurnbergToAugsburg = new PhysicalConnection(reichMap.get("Nurnberg"), reichMap.get("Augsburg"), 4, Colors.BLACK);
        reichMap.get("Nurnberg").addPhysicalConnection(nurnbergToAugsburg);
        reichMap.get("Augsburg").addPhysicalConnection(nurnbergToAugsburg);

// NURNBERG to MUNCHEN
        PhysicalConnection nurnbergToMunchen = new PhysicalConnection(reichMap.get("Nurnberg"), reichMap.get("Munchen"), 5, Colors.BLUE);
        reichMap.get("Nurnberg").addPhysicalConnection(nurnbergToMunchen);
        reichMap.get("Munchen").addPhysicalConnection(nurnbergToMunchen);
        nurnbergToMunchen = new PhysicalConnection(reichMap.get("Nurnberg"), reichMap.get("Munchen"), 5, Colors.GRAY);
        reichMap.get("Nurnberg").addPhysicalConnection(nurnbergToMunchen);
        reichMap.get("Munchen").addPhysicalConnection(nurnbergToMunchen);

// NURNBERG to REGENSBURG
        PhysicalConnection nurnbergToRegensburg = new PhysicalConnection(reichMap.get("Nurnberg"), reichMap.get("Regensburg"), 3, Colors.GREEN);
        reichMap.get("Nurnberg").addPhysicalConnection(nurnbergToRegensburg);
        reichMap.get("Regensburg").addPhysicalConnection(nurnbergToRegensburg);

// MANNHEIM to SAARBRUCKEN
        PhysicalConnection mannheimToSaarbrucken = new PhysicalConnection(reichMap.get("Mannheim"), reichMap.get("Saarbrucken"), 3, Colors.GRAY);
        reichMap.get("Mannheim").addPhysicalConnection(mannheimToSaarbrucken);
        reichMap.get("Saarbrucken").addPhysicalConnection(mannheimToSaarbrucken);

// MANNHEIM to KARLSRUHE
        PhysicalConnection mannheimToKarlsruhe = new PhysicalConnection(reichMap.get("Mannheim"), reichMap.get("Karlsruhe"), 1, Colors.BLUE);
        reichMap.get("Mannheim").addPhysicalConnection(mannheimToKarlsruhe);
        reichMap.get("Karlsruhe").addPhysicalConnection(mannheimToKarlsruhe);

// MANNHEIM to STUTTGART
        PhysicalConnection mannheimToStuttgart = new PhysicalConnection(reichMap.get("Mannheim"), reichMap.get("Stuttgart"), 2, Colors.GRAY);
        reichMap.get("Mannheim").addPhysicalConnection(mannheimToStuttgart);
        reichMap.get("Stuttgart").addPhysicalConnection(mannheimToStuttgart);
        mannheimToStuttgart = new PhysicalConnection(reichMap.get("Mannheim"), reichMap.get("Stuttgart"), 2, Colors.GRAY);
        reichMap.get("Mannheim").addPhysicalConnection(mannheimToStuttgart);
        reichMap.get("Stuttgart").addPhysicalConnection(mannheimToStuttgart);

// SAARBRUCKEN to FRANKREICH
        PhysicalConnection saarbruckenToFrankreich = new PhysicalConnection(reichMap.get("Saarbrucken"), reichMap.get("Frankreich"), 1, Colors.GREEN);
        reichMap.get("Saarbrucken").addPhysicalConnection(saarbruckenToFrankreich);
        reichMap.get("Frankreich").addPhysicalConnection(saarbruckenToFrankreich);

// SAARBRUCKEN to KARLSRUHE
        PhysicalConnection saarbruckenToKarlsruhe = new PhysicalConnection(reichMap.get("Saarbrucken"), reichMap.get("Karlsruhe"), 3, Colors.BLACK);
        reichMap.get("Saarbrucken").addPhysicalConnection(saarbruckenToKarlsruhe);
        reichMap.get("Karlsruhe").addPhysicalConnection(saarbruckenToKarlsruhe);

// KARLSRUHE to FRANKREICH
        PhysicalConnection karlsruheToFrankreich = new PhysicalConnection(reichMap.get("Karlsruhe"), reichMap.get("Frankreich"), 2, Colors.BLACK);
        reichMap.get("Karlsruhe").addPhysicalConnection(karlsruheToFrankreich);
        reichMap.get("Frankreich").addPhysicalConnection(karlsruheToFrankreich);

// KARLSRUHE to FREIBURG
        PhysicalConnection karlsruheToFreiburg = new PhysicalConnection(reichMap.get("Karlsruhe"), reichMap.get("Freiburg"), 3, Colors.WHITE);
        reichMap.get("Karlsruhe").addPhysicalConnection(karlsruheToFreiburg);
        reichMap.get("Freiburg").addPhysicalConnection(karlsruheToFreiburg);

// KARLSRUHE to STUTTGART
        PhysicalConnection karlsruheToStuttgart = new PhysicalConnection(reichMap.get("Karlsruhe"), reichMap.get("Stuttgart"), 1, Colors.PINK);
        reichMap.get("Karlsruhe").addPhysicalConnection(karlsruheToStuttgart);
        reichMap.get("Stuttgart").addPhysicalConnection(karlsruheToStuttgart);


        // STUTTGART to FREIBURG
        PhysicalConnection stuttgartToFreiburg = new PhysicalConnection(reichMap.get("Stuttgart"), reichMap.get("Freiburg"), 3, Colors.RED);
        reichMap.get("Stuttgart").addPhysicalConnection(stuttgartToFreiburg);
        reichMap.get("Freiburg").addPhysicalConnection(stuttgartToFreiburg);

// STUTTGART to KONSTANZ
        PhysicalConnection stuttgartToKonstanz = new PhysicalConnection(reichMap.get("Stuttgart"), reichMap.get("Konstanz"), 3, Colors.GREEN);
        reichMap.get("Stuttgart").addPhysicalConnection(stuttgartToKonstanz);
        reichMap.get("Konstanz").addPhysicalConnection(stuttgartToKonstanz);

// STUTTGART to ULM
        PhysicalConnection stuttgartToUlm = new PhysicalConnection(reichMap.get("Stuttgart"), reichMap.get("Ulm"), 2, Colors.GRAY);
        reichMap.get("Stuttgart").addPhysicalConnection(stuttgartToUlm);
        reichMap.get("Ulm").addPhysicalConnection(stuttgartToUlm);
        stuttgartToUlm = new PhysicalConnection(reichMap.get("Stuttgart"), reichMap.get("Ulm"), 2, Colors.GRAY);
        reichMap.get("Stuttgart").addPhysicalConnection(stuttgartToUlm);
        reichMap.get("Ulm").addPhysicalConnection(stuttgartToUlm);

// REGENSBURG to MUNCHEN
        PhysicalConnection regensburgToMunchen = new PhysicalConnection(reichMap.get("Regensburg"), reichMap.get("Munchen"), 3, Colors.ORANGE);
        reichMap.get("Regensburg").addPhysicalConnection(regensburgToMunchen);
        reichMap.get("Munchen").addPhysicalConnection(regensburgToMunchen);

// REGENSBURG to OSTERREICH
        PhysicalConnection regensburgToOsterreich = new PhysicalConnection(reichMap.get("Regensburg"), reichMap.get("Osterreich"), 4, Colors.YELLOW);
        reichMap.get("Regensburg").addPhysicalConnection(regensburgToOsterreich);
        reichMap.get("Osterreich").addPhysicalConnection(regensburgToOsterreich);

// FRANKREICH to FREIBURG
        PhysicalConnection frankreichToFreiburg = new PhysicalConnection(reichMap.get("Frankreich"), reichMap.get("Freiburg"), 2, Colors.YELLOW);
        reichMap.get("Frankreich").addPhysicalConnection(frankreichToFreiburg);
        reichMap.get("Freiburg").addPhysicalConnection(frankreichToFreiburg);

// ULM to AUGSBURG
        PhysicalConnection ulmToAugsburg = new PhysicalConnection(reichMap.get("Ulm"), reichMap.get("Augsburg"), 1, Colors.GRAY);
        reichMap.get("Ulm").addPhysicalConnection(ulmToAugsburg);
        reichMap.get("Augsburg").addPhysicalConnection(ulmToAugsburg);
        ulmToAugsburg = new PhysicalConnection(reichMap.get("Ulm"), reichMap.get("Augsburg"), 1, Colors.GRAY);
        reichMap.get("Ulm").addPhysicalConnection(ulmToAugsburg);
        reichMap.get("Augsburg").addPhysicalConnection(ulmToAugsburg);

// ULM to LINDAU
        PhysicalConnection ulmToLindau = new PhysicalConnection(reichMap.get("Ulm"), reichMap.get("Lindau"), 2, Colors.RED);
        reichMap.get("Ulm").addPhysicalConnection(ulmToLindau);
        reichMap.get("Lindau").addPhysicalConnection(ulmToLindau);

// AUGSBURG to MUNCHEN
        PhysicalConnection augsburgToMunchen = new PhysicalConnection(reichMap.get("Augsburg"), reichMap.get("Munchen"), 2, Colors.WHITE);
        reichMap.get("Augsburg").addPhysicalConnection(augsburgToMunchen);
        reichMap.get("Munchen").addPhysicalConnection(augsburgToMunchen);
        augsburgToMunchen = new PhysicalConnection(reichMap.get("Augsburg"), reichMap.get("Munchen"), 2, Colors.GRAY);
        reichMap.get("Augsburg").addPhysicalConnection(augsburgToMunchen);
        reichMap.get("Munchen").addPhysicalConnection(augsburgToMunchen);

// MUNCHEN to OSTERREICH
        PhysicalConnection munchenToOsterreich = new PhysicalConnection(reichMap.get("Munchen"), reichMap.get("Osterreich"), 3, Colors.RED);
        reichMap.get("Munchen").addPhysicalConnection(munchenToOsterreich);
        reichMap.get("Osterreich").addPhysicalConnection(munchenToOsterreich);

// MUNCHEN to LINDAU
        PhysicalConnection munchenToLindau = new PhysicalConnection(reichMap.get("Munchen"), reichMap.get("Lindau"), 5, Colors.GRAY);
        reichMap.get("Munchen").addPhysicalConnection(munchenToLindau);
        reichMap.get("Lindau").addPhysicalConnection(munchenToLindau);

// FREIBURG to SCHWEIZ
        PhysicalConnection freiburgToSchweiz = new PhysicalConnection(reichMap.get("Freiburg"), reichMap.get("Schweiz"), 2, Colors.ORANGE);
        reichMap.get("Freiburg").addPhysicalConnection(freiburgToSchweiz);
        reichMap.get("Schweiz").addPhysicalConnection(freiburgToSchweiz);

// FREIBURG to KONSTANZ
        PhysicalConnection freiburgToKonstanz = new PhysicalConnection(reichMap.get("Freiburg"), reichMap.get("Konstanz"), 2, Colors.BLACK);
        reichMap.get("Freiburg").addPhysicalConnection(freiburgToKonstanz);
        reichMap.get("Konstanz").addPhysicalConnection(freiburgToKonstanz);

// KONSTANZ to SCHWEIZ
        PhysicalConnection konstanzToSchweiz = new PhysicalConnection(reichMap.get("Konstanz"), reichMap.get("Schweiz"), 1, Colors.RED);
        reichMap.get("Konstanz").addPhysicalConnection(konstanzToSchweiz);
        reichMap.get("Schweiz").addPhysicalConnection(konstanzToSchweiz);

// KONSTANZ to LINDAU
        PhysicalConnection konstanzToLindau = new PhysicalConnection(reichMap.get("Konstanz"), reichMap.get("Lindau"), 1, Colors.YELLOW);
        reichMap.get("Konstanz").addPhysicalConnection(konstanzToLindau);
        reichMap.get("Lindau").addPhysicalConnection(konstanzToLindau);

// LINDAU to SCHWEIZ
        PhysicalConnection lindauToSchweiz = new PhysicalConnection(reichMap.get("Lindau"), reichMap.get("Schweiz"), 2, Colors.BLUE);
        reichMap.get("Lindau").addPhysicalConnection(lindauToSchweiz);
        reichMap.get("Schweiz").addPhysicalConnection(lindauToSchweiz);

// LINDAU to OSTERREICH
        PhysicalConnection lindauToOsterreich = new PhysicalConnection(reichMap.get("Lindau"), reichMap.get("Osterreich"), 2, Colors.PINK);
        reichMap.get("Lindau").addPhysicalConnection(lindauToOsterreich);
        reichMap.get("Osterreich").addPhysicalConnection(lindauToOsterreich);

        return reichMap;
    }
}
