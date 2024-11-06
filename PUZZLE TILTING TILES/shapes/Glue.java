package shapes;

public abstract class Glue extends Circle {
    private boolean isRoot;

    public Glue() {
        this.isRoot = false;

    }

    public void setDimensions(int xPos, int yPos, int radius) {
        this.setxPosition(xPos);
        this.setyPosition(yPos);
        this.setDiameter(radius);
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
        this.makeVisible();
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public abstract void relocate(int xPos, int yPos);
    public abstract boolean canMove();

}
