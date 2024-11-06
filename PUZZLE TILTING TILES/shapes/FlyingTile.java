package shapes;


public class FlyingTile extends Tile {

    /**
     * Constructor for objects of class FlyingTile
     */
    public FlyingTile(int row, int col) {
        super(row, col);
        changeColor("orange");



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


}
