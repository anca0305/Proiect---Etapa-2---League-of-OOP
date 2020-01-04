package heroes;

import common.Constants;

import java.util.ArrayList;
import java.util.List;

public final class HeroesFactory {

    private HeroesFactory() {
    }

    public static Hero createHero(final Character heroType, final int x, final int y) {
        switch (heroType) {
            case 'K':
                return new Knight('L', x, y, Constants.INITIALHPK,
                        Constants.HPPERLEVELK, Constants.BONUSDAMAGEK);
            case 'P':
                return new Pyromancer('V', x, y, Constants.INITIALHPP,
                        Constants.HPPERLEVELP, Constants.BONUSDAMAGEP);
            case 'R':
                return new Rogue('W', x, y, Constants.INITIALHPR,
                        Constants.HPPERLEVELR, Constants.BONUSDAMAGER);
            case 'W':
                return new Wizard('D', x, y, Constants.INITIALHPW,
                        Constants.HPPERLEVELW, Constants.BONUSDAMAGEW);
            default:
                return null;
        }
    }

    public static ArrayList<Hero> createHeroes(final List<Character> heroes,
                                               final Integer[][] heroesPosition) {
        ArrayList<Hero> heroesList = new ArrayList<Hero>();
        int i;
        for (i = 0; i < heroes.size(); i++) {
            heroesList.add(createHero(heroes.get(i), heroesPosition[i][0], heroesPosition[i][1]));
        }
        return heroesList;
    }
}
