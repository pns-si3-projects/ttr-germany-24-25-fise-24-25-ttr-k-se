package fr.cotedazur.univ.polytech.teamK.game;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import fr.cotedazur.univ.polytech.teamK.bot.Bot;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WrongPlayerException extends Exception {
    public WrongPlayerException(String message) {
        super(message);
    }
}
