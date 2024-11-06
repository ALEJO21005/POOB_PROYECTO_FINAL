package test;


import puzzle.Puzzle;
import org.junit.Test;
import shapes.*;
import shapes.Tile;

import static org.junit.Assert.*;

public class Main {

    /**
     * Prueba de aceptación encargada de simular el movimiento del tablero aplicado a los diferentes tipos de baldosas
       diseñadas en el puzzle
     */
    @Test
    public void acceptanceTestShouldTiltCorrectlyWithDiferentsTypeOfTilesByCarmona() {
        char[][] starting = {{'r', 'b', '.', 'g'}, {'.', 'g', '.', '.'}, {'r', 'b', '.', '.'}, {'.', '.', '.', 'g'}};
        char[][] ending = {{'r', 'b', '.', 'g'}, {'.', 'g', '.', '.'}, {'r', 'b', '.', '.'}, {'.', '.', '.', 'g'}};
        Puzzle puzzle = new Puzzle(starting, ending);
        char [][] expected = {{'.', 'r', 'b', 'g'}, {'.', '.', '.', 'g'}, {'.', '.', 'r', 'b'}, {'.', '.', '.', 'g'}};
        puzzle.tilt('r');
        assertArrayEquals(starting, expected);
        puzzle.addTile("rough", 2,0);
        puzzle.addTile("fixed", 1,0);
        puzzle.tilt('d');
        expected = new char[][]{{'.', '.', '.', 'g'}, {'.', '.', '.', 'g'}, {'.', '.', 'b', 'b'}, {'.', 'r', 'r', 'g'}};
        assertArrayEquals(starting, expected);
        assertTrue(puzzle.baldosasStarting[2][0] instanceof RoughTile);
        assertTrue(puzzle.baldosasStarting[1][0] instanceof FixedTile);
    }

    

    public static void main(String[] args) {

    }

}
