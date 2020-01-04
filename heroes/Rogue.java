package heroes;

import angels.*;
import common.Constants;

public final class Rogue extends Hero {

    public Rogue(final Character mainGround, final int x, final int y,
                 final int initialhp, final int hpperlevel, final float bonusDamage) {
        super(mainGround, x, y, initialhp, hpperlevel, bonusDamage);
    }

    @Override
    public void calculateRaceAmplificationFor(final Hero h) {
        h.calculateRaceAmplification(this);
    }

    @Override
    public void calculateRaceAmplification(final Knight knight) {
        this.raceAmplificationFA = Constants.RACEAMPIFICATION09;
        this.raceAmplificationSA = Constants.RACEAMPIFICATION08;
    }

    @Override
    public void calculateRaceAmplification(final Pyromancer pyromancer) {
        this.raceAmplificationFA = Constants.RACEAMPIFICATION125;
        this.raceAmplificationSA = Constants.RACEAMPIFICATION12;
    }

    @Override
    public void calculateRaceAmplification(final Rogue rogue) {
        this.raceAmplificationFA = Constants.RACEAMPIFICATION12;
        this.raceAmplificationSA = Constants.RACEAMPIFICATION09;
    }

    @Override
    public void calculateRaceAmplification(final Wizard wizard) {
        this.raceAmplificationFA = Constants.RACEAMPIFICATION125;
        this.raceAmplificationSA = Constants.RACEAMPIFICATION125;
    }
    /**
     * @param ground Tipul terenului pe care va avea loc lupta
     * @param h Eroul cu care se va lupta
     * @param level Nivelul curent pe care il are eroul
     * Functia va seta killer-ul eroului atacat ca fiind eroul curent - astfel, in cazul in care
     * eroul atacat moare in urma damege-ului primit la runda curenta sau dat de o abilitate ce
     * are efect overtime, eroul curent - atacatorul - va primi xp-ul corespunzator.
     * Vom verifica daca suntem pe terenul de baza a lui Rogue.
     * Vom calcula damage-ul dat de fiecare abilitate si il vom aplica eroului atacat.
     * Urmeaza calcularea damage-ul pe care il va da eroul curent cu efect prelungit. Vom seta
     * pentru eroul atacat valoarea numarului de runde in care urmeaza sa fie sub efectul celei
     * de-a doua abilitati a lui Rogue, dar si valoarea cu care va scadea hp-ul lui.
     * Valoarea rundelor in care este imobilizat va fi egal cu cel al rundelor in care va primi
     * damage overtime. In functie de rezultatul primei verificari (terenul pe care se desfasoara
     * lupta), dar si de runda in care ne aflam, valoarea damage-ului calculat pentru prima
     * abilitate va suferi o amplificare de 1.5 (critical hit), dar si valorile in care oponentii
     * vor fi sub efectul celei de-a doua abilitate vor fi duble.
     */
    @Override
    public void calculateDamage(final Character ground, final Hero h, final int level) {
        h.setKiller(this);
        int dif = this.currentGround - this.getMainGround();
        this.setDamageAbilities(level);
        if (this.currentRound % Constants.NOROUNDSFORCRITICALHIT == 0) {
            if (dif == 0) {
                this.damageFirstAbility *= Constants.CRITICALAMPIFICATION;
            }
        }
        float toRoundDamageFA = this.damageFirstAbility;
        toRoundDamageFA *= this.calculateGroundAmplification(ground);
        h.calculateRaceAmplificationFor(this);
        toRoundDamageFA *= this.raceAmplificationFA;
        float toRoundDamageSA = this.damageSecondAbility;
        toRoundDamageSA *= this.calculateGroundAmplification(ground);
        toRoundDamageSA *= this.raceAmplificationSA;
        toRoundDamageFA = Math.round(toRoundDamageFA);
        toRoundDamageFA += toRoundDamageSA;
        this.damage = Math.round(toRoundDamageFA);
        h.periodicDamage = Math.round(toRoundDamageSA);
        if (dif == 0) {
            h.noRoundsToReceivePeriodicDamage = Constants.ROUNDSTORECEIVEPERIODICDAMAGER;
            h.noRoundsCantMove = Constants.ROUNDSTORECEIVEPERIODICDAMAGER;
        } else {
            h.noRoundsToReceivePeriodicDamage = Constants.ROUNDSTORECEIVEPERIODICDAMAGER / 2;
            h.noRoundsCantMove = Constants.ROUNDSTORECEIVEPERIODICDAMAGER / 2;
        }
        h.receiveDamage(this.damage);
    }
    /**
     * @param level Nivelul curent pe care il are eroul
     * Functia va seta valorile damage-ului dat de cele doua abilitati ale eroului de tip Rogue.
     */
    @Override
    void setDamageAbilities(final int level) {
        this.damageFirstAbility = Constants.INITIALDAMAGEFAR + Constants.DAMAGEPERLEVELFAR * level;
        this.damageSecondAbility = Constants.INITIALDAMAGESAR
                + Constants.DAMAGEPERLEVELSAR * level;
    }

    @Override
    public void accept(DamageAngel damageAngel) {
        damageAngel.interactWith(this);
    }

    @Override
    public void accept(DarkAngel darkAngel) {
        darkAngel.interactWith(this);
    }

    @Override
    public void accept(Dracula dracula) {
        dracula.interactWith(this);
    }

    @Override
    public void accept(GoodBoy goodBoy) {
        goodBoy.interactWith(this);
    }

    @Override
    public void accept(LevelUpAngel levelUpAngel) {
        levelUpAngel.interactWith(this);
    }

    @Override
    public void accept(LifeGiver lifeGiver) {
        lifeGiver.interactWith(this);
    }

    @Override
    public void accept(SmallAngel smallAngel) {
        smallAngel.interactWith(this);
    }

    @Override
    public void accept(Spawner spawner) {
        spawner.interactWith(this);
    }

    @Override
    public void accept(TheDoomer theDoomer) {
        theDoomer.interactWith(this);
    }

    @Override
    public void accept(XPAngel xpAngel) {
        xpAngel.interactWith(this);
    }
}
