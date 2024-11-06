package shapes;
import java.awt.Color;

public abstract class Tile extends Rectangle {
    private int row;
    private int col;
    //private boolean isGlued;
    protected Glue glue;
    //private boolean isRoot;
    private boolean isLeaky;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.isLeaky = false;
        this.glue = null;
    }


    public abstract void relocate(String color);
    public abstract void delete(String color);
    public abstract void move(String color);

    public int getRow() {return this.row;}
    public int getCol() {return this.col;}

    public void setRow(int row) {this.row = row;}
    public void setCol(int col) {this.col = col;}

    /**
    public void setGlued() {
        this.isGlued = true;
    }

    public void setUnglued() {
        this.isGlued = false;
    }

    public void setRoot(boolean condition) {
        this.isRoot = condition;
    }


    public boolean getGlued() {
        return this.isGlued;
    }

    public boolean getRoot() {
        return this.isRoot;
    }
     **/

    private Glue instanceGlue(String type, int row, int column) {
        switch (type) {
            case "fragil":
                FragilGlue fragilGlue = new FragilGlue();
                return fragilGlue;
            default:
                NormalGlue normalGlue = new NormalGlue();
                return normalGlue;

        }
    }
    public void addGlue(String type) {
        Glue glue = instanceGlue(type, row, col);
        int xPos = this.getxPosition();
        int yPos = this.getyPosition();
        int radius = this.getHeight();
        this.glue = glue;
        glue.setDimensions(xPos, yPos, radius);
    }

    public Glue getGlue() {
        return this.glue;
    }

    public void deleteGlue() {
        this.glue = null;
    }

    public void setLeaked() {
        this.isLeaky = true;
    }

    public boolean hasHole() {
        return this.isLeaky;
    }


    public final void showGlue(boolean condition) {
        if (this.glue != null) {
            if (condition) {
                glue.makeVisible();
            } else { glue.makeInvisible(); }
        }
    }

    public final void removeCircleGlue() {
        if (this.glue!=null) {
            this.glue.makeInvisible();
            this.glue = null;
        }
    }

}


