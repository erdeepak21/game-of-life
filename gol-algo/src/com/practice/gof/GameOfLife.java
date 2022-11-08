package com.practice.gof;

/**
 * 1. Any live cell with fewer than two live neighbors dies as if caused by underpopulation.
 * 2. Any live cell with two or three live neighbors lives on to the next generation.
 * 3. Any live cell with more than three live neighbors dies, as if by overpopulation.
 * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 */
public class GameOfLife {
    private static final String LIVE = " LIVE : ";
    private static final String DEAD = " DEAD : ";

    public static void printPattern(int[][] series, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            System.out.print(":");
            for (int j = 0; j < columns; j++) {
                if (series[i][j] == 0) {
                    System.out.print(DEAD);
                } else {
                    System.out.print(LIVE);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int countLiveNeighbours(int rowIndex, int colIndex, int rows, int columns, int[][] series) {
        int liveNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((rowIndex + i >= 0 && rowIndex + i < rows) && (colIndex + j >= 0 && colIndex + j < columns)) {
                    liveNeighbours += series[rowIndex + i][colIndex + j];
                }
            }
        }
        liveNeighbours -= series[rowIndex][colIndex];

        return liveNeighbours;
    }

    public static int[][] nextGen(int[][] series, int rows, int cols) {
        int[][] nextGen = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int aliveNeighbours = countLiveNeighbours(i, j, rows, cols, series);
                if ((series[i][j] == 1) && (aliveNeighbours < 2)) {
                    nextGen[i][j] = 0;
                } else if ((series[i][j] == 1) && (aliveNeighbours > 3)) {
                    nextGen[i][j] = 0;
                } else if ((series[i][j] == 0) && (aliveNeighbours == 3)) {
                    nextGen[i][j] = 1;
                } else {
                    nextGen[i][j] = series[i][j];
                }
            }
        }
        return nextGen;
    }

    public static void main(String[] args) {
        int[][] statics = {{0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        int gen = 0;
        int columns = 5;
        int rows = 4;
        System.out.println("Original State................\n");
        printPattern(statics, rows, columns);
        while (gen < 20) {
            statics = nextGen(statics, rows, columns);
            System.out.println(gen + " Generation.....................\n");
            printPattern(statics, rows, columns);
            gen++;
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
