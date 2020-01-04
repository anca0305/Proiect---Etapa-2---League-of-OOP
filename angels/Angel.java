package angels;

import heroes.*;

public abstract class Angel {
    private final int round;
    private final int x;
    private final int y;

    public Angel(int round, int x, int y) {
        this.round = round;
        this.x = x;
        this.y = y;
    }

    public abstract void interactWith(Knight knight);

    public abstract void interactWith(Pyromancer pyromancer);

    public abstract void interactWith(Rogue rogue);

    public abstract void interactWith(Wizard wizard);
}
