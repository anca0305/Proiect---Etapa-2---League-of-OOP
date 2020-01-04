package main;

import fileio.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class GameInputLoader {
    private final String mInputPath;
    private final String mOutputPath;

    GameInputLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    /**
     * Sa inceapa jocul!
     */
    public GameInput load() {
        List<Character> heroes = new ArrayList<>();
        ArrayList<String> angels = new ArrayList<>();
        Character[][] moves = new Character[0][];
        Character[][] map = new Character[0][];
        Integer[][] heroesPosition = new Integer[0][];
        Integer[][] angelsPosition = new Integer[0][];
        String currentRow;
        String currentRowMoves;
        int rows = 0;
        int columns = 0;
        int noHeroes = 0;
        int noAngels = 0;
        int currentNoAngels = 0;
        int noRounds = 0;
        int i, j;

        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);

            rows = fs.nextInt();
            columns = fs.nextInt();
            map = new Character[rows][columns];

            for (i = 0; i < rows; i++) {
                currentRow = fs.nextWord();
                for (j = 0; j < columns; j++) {
                    map[i][j] = currentRow.charAt(j);
                }
            }

            noHeroes = fs.nextInt();
            heroesPosition = new Integer[noHeroes][2];

            for (i = 0; i < noHeroes; ++i) {
                heroes.add(fs.nextWord().charAt(0));
                heroesPosition[i][0] = fs.nextInt();
                heroesPosition[i][1] = fs.nextInt();
            }

            noRounds = fs.nextInt();
            moves = new Character[noRounds][noHeroes];

            for (i = 0; i < noRounds; i++) {
                currentRowMoves = fs.nextWord();
                for (j = 0; j < noHeroes; j++) {
                    moves[i][j] = currentRowMoves.charAt(j);
                }
            }

            for (i = 0; i < noRounds; i++) {
                currentNoAngels = fs.nextInt();
                angelsPosition = new Integer[100][3];
                for (j = 0; j < currentNoAngels; j++) {
                    String[] line = fs.nextWord().split(",");
                    angels.add(line[0]);
                    angelsPosition[noAngels][0] = i;
                    angelsPosition[noAngels][1] = Integer.parseInt(line[1]);
                    angelsPosition[noAngels][2] = Integer.parseInt(line[2]);
                    noAngels++;
                }
            }
            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(noRounds, map, heroes, heroesPosition, moves, angels, angelsPosition);
    }
}
