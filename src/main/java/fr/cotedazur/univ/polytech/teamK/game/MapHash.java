package fr.cotedazur.univ.polytech.teamK.game;

import fr.cotedazur.univ.polytech.teamK.board.Colors;
import fr.cotedazur.univ.polytech.teamK.board.map.City;
import fr.cotedazur.univ.polytech.teamK.board.map.PhysicalConnection;

import java.util.HashMap;

public class MapHash {
    private HashMap<Integer, City> cities;
    public MapHash(String name) {
        if (name.equals("Reich"))
        {
            cities = buildReichMap();
        }
    }

    public HashMap<Integer, City> getCities()
    {
        return cities;
    }
    private HashMap buildReichMap()
    {
        HashMap<Integer, City> reichMap = new HashMap<Integer, City>();

        reichMap.put(0, new City("Danemark", 1));
        reichMap.put(1, new City("Kiel", 1));
        reichMap.put(2, new City("Rostock", 1));
        reichMap.put(3, new City("Emden", 1));
        reichMap.put(4, new City("Bremerhaven", 1));
        reichMap.put(5, new City("Hamburg", 4));
        reichMap.put(6, new City("Schwerin", 1));
        reichMap.put(7, new City("Munster", 1));
        reichMap.put(8, new City("Hannover", 1));
        reichMap.put(9, new City("Berlin", 5));
        reichMap.put(10, new City("Dusseldorf", 1));
        reichMap.put(11, new City("Dortmund", 1));
        reichMap.put(12, new City("Kassel", 1));
        reichMap.put(13, new City("Magdeburg", 1));
        reichMap.put(14, new City("Leipzig", 3));
        reichMap.put(15, new City("Erfurt", 1));
        reichMap.put(16, new City("Dresden", 1));
        reichMap.put(17, new City("Chemnitz", 1));
        reichMap.put(18, new City("Koln", 4));
        reichMap.put(19, new City("Koblenz", 1));
        reichMap.put(20, new City("Frankfurt", 4));
        reichMap.put(21, new City("Mainz", 1));
        reichMap.put(22, new City("Wurzburg", 1));
        reichMap.put(23, new City("Nurnberg", 1));
        reichMap.put(24, new City("Regensburg", 1));
        reichMap.put(25, new City("Munchen", 4));
        reichMap.put(26, new City("Augsburg", 1));
        reichMap.put(27, new City("Ulm", 1));
        reichMap.put(28, new City("Stuttgart", 3));
        reichMap.put(29, new City("Karlsruhe", 1));
        reichMap.put(30, new City("Mannheim", 1));
        reichMap.put(31, new City("Saarbrucken", 1));
        reichMap.put(32, new City("Freiburg", 1));
        reichMap.put(33, new City("Konstanz", 1));
        reichMap.put(34, new City("Lindau", 1));
        reichMap.put(35, new City("Bremen", 1));
        reichMap.put(36, new City("Frankreich", 1));
        reichMap.put(37, new City("Schweiz", 1));
        reichMap.put(38, new City("Niederlande", 1));
        reichMap.put(39, new City("Osterreich", 1));

// DANEMARK to BREMERHAVEN
        PhysicalConnection danemarkToBremerhaven = new PhysicalConnection(reichMap.get(0), reichMap.get(4), 5, Colors.BLACK);
        reichMap.get(0).addPhysicalConnection(danemarkToBremerhaven);
        reichMap.get(4).addPhysicalConnection(danemarkToBremerhaven);

// DANEMARK to KIEL
        PhysicalConnection danemarkToKiel = new PhysicalConnection(reichMap.get(0), reichMap.get(1), 2, Colors.GRAY);
        reichMap.get(0).addPhysicalConnection(danemarkToKiel);
        reichMap.get(1).addPhysicalConnection(danemarkToKiel);

// KIEL to BREMERHAVEN
        PhysicalConnection kielToBremerhaven = new PhysicalConnection(reichMap.get(1), reichMap.get(4), 3, Colors.GRAY);
        reichMap.get(1).addPhysicalConnection(kielToBremerhaven);
        reichMap.get(4).addPhysicalConnection(kielToBremerhaven);
// KIEL TO HAMBURG
        PhysicalConnection kielToHamburg = new PhysicalConnection(reichMap.get(1), reichMap.get(5), 2, Colors.BLACK);
        reichMap.get(1).addPhysicalConnection(kielToHamburg);
        reichMap.get(5).addPhysicalConnection(kielToHamburg);
        kielToHamburg = new PhysicalConnection(reichMap.get(1), reichMap.get(5), 2, Colors.PINK);
        reichMap.get(1).addPhysicalConnection(kielToHamburg);
        reichMap.get(5).addPhysicalConnection(kielToHamburg);
// KIEL TO SCHWERIN
        PhysicalConnection kielToSchwerin = new PhysicalConnection(reichMap.get(1), reichMap.get(6), 3, Colors.YELLOW);
        reichMap.get(1).addPhysicalConnection(kielToSchwerin);
        reichMap.get(6).addPhysicalConnection(kielToSchwerin);

        PhysicalConnection kielToRostock = new PhysicalConnection(reichMap.get(1), reichMap.get(2), 4, Colors.ORANGE);
        reichMap.get(1).addPhysicalConnection(kielToRostock);
        reichMap.get(2).addPhysicalConnection(kielToRostock);

        PhysicalConnection rostockToSchwerin = new PhysicalConnection(reichMap.get(2), reichMap.get(6), 2, Colors.RED);
        reichMap.get(2).addPhysicalConnection(rostockToSchwerin);
        reichMap.get(6).addPhysicalConnection(rostockToSchwerin);

        PhysicalConnection rostockToBerlin = new PhysicalConnection(reichMap.get(2), reichMap.get(9), 6, Colors.PINK);
        reichMap.get(2).addPhysicalConnection(rostockToBerlin);
        reichMap.get(9).addPhysicalConnection(rostockToBerlin);

        PhysicalConnection schwerinToBerlin = new PhysicalConnection(reichMap.get(6), reichMap.get(9), 5, Colors.WHITE);
        reichMap.get(6).addPhysicalConnection(schwerinToBerlin);
        reichMap.get(9).addPhysicalConnection(schwerinToBerlin);

        PhysicalConnection schwerinToHamburg = new PhysicalConnection(reichMap.get(6), reichMap.get(5), 2, Colors.RED);
        reichMap.get(6).addPhysicalConnection(schwerinToHamburg);
        reichMap.get(5).addPhysicalConnection(schwerinToHamburg);

        PhysicalConnection hamburgToBerlin = new PhysicalConnection(reichMap.get(5), reichMap.get(9), 7, Colors.BLUE);
        reichMap.get(5).addPhysicalConnection(hamburgToBerlin);
        reichMap.get(9).addPhysicalConnection(hamburgToBerlin);
        hamburgToBerlin = new PhysicalConnection(reichMap.get(5), reichMap.get(9), 7, Colors.YELLOW);
        reichMap.get(5).addPhysicalConnection(hamburgToBerlin);
        reichMap.get(9).addPhysicalConnection(hamburgToBerlin);

        PhysicalConnection hamburgToHannover = new PhysicalConnection(reichMap.get(5), reichMap.get(8), 4, Colors.RED);
        reichMap.get(5).addPhysicalConnection(hamburgToHannover);
        reichMap.get(8).addPhysicalConnection(hamburgToHannover);
        hamburgToHannover = new PhysicalConnection(reichMap.get(5), reichMap.get(8), 4, Colors.WHITE);
        reichMap.get(5).addPhysicalConnection(hamburgToHannover);
        reichMap.get(8).addPhysicalConnection(hamburgToHannover);

        PhysicalConnection hamburgToBremerhaven = new PhysicalConnection(reichMap.get(5), reichMap.get(4), 3, Colors.GRAY);
        reichMap.get(5).addPhysicalConnection(hamburgToBremerhaven);
        reichMap.get(4).addPhysicalConnection(hamburgToBremerhaven);

        PhysicalConnection hamburgToBremen = new PhysicalConnection(reichMap.get(5), reichMap.get(35), 3, Colors.ORANGE);
        reichMap.get(5).addPhysicalConnection(hamburgToBremen);
        reichMap.get(35).addPhysicalConnection(hamburgToBremen);

        PhysicalConnection bremerhavenToBremen = new PhysicalConnection(reichMap.get(4), reichMap.get(35), 1, Colors.WHITE);
        reichMap.get(4).addPhysicalConnection(bremerhavenToBremen);
        reichMap.get(35).addPhysicalConnection(bremerhavenToBremen);

        PhysicalConnection bremerhavenToEmden = new PhysicalConnection(reichMap.get(4), reichMap.get(3), 3, Colors.GRAY);
        reichMap.get(4).addPhysicalConnection(bremerhavenToEmden);
        reichMap.get(3).addPhysicalConnection(bremerhavenToEmden);

        PhysicalConnection emdenToNiederlande = new PhysicalConnection(reichMap.get(3), reichMap.get(38), 2, Colors.WHITE);
        reichMap.get(3).addPhysicalConnection(emdenToNiederlande);
        reichMap.get(38).addPhysicalConnection(emdenToNiederlande);

        PhysicalConnection emdenToMunster = new PhysicalConnection(reichMap.get(3), reichMap.get(7), 4, Colors.RED);
        reichMap.get(3).addPhysicalConnection(emdenToMunster);
        reichMap.get(7).addPhysicalConnection(emdenToMunster);

        PhysicalConnection emdenToBremen = new PhysicalConnection(reichMap.get(3), reichMap.get(35), 3, Colors.BLUE);
        reichMap.get(3).addPhysicalConnection(emdenToBremen);
        reichMap.get(35).addPhysicalConnection(emdenToBremen);

        PhysicalConnection bremenToHannover = new PhysicalConnection(reichMap.get(35), reichMap.get(8), 3, Colors.PINK);
        reichMap.get(35).addPhysicalConnection(bremenToHannover);
        reichMap.get(8).addPhysicalConnection(bremenToHannover);

        PhysicalConnection bremenToMunster = new PhysicalConnection(reichMap.get(35), reichMap.get(7), 3, Colors.BLACK);
        reichMap.get(35).addPhysicalConnection(bremenToMunster);
        reichMap.get(7).addPhysicalConnection(bremenToMunster);

        PhysicalConnection niederlandeToMunster = new PhysicalConnection(reichMap.get(38), reichMap.get(7), 2, Colors.ORANGE);
        reichMap.get(38).addPhysicalConnection(niederlandeToMunster);
        reichMap.get(7).addPhysicalConnection(niederlandeToMunster);

        PhysicalConnection niederlandeToDusseldorf = new PhysicalConnection(reichMap.get(38), reichMap.get(10), 3, Colors.PINK);
        reichMap.get(38).addPhysicalConnection(niederlandeToDusseldorf);
        reichMap.get(10).addPhysicalConnection(niederlandeToDusseldorf);

        PhysicalConnection berlinToHannover = new PhysicalConnection(reichMap.get(9), reichMap.get(8), 7, Colors.YELLOW);
        reichMap.get(9).addPhysicalConnection(berlinToHannover);
        reichMap.get(8).addPhysicalConnection(berlinToHannover);

        PhysicalConnection berlinToMagdeburg = new PhysicalConnection(reichMap.get(9), reichMap.get(13), 3, Colors.RED);
        reichMap.get(9).addPhysicalConnection(berlinToMagdeburg);
        reichMap.get(13).addPhysicalConnection(berlinToMagdeburg);

        PhysicalConnection berlinToLeipzig = new PhysicalConnection(reichMap.get(9), reichMap.get(14), 4, Colors.PINK);
        reichMap.get(9).addPhysicalConnection(berlinToLeipzig);
        reichMap.get(14).addPhysicalConnection(berlinToLeipzig);

        PhysicalConnection berlinToDresden = new PhysicalConnection(reichMap.get(9), reichMap.get(16), 5, Colors.GREEN);
        reichMap.get(9).addPhysicalConnection(berlinToDresden);
        reichMap.get(16).addPhysicalConnection(berlinToDresden);

        PhysicalConnection hannoverToMagdeburg = new PhysicalConnection(reichMap.get(8), reichMap.get(13), 4, Colors.BLUE);
        reichMap.get(8).addPhysicalConnection(hannoverToMagdeburg);
        reichMap.get(13).addPhysicalConnection(hannoverToMagdeburg);

        PhysicalConnection hannoverToErfurt = new PhysicalConnection(reichMap.get(8), reichMap.get(15), 5, Colors.ORANGE);
        reichMap.get(8).addPhysicalConnection(hannoverToErfurt);
        reichMap.get(15).addPhysicalConnection(hannoverToErfurt);
        hannoverToErfurt = new PhysicalConnection(reichMap.get(8), reichMap.get(15), 5, Colors.GREEN);
        reichMap.get(8).addPhysicalConnection(hannoverToErfurt);
        reichMap.get(15).addPhysicalConnection(hannoverToErfurt);

        PhysicalConnection hannoverToKassel = new PhysicalConnection(reichMap.get(8), reichMap.get(12), 3, Colors.GRAY);
        reichMap.get(8).addPhysicalConnection(hannoverToKassel);
        reichMap.get(12).addPhysicalConnection(hannoverToKassel);
        hannoverToKassel = new PhysicalConnection(reichMap.get(8), reichMap.get(12), 3, Colors.GRAY);
        reichMap.get(8).addPhysicalConnection(hannoverToKassel);
        reichMap.get(12).addPhysicalConnection(hannoverToKassel);

        PhysicalConnection hannoverToMunster = new PhysicalConnection(reichMap.get(8), reichMap.get(7), 4, Colors.YELLOW);
        reichMap.get(8).addPhysicalConnection(hannoverToMunster);
        reichMap.get(7).addPhysicalConnection(hannoverToMunster);

        PhysicalConnection munsterToDortmund = new PhysicalConnection(reichMap.get(7), reichMap.get(11), 1, Colors.GRAY);
        reichMap.get(7).addPhysicalConnection(munsterToDortmund);
        reichMap.get(11).addPhysicalConnection(munsterToDortmund);
        munsterToDortmund = new PhysicalConnection(reichMap.get(7), reichMap.get(11), 1, Colors.GRAY);
        reichMap.get(7).addPhysicalConnection(munsterToDortmund);
        reichMap.get(11).addPhysicalConnection(munsterToDortmund);

// MAGDEBURG to LEIPZIG
        PhysicalConnection magdeburgToLeipzig = new PhysicalConnection(reichMap.get(13), reichMap.get(14), 2, Colors.YELLOW);
        reichMap.get(13).addPhysicalConnection(magdeburgToLeipzig);
        reichMap.get(14).addPhysicalConnection(magdeburgToLeipzig);

// DORTMUND to DUSSELDORF
        PhysicalConnection dortmundToDusseldorf = new PhysicalConnection(reichMap.get(11), reichMap.get(10), 1, Colors.GRAY);
        reichMap.get(11).addPhysicalConnection(dortmundToDusseldorf);
        reichMap.get(10).addPhysicalConnection(dortmundToDusseldorf);
        dortmundToDusseldorf = new PhysicalConnection(reichMap.get(11), reichMap.get(10), 1, Colors.GRAY);
        reichMap.get(11).addPhysicalConnection(dortmundToDusseldorf);
        reichMap.get(10).addPhysicalConnection(dortmundToDusseldorf);
        dortmundToDusseldorf = new PhysicalConnection(reichMap.get(11), reichMap.get(10), 1, Colors.GRAY);
        reichMap.get(11).addPhysicalConnection(dortmundToDusseldorf);
        reichMap.get(10).addPhysicalConnection(dortmundToDusseldorf);

// DORTMUND to KASSEL
        PhysicalConnection dortmundToKassel = new PhysicalConnection(reichMap.get(11), reichMap.get(12), 4, Colors.GREEN);
        reichMap.get(11).addPhysicalConnection(dortmundToKassel);
        reichMap.get(12).addPhysicalConnection(dortmundToKassel);

// DUSSELDORF to KOLN
        PhysicalConnection dusseldorfToKoln = new PhysicalConnection(reichMap.get(10), reichMap.get(18), 1, Colors.GRAY);
        reichMap.get(10).addPhysicalConnection(dusseldorfToKoln);
        reichMap.get(18).addPhysicalConnection(dusseldorfToKoln);
        dusseldorfToKoln = new PhysicalConnection(reichMap.get(10), reichMap.get(18), 1, Colors.GRAY);
        reichMap.get(10).addPhysicalConnection(dusseldorfToKoln);
        reichMap.get(18).addPhysicalConnection(dusseldorfToKoln);dusseldorfToKoln = new PhysicalConnection(reichMap.get(10), reichMap.get(18), 1, Colors.GRAY);
        reichMap.get(10).addPhysicalConnection(dusseldorfToKoln);
        reichMap.get(18).addPhysicalConnection(dusseldorfToKoln);

// LEIPZIG to DRESDEN
        PhysicalConnection leipzigToDresden = new PhysicalConnection(reichMap.get(14), reichMap.get(16), 3, Colors.BLACK);
        reichMap.get(14).addPhysicalConnection(leipzigToDresden);
        reichMap.get(16).addPhysicalConnection(leipzigToDresden);

// LEIPZIG to CHEMNITZ
        PhysicalConnection leipzigToChemnitz = new PhysicalConnection(reichMap.get(14), reichMap.get(17), 2, Colors.BLUE);
        reichMap.get(14).addPhysicalConnection(leipzigToChemnitz);
        reichMap.get(17).addPhysicalConnection(leipzigToChemnitz);

// LEIPZIG to ERFURT
        PhysicalConnection leipzigToErfurt = new PhysicalConnection(reichMap.get(14), reichMap.get(15), 3, Colors.YELLOW);
        reichMap.get(14).addPhysicalConnection(leipzigToErfurt);
        reichMap.get(15).addPhysicalConnection(leipzigToErfurt);

// KASSEL to ERFURT
        PhysicalConnection kasselToErfurt = new PhysicalConnection(reichMap.get(12), reichMap.get(15), 3, Colors.GRAY);
        reichMap.get(12).addPhysicalConnection(kasselToErfurt);
        reichMap.get(15).addPhysicalConnection(kasselToErfurt);

// KASSEL to FRANKFURT
        PhysicalConnection kasselToFrankfurt = new PhysicalConnection(reichMap.get(12), reichMap.get(20), 4, Colors.BLUE);
        reichMap.get(12).addPhysicalConnection(kasselToFrankfurt);
        reichMap.get(20).addPhysicalConnection(kasselToFrankfurt);
        kasselToFrankfurt = new PhysicalConnection(reichMap.get(12), reichMap.get(20), 4, Colors.GRAY);
        reichMap.get(12).addPhysicalConnection(kasselToFrankfurt);
        reichMap.get(20).addPhysicalConnection(kasselToFrankfurt);

// KOLN to FRANKFURT
        PhysicalConnection kolnToFrankfurt = new PhysicalConnection(reichMap.get(18), reichMap.get(20), 4, Colors.GRAY);
        reichMap.get(18).addPhysicalConnection(kolnToFrankfurt);
        reichMap.get(20).addPhysicalConnection(kolnToFrankfurt);
        kolnToFrankfurt = new PhysicalConnection(reichMap.get(18), reichMap.get(20), 4, Colors.WHITE);
        reichMap.get(18).addPhysicalConnection(kolnToFrankfurt);
        reichMap.get(20).addPhysicalConnection(kolnToFrankfurt);

// KOLN to KOBLENZ
        PhysicalConnection kolnToKoblenz = new PhysicalConnection(reichMap.get(18), reichMap.get(19), 1, Colors.GRAY);
        reichMap.get(18).addPhysicalConnection(kolnToKoblenz);
        reichMap.get(19).addPhysicalConnection(kolnToKoblenz);
        kolnToKoblenz = new PhysicalConnection(reichMap.get(18), reichMap.get(19), 1, Colors.GRAY);
        reichMap.get(18).addPhysicalConnection(kolnToKoblenz);
        reichMap.get(19).addPhysicalConnection(kolnToKoblenz);

// ERFURT to NURNBERG
        PhysicalConnection erfurtToNurnberg = new PhysicalConnection(reichMap.get(15), reichMap.get(23), 4, Colors.RED);
        reichMap.get(15).addPhysicalConnection(erfurtToNurnberg);
        reichMap.get(23).addPhysicalConnection(erfurtToNurnberg);
        erfurtToNurnberg = new PhysicalConnection(reichMap.get(15), reichMap.get(23), 4, Colors.BLUE);
        reichMap.get(15).addPhysicalConnection(erfurtToNurnberg);
        reichMap.get(23).addPhysicalConnection(erfurtToNurnberg);

// ERFURT to REGENSBURG
        PhysicalConnection erfurtToRegensburg = new PhysicalConnection(reichMap.get(15), reichMap.get(24), 7, Colors.WHITE);
        reichMap.get(15).addPhysicalConnection(erfurtToRegensburg);
        reichMap.get(24).addPhysicalConnection(erfurtToRegensburg);

// ERFURT to CHEMNITZ
        PhysicalConnection erfurtToChemnitz = new PhysicalConnection(reichMap.get(15), reichMap.get(17), 3, Colors.BLACK);
        reichMap.get(15).addPhysicalConnection(erfurtToChemnitz);
        reichMap.get(17).addPhysicalConnection(erfurtToChemnitz);

// DRESDEN to CHEMNITZ
        PhysicalConnection dresdenToChemnitz = new PhysicalConnection(reichMap.get(16), reichMap.get(17), 1, Colors.YELLOW);
        reichMap.get(16).addPhysicalConnection(dresdenToChemnitz);
        reichMap.get(17).addPhysicalConnection(dresdenToChemnitz);

// DRESDEN to REGENSBURG
        PhysicalConnection dresdenToRegensburg = new PhysicalConnection(reichMap.get(16), reichMap.get(24), 7, Colors.RED);
        reichMap.get(16).addPhysicalConnection(dresdenToRegensburg);
        reichMap.get(24).addPhysicalConnection(dresdenToRegensburg);

// CHEMNITZ to REGENSBURG
        PhysicalConnection chemnitzToRegensburg = new PhysicalConnection(reichMap.get(17), reichMap.get(24), 6, Colors.PINK);
        reichMap.get(17).addPhysicalConnection(chemnitzToRegensburg);
        reichMap.get(24).addPhysicalConnection(chemnitzToRegensburg);

// KOBLENZ to MAINZ
        PhysicalConnection koblenzToMainz = new PhysicalConnection(reichMap.get(19), reichMap.get(21), 2, Colors.GRAY);
        reichMap.get(19).addPhysicalConnection(koblenzToMainz);
        reichMap.get(21).addPhysicalConnection(koblenzToMainz);
        koblenzToMainz = new PhysicalConnection(reichMap.get(19), reichMap.get(21), 2, Colors.GRAY);
        reichMap.get(19).addPhysicalConnection(koblenzToMainz);
        reichMap.get(21).addPhysicalConnection(koblenzToMainz);

// KOBLENZ to SAARBRUCKEN
        PhysicalConnection koblenzToSaarbrucken = new PhysicalConnection(reichMap.get(19), reichMap.get(31), 3, Colors.GRAY);
        reichMap.get(19).addPhysicalConnection(koblenzToSaarbrucken);
        reichMap.get(31).addPhysicalConnection(koblenzToSaarbrucken);

// FRANKFURT to MAINZ
        PhysicalConnection frankfurtToMainz = new PhysicalConnection(reichMap.get(20), reichMap.get(21), 1, Colors.WHITE);
        reichMap.get(20).addPhysicalConnection(frankfurtToMainz);
        reichMap.get(21).addPhysicalConnection(frankfurtToMainz);
        frankfurtToMainz = new PhysicalConnection(reichMap.get(20), reichMap.get(21), 1, Colors.GRAY);
        reichMap.get(20).addPhysicalConnection(frankfurtToMainz);
        reichMap.get(21).addPhysicalConnection(frankfurtToMainz);

// FRANKFURT to MANNHEIM
        PhysicalConnection frankfurtToMannheim = new PhysicalConnection(reichMap.get(20), reichMap.get(30), 2, Colors.GRAY);
        reichMap.get(20).addPhysicalConnection(frankfurtToMannheim);
        reichMap.get(30).addPhysicalConnection(frankfurtToMannheim);
        frankfurtToMannheim = new PhysicalConnection(reichMap.get(20), reichMap.get(30), 2, Colors.GRAY);
        reichMap.get(20).addPhysicalConnection(frankfurtToMannheim);
        reichMap.get(30).addPhysicalConnection(frankfurtToMannheim);

// FRANKFURT to WURZBURG
        PhysicalConnection frankfurtToWurzburg = new PhysicalConnection(reichMap.get(20), reichMap.get(22), 2, Colors.GRAY);
        reichMap.get(20).addPhysicalConnection(frankfurtToWurzburg);
        reichMap.get(22).addPhysicalConnection(frankfurtToWurzburg);
        frankfurtToWurzburg = new PhysicalConnection(reichMap.get(20), reichMap.get(22), 2, Colors.BLACK);
        reichMap.get(20).addPhysicalConnection(frankfurtToWurzburg);
        reichMap.get(22).addPhysicalConnection(frankfurtToWurzburg);

// MAINZ to SAARBRUCKEN
        PhysicalConnection mainzToSaarbrucken = new PhysicalConnection(reichMap.get(21), reichMap.get(31), 3, Colors.GRAY);
        reichMap.get(21).addPhysicalConnection(mainzToSaarbrucken);
        reichMap.get(31).addPhysicalConnection(mainzToSaarbrucken);

// MAINZ to MANNHEIM
        PhysicalConnection mainzToMannheim = new PhysicalConnection(reichMap.get(21), reichMap.get(30), 1, Colors.GRAY);
        reichMap.get(21).addPhysicalConnection(mainzToMannheim);
        reichMap.get(30).addPhysicalConnection(mainzToMannheim);
        mainzToMannheim = new PhysicalConnection(reichMap.get(21), reichMap.get(30), 1,  Colors.YELLOW);
        reichMap.get(21).addPhysicalConnection(mainzToMannheim);
        reichMap.get(30).addPhysicalConnection(mainzToMannheim);

// WURZBURG to NURNBERG
        PhysicalConnection wurzburgToNurnberg = new PhysicalConnection(reichMap.get(22), reichMap.get(23), 2, Colors.GRAY);
        reichMap.get(22).addPhysicalConnection(wurzburgToNurnberg);
        reichMap.get(23).addPhysicalConnection(wurzburgToNurnberg);
        wurzburgToNurnberg = new PhysicalConnection(reichMap.get(22), reichMap.get(23), 2, Colors.YELLOW);
        reichMap.get(22).addPhysicalConnection(wurzburgToNurnberg);
        reichMap.get(23).addPhysicalConnection(wurzburgToNurnberg);

// NURNBERG to AUGSBURG
        PhysicalConnection nurnbergToAugsburg = new PhysicalConnection(reichMap.get(23), reichMap.get(26), 4, Colors.BLACK);
        reichMap.get(23).addPhysicalConnection(nurnbergToAugsburg);
        reichMap.get(26).addPhysicalConnection(nurnbergToAugsburg);

// NURNBERG to MUNCHEN
        PhysicalConnection nurnbergToMunchen = new PhysicalConnection(reichMap.get(23), reichMap.get(25), 5, Colors.BLUE);
        reichMap.get(23).addPhysicalConnection(nurnbergToMunchen);
        reichMap.get(25).addPhysicalConnection(nurnbergToMunchen);
        nurnbergToMunchen = new PhysicalConnection(reichMap.get(23), reichMap.get(25), 5, Colors.GRAY);
        reichMap.get(23).addPhysicalConnection(nurnbergToMunchen);
        reichMap.get(25).addPhysicalConnection(nurnbergToMunchen);

// NURNBERG to REGENSBURG
        PhysicalConnection nurnbergToRegensburg = new PhysicalConnection(reichMap.get(23), reichMap.get(24), 3, Colors.GREEN);
        reichMap.get(23).addPhysicalConnection(nurnbergToRegensburg);
        reichMap.get(24).addPhysicalConnection(nurnbergToRegensburg);

// MANNHEIM to SAARBRUCKEN
        PhysicalConnection mannheimToSaarbrucken = new PhysicalConnection(reichMap.get(30), reichMap.get(31), 3, Colors.GRAY);
        reichMap.get(30).addPhysicalConnection(mannheimToSaarbrucken);
        reichMap.get(31).addPhysicalConnection(mannheimToSaarbrucken);

// MANNHEIM to KARLSRUHE
        PhysicalConnection mannheimToKarlsruhe = new PhysicalConnection(reichMap.get(30), reichMap.get(29), 1, Colors.BLUE);
        reichMap.get(30).addPhysicalConnection(mannheimToKarlsruhe);
        reichMap.get(29).addPhysicalConnection(mannheimToKarlsruhe);

// MANNHEIM to STUTTGART
        PhysicalConnection mannheimToStuttgart = new PhysicalConnection(reichMap.get(30), reichMap.get(28), 2, Colors.GRAY);
        reichMap.get(30).addPhysicalConnection(mannheimToStuttgart);
        reichMap.get(28).addPhysicalConnection(mannheimToStuttgart);
        mannheimToStuttgart = new PhysicalConnection(reichMap.get(30), reichMap.get(28), 2, Colors.GRAY);
        reichMap.get(30).addPhysicalConnection(mannheimToStuttgart);
        reichMap.get(28).addPhysicalConnection(mannheimToStuttgart);

// SAARBRUCKEN to FRANKREICH
        PhysicalConnection saarbruckenToFrankreich = new PhysicalConnection(reichMap.get(31), reichMap.get(36), 1, Colors.GREEN);
        reichMap.get(31).addPhysicalConnection(saarbruckenToFrankreich);
        reichMap.get(36).addPhysicalConnection(saarbruckenToFrankreich);

// SAARBRUCKEN to KARLSRUHE
        PhysicalConnection saarbruckenToKarlsruhe = new PhysicalConnection(reichMap.get(31), reichMap.get(29), 3, Colors.BLACK);
        reichMap.get(31).addPhysicalConnection(saarbruckenToKarlsruhe);
        reichMap.get(29).addPhysicalConnection(saarbruckenToKarlsruhe);

// KARLSRUHE to FRANKREICH
        PhysicalConnection karlsruheToFrankreich = new PhysicalConnection(reichMap.get(29), reichMap.get(36), 2, Colors.BLACK);
        reichMap.get(29).addPhysicalConnection(karlsruheToFrankreich);
        reichMap.get(36).addPhysicalConnection(karlsruheToFrankreich);

// KARLSRUHE to FREIBURG
        PhysicalConnection karlsruheToFreiburg = new PhysicalConnection(reichMap.get(29), reichMap.get(32), 3, Colors.WHITE);
        reichMap.get(29).addPhysicalConnection(karlsruheToFreiburg);
        reichMap.get(32).addPhysicalConnection(karlsruheToFreiburg);

// KARLSRUHE to STUTTGART
        PhysicalConnection karlsruheToStuttgart = new PhysicalConnection(reichMap.get(29), reichMap.get(28), 1, Colors.PINK);
        reichMap.get(29).addPhysicalConnection(karlsruheToStuttgart);
        reichMap.get(28).addPhysicalConnection(karlsruheToStuttgart);


        // STUTTGART to FREIBURG
        PhysicalConnection stuttgartToFreiburg = new PhysicalConnection(reichMap.get(28), reichMap.get(32), 3, Colors.RED);
        reichMap.get(28).addPhysicalConnection(stuttgartToFreiburg);
        reichMap.get(32).addPhysicalConnection(stuttgartToFreiburg);

// STUTTGART to KONSTANZ
        PhysicalConnection stuttgartToKonstanz = new PhysicalConnection(reichMap.get(28), reichMap.get(33), 3, Colors.GREEN);
        reichMap.get(28).addPhysicalConnection(stuttgartToKonstanz);
        reichMap.get(33).addPhysicalConnection(stuttgartToKonstanz);

// STUTTGART to ULM
        PhysicalConnection stuttgartToUlm = new PhysicalConnection(reichMap.get(28), reichMap.get(27), 2, Colors.GRAY);
        reichMap.get(28).addPhysicalConnection(stuttgartToUlm);
        reichMap.get(27).addPhysicalConnection(stuttgartToUlm);
        stuttgartToUlm = new PhysicalConnection(reichMap.get(28), reichMap.get(27), 2, Colors.GRAY);
        reichMap.get(28).addPhysicalConnection(stuttgartToUlm);
        reichMap.get(27).addPhysicalConnection(stuttgartToUlm);

// REGENSBURG to MUNCHEN
        PhysicalConnection regensburgToMunchen = new PhysicalConnection(reichMap.get(24), reichMap.get(25), 3, Colors.ORANGE);
        reichMap.get(24).addPhysicalConnection(regensburgToMunchen);
        reichMap.get(25).addPhysicalConnection(regensburgToMunchen);

// REGENSBURG to OSTERREICH
        PhysicalConnection regensburgToOsterreich = new PhysicalConnection(reichMap.get(24), reichMap.get(39), 4, Colors.YELLOW);
        reichMap.get(24).addPhysicalConnection(regensburgToOsterreich);
        reichMap.get(39).addPhysicalConnection(regensburgToOsterreich);

// FRANKREICH to FREIBURG
        PhysicalConnection frankreichToFreiburg = new PhysicalConnection(reichMap.get(36), reichMap.get(32), 2, Colors.YELLOW);
        reichMap.get(36).addPhysicalConnection(frankreichToFreiburg);
        reichMap.get(32).addPhysicalConnection(frankreichToFreiburg);

// ULM to AUGSBURG
        PhysicalConnection ulmToAugsburg = new PhysicalConnection(reichMap.get(27), reichMap.get(26), 1, Colors.GRAY);
        reichMap.get(27).addPhysicalConnection(ulmToAugsburg);
        reichMap.get(26).addPhysicalConnection(ulmToAugsburg);
        ulmToAugsburg = new PhysicalConnection(reichMap.get(27), reichMap.get(26), 1, Colors.GRAY);
        reichMap.get(27).addPhysicalConnection(ulmToAugsburg);
        reichMap.get(26).addPhysicalConnection(ulmToAugsburg);

// ULM to LINDAU
        PhysicalConnection ulmToLindau = new PhysicalConnection(reichMap.get(27), reichMap.get(34), 2, Colors.RED);
        reichMap.get(27).addPhysicalConnection(ulmToLindau);
        reichMap.get(34).addPhysicalConnection(ulmToLindau);

// AUGSBURG to MUNCHEN
        PhysicalConnection augsburgToMunchen = new PhysicalConnection(reichMap.get(26), reichMap.get(25), 2, Colors.WHITE);
        reichMap.get(26).addPhysicalConnection(augsburgToMunchen);
        reichMap.get(25).addPhysicalConnection(augsburgToMunchen);
        augsburgToMunchen = new PhysicalConnection(reichMap.get(26), reichMap.get(25), 2, Colors.GRAY);
        reichMap.get(26).addPhysicalConnection(augsburgToMunchen);
        reichMap.get(25).addPhysicalConnection(augsburgToMunchen);

// MUNCHEN to OSTERREICH
        PhysicalConnection munchenToOsterreich = new PhysicalConnection(reichMap.get(25), reichMap.get(39), 3, Colors.RED);
        reichMap.get(25).addPhysicalConnection(munchenToOsterreich);
        reichMap.get(39).addPhysicalConnection(munchenToOsterreich);

// MUNCHEN to LINDAU
        PhysicalConnection munchenToLindau = new PhysicalConnection(reichMap.get(25), reichMap.get(34), 5, Colors.GRAY);
        reichMap.get(25).addPhysicalConnection(munchenToLindau);
        reichMap.get(34).addPhysicalConnection(munchenToLindau);

// FREIBURG to SCHWEIZ
        PhysicalConnection freiburgToSchweiz = new PhysicalConnection(reichMap.get(32), reichMap.get(37), 2, Colors.ORANGE);
        reichMap.get(32).addPhysicalConnection(freiburgToSchweiz);
        reichMap.get(37).addPhysicalConnection(freiburgToSchweiz);

// FREIBURG to KONSTANZ
        PhysicalConnection freiburgToKonstanz = new PhysicalConnection(reichMap.get(32), reichMap.get(33), 2, Colors.BLACK);
        reichMap.get(32).addPhysicalConnection(freiburgToKonstanz);
        reichMap.get(33).addPhysicalConnection(freiburgToKonstanz);

// KONSTANZ to SCHWEIZ
        PhysicalConnection konstanzToSchweiz = new PhysicalConnection(reichMap.get(33), reichMap.get(37), 1, Colors.RED);
        reichMap.get(33).addPhysicalConnection(konstanzToSchweiz);
        reichMap.get(37).addPhysicalConnection(konstanzToSchweiz);

// KONSTANZ to LINDAU
        PhysicalConnection konstanzToLindau = new PhysicalConnection(reichMap.get(33), reichMap.get(34), 1, Colors.YELLOW);
        reichMap.get(33).addPhysicalConnection(konstanzToLindau);
        reichMap.get(34).addPhysicalConnection(konstanzToLindau);

// LINDAU to SCHWEIZ
        PhysicalConnection lindauToSchweiz = new PhysicalConnection(reichMap.get(34), reichMap.get(37), 2, Colors.BLUE);
        reichMap.get(34).addPhysicalConnection(lindauToSchweiz);
        reichMap.get(37).addPhysicalConnection(lindauToSchweiz);

// LINDAU to OSTERREICH
        PhysicalConnection lindauToOsterreich = new PhysicalConnection(reichMap.get(34), reichMap.get(39), 2, Colors.PINK);
        reichMap.get(34).addPhysicalConnection(lindauToOsterreich);
        reichMap.get(39).addPhysicalConnection(lindauToOsterreich);

        return reichMap;
    }
}
