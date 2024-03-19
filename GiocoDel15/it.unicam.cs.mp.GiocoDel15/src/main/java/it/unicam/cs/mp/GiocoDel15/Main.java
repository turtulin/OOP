package it.unicam.cs.mp.GiocoDel15;

import java.util.Random;

// Quale versione di gradle usa?
// gradle init --use-defaults --type java-application
// Provare a stampare matrice
public class Main {
    public static void main(String[] args) {
        int size = 4;
        int[][] cells = new int[size][size];
        Position freeCell = new Position(size - 1, size -1, size);
        PuzzleBoard game = new PuzzleBoard(size, cells, freeCell);
        game.initializeBoard();
        //Random random = new Random();
        //game.shuffle(random, 15);
        printPuzzleBoard(game);
        game.move(SlidingDirection.DOWN);
        printPuzzleBoard(game);
        game.move(SlidingDirection.UP);
        printPuzzleBoard(game);
        game.move(SlidingDirection.LEFT);
        printPuzzleBoard(game);
        game.move(SlidingDirection.RIGHT);
        printPuzzleBoard(game);
    }

    public static void printPuzzleBoard(PuzzleBoard game) {
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                System.out.print(game.getCells()[i][j] + " ");
            }
            System.out.println();
        }
    }
}