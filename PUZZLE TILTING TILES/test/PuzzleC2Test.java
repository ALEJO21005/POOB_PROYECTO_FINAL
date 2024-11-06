package test;

import org.junit.Test;
import puzzle.*;
import static org.junit.Assert.*;

public class PuzzleC2Test {


    @Test
    public void accordingCIshouldInitializePuzzleGivenBoard() {
        char[][] starting = {{'r', 'b', '.'}, {'.', 'g', 'y'}, {'.', '.', '.'}};
        char[][] ending = {{'b', 'y', 'r'}, {'g', '.', '.'}, {'.', '.', '.'}};

        Puzzle puzzle = new Puzzle(starting, ending);
        assertArrayEquals(starting, puzzle.getStarting());
        assertArrayEquals(ending, puzzle.getEnding());
    }

    @Test
    public void accordingCITiltShouldNotMoveRightWhenNoSpace() {
        char[][] starting = {{'r', 'b', 'g'}, {'y', 'b', 'r'}, {'g', 'b', 'y'}};
        Puzzle puzzle = new Puzzle(starting, starting);

        puzzle.tilt('r');
        assertArrayEquals(starting, puzzle.getStarting());
    }

    @Test
    public void accordingCITiltShouldMoveLeftWhenThereIsASpace() {
        char[][] starting = {{'.', 'b', 'y'}, {'.', '.', 'r'}, {'g', '.', 'y'}};
        char[][] expected = {{'b', 'y', '.'}, {'r', '.', '.'}, {'g', 'y', '.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.tilt('l');
        assertArrayEquals(expected, puzzle.getStarting());
    }


    @Test
    public void accordingCIShouldRelocateTileCorrectly() {
        char[][] starting = {{'.','r'}, {'.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        int [] from = {0,1};
        int [] to = {1,0};
        puzzle.relocateTile(from, to);
        assertArrayEquals(starting, puzzle.getStarting());
}

    @Test
    public void accordingCIShouldShowIsGoal(){
        char[][] starting = {{'.', 'r', 'b', 'g'}, {'y', '.', '.', 'r'}, {'b', 'g', 'y', '.'}, {'.', 'r', 'b', 'g'}};
        char[][] ending = {{'.', 'r', 'b', 'g'}, {'y', '.', '.', 'r'}, {'b', 'g', 'y', '.'}, {'.', 'r', 'b', 'g'}};
        Puzzle puzzle = new Puzzle(starting, ending);
        assertArrayEquals(starting, puzzle.getEnding());
    }

    @Test
    public void accordingCIShouldNotShowIsGoal(){
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        char[][] ending = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, ending);
        puzzle.tilt('d');
        assertFalse(puzzle.isGoal());
    }


    @Test
    public void accordingCIShouldMakeAHoleInBlackTile(){
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        int row = 0;
        int col = 0;
        puzzle.makeHole(row, col);
        assertTrue(puzzle.baldosasStarting[row][col].hasHole());
    }

    @Test
    public void accordingCIShouldNotMakeAHoleInATile(){
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        int row = 0;
        int col = 1;
        puzzle.makeHole(row, col);
        assertFalse(puzzle.baldosasStarting[row][col].hasHole());
    }

    @Test
    public void accordingCIShouldAddGlue() {
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.addGlue(0,1);
        assertNotNull(puzzle.baldosasStarting[0][1].getGlue());
    }

    @Test
    public void accordingCIShouldNotAddGlue() {
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.addGlue(0,0);
        assertNull(puzzle.baldosasStarting[0][0].getGlue());
    }

    @Test
    public void accordingCITiltShouldMoveDownWhenThereIsASpace() {
        char[][] starting = {{'r', 'b', 'y'}, {'.', '.', 'r'}, {'g', '.', 'y'}};
        char[][] expected = {{'.', '.', 'y'}, {'r', '.', 'r'}, {'g', 'b', 'y'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.tilt('d');
        assertArrayEquals(expected, puzzle.getStarting());
    }

    @Test
    public void accordingCITiltShouldMoveUpWhenThereIsASpace() {
        char[][] starting = {{'.', '.', 'y'}, {'r', '.', 'r'}, {'g', 'b', 'y'}};
        char[][] expected = {{'r', 'b', 'y'}, {'g', '.', 'r'}, {'.', '.', 'y'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.tilt('u');
        assertArrayEquals(expected, puzzle.getStarting());
    }

    @Test
    public void accordingCITiltShouldExchangeTheBoards() {
        char[][] starting = {{'r', 'b', 'y'}, {'g', '.', 'r'}, {'.', '.', 'y'}};
        char[][] expected = {{'r', 'b', 'y'}, {'g', '.', 'r'}, {'.', '.', 'y'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.exchange();
        assertArrayEquals(expected, puzzle.getStarting());
    }

    @Test
    public void accordingCITiltShouldConsultCorrectly() {
        char[][] starting = {{'.', '.', 'r'}, {'r', '.', 'r'}, {'g', 'b', 'y'}};
        char[][] expected = {{'r', 'b', 'r'}, {'g', '.', 'r'}, {'.', '.', 'y'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.tilt('u');
        assertArrayEquals(expected, puzzle.getStarting());
    }

    @Test
    public void accordingCIShouldAddTileCorrectly(){
    char[][] starting = {{'b', 'g', 'r', '.'}, {'y', '.', 'b', 'r'}, {'g', '.', '.', 'b'}, {'r', '.', 'y', 'g'}};
    char[][] ending = {{'b', 'g', 'r', '.'}, {'y', '.', 'b', 'r'}, {'g', 'y', '.', 'b'}, {'r', '.', 'y', 'g'}};
    Puzzle puzzle = new Puzzle(starting, ending);
    puzzle.addTile(2, 1, 'y');
    assertArrayEquals(ending, puzzle.getStarting());
    }

    @Test
    public void accordingCIShouldNotAddTile() {
        char[][] starting = {{'b', 'g', 'r', '.'}, {'y', '.', 'b', 'r'}, {'g', '.', '.', 'b'}, {'r', '.', 'y', 'g'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        char[][] expected = {{'b', 'g', 'r', '.'}, {'y', '.', 'b', 'r'}, {'g', '.', '.', 'b'}, {'r', '.', 'y', 'g'}};
        puzzle.addTile(0, 0, 'g');

        assertArrayEquals(expected, puzzle.getStarting());
        assertNotEquals('g', puzzle.baldosasStarting[0][0].getColor());
    }


    @Test
    public void accordingCIShouldDeleteTile(){
        char[][] starting = {{'b', 'g', 'r', '.'}, {'y', '.', 'b', 'r'}, {'g', '.', '.', 'b'}, {'r', '.', 'y', 'g'}};
        char[][] expected = {{'b', 'g', 'r', '.'}, {'y', '.', 'b', 'r'}, {'g', '.', '.', 'b'}, {'r', '.', 'y', '.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.deleteTile(3, 3);
        assertArrayEquals(expected, puzzle.getStarting());
    }

    @Test
    public void accordingCIShouldNotDeleteTile() {
        char[][] starting = {{'b', 'g', 'r', '.'}, {'y', '.', 'b', 'r'}, {'g', '.', '.', 'b'}, {'.', '.', '.', '.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.deleteTile(3, 1);
        assertArrayEquals(starting, puzzle.getStarting());
    }


    @Test
    public void accordingCIShouldDeleteGlueInATile(){
        char [][] starting = {{'b', 'g', 'r'}, {'g', '.', 'b'}, {'.', '.', '.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.addGlue(1, 1);
        puzzle.deleteTile(1, 1);
        assertNull(puzzle.baldosasStarting[0][0].getGlue());
    }


    @Test
    public void accordingCIShouldNotDeleteGlueInATile() {
        char[][] starting = {{'b', 'g', 'r'}, {'g', '.', 'b'}, {'.', '.', '.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.addGlue(0, 1);
        puzzle.deleteTile(0, 0);
        assertFalse(puzzle.baldosasStarting[0][0].getGlue().isRoot());
    }
}
