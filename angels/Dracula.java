package angels;

import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Dracula extends Angel {

    public Dracula(int round, int x, int y) {
        super(round, x, y);
    }

    @Override
    public void interactWith(Knight knight) {
        knight.setRaceAmplificationFA(knight.getRaceAmplificationFA() * Constants.RACEMODIFIER08);
        knight.setHp(knight.getHp() - Constants.HPMODIFIER60);
    }

    @Override
    public void interactWith(Pyromancer pyromancer) {
        pyromancer.setRaceAmplificationFA(pyromancer.getRaceAmplificationFA()
                * Constants.RACEMODIFIER07);
        pyromancer.setHp(pyromancer.getHp() - Constants.HPMODIFIER40);
    }

    @Override
    public void interactWith(Rogue rogue) {
        rogue.setRaceAmplificationFA(rogue.getRaceAmplificationFA() * Constants.RACEMODIFIER09);
        rogue.setHp(rogue.getHp() - Constants.HPMODIFIER35);
    }

    @Override
    public void interactWith(Wizard wizard) {
        wizard.setRaceAmplificationFA(wizard.getRaceAmplificationFA() * Constants.RACEMODIFIER06);
        wizard.setHp(wizard.getHp() - Constants.HPMODIFIER20);
    }
}
