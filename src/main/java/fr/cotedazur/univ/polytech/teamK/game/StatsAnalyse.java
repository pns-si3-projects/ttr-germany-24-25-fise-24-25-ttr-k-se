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
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class StatsAnalyse {
    private GameView gameView;
    private ScoreManager scoreManager;

    public StatsAnalyse (GameView gameView, ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        this.gameView = gameView;
    }

    public void analyse() throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(new FileReader("stats/gameStats.csv"));

        String nameWinner = scoreManager.getHighestScoreAndWinner().getKey().getName();
        ArrayList<Bot> seenBot = new ArrayList<>();
        String [] nextLine ;
        ArrayList<String> allBot = gameView.getAllBotName();

       //save the file
        ArrayList<String []> file = new ArrayList<>();
        while ((nextLine = reader.readNext()) != null) {
            file.add(nextLine);
        }
        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter("stats/gameStats.csv",false));
        String[] record = "BOT_NAME, NUMBER_GAME, NUMBER_WIN, RATIO, AVERAGE_SCORE, AVERAGE_CONNECTION_BOUGHT".split(",");
        writer.writeNext(record, false);

        for(String [] line : file) {
            if(allBot.contains(line[0])) {
                line[1] = "" + (parseInt(line[1]) + 1);
                if(Objects.equals(line[0], nameWinner)) line[2] = "" + (parseInt(line[2]) + 1);
                line[3] = "" + (parseInt(line[2])/ parseInt(line[1]) * 100) ;
                line[4] = "" + ((gameView.getMyScore() + (parseInt(line[4]))));
                allBot.remove(line[0]);
                writer.writeNext(line);
            }
        }
        for (String bot : allBot) {
            String info = bot + ",1,";
            if (Objects.equals(bot, nameWinner)) info += "1,100,";
            else info += "0,0,";
            info += gameView.getMyScore();
            record = info.split(",");
            writer.writeNext(record);
        }
        writer.close();
    }
}
