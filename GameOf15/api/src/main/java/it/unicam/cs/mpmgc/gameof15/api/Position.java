package it.unicam.cs.mpmgc.gameof15.api;

import java.util.LinkedList;

public class Position {
    private final int row;
    private final int column;
    private final int size;
    public Position(int row, int column, int size) {
        this.row = row;
        this.column = column;
        this.size = size;
    }

    public Position(int size) {
        this(size-1, size-1, size);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    public Position movingCell(SlidingDirection dir) {
        if ((dir == SlidingDirection.UP) && (row > 0)) return new Position((row+1), column, size);
        if ((dir == SlidingDirection.DOWN) && (row < size-1)) return new Position((row-1), column, size);
        if ((dir == SlidingDirection.LEFT) && (column > 0)) return new Position(row, (column+1), size);
        if ((dir == SlidingDirection.RIGHT) && (column < size-1)) return new Position(row, (column-1), size);
        return null;
    }

    public SlidingDirection[] enabledMoves() {
        LinkedList<SlidingDirection> moves = new LinkedList<>();
        if (row > 0) moves.add(SlidingDirection.LEFT);
        if (row < size-1) moves.add(SlidingDirection.RIGHT);
        if (column > 0) moves.add(SlidingDirection.DOWN);
        if (column < size-1) moves.add(SlidingDirection.UP);
        return moves.toArray(new SlidingDirection[0]);
    }

    public int getDisorderDegree(int n) {
        return Math.abs(this.row - (n - 1) / this.size) + Math.abs(this.column - (n - 1) % this.size);
    }
}