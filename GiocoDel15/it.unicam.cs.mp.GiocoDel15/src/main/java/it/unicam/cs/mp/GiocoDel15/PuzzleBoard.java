package it.unicam.cs.mp.GiocoDel15;

import javax.management.remote.JMXConnectionNotification;
import java.util.Random;

public class PuzzleBoard {
    private final int size;
    private int[][] cells;
    private Position freeCell;

    public PuzzleBoard(int size, int[][] cells, Position freeCell) {
        this.size = size;
        this.cells = cells;
        this.freeCell = freeCell;
    }

    private int numberOfCorrectCells = 15;

    public boolean isSolved() {
        return (this.numberOfCorrectCells == 15);
    }

    public void initializeBoard() {
        int value = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = value;
                value++;
            }
        }
        cells[size - 1][size - 1] = 0;
    }

    public void shuffle(Random random, int movements) {
        for(int i = 0; i < movements; i++) {
            SlidingDirection[] enabledMovement = enabledMoves();
            if (enabledMovement.length > 0) {
                int idx = random.nextInt(enabledMovement.length);
                move(enabledMovement[idx]);
            }
        }
    }

    public SlidingDirection[] enabledMoves() {
        return freeCell.enabledMoves();
    }

    public boolean move(SlidingDirection dir) {
        Position movingCell = freeCell.next(dir);
        if (movingCell == null) {
            return false;
        }
        int value = getValueAt(movingCell);
        if (movingCell.isCorrect(value)) numberOfCorrectCells--;
        if (freeCell.isCorrect(value)) numberOfCorrectCells++;
        set(freeCell, movingCell);
        freeCell = movingCell;
        return true;
    }

    private void set(Position pos, Position movingCell) {
        cells[pos.getRow()][pos.getColumn()] = getValueAt(movingCell);
        cells[movingCell.getRow()][movingCell.getColumn()] = 0;
    }

    public int getValueAt(Position pos) {
        return cells[pos.getRow()][pos.getColumn()];
    }

    public int[][] getCells() {
        return cells;
    }

    public int getSize() {
        return size;
    }
}
