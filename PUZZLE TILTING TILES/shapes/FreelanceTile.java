package shapes;

import javax.swing.*;
import java.awt.*;


public class FreelanceTile extends Tile {

    public FreelanceTile(int row, int col) {
        super(row, col);
        changeColor("gray");

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
        changeColor(color);
    }

    @Override
    public void addGlue(String type) {
        JOptionPane.showMessageDialog(null, "Las 'FreelanceTile' no permiten añadirle pegamento");
    }

    @Override
    public void deleteGlue() {
        
    }
}
