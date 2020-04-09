package com.company;

import java.util.ArrayList;

/**
 * the turn attribute says whose turn is at the current moment
 * Contains an arrayList of a certain number of tokens, an arrayList of players, and an arrayList of integers(Which contains the maximum length of each player's arithmetic progression )
 */
public class Board {
    private int nrOfTokens;
    private int maxValue;
    private int playersNumber;
    private int progressionsSize;
    private int gameOver=0;
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Integer> maxProgression = new ArrayList<>();
    private int turn = 1;

    /**
     * The constructor creates new tokens and puts them in the 'tokens' arrayList
     *
     * @param nrOfTokens       the number of tokens (n)
     * @param maxValue         the maximum value a token can have (m)
     * @param progressionsSize the length needed in order to win the game (k)
     */
    public Board(int nrOfTokens, int maxValue, int progressionsSize) {
        this.progressionsSize = progressionsSize;
        this.maxValue = maxValue;
        this.nrOfTokens = nrOfTokens;
        for (int i = 0; i < nrOfTokens; i++) {
            tokens.add(new Token(maxValue));
        }

    }

    /**
     *
     * @return whether the game is over(1) or not(0)
     */
    public int isGameOver(){
        return gameOver;
    }

    /**
     * @return the length needed in order to win the game
     */
    public int getProgressionsSize() {
        return this.progressionsSize;
    }

    /**
     * @return the total number of players
     */
    public int getPlayersNumber() {
        return playersNumber;
    }

    /**
     * @return the existing number of tokens on the board
     */
    public int getNumberOfTokens() {
        return nrOfTokens;
    }

    /**
     * @param index
     * @return the value of the token having the given index
     */
    public int getTokenValue(int index) {
        if (index >= tokens.size()) index--;

        return tokens.get(index).getValue();
    }

    /**
     * @param player is added at the board's players arrayList
     */
    public void addPlayers(Player player) {
        players.add(player);
        playersNumber++;
    }

    /**
     * @return whose turn is at the moment
     */
    public int getTurn() {
        return turn;
    }

    /**
     * the method changes the turn variable in order to synchronize the game
     */
    public void changeTurn() {
        if (turn == this.getPlayersNumber()) turn = 1;
        else turn++;
    }

    /**
     *
     * @return the number of available tokens on the board
     */
    public int currentNrOfTokens() {
        return tokens.size() - 1;
    }


    /**
     * When a token is chosen by a player, this method removes that token from the arrayList
     *If there are no more tokens on the board, the game is over
     * @param i the index chosen by the player
     */
    public void chooseToken(int i) {
        if (i == tokens.size()) i--;
        tokens.remove(i);
        nrOfTokens--;
        if(tokens.size()==0)
            gameOver=1;
    }


    /**
     * The method prints the board's tokens
     */
    public void printBoard() {
        System.out.println("My board is: " + tokens);

    }

    /**
     * The method prints the board's players' names
     */
    public void printPlayers() {
        for (Player p : players) {
            System.out.println(p.getName());
        }
    }

    /**
     * @return 1 if the board is out of tokens, else, returns 0
     */
    public int outOfTokens() {

        if (nrOfTokens == 0) return 1;
        return 0;
    }

    /**
     * the method ends the game
     */
    public void endTheGame(){
        gameOver=1;
    }

    /**
     * @param length adds a new lenght to the maxProgression array List, in order to hold all the maximum sizes from each players' arithmetic progression
     */
    public void addScore(int length) {

        maxProgression.add(length);
        if (maxProgression.size() == playersNumber) this.setWinner();
    }

    /**
     * The method finds out the maximum length from the maxProgression arrayList, in order to find the winner
     * If more players have the same maximum lenght, the method will print "There is no winner"
     */
    public void setWinner() {
        int max = 0;
        int winner = 0;
        int equality = 0;
        for (int i = 0; i < maxProgression.size(); i++) {
            if (maxProgression.get(i) > max) {
                max = maxProgression.get(i);
                winner = i;
            }

        }
        for (int i = 0; i < maxProgression.size(); i++) {
            if (winner != i && maxProgression.get(i) == max)
                equality = 1;
        }
        winner++;
        if (equality == 1) {
            System.out.println("There is no winner");
        } else System.out.println("The winner is Player" + winner);

    }
}


