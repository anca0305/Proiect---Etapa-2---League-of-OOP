package angels;

import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class GoodBoy extends Angel {

    public GoodBoy(int round, int x, int y) {
        super(round, x, y);
    }

    @Override
    public void interactWith(Knight knight) {
        knight.setRaceAmplificationFA(knight.getRaceAmplificationFA() * Constants.RACEMODIFIER14);
        knight.setHp(knight.getHp() + Constants.HPMODIFIER20);
    }

    @Override
    public void interactWith(Pyromancer pyromancer) {
        pyromancer.setRaceAmplificationFA(pyromancer.getRaceAmplificationFA()
                * Constants.RACEMODIFIER15);
        pyromancer.setHp(pyromancer.getHp() + Constants.HPMODIFIER30);
    }

    @Override
    public void interactWith(Rogue rogue) {
        rogue.setRaceAmplificationFA(rogue.getRaceAmplificationFA() * Constants.RACEMODIFIER14);
        rogue.setHp(rogue.getHp() + Constants.HPMODIFIER40);
    }

    @Override
    public void interactWith(Wizard wizard) {
        wizard.setRaceAmplificationFA(wizard.getRaceAmplificationFA() * Constants.RACEMODIFIER13);
        wizard.setHp(wizard.getHp() + Constants.HPMODIFIER50);
    }
}
