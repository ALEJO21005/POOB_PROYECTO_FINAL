package shapes;


public class NormalGlue extends Glue {

    public NormalGlue() {
        super();
    }

    @Override
    public void relocate(int xPos, int yPos) {
        this.setxPosition(xPos);
        this.setyPosition(yPos);
    }

    @Override
    public boolean canMove() {
        return true;
    }


}
