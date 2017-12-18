package me.faizaand.simulator;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Generates a set of random numbers.
 */
public class RandomSet {

    private static final Random RAND = new Random(System.nanoTime());

    private int min, max;
    private int amount;

    public RandomSet(int min, int max, int amount) {
        this.min = min;
        this.max = max;
        this.amount = amount;
    }

    public int[] generate() {
        int[] returnVal = new int[amount];

        for(int i = 0; i < returnVal.length; i++) {
            int randomNumber = generateRandomNumber();
            returnVal[i] = randomNumber;
        }

        return returnVal;
    }
    private int generateRandomNumber() {
        return RAND.nextInt(max - min) + min;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getAmount() {
        return amount;
    }

}
