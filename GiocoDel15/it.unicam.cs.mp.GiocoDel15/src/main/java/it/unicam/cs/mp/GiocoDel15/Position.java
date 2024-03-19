package it.unicam.cs.mp.GiocoDel15;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private final int row;
    private final int column;
    private final int size;
    public Position(int row, int column, int size) {
        this.row = row;
        this.column = column;
        this.size = size;
    }

    public int getColumn() { return column; }

    public int getRow() { return row; }

    public int getSize() { return size; }

    public Position next(SlidingDirection dir) {
        switch (dir) {
            case UP:
                if (row < size-1)
                    return new Position(row+1,column,size);
            case DOWN:
                if (row > 0)
                    return new Position(row-1,column, size);
            case LEFT:
                if (column > 0)
                    return new Position(row, column-1, size);
            case RIGHT:
                if (column < size-1)
                    return new Position(row, column+1, size);
        }
        return null;
    }

    //is it better to use arrays and then copying it or lists?
    public SlidingDirection[] enabledMoves() {
        List<SlidingDirection> moves = new ArrayList<>();
        if (row > 0) moves.add(SlidingDirection.UP);
        if (row < size - 1) moves.add(SlidingDirection.DOWN);
        if (column > 0) moves.add(SlidingDirection.LEFT);
        if (column < size - 1) moves.add(SlidingDirection.RIGHT);
        return moves.toArray(new SlidingDirection[0]);
    }

    public boolean isCorrect(int value) {
        int correctRow = (value - 1) / size;
        int correctColumn = (value - 1) % size;
        return this.row == correctRow && this.column == correctColumn;
    }
}



