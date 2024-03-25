package it.unicam.cs.mpmgc.gameof15.api;

import java.util.Random;
public class PuzzleBoard {
    public static final int DEFAULT_SIZE = 4;
    private final int[][] board;
    private final int size;
    private Position emptyCell;
    private int shufflingDegree;

    public PuzzleBoard() {
        this(DEFAULT_SIZE);
    }

    public PuzzleBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
        reset();
    }

    private void reset() {
        shufflingDegree = 0;
        emptyCell = new Position(size);
        int counter = 1;
        for(int i=0; i < size; i++) {
            for(int j=0; j < size; j++) {
                this.board[i][j] = (counter++)%(size*size);
            }
        }
    }

    public boolean move(SlidingDirection dir) {
        Position movingCell = emptyCell.movingCell(dir);
        if (movingCell == null) {
            return false;
        }
        int n = get(movingCell);
        set(emptyCell, n);
        set(movingCell, 0);
        shufflingDegree = shufflingDegree-movingCell.getDisorderDegree(n)+emptyCell.getDisorderDegree(n);
        emptyCell = movingCell;
        return true;
    }

    private int get(Position p) {
        return get(p.getRow(), p.getColumn());
    }

    private void set(Position p, int v) {
        this.board[p.getRow()][p.getColumn()] = v;
    }

    public int get(int x, int y) {
        return this.board[x][y];
    }

    public void shuffle(int movements) {
        shuffle(new Random(), movements);
    }

    public void shuffle(Random random, int movements) {
        for(int i=0; i<movements; i++) {
            SlidingDirection[] enabledMovement = enabledMoves();
            if (enabledMovement.length>0) {
                move(enabledMovement[random.nextInt(enabledMovement.length)]);
            }
        }
    }

    public SlidingDirection[] enabledMoves() {
        return emptyCell.enabledMoves();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(int i, int j) {
        return board[i][j]==0;
    }

    public boolean solved() {
        return this.shufflingDegree==0;
    }
}
