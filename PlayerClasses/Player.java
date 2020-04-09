package com.company;

import java.util.ArrayList;

/**
 * The abstract class having RandomPlayer, SmartPlayer and ManualPlayer as subclasses
 */
public abstract class Player implements Runnable {
    String name;
    int id;
    int value;
    Board board;
    ArrayList<Integer> tokensValues = new ArrayList<>();

    /**
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }


    /**
     * the method calculated the players maximum arithmetic progression lenght
     *
     * @return max length
     */
    public int progressionLength() {
        int maxLenght = 2;
        if (tokensValues.size() > 2) {
            int currentLenght = 2;
            int r = tokensValues.get(1) - tokensValues.get(0);
            for (int i = 2; i < tokensValues.size(); i++) {
                if (tokensValues.get(i) - tokensValues.get(i - 1) == r || tokensValues.get(i) == 0 || tokensValues.get(i - 1) == 0)
                    currentLenght++;
                else {
                    if (currentLenght > maxLenght) maxLenght = currentLenght;
                    r = tokensValues.get(i) - tokensValues.get(i - 1);
                    currentLenght = 2;

                }

            }
        }
        return maxLenght;
    }
}
