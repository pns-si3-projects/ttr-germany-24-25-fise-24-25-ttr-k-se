package fr.cotedazur.univ.polytech.teamK.game;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class StatsAnalyse {
    private ScoreGeneralManager scoreManager;
    private ArrayList<String> allBot ;

    /**
     * The {@code StatsAnalyse} class is responsible for analyzing game statistics.
     * It updates a CSV file containing statistics about bot performances,
     * including the number of games played, number of wins, win ratio, and total score.
     */
    public StatsAnalyse (GameEngine gameEngine, GameView gameView) {
        this.scoreManager = gameEngine.getScoreManager();
        this.allBot = gameView.getAllBotName();
    }

    /**
     * Reads and updates the statistics file "stats/gameStats.csv".
     * The method updates the number of games played, number of wins, win ratio, and total score
     * for each bot based on the latest game results.
     *
     * @throws CsvValidationException If an error occurs while reading the CSV file.
     * @throws IOException            If an I/O error occurs while accessing the CSV file.
     */
    public void analyse() throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(new FileReader("stats/gameStats.csv"));

        String nameWinner = scoreManager.getHighestScoreAndWinner().getKey().getName();
        ArrayList<Bot> seenBot = new ArrayList<>();
        String [] nextLine ;


       //save the file
        ArrayList<String []> file = new ArrayList<>();
        while ((nextLine = reader.readNext()) != null) {
            file.add(nextLine);
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter("stats/gameStats.csv", false))) {
            String[] record = "BOT_NAME, NUMBER_GAME, NUMBER_WIN, RATIO, TOTAL_SCORE".split(",");
            writer.writeNext(record, false);

            for(String [] line : file) {
                if(allBot.contains(line[0])) {
                    line[1] = "" + (parseInt(line[1]) + 1);
                    if(Objects.equals(line[0], nameWinner)) line[2] = "" + (parseInt(line[2]) + 1);
                    line[3] = "" + ((float) (parseInt(line[2]) / (float) parseInt(line[1])) * 100) ;
                    line[4] = "" + (scoreManager.getPlayerScore(line[0]) + parseInt(line[4]) );
                    allBot.remove(line[0]);
                    writer.writeNext(line);
                }
            }
            for (String bot : allBot) {
                String info = bot + ",1,";
                if (Objects.equals(bot, nameWinner)) info += "1,100,";
                else info += "0,0,";
                info += "" + scoreManager.getPlayerScore(bot);
                record = info.split(",");
                writer.writeNext(record);
            }
        } catch (IOException e) {
            System.err.println("Une erreur est survenue lors de l'exécution.");
        }

        reader.close();
    }
}
