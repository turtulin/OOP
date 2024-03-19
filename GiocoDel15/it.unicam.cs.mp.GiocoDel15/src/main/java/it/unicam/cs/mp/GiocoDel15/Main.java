package it.unicam.cs.mp.GiocoDel15;

import java.util.Random;

// Quale versione di gradle usa?
// gradle init --use-defaults --type java-application
// Aggiungere git
// Provare a stampare matrice
public class Main {
    public static void main(String[] args) {
        int size = 4;
        int[][] cells = new int[size][size];
        Position freeCell = new Position(size - 1, size -1, size);
        PuzzleBoard game = new PuzzleBoard(size, cells, freeCell);
        Random random = new Random();
        game.shuffle(random, 15);

    }
}