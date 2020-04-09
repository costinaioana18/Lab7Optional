package com.company;

/**
 * Generates and holds a number from 0 to a certain maximum value
 */
public class Token {
    int value;

    /**

     * The constructor automatically generates a random value between 0 and maxValue
     *
     * @param maxValue the maximum value the token can take
     */
    public Token(int maxValue) {
        double rand = Math.random();
        this.value = (int) (rand * maxValue);
    }

    /**
     * @return token's value
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
