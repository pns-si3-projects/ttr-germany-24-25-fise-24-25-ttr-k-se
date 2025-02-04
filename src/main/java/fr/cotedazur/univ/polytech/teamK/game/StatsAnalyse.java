package fr.cotedazur.univ.polytech.teamK.game;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class StatsAnalyse {
    GameView gameView;
    public StatsAnalyse (GameView gameView) {
        this.gameView = gameView;
    }
    public void analyse() throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(new FileReader("stats/gameStats.csv"));
        CSVWriter writer = new CSVWriter(new FileWriter("stats/gameStats.csv"));
        ArrayList<Bot> seenBot = new ArrayList<>();
        String [] nextLine = reader.readNext();
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            if(gameView.getAllBotName().contains(nextLine[0])) {
                updateLine(nextLine);
                seenBot.add(gameView.getBotByName(nextLine[0]));
            }
        }
        reader.close();
        for(Bot bot : gameView.getAllBot()){
            if(!seenBot.contains(bot)) {
                String[] record = "2,Rahul,Vaidya,India,35".split(",");
                writer.writeNext(record, false);
            }
        }
        writer.close();
    }

    public String [] updateLine (String [] line) {

        return line;
    }
}
