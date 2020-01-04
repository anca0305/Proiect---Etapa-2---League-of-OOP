package main;

import angels.Angel;
import angels.AngelsFactory;
import heroes.Hero;
import heroes.HeroesFactory;
import map.Moves;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

final class Main {

    private Main() {
        // just to trick checkstyle
    }

    public static void main(final String[] args) throws IOException {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        ArrayList<Hero> heroes;
        ArrayList<Angel> angels;
        heroes = HeroesFactory.createHeroes(gameInput.getHeroes(), gameInput.getHeroesPosition());
        angels = AngelsFactory.createAngels(gameInput.getAngels(), gameInput.getAngelsPosition());
        int i;
        int j;
        int k;
        Character[][] map;
        map = gameInput.getMap();
        Moves moves = new Moves();
        for (i = 0; i < gameInput.getNoRounds(); i++) {
            moves.moves(i, heroes, gameInput.getMoves());
            for (j = 0; j < heroes.size(); j++) {
                heroes.get(j).setCurrentGround(map[heroes.get(j).getX()][heroes.get(j).getY()]);
                heroes.get(j).setCurrentRound(i);
            }
            for (j = 0; j < heroes.size() - 1; j++) {
                for (k = j + 1; k < heroes.size(); k++) {
                    if (heroes.get(j).checkPosition(heroes.get(k))) {
                        if (heroes.get(j).getHp() <= 0) {
                            heroes.get(j).setDead(1);
                        }
                        if (heroes.get(k).getHp() <= 0) {
                            heroes.get(k).setDead(1);
                        }
                        if (heroes.get(j).getDead() == 0 && heroes.get(k).getDead() == 0) {
                            heroes.get(j).calculateDamage(heroes.get(j).getCurrentGround(),
                                    heroes.get(k), heroes.get(j).getLevel());
                            heroes.get(k).calculateDamage(heroes.get(k).getCurrentGround(),
                                    heroes.get(j), heroes.get(k).getLevel());
                            if (heroes.get(j).getDead() == 1 || heroes.get(j).getHp() <= 0) {
                                heroes.get(j).setDead(1);
                                if (heroes.get(k).getDead() == 1 || heroes.get(k).getHp() <= 0) {
                                    heroes.get(k).setDead(1);
                                }
                                if (heroes.get(j).getKiller().getDead() == 0) {
                                    heroes.get(j).getKiller().setXp(heroes.get(j).getLevel());
                                    heroes.get(j).getKiller().checkLevel();
                                }
                            }
                            if (heroes.get(k).getDead() == 1 || heroes.get(k).getHp() <= 0) {
                                heroes.get(k).setDead(1);
                                if (heroes.get(k).getKiller().getDead() == 0) {
                                    heroes.get(k).getKiller().setXp(heroes.get(k).getLevel());
                                    heroes.get(k).getKiller().checkLevel();
                                }
                            }
                        }
                    }
                }
            }
        }

        for (i = 0; i < heroes.size(); i++) {
            System.out.println(gameInput.getHeroes().get(i) + heroes.get(i).toString());
            writer.write(gameInput.getHeroes().get(i) + heroes.get(i).toString() + "\n");
        }
        writer.close();
    }
}

