package com.company;

import java.util.ArrayList;

/**
 * The game creates the board, adds the players, creates a timekeeper thread that runs and displays the running time at the end of the game
 */
public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private int tokensNumber;
    private int maxValue;
    private int progressionsSize;

    public Game(int tokensNumber, int maxValue, int progressionsSize) {
        this.tokensNumber = tokensNumber;
        this.maxValue = maxValue;
        this.progressionsSize = progressionsSize;
    }

    /**
     * The method creates the board and the players and uses a thread for each player, creates the timekeeping thread
     */
    public void playGame() {
        Board board = new Board(tokensNumber, maxValue, progressionsSize);
        Runnable player1 = new RandomPlayer(1, "Noah", board);
        Runnable player2 = new SmartPlayer(2, "Sam", board);
        Runnable player3 = new ManualPlayer(3, "Jasmine", board);

        Runnable timekeeper= new Timekeeper(board,600);

        System.out.println("The current game has the following " + board.getPlayersNumber() + " players:");
        board.printPlayers();
        board.printBoard();
        new Thread(timekeeper).start();
        new Thread(player1).start();
        new Thread(player2).start();
        new Thread(player3).start();


    }

}
