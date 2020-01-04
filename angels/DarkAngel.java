package angels;

import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class DarkAngel extends Angel {

    public DarkAngel(int round, int x, int y) {
        super(round, x, y);
    }

    @Override
    public void interactWith(Knight knight) {
        knight.setHp(knight.getHp() - Constants.HPMODIFIER40);
    }

    @Override
    public void interactWith(Pyromancer pyromancer) {
        pyromancer.setHp(pyromancer.getHp() - Constants.HPMODIFIER30);
    }

    @Override
    public void interactWith(Rogue rogue) {
        rogue.setHp(rogue.getHp() - Constants.HPMODIFIER10);
    }

    @Override
    public void interactWith(Wizard wizard) {
        wizard.setHp(wizard.getHp() - Constants.HPMODIFIER20);
    }
}
