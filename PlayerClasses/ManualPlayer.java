package com.company;

import java.util.Scanner;

/**
 * The player repeatedly MANUALLY chooses a token, waits for his turn due to the synchronisation, creates an arithmetic progression
 */
public class ManualPlayer extends Player {

    public ManualPlayer(int id, String name, Board board) {
        this.id = id;
        this.name = name;
        this.board = board;
        board.addPlayers(this);
    }

    @Override
    public void run() {
        this.extractToken();
    }

    /**
     * The method manually extracts a token from the board while the board still has tokens and while there is no other winner
     * The method prints the available indexes (for example: "between 0 and 5")
     * if the chosen index is not existing, a random one will be assigned and a warning message will be displayed
     * It tells the board to change the players turn after he extracts his number
     * After the extractions are over, it prints his own arithmetic progression and adds his max length to the board
     */
    public synchronized void extractToken() {

        int i = 1;
        int gameOver = 0;
        while (i < 5 && board.outOfTokens() == 0 && gameOver == 0) {
            if (board.getTurn() == id) {

                System.out.println(this.name + ", it's your turn now!");
                Scanner s = new Scanner(System.in);
                System.out.println("Choose a token's index. It must be a number between 0 and " + board.currentNrOfTokens());
                String stringIndex = s.nextLine();

                int index = Integer.parseInt(stringIndex);
                if (index > board.currentNrOfTokens()) {
                    System.out.printf("%d is not a valid index. We picked a random token for you%n", index);
                    double rand = Math.random();
                    index = (int) (rand * board.getNumberOfTokens());

                }

                value++;
                System.out.println("Round " + value + ": " + name + " chose the token with index " + index);
                tokensValues.add(board.getTokenValue(index));
                if (this.progressionLength() == board.getProgressionsSize()) {gameOver = 1; board.endTheGame();}
                board.chooseToken(index);
                board.printBoard();
                i++;
                board.changeTurn();
                System.out.println(this.name + ", wait for your turn!");
            }
        }
        try {
            wait(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "'s progression is: " + tokensValues);
        board.addScore(this.progressionLength());


    }
}
