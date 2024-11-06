package shapes;
public class FragilGlue extends Glue {
    private int count;
    private boolean move;

    public FragilGlue() {
        super();
        changeColor("gray");
        this.count = 0;
        this.move = true;
    }

    @Override
    public void relocate(int xPos, int yPos) {
        if (count != 1) {
            this.setxPosition(xPos);
            this.setyPosition(yPos);
            count += 1;
        }
        else {
            this.move = false;
            changeColor("darkGray");
        }
    }

    public boolean canMove() {
        return this.move;
    }

}
