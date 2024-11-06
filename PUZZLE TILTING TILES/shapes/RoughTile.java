package shapes;

import javax.swing.*;
import java.awt.Color;

public class RoughTile extends Tile {

    public RoughTile(int row, int col) {
        super(row, col);
        changeColor("cyan");

    }
    @Override
    public void relocate(String color) {
        changeColor(color);
    }

    @Override
    public void delete(String color) {
        changeColor(color);
    }

    @Override
    public void move(String color) {
    }

}
