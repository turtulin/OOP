package it.unicam.cs.mpmgc.gameof15.api;

public enum SlidingDirection {
    UP, DOWN, LEFT, RIGHT;

    SlidingDirection reverse() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
