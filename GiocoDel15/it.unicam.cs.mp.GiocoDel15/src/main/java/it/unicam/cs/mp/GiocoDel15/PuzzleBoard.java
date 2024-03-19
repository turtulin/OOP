package it.unicam.cs.mp.GiocoDel15;

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
        return (this.numberOfCorrectCells==15);
    }

    public void initializeBoard() {
        int value = 1;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (value < size * size) {
                    cells[row][column] = value++;
                } else {
                    cells[row][column] = 0;
                }
            }
        }
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
        set(freeCell, getValueAt(movingCell));
        freeCell = movingCell;
        return true;
    }

    private void set(Position pos, int value) {
        cells[pos.getRow()][pos.getColumn()] = value;
    }

    //What's the difference between this method and get (at line 38)
    private int getValueAt(Position pos) {
        return cells[pos.getRow()][pos.getColumn()];
    }
}
