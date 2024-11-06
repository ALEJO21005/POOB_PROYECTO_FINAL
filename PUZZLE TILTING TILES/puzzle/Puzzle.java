package puzzle;
import shapes.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Un puzzle que simula el problema de Tilting Tiles
 *
 * @author  Santiago Carmona, Sergio Idarraga
 * @version 1.0  (28 Octubre 2024)()
 */

public class Puzzle {
    private int h;
    private int w;
    public char[][] starting;
    public char[][] ending;
    private boolean isVisible;
    public Tile[][] baldosasStarting;
    public Tile[][] baldosasEnding;
    private boolean isFinished;


    /**
     * Crea un nuevo Puzzle generando los tableros de forma aleatoria e indicado solo las dimensiones.
     */
    public Puzzle(int h, int w) {

        this.h = h;
        this.w = w;
        this.starting = createMatrix(h, w);
        //this.ending = createMatrix(h, w);
        this.isVisible = false;
        this.baldosasStarting = new Tile[h][w];
        this.baldosasEnding = new Tile[h][w];
        this.push(this.starting, this.baldosasStarting);
        this.endingPush();
        moveEnding();
        this.isFinished = false;

    }

    /**
     * Crea un nuevo Puzzle indicado el tablero inicial y final
     */
    public Puzzle(char[][] starting, char[][] ending) {
        this.isVisible = false;
        this.starting = starting;
        this.ending = ending;
        this.baldosasStarting = new Tile[this.starting.length][this.starting[0].length];
        this.baldosasEnding = new Tile[this.starting.length][this.starting[0].length];
        this.push(this.starting, this.baldosasStarting);
        this.push(this.ending, this.baldosasEnding);
        relocateEnding();
        this.isFinished = false;
    }

    /**
     * Crea un nuevo Puzzle indicado solamente el tablero final
     */
    public Puzzle(char[][] ending) {
        this.isVisible = false;
        this.ending = ending;
        this.starting = new char[this.ending.length][this.ending[0].length];
        this.baldosasStarting = new Tile[this.starting.length][this.starting[0].length];
        this.baldosasEnding = new Tile[this.ending.length][this.ending[0].length];
        this.push(this.starting, this.baldosasStarting);
        this.endingPush();
        this.isFinished = false;
        this.starting = createMatrix(this.ending.length, this.ending[0].length);
    }


    private void push(char[][] tablero, Tile[][] baldosas) {
        int height = 300 / tablero.length;
        int width = 300 / tablero[0].length;
        boolean condition = false;

        for (int i = 0; i < tablero.length; i++) {
            Tile baldosaInicial = new NormalTile(i, 0);
            baldosaInicial.changeColor(calculateColor(this.starting[i][0]));
            baldosaInicial.changeSize(height, width);

            if (condition) {
                baldosaInicial.setyPosition(baldosas[i - 1][baldosas.length - 1].getyPosition());
                baldosaInicial.moveVertical(height + 6);
            }
            baldosas[i][0] = baldosaInicial;

            for (int j = 1; j < tablero[i].length; j++) {
                Tile baldosa = new NormalTile(i, j);
                baldosa.changeColor(calculateColor(this.starting[i][j]));
                baldosa.changeSize(height, width);
                baldosa.setxPosition(baldosas[i][j - 1].getxPosition() + width + 6);
                baldosa.setyPosition(baldosas[i][j - 1].getyPosition());
                baldosas[i][j] = baldosa;
            }
            condition = true;
        }
    }

    private void endingPush() {
        char direction;
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                Tile baldosa = new NormalTile(i, j);
                baldosa.changeColor(this.baldosasStarting[i][j].getColor());
                baldosa.changeSize(this.baldosasStarting[i][j].getHeight(), this.baldosasStarting[i][j].getWidth());
                baldosa.setxPosition(this.baldosasStarting[i][j].getxPosition() + 600);
                baldosa.setyPosition(this.baldosasStarting[i][j].getyPosition());
                this.baldosasEnding[i][j] = baldosa;

            }
        }
    }

    private void relocateEnding() {
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                this.baldosasEnding[i][j].setxPosition(this.baldosasStarting[i][j].getxPosition() + 600);
            }
        }
    }


    private void moveEnding() {
        char direction;
        for (int j = 0; j < (Math.random() * 6) + 1; j++) {
            int move = (int) (Math.random() * 4);
            switch (move) {
                case 0:
                    direction = 'r';
                    this.tiltEnding(direction);
                    break;
                case 1:
                    direction = 'l';
                    this.tiltEnding(direction);
                case 2:
                    direction = 'u';
                    this.tiltEnding(direction);
                case 3:
                    direction = 'd';
                    this.tiltEnding(direction);
            }
        }
    }

    public char[][] getStarting(){
        return this.starting;
    }

    public char[][] getEnding(){
        return this.ending;
    }

    private String calculateColor(char color) {
        switch (color) {
            case 'r':
                return "red";
            case 'g':
                return "green";
            case 'b':
                return "blue";
            case 'y':
                return "yellow";
            case '.':
                return "black";
            default:
                return null;
        }
    }

    private char getCharColor(String color) {
        switch (color) {
            case "red":
                return 'r';
            case "green":
                return 'g';
            case "blue":
                return 'b';
            case "yellow":
                return 'y';
            case "black":
                return '.';
        }
        return '.';
    }

    private char[][] createMatrix(int rows, int columns) {
        char[][] temp = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int n = (int) (Math.random() * 8);
                switch (n) {
                    case 0:
                        temp[i][j] = 'r';
                        break;
                    case 1:
                        temp[i][j] = 'b';
                        break;
                    case 2:
                        temp[i][j] = 'g';
                        break;
                    case 3:
                        temp[i][j] = 'y';
                        break;
                    case 4, 5, 6, 7:
                        temp[i][j] = '.';
                        break;
                }
            }

        }
        return temp;
    }



    /**
     * Permite hacer visible los puzzles
     */
    public void makeVisible() {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        this.isVisible = true;

        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (Tile baldosa : this.baldosasStarting[i]) {
                baldosa.makeVisible();
                if ((baldosa.getGlue() != null) && (baldosa.getGlue().isRoot())) {
                    baldosa.showGlue(true);
                }

            }
        }
        for (int i = 0; i < this.baldosasEnding.length; i++) {
            for (Tile baldosa : this.baldosasEnding[i]) {
                baldosa.makeVisible();
            }
        }
    }

    /**
     * Permite hacer invisible los puzzles
     */
    public void makeInvisible() {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        this.isVisible = false;
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (Tile baldosa : this.baldosasStarting[i]) {
                baldosa.makeInvisible();
                baldosa.showGlue(false);
            }
        }
        for (int i = 0; i < this.baldosasEnding.length; i++) {
            for (Tile baldosa : this.baldosasEnding[i]) {
                baldosa.makeInvisible();
            }
        }

    }

    /**
     * Añade una nueva baldosa al tablero
     */
    public void addTile(int row, int column, char color) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        if (color != 'r' && color != 'g' && color != 'b' && color != 'y') {
            JOptionPane.showMessageDialog(null, "Color incorrecto");
        } else if (!this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "Ya existe una ficha en esa posición");
        } else {
            this.baldosasStarting[row][column].changeColor(calculateColor(color));
        }
        updateStartingMatrix(this.baldosasStarting, this.starting);
    }

    public void addTile(String type, int row, int column) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        if (!this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "Ya existe una ficha en esa posición");
        }
        else {
            if(type.equals("fixed")){instanceTile(type, row, column); return;}
            if(type.equals("rough")){instanceTile(type, row, column); return;}
            if(type.equals("freelance")){instanceTile(type, row, column); return;}
            if(type.equals("flying")){instanceTile(type, row, column); return;}
            if(type.equals("normal")){instanceTile(type, row, column); }
        }
    }


    private void buildNewTile(Tile tile, int row, int column) {
        tile .changeSize(this.baldosasStarting[row][column].getHeight(), this.baldosasStarting[row][column].getWidth());
        tile.setxPosition(this.baldosasStarting[row][column].getxPosition());
        tile.setyPosition(this.baldosasStarting[row][column].getyPosition());
        this.baldosasStarting[row][column] = tile;
        tile.makeVisible();
    }


    private Tile instanceTile(String type, int row, int column) {
        switch (type) {
            case "fixed":
                FixedTile fixedTile = new FixedTile(row, column);
                buildNewTile(fixedTile, row, column);
                return fixedTile;
            case "rough":
                RoughTile roughTile = new RoughTile(row, column);
                buildNewTile(roughTile, row, column);
                return roughTile;
            case "freelance":
                FreelanceTile freelanceTile = new FreelanceTile(row, column);
                buildNewTile(freelanceTile, row, column);
                //Triangle.addOnTile(row, column);
                //Triangle freeTriangle = addTriangle(row, column);
                //freeTriangle.changeColor("blue");
                return freelanceTile;
            case "flying":
                FlyingTile flyingTile = new FlyingTile(row, column);
                buildNewTile(flyingTile, row, column);
                return flyingTile;
            default:
                Tile normalTile = new NormalTile(row, column);
                buildNewTile(normalTile, row, column);
                return normalTile;
        }
    }

    /**
     * Elimina una baldosa del puzzle
     */
    public void deleteTile (int row, int column) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        if (this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "No hay ninguna ficha en la posición indicada.");
        } else {
            if (!(this.baldosasStarting[row][column] instanceof FlyingTile)) this.baldosasStarting[row][column].delete("black");
        }
        updateStartingMatrix(this.baldosasStarting, this.starting);
    }


    /**
     * Mueve de lugar una baldosa indicada por teclado
     */
    private void relocateTile (String typeOfGlue, int[] from, int[] to, Tile[][] tablero) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
        } else {
            int row1 = from[0];
            int column1 = from[1];
            int row2 = to[0];
            int column2 = to[1];
            if (!(this.baldosasStarting[row1][column1] instanceof RoughTile)) {
                tablero[row2][column2].move(tablero[row1][column1].getColor());
            }
            tablero[row1][column1].move("black");
            if ((tablero[row1][column1].getGlue()) != null && (tablero[row1][column1].getGlue().isRoot())) {
                tablero[row2][column2].addGlue(typeOfGlue);
                tablero[row1][column1].getGlue().makeInvisible();
                tablero[row1][column1].deleteGlue();
            }
        }
    }

    private void updateStartingMatrix(Tile [][] tablero, char[][] starting ) {
        for(int i = 0; i < tablero.length; i++) {
            for(int j = 0; j < tablero[i].length; j++) {
                Tile tile = tablero[i][j];
                starting[i][j] = getCharColor(tile.getColor());
            }
        }
    }

    public void relocateTile (int[] from, int[] to) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
        } else {
            int row1 = from[0];
            int column1 = from[1];
            int row2 = to[0];
            int column2 = to[1];

            if (this.baldosasStarting[row1][column1].getColor().equals("black") || !this.baldosasStarting[row2][column2].getColor().equals("black")) {
                JOptionPane.showMessageDialog(null, "No se puede realizar el movimiento");
            } else {
                if (!(this.baldosasStarting[row1][column1] instanceof FixedTile)) {
                    this.baldosasStarting[row2][column2].relocate(this.baldosasStarting[row1][column1].getColor());
                }
                this.baldosasStarting[row1][column1].relocate("black");
                if ((this.baldosasStarting[row1][column1].getGlue() != null) && this.baldosasStarting[row1][column1].getGlue().isRoot() ) {
                    this.baldosasStarting[row2][column2].addGlue("normal");
                    this.baldosasStarting[row1][column1].deleteGlue();
                }
                updateStartingMatrix(this.baldosasStarting, this.starting);
            }
        }
    }

    /**private Tile typeOfTile (int row, int column) {
        Triangle triangle = new Triangle();
    }**/



    /**
     * Gira el tablero en la dirección indicada por teclado
     */
    public void tilt(char direction) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        switch (direction) {
            case 'r':
                int[] indices = searchRoot();
                if (indices != null) {
                    int moves = minMoves(indices[0], indices[1], 'r');
                    moveRight(moves, this.baldosasStarting);
                } else {moveRight(0, this.baldosasStarting);}
                break;
            case 'l':
                indices = searchRoot();
                if (indices != null) {
                    int moves = minMoves(indices[0], indices[1], 'l');
                    moveLeft(moves, this.baldosasStarting);
                } else {
                    moveLeft(0, this.baldosasStarting);
                }
                break;
            case 'u':
                indices = searchRoot();
                if (indices != null){
                    int moves = minMoves(indices[0], indices[1], 'u');
                    moveUp(moves, this.baldosasStarting);
                } else {moveUp(0, this.baldosasStarting);}
                break;
            case 'd':
                indices = searchRoot();
                if (indices != null){
                    int moves = minMoves(indices[0], indices[1], 'd');
                    moveDown(moves, this.baldosasStarting);
                } else {moveDown(0, this.baldosasStarting);}
                break;
            default:
                JOptionPane.showMessageDialog(null, "Dirección incorrecta");
                break;
        }
        updateStartingMatrix(this.baldosasStarting, this.starting);
        //print(this.starting);
    }

    private int[] searchRoot() {
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                if ((this.baldosasStarting[i][j].getGlue() != null) && (this.baldosasStarting[i][j].getGlue().isRoot())) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private String typeOfGlue(int row, int column) {
        if (this.baldosasStarting[row][column].getGlue() instanceof NormalGlue) {
            return "normal";
        }
        else if (this.baldosasStarting[row][column].getGlue() instanceof FragilGlue) {
            return "fragil";
        }
        return null;
    }


    //{{'r', 'g', 'y', '.'}, {'b', '.', '.', '.'}, {'g', 'b', '.', '.'}, {'g', 'b', '.', '.'}}

    private int min(ArrayList<Integer> Array) {
        int minimo = Array.get(0);
        for (int i = 1; i < Array.size(); i++) {
            if (Array.get(i) < minimo) {
                minimo = Array.get(i);
            }
        }
        return minimo;
    }


    private void tiltEnding(char direction) {
        for (int i = 0; i < this.baldosasEnding.length; i++) {
            switch (direction) {
                case 'r':
                    moveRight(0, this.baldosasEnding);
                    break;
                case 'l':
                    moveLeft(0, this.baldosasEnding);
                    break;
                case 'u':
                    moveUp(0, this.baldosasEnding);
                    break;
                case 'd':
                    moveDown(0, this.baldosasEnding);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Dirección incorrecta");
                    break;
            }
        }
    }

    /** Calcula los movimientos hacia la derecha e izquierda
     *
     * @param baldosa, la baldosa a la cual quiero calcular el número de movimientos
     * @param row, la fila dónde se encuentra esa baldosa
     * @param direction, dirección hacia la cual quiero calcular el número de movimientos
     * @return int moves, la cantidad de movimientos posibles para esa baldosa
     */
    private int calculateMoves(Tile baldosa, Tile[] row, char direction, boolean firstTime) {
        int moves = 0;
        int[] indices = index(baldosa);
        int indexColumn = indices[1];
        int indexRow = indices[0];


        switch (direction) {
            case 'r':
                for (int i = indexColumn + 1; i < this.baldosasStarting[0].length; i++) {
                    if (row[i].hasHole() && !baldosa.getColor().equals("black") && !(baldosa instanceof FlyingTile)) {
                        deleteTile(indexRow, indexColumn);
                    }
                    if (row[i].getColor().equals("black")) {
                        moves ++;
                    }
                    else {
                        if (!firstTime) {
                            break;
                        }
                    }
                }

                break;
            case 'l':
                for (int i = indexColumn - 1; i >= 0; i--) {
                    if (row[i].hasHole() && !baldosa.getColor().equals("black")) {
                        deleteTile(indexRow, indexColumn);
                    }
                    if (row[i].getColor().equals("black")) {
                        moves ++;
                    } else {
                        if (!firstTime) {
                            break;
                        }
                    }
                }
                break;
            }
        return moves;
    }

    /**
     * Calcula los movimientos hacia arriba y abajo
     * @param tablero El tablero que referencia a starting
     * @param baldosa La baldosa la cual se quiere calcular el número de movimientos
     * @param row La fila dónde se encuntra dicha baldosa
     * @param direction La dirección hacia la cual se calcula el número de movimientos
     * @return
     */
    private int calculateMovesUpAndDown(Tile[][] tablero, Tile baldosa, Tile[] row, char direction, boolean firstTime) {
        int moves = 0;
        int[] indices = index(baldosa);
        int indexColumn = indices[1];
        int indexRow = indices[0];

        switch (direction) {
            case 'u':
                for (int i = baldosa.getRow() - 1; i >= 0; i--) {
                    if (tablero[i][indexColumn].hasHole() && !baldosa.getColor().equals("black")) {
                        deleteTile(indexRow, indexColumn);
                    }

                    if (tablero[i][indexColumn].getColor().equals("black")) {
                        moves++;
                    }
                    else {
                        if (!firstTime) {
                            break;
                        }
                    }
                }

                break;
            case 'd':
                for (int i = baldosa.getRow() + 1; i < tablero.length; i++) {
                    if(tablero[i][indexColumn].hasHole() && !baldosa.getColor().equals("black")) {
                        deleteTile(indexRow, indexColumn);
                    }
                    if (tablero[i][indexColumn].getColor().equals("black")) {
                        moves++;
                    }
                }
                break;
        }
        return moves;
    }

    private int[] index(Tile baldosa) {
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                if (this.baldosasStarting[i][j].getRow() == baldosa.getRow() && this.baldosasStarting[i][j].getCol() == baldosa.getCol()) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }


    private int calculateMovesGlued(int r, int c, char direction) {
        ArrayList<Integer> moves = new ArrayList<>();
        if ((this.baldosasStarting[r][c].getGlue() instanceof FragilGlue) && !this.baldosasStarting[r][c].getGlue().canMove()) {
            return 0;
        }
        moves.add(calculateMoves(this.baldosasStarting[r][c], this.baldosasStarting[r], direction, true));

        if (r > 0) {
            moves.add(calculateMoves(this.baldosasStarting[r - 1][c], this.baldosasStarting[r - 1], direction, true));
        }

        if (r < this.baldosasStarting.length - 1) {
            moves.add(calculateMoves(this.baldosasStarting[r + 1][c], this.baldosasStarting[r + 1], direction, true));
        }

        System.out.println("Moves: " + moves);
        return min(moves);

    }

    private int calculateMovesGluedUpAndDown(Tile baldosa, int r, int c, char direction) {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(calculateMovesUpAndDown(this.baldosasStarting, this.baldosasStarting[r][c], this.baldosasStarting[r], direction, true));
        if (c > 0) {
            moves.add(calculateMovesUpAndDown(this.baldosasStarting,this.baldosasStarting[r][c - 1], this.baldosasStarting[r], direction, true)); // Izquierda
        }

        if (c < this.baldosasStarting[0].length - 1) {
            moves.add(calculateMovesUpAndDown(this.baldosasStarting,this.baldosasStarting[r][c + 1], this.baldosasStarting[r], direction, true)); // Derecha
        }
        return min(moves);

    }

    private int minMoves(int r, int c, char direction) {
        int moves;
        if (direction == 'u' || direction == 'd') {
            moves = calculateMovesGluedUpAndDown(this.baldosasStarting[r][c], r, c, direction);
        }
        else {
            moves = calculateMovesGlued(r, c, direction);
        }
        return moves;
    }

    private void moveRight(int movesGlued, Tile[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = tablero.length - 1; j >= 0; j --) {
                if (tablero[i][j].getGlue() == null) {
                    if (!tablero[i][j].getColor().equals("black")) {
                        int moves = calculateMoves(tablero[i][j], tablero[i], 'r', false);
                        if (moves > 0) {
                            this.relocateTile(null, new int[]{i,j}, new int[]{i, j + moves}, tablero);
                        }
                    }
                } else {
                    if (movesGlued > 0) {
                        String typeOfGlue = typeOfGlue(i,j);
                        this.relocateTile(typeOfGlue, new int[]{i,j}, new int[]{i, j + movesGlued}, tablero);
                        this.baldosasStarting[i][j].deleteGlue();
                        this.baldosasStarting[i][j + movesGlued].deleteGlue();
                        if ((this.baldosasStarting[i][j].getGlue() != null) && (this.baldosasStarting[i][j].getGlue().isRoot())) {
                            int xPos = this.baldosasStarting[i][j + movesGlued].getxPosition();
                            int yPos = this.baldosasStarting[i][j + movesGlued].getyPosition();
                            this.baldosasStarting[i][j].getGlue().relocate(xPos, yPos);
                            this.baldosasStarting[i][j].getGlue().setRoot(false);
                            this.baldosasStarting[i][j + movesGlued].getGlue().setRoot(true);

                        }
                    }
                }
            }
        }
    }

    private void moveLeft(int movesGlued, Tile[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j ++) {
                if (tablero[i][j].getGlue() == null) {
                    if (!tablero[i][j].getColor().equals("black")) {
                        int moves = calculateMoves(tablero[i][j], tablero[i], 'l', false);

                        if (moves > 0) {
                            this.relocateTile(null, new int[]{i,j}, new int[]{i, j - moves}, tablero);
                        }
                    }
                } else {
                    if (movesGlued > 0) {
                        String typeOfGlue = typeOfGlue(i,j);
                        this.relocateTile(typeOfGlue, new int[]{i,j}, new int[]{i, j - movesGlued}, tablero);
                        this.baldosasStarting[i][j].deleteGlue();
                        this.baldosasStarting[i][j - movesGlued].addGlue(typeOfGlue);
                        if ((this.baldosasStarting[i][j].getGlue() != null) && (this.baldosasStarting[i][j].getGlue().isRoot())) {
                            this.baldosasStarting[i][j].getGlue().setRoot(false);
                            this.baldosasStarting[i][j - movesGlued].getGlue().setRoot(true);
                        }
                    }
                }
            }
        }
    }

    private void moveUp(int movesGlued, Tile[][] tablero) {
        for (int j = 0; j < tablero[0].length; j++) {
            for (int i = 0; i < tablero.length; i++) {
                if(tablero[i][j].getGlue() == null){
                    if (!tablero[i][j].getColor().equals("black")) {
                        int moves = calculateMovesUpAndDown(tablero, tablero[i][j], tablero[i], 'u', false);
                        if (moves > 0) {
                            this.relocateTile(null, new int[]{i, j}, new int[]{i - moves, j}, tablero);
                        }
                    }
                } else {
                    if (movesGlued > 0) {
                        String typeOfGlue = typeOfGlue(i,j);
                        this.relocateTile(typeOfGlue, new int[]{i,j}, new int[]{i - movesGlued, j}, tablero);
                        this.baldosasStarting[i][j].deleteGlue();
                        this.baldosasStarting[i - movesGlued][j].addGlue(typeOfGlue);

                        if ((this.baldosasStarting[i][j].getGlue() != null) && (this.baldosasStarting[i][j].getGlue().isRoot())) {
                            this.baldosasStarting[i][j].getGlue().setRoot(false);
                            this.baldosasStarting[i - movesGlued][j].getGlue().setRoot(true);
                            // {{'.','.','.','.'},{'y','.','.','.'},{'b','r','r','.'},{'b','y','r','.'}}
                        }
                    }
                }
            }
        }
    }

    private void moveDown(int movesGlued, Tile[][] tablero) {
        for (int j = 0; j < tablero[0].length; j++) {
            for (int i = tablero.length - 1; i >= 0; i--) {
                if(tablero[i][j].getGlue() == null){
                    if (!tablero[i][j].getColor().equals("black")) {
                        int moves = calculateMovesUpAndDown(tablero, tablero[i][j], tablero[i],  'd', false);
                        if (moves > 0) {
                            this.relocateTile(null, new int[]{i, j}, new int[]{i + moves, j}, tablero);
                        }
                    }
                } else {
                    if(movesGlued > 0){
                        String typeOfGlue = typeOfGlue(i,j);
                        relocateTile(typeOfGlue, new int[]{i,j}, new int[]{i + movesGlued, j}, tablero);
                        this.baldosasStarting[i][j].deleteGlue();
                        //this.baldosasStarting
                        this.baldosasStarting[i + movesGlued][j].addGlue(typeOfGlue);
                        if ((this.baldosasStarting[i][j].getGlue() != null) && (this.baldosasStarting[i][j].getGlue().isRoot())) {
                            this.baldosasStarting[i][j].getGlue().setRoot(false);
                            this.baldosasStarting[i + movesGlued][j].getGlue().setRoot(true);
                        }
                    }
                }
            }
        }
    }
    /**
     * Retorna si el tablero inicial es igual al tablero final
     */
    public boolean isGoal() {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
        } else {
            for (int i = 0; i < baldosasStarting.length; i++) {
                for (int j = 0; j < baldosasStarting[i].length; j++) {
                    if (!baldosasStarting[i][j].getColor().equals(baldosasEnding[i][j].getColor())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static void print(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Imprime el estado actual del tablero en formato matriz
     */
    public char[][] actualArrangement() {
        char[][] matrixActual = new char[this.baldosasStarting.length][this.baldosasStarting[0].length];
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
        } else {
            for (int i = 0; i < this.baldosasStarting.length; i++) {
                for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                    String color = baldosasStarting[i][j].getColor();
                    if (color.equals("black")) {
                        matrixActual[i][j] = '.';
                    } else {
                        matrixActual[i][j] = color.charAt(0);
                    }
                }
            }
            print(matrixActual);
        }
        return matrixActual;
    }

    public void addGlue(int row, int column) {
        if ((this.baldosasStarting[row][column].getGlue() != null) && (this.baldosasStarting[row][column].getGlue().isRoot())) {
            JOptionPane.showMessageDialog(null, "La baldosa ya tiene pegamento aplicado");
        } else if ((this.baldosasStarting[row][column].getGlue() != null) && (!this.baldosasStarting[row][column].getGlue().isRoot())) {
            JOptionPane.showMessageDialog(null, "La baldosa ya está pegada a otra baldosa");
        } else if (this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "No hay ninguna baldosa en esta posición");
        }
        else {
            if(!(this.baldosasStarting[row][column] instanceof FreelanceTile)) {
                this.baldosasStarting[row][column].addGlue("normal");
                this.baldosasStarting[row][column].getGlue().setRoot(true);
                if (row > 0 && !this.baldosasStarting[row - 1][column].getColor().equals("black")) {
                    this.baldosasStarting[row - 1][column].addGlue("normal");
                }

                if (column > 0 && !this.baldosasStarting[row][column - 1].getColor().equals("black")) {
                    this.baldosasStarting[row][column - 1].addGlue("normal");
                }

                if (row < this.baldosasStarting.length - 1 && !this.baldosasStarting[row + 1][column].getColor().equals("black")) {
                    this.baldosasStarting[row + 1][column].addGlue("normal");
                }

                if (column < this.baldosasStarting[0].length - 1 &&  !this.baldosasStarting[row][column + 1].getColor().equals("black")) {
                    this.baldosasStarting[row][column + 1].addGlue("normal");
                }
            }
        }
    }

    public void addGlue(String type, int row, int column) {
        if ((this.baldosasStarting[row][column].getGlue() != null) && (this.baldosasStarting[row][column].getGlue().isRoot())) {
            JOptionPane.showMessageDialog(null, "La baldosa ya tiene pegamento aplicado");
        } else if ((this.baldosasStarting[row][column].getGlue() != null) && (!this.baldosasStarting[row][column].getGlue().isRoot())) {
            JOptionPane.showMessageDialog(null, "La baldosa ya está pegada a otra baldosa");
        } else if (this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "No hay ninguna baldosa en esta posición");
        }
        else {
            if(!(this.baldosasStarting[row][column] instanceof FreelanceTile)) {
                this.baldosasStarting[row][column].addGlue(type);
                this.baldosasStarting[row][column].getGlue().setRoot(true);
                if (row > 0 && !this.baldosasStarting[row - 1][column].getColor().equals("black")) {
                    this.baldosasStarting[row - 1][column].addGlue(type);
                }

                if (column > 0 && !this.baldosasStarting[row][column - 1].getColor().equals("black")) {
                    this.baldosasStarting[row][column - 1].addGlue(type);
                }

                if (row < this.baldosasStarting.length - 1 && !this.baldosasStarting[row + 1][column].getColor().equals("black")) {
                    this.baldosasStarting[row + 1][column].addGlue(type);
                }

                if (column < this.baldosasStarting[0].length - 1 &&  !this.baldosasStarting[row][column + 1].getColor().equals("black")) {
                    this.baldosasStarting[row][column + 1].addGlue(type);
                }
            }
        }
    }

    private Circle addCircle(int row, int column) {
        Circle circle = new Circle();
        circle.setxPosition(this.baldosasStarting[row][column].getxPosition());
        circle.setyPosition(this.baldosasStarting[row][column].getyPosition());
        circle.setDiameter(this.baldosasStarting[row][column].getHeight());
        circle.makeVisible();
        return circle;
    }

    private Tile[][] copyStarting() {
        Tile[][] tempStarting = new Tile[this.baldosasStarting.length][this.baldosasStarting[0].length];
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                tempStarting[i][j] = new NormalTile(i, j);
                tempStarting[i][j].changeColor(this.baldosasStarting[i][j].getColor());
                tempStarting[i][j].changeSize(this.baldosasStarting[i][j].getHeight(), this.baldosasStarting[i][j].getWidth());
                tempStarting[i][j].setxPosition(this.baldosasStarting[i][j].getxPosition());
                tempStarting[i][j].setyPosition(this.baldosasStarting[i][j].getyPosition());
            }
        }
        return tempStarting;
    }

    /**
     * Permite intercambiar de posiciones los tableros starting y ending
     */
    public void exchange() {
        Tile[][] tempStarting = copyStarting();
        for (int i = 0; i < this.baldosasEnding.length; i++) {
            for (int j = 0; j < this.baldosasEnding[i].length; j++) {
                this.baldosasStarting[i][j].changeColor(this.baldosasEnding[i][j].getColor());
            }
        }
        updateStartingMatrix(this.baldosasStarting, this.starting);

        for (int i = 0; i < this.baldosasEnding.length; i++) {
            for (int j = 0; j < this.baldosasEnding[i].length; j++) {
                this.baldosasEnding[i][j].changeColor(tempStarting[i][j].getColor());
            }
        }
        updateStartingMatrix(this.baldosasStarting, this.starting);
    }

    /**
     * Realiza un hueco donde las fichas se caen y desaparecen en la posición dada
     * @param row fila donde irá el hueco
     * @param column columna donde irá el hueco
     */
    public void makeHole(int row, int column) {

        Circle.addHolePosition(row, column);

        if (this.baldosasStarting[row][column].getColor().equals("black")) {
            this.baldosasStarting[row][column].setLeaked();
            Circle hole = addCircle(row, column);
            hole.changeColor("white");
        } else {
            JOptionPane.showMessageDialog(null, "Solamente puedes agujerear celdas vacías");
        }
    }

    /**
     * Se encarga de eliminar el pegante que esté en la posición dada
     * @param row indica la fila donde está el pegante
     * @param column indica la columna donde está el pegante
     */
    public void deleteGlue(int row, int column) {
        if(this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "No existe pegante en esta posición");
            return;
        }
        if((this.baldosasStarting[row][column].getGlue() != null) && (!this.baldosasStarting[row][column].getGlue().isRoot())) {
            JOptionPane.showMessageDialog(null, "Solo puedes eliminar el pegante en la ficha que lo tiene");
            return;
        }
        if(this.baldosasStarting[row][column].getGlue().isRoot()) {
            for(int i = 0; i < this.baldosasStarting.length; i++){
                for(int j = 0; j < this.baldosasStarting[i].length; j++){
                    if((!this.baldosasStarting[i][j].getColor().equals("black")) && (this.baldosasStarting[i][j].getGlue() != null)){
                        this.baldosasStarting[i][j].deleteGlue();
                        if(this.baldosasStarting[i][j].getGlue().isRoot()){
                            this.baldosasStarting[i][j].getGlue().setRoot(false);
                            this.baldosasStarting[i][j].removeCircleGlue();
                        }
                    }
                }
            }
        }
    }

    /**
     * Encuentra cuantas fichas estan en su posición correcta en el tablero
     */
    public void misplacedTiles() {
        int count = 0;
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                if (!this.baldosasStarting[i][j].getColor().equals(this.baldosasEnding[i][j].getColor()) && !this.baldosasStarting[i][j].getColor().equals("black")) {
                    //System.out.println("Baldosa mal colocada: [" + i + "][" + j + "] - Color inicial: " + this.baldosasStarting[i][j].getColor() + ", Color final: " + this.baldosasEnding[i][j].getColor());
                    count++;
                }
            }
        }
        System.out.println("La cantidad de baldosas que no están en su posición son: " + count);
    }

    /**
     * Finaliza la ejecución del programa y no se puede hacer ningún otro movimiento.
     */
    public void finish() {
        this.isFinished = true;
    }
}

