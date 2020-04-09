package com.company;

/**
 * The player repeatedly RANDOMLY chooses a token, waits for his turn due to the synchronisation, creates an arithmetic progression
 */
public class RandomPlayer extends Player {

    public RandomPlayer(int id, String name, Board board) {
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
     * The method randomly extracts a token from the board while the board still has tokens and while there is no other winner
     * "value" holds the round number
     * It tells the board to change the players turn after he extracts his number
     * After the extractions are over, it prints his own arithmetic progression and adds his max length to the board
     */
    public synchronized void extractToken() {

        int i = 1;
        int gameOver = 0;
        while (i < 5 && board.outOfTokens() == 0 && gameOver == 0) {
            if (board.getTurn() == id) {
                System.out.println(this.name + ", it's your turn now!");
                value++;
                double rand = Math.random();
                int index = (int) (rand * board.getNumberOfTokens());
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
