package fr.cotedazur.univ.polytech.teamK.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorsTest {
    @Test
    public void testById () {
        for(int i=0 ; i<Colors.values().length ; i++) {
            assertEquals(Colors.values()[i],Colors.RAINBOW.getColorById(i));
        }

    }
}