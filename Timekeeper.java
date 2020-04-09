package com.company;

import java.util.concurrent.TimeUnit;

/**
 * the timekeeper thread will run concurrently with the players, and either displays the running time at the end of the game, either stops the game at a certain time limit
 */
public class Timekeeper implements Runnable {
    private int timeCounter = 0;
    private Board board;
    private int timeLimit;

    public Timekeeper(Board board, int timeLimit) {
        this.board = board;
        this.timeLimit = timeLimit;
    }

    /**
     * the method counts the game running time (seconds)
     * if the running time reaches the specified limit, the game will be stopped
     * at the end of the game, the running time will pe printed
     */
    @Override
    public void run() {
        while (board.isGameOver() == 0 && timeCounter < timeLimit) {
            if (board.getTurn() == 1) {
                timeCounter++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        if (timeCounter < timeLimit)
            board.endTheGame();
        System.out.println("This game lasted " + timeCounter + " minutes");

    }
}
