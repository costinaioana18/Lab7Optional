package com.company;

/**
 * The player repeatedly SMARTLY chooses a token, waits for his turn due to the synchronisation, creates an arithmetic progression
 */
public class SmartPlayer extends Player {

    public SmartPlayer(int id, String name, Board board) {
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
     * The method smartly extracts a token from the board while the board still has tokens and while there is no other winner
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
                int index = smartChosenIndex();
                System.out.println("Round " + value + ": " + name + " chose the token with index " + index);
                tokensValues.add(board.getTokenValue(index));
                if (this.progressionLength() == board.getProgressionsSize()) {
                    gameOver = 1;
                    board.endTheGame();
                }
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

    /**
     * The method searches a number on the board that would logically follow the existing arithmetic progression in order to extend it
     * If there is no proper token, a random one will be chosen
     * @return the smartly chosen index, such as the created arithmetic progression will be the longest possible
     */
    public int smartChosenIndex() {
        int index = -1;
        if (tokensValues.size() > 1) {
            int r = tokensValues.get(tokensValues.size() - 1) - tokensValues.get(tokensValues.size() - 2);

            for (int i = 0; i < board.currentNrOfTokens(); i++) {
                if (r == board.getTokenValue(i) - tokensValues.get(tokensValues.size() - 1)) return i;
            }
        }
        if (index == -1) {
            double rand = Math.random();
            index = (int) (rand * board.getNumberOfTokens());
        }

        return index;
    }
}
