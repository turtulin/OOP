package it.unicam.cs.mpmgc.gameof15.app;

import it.unicam.cs.mpmgc.gameof15.api.PuzzleBoard;
import it.unicam.cs.mpmgc.gameof15.api.SlidingDirection;

import java.util.Scanner;

public class ConsoleApp {
    private final PuzzleBoard board;

    private final Scanner input = new Scanner(System.in);

    public ConsoleApp() {
        this(PuzzleBoard.DEFAULT_SIZE);
    }

    public ConsoleApp(int size) {
        this.board = new PuzzleBoard(size);
    }


    private void printBoard() {
        for(int i=0; i < board.getSize(); i++) {
            printRowSeparator();
            printRow(i);
        }
        printRowSeparator();
    }

    private void printRow(int i) {
        for(int j=0; j < board.getSize() ; j++) {
            if (board.isEmpty(i, j)) {
                System.out.print("+    ");
            } else {
                System.out.printf("+ %2d ",board.get(i, j));
            }
        }
        System.out.println("+");
    }

    private void printRowSeparator() {
        for(int i=0; i < board.getSize() ; i++) {
            System.out.print("+----");
        }
        System.out.println("+");
    }

    public void start() {
        board.shuffle(200);
        int movesCounter = 0;
        while (!board.solved()) {
            printBoard();
            if (doAction()) {
                movesCounter++;
            }
        }
        System.out.printf("Well done! You solved the puzzle with %d moves!\n", movesCounter);
    }

    private boolean doAction() {
        System.out.println("Enter your move (u, d, l, r):  ");
        System.out.flush();
        boolean flag = true;
        boolean rightCommand = true;
        String command = input.nextLine();
        switch (command.charAt(0)) {
            case 'u':
                flag = board.move(SlidingDirection.UP);
                break;
            case 'd':
                flag = board.move(SlidingDirection.DOWN);
                break;
            case 'l':
                flag = board.move(SlidingDirection.LEFT);
                break;
            case 'r':
                flag = board.move(SlidingDirection.RIGHT);
                break;
            default:
                rightCommand = false;
        }
        if (!flag) {
            System.out.println("\n\nERROR: Illegal move!\n\n");
            return false;
        }
        if (!rightCommand) {
            System.out.println("Illegal command!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ConsoleApp cp = new ConsoleApp(5);
        cp.start();
    }
}
