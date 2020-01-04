package main;

import java.util.ArrayList;
import java.util.List;

public class GameInput {
    private final Character[][] map;
    private final List<Character> heroes;
    private final ArrayList<String> angels;
    private final Integer[][] heroesPosition;
    private final Integer[][] angelsPosition;
    private final Character[][] moves;
    private final int noRounds;

    /**
     * Constructor.
     */
    public GameInput(final int mNoRounds, final Character[][] mMap, final List<Character> mHeroes,
                     final Integer[][] mHeroesPosition, final Character[][] mMoves,
                     final ArrayList<String> mAngels, final Integer[][] mAngelsPosition) {
        noRounds = mNoRounds;
        map = mMap;
        heroes = mHeroes;
        heroesPosition = mHeroesPosition;
        moves = mMoves;
        angels = mAngels;
        angelsPosition = mAngelsPosition;
    }
    /**
     * Getter pentru harta.
     */
    public Character[][] getMap() {
        return map;
    }
    /**
     * Getter pentru lista eroilor.
     */
    public List<Character> getHeroes() {
        return heroes;
    }
    /**
     * Getter pentru lista ingerilor.
     */
    public ArrayList<String> getAngels() {
        return angels;
    }
    /**
     * Getter pentru pozitiile initiale ale eroilor.
     */
    public Integer[][] getHeroesPosition() {
        return heroesPosition;
    }
    /**
     * Getter pentru runda si pozitia unde va aparea fiecare inger.
     */
    public Integer[][] getAngelsPosition() {
        return angelsPosition;
    }
    /**
     * Getter pentru matricea mutarilor.
     */
    public Character[][] getMoves() {
        return moves;
    }
    /**
     * Getter pentru numarul rundelor.
     */
    public int getNoRounds() {
        return noRounds;
    }
    /**
     * Functie ce verifica daca input-ul a fost corect.
     */
    public final boolean isValidInput() {
        boolean hartaDescrisa = map != null;
        boolean jucatoriInstantiati = heroes != null && heroesPosition != null;
        boolean jucatoriExistenta = heroes.size() > 0;

        return hartaDescrisa && jucatoriInstantiati && jucatoriExistenta;
    }
}

