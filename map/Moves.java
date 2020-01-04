package map;

import heroes.Hero;

import java.util.List;

public class Moves {

    public static void makeMove(final Character moveType, final Hero hero,
                                final int x, final int y) {
        switch (moveType) {
            case 'U':
                hero.resetPosition(x - 1, y);
                return;
            case 'D':
                hero.resetPosition(x + 1, y);
                return;
            case 'L':
                hero.resetPosition(x, (y - 1));
                return;
            case 'R':
                hero.resetPosition(x, y + 1);
                return;
            case '_':
                return;
            default:
                return;
        }
    }
    /**
     * @param currentRound Runda curenta
     * @param heroes Lista eroilor
     * @param moves Matricea miscarilor
     * Functia va realiza miscarile tuturor jucatorilor din runda curenta. Jucatorii
     * vor efectua miscarea doar daca nu sunt sub efectul unei abilitati cu efect prelungit
     * de incapacitate de miscare. Tot in aceasta functie va fi verificat si cel de-al doilea
     * efect al abilitatilor - primirea de damage overtime. Daca numarul rundelor in care eroul
     * este sub acest efect este diferit de 0, eroului ii va scadea hp-ul, iar numarul de runde
     * va scadea cu 1.
     */
    public void moves(final int currentRound, final List<Hero> heroes, final Character[][] moves) {
        int i;
        for (i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getDead() == 0) {
                if (heroes.get(i).getNoRoundsCantMove() == 0) {
                    makeMove(moves[currentRound][i], heroes.get(i),
                            heroes.get(i).getX(), heroes.get(i).getY());
                } else {
                    heroes.get(i).setNoRoundsCantMove(heroes.get(i).getNoRoundsCantMove() - 1);
                }
                if (heroes.get(i).getNoRoundsToReceivePeriodicDamage() != 0) {
                    heroes.get(i).setHp(heroes.get(i).getHp() - heroes.get(i).getPeriodicDamage());
                    heroes.get(i).setNoRoundsToReceivePeriodicDamage(
                            heroes.get(i).getNoRoundsToReceivePeriodicDamage() - 1);
                }
            }
        }
    }
}
