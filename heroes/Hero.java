package heroes;

import angels.*;
import common.Constants;

public abstract class Hero {
    private final Character mainGround;
    private final int initialhp;
    private final int hpperlevel;
    private final float bonusDamage;
    protected float raceAmplificationFA = 0;
    protected float raceAmplificationSA = 0;
    protected int damage;
    protected int damageFirstAbility;
    protected int damageSecondAbility;
    protected int periodicDamage;
    protected int noRoundsToReceivePeriodicDamage = Constants.DEFAULTVALUE;
    protected int noRoundsCantMove = Constants.DEFAULTVALUE;
    protected int currentRound;
    protected Character currentGround;
    private int x;
    private int y;
    private int hp;
    private int xp = Constants.DEFAULTVALUE;
    private int level = Constants.DEFAULTVALUE;
    private float groundAmplification;
    private int dead = Constants.DEFAULTVALUE;
    private Hero killer;
    /**
     * Constructor pentru un erou.
     */
    public Hero(final Character mainGround, final int x, final int y,
                final int initialhp, final int hpperlevel, final float bonusDamage) {
        this.mainGround = mainGround;
        this.x = x;
        this.y = y;
        this.initialhp = initialhp;
        this.hpperlevel = hpperlevel;
        this.bonusDamage = bonusDamage;
        this.hp = initialhp;
    }
    /**
     * Functia va verifica nivelul eroului dupa o lupta. Nivelul se va modifica in functie de xp-ul
     * eroului. La cresterea in nivel, hp-ul va fi resetat la valoarea de baza la care adunam
     * valoarea hp-ului pe care il va primi pentru fiecare nivel.
     */
    public void checkLevel() {
        if (this.xp < Constants.MINHPFORLEVEL1) {
            return;
        }
        int newLevel;
        newLevel = (this.xp - (Constants.MINHPFORLEVEL1 - Constants.HPLEVELUP))
                / Constants.HPLEVELUP;
        if (this.level != newLevel) {
            this.hp = this.initialhp + this.hpperlevel * newLevel;
            this.level = newLevel;
        }
    }
    /**
     * @param levelLoser Nivelul eroului care a fost omorat
     * Functia va modifica xp-ul eroului dupa formula data in enunt.
     */
    public void setXp(final int levelLoser) {
        int aux;
        aux = Constants.SETXP - (this.level - levelLoser) * Constants.XPPERLEVEL;
        if (aux > 0) {
            this.xp += aux;
        }
    }
    /**
     * @param newX Noua linie pe care se afla eroul
     * @param newY Noua coloana pe care se afla eroul
     * Functia va reseta pozitia eroului, plasandu-l pe harta la noile coordonate.
     */
    public void resetPosition(final int newX, final int newY) {
        this.x = newX;
        this.y = newY;
    }
    /**
     * Getter pentru campul ce retine linia pe care se afla pe harta eroul curent.
     */
    public int getX() {
        return x;
    }
    /**
     * Getter pentru campul ce retine coloana pe care se afla pe harta eroul curent.
     */
    public int getY() {
        return y;
    }
    /**
     * @param h Eroul pentru care vrem sa verificam daca are aceeasi pozitie cu eroul curent.
     * Functia va verifica daca cei doi eroi se afla pe aceeasi pozitie si va returna true
     * in cazul in care pozitia coincide si false, altfel.
     */
    public boolean checkPosition(final Hero h) {
        if (this.x == h.getX()) {
            return this.y == h.getY();
        }
        return false;
    }
    /**
     * @param ground Terenul pe care se vor lupta eroii
     * Functia va calcula amplificarea data de teren. Daca eroul se afla pe terenul lui ,,de baza",
     * la valoarea initiala (1), se va adauga valoarea lui bonus damage.
     */
    public float calculateGroundAmplification(final Character ground) {
        if (this.mainGround == ground) {
            this.groundAmplification = 1 + bonusDamage;
        } else {
            groundAmplification = 1;
        }
        return groundAmplification;
    }
    /**
     * @param h Eroul pentru care vom calcula amplificarea data de rasa eroului cu care se lupta.
     * Aici vom folosi double dispatch.
     * Functia se va folosi de urmatoarele patru functii (pentru toate cele patru tipuri de eroi).
     * Toate cele cinci functii trebuie sa fie suprascrise in clasele ce mostenesc clasa Hero.
     */
    public abstract void calculateRaceAmplificationFor(Hero h);

    public abstract void calculateRaceAmplification(Knight knight);

    public abstract void calculateRaceAmplification(Pyromancer pyromancer);

    public abstract void calculateRaceAmplification(Rogue rogue);

    public abstract void calculateRaceAmplification(Wizard wizard);

    public abstract void calculateDamage(Character ground, Hero h, int nlevel);
    /**
     * @param decreasehp Valoarea cu care va scadea hp-ul eroului
     * Functia seteaza noua valoare a hp-ului.
     */
    public void receiveDamage(final int decreasehp) {
        this.hp -= decreasehp;
    }
    /**
     * @param currentlevel Nivelul curent pe care il are eroul
     * Functia va seta valorile damage-ului dat de cele doua abilitati.
     */
    abstract void setDamageAbilities(int currentlevel);
    /**
     * Getter pentru campul ce retine valoarea initiala a hp-ului .
     */
    public int getInitialhp() {
        return initialhp;
    }
    /**
     * Getter pentru campul ce retine nivelul eroului.
     */
    public int getLevel() {
        return level;
    }
    /**
     * Getter pentru campul ce retine valoarea hp-ului primit la trecerea la un nivel superior.
     */
    public int getHpperlevel() {
        return hpperlevel;
    }
    /**
     * Getter pentru campul ce retine numarul de hit points(HP, "viata").
     */
    public int getHp() {
        return hp;
    }
    /**
     * Setter pentru campul ce retine numarul de hit points(HP, "viata").
     */
    public void setHp(final int hp) {
        this.hp = hp;
    }
    /**
     * Setter pentru campul ce retine numarul rundei curente.
     */
    public void setCurrentRound(final int currentRound) {
        this.currentRound = currentRound;
    }
    /**
     * Getter pentru campul ce retine tipul terenului curent.
     */
    public Character getCurrentGround() {
        return currentGround;
    }
    /**
     * Setter pentru campul ce retine tipul terenului curent.
     */
    public void setCurrentGround(final Character currentGround) {
        this.currentGround = currentGround;
    }
    /**
     * Getter pentru campul ce retine terenul pe care jucatorul primeste bonus damage.
     */
    public Character getMainGround() {
        return mainGround;
    }
    /**
     * Getter pentru campul ce retine daca eroul este sau nu in viata.
     */
    public int getDead() {
        return dead;
    }
    /**
     * Setter pentru campul ce retine daca eroul este sau nu in viata.
     */
    public void setDead(final int dead) {
        this.dead = dead;
        this.setHp(0);
    }
    /**
     * Getter pentru campul ce retine valoarea damage-ului overtime pe care il va primi eroul.
     */
    public int getPeriodicDamage() {
        return periodicDamage;
    }
    /**
     * Getter pentru campul ce retine numarul de runde overtime in care eroul va primi damage.
     */
    public int getNoRoundsToReceivePeriodicDamage() {
        return noRoundsToReceivePeriodicDamage;
    }
    /**
     * Setter pentru campul ce retine numarul de runde overtime in care eroul va primi damage.
     */
    public void setNoRoundsToReceivePeriodicDamage(final int noRoundsToReceivePeriodicDamage) {
        this.noRoundsToReceivePeriodicDamage = noRoundsToReceivePeriodicDamage;
    }
    /**
     * Getter pentru campul ce retine numarul de runde de incapacitate a eroului
     * de a efectua mutarea.
     */
    public int getNoRoundsCantMove() {
        return noRoundsCantMove;
    }
    /**
     * Setter pentru campul ce retine numarul de runde de incapacitate a eroului
     * de a efectua mutarea.
     */
    public void setNoRoundsCantMove(final int noRoundsCantMove) {
        this.noRoundsCantMove = noRoundsCantMove;
    }
    /**
     * Getter pentru killer-ul eroului curent.
     */
    public Hero getKiller() {
        return killer;
    }
    /**
     * Setter pentru killer-ul eroului curent.
     */
    public void setKiller(final Hero killer) {
        this.killer = killer;
    }
    public abstract void accept(DamageAngel damageAngel);
    public abstract void accept(DarkAngel darkAngel);
    public abstract void accept(Dracula dracula);
    public abstract void accept(GoodBoy goodBoy);
    public abstract void accept(LevelUpAngel levelUpAngel);
    public abstract void accept(LifeGiver lifeGiver);
    public abstract void accept(SmallAngel smallAngel);
    public abstract void accept(Spawner spawner);
    public abstract void accept(TheDoomer theDoomer);
    public abstract void accept(XPAngel xpAngel);

    public void setRaceAmplificationFA(float raceAmplificationFA) {
        this.raceAmplificationFA = raceAmplificationFA;
    }

    public void setRaceAmplificationSA(float raceAmplificationSA) {
        this.raceAmplificationSA = raceAmplificationSA;
    }

    public float getRaceAmplificationFA() {
        return raceAmplificationFA;
    }

    public float getRaceAmplificationSA() {
        return raceAmplificationSA;
    }

    public int getXp() {
        return xp;
    }

    /**
     * Suprascrierea metodei toString pentru a respecta output-ul cerut.
     */

    @Override
    public String toString() {
        if (this.getDead() == 1) {
            return " dead";
        }
        return " " + level + " " + xp + " " + hp + " " + x + " " + y;
    }
}
