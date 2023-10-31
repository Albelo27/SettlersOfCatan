package com.example.util;
/**
 * Pair is a custom util class that relates Generic pairs
 *
 * @author Anthony Albelo
 * @author Eric Su
 * @author Connor Santa Monica
 * @author Reiss Oliveross
 * @author Ryley vargas
 *
 * @Version October 30th 2023
 */
public class Pair<T, K> {

    private T pair1;
    private K pair2;

    /**
     * The Pair constructor initializes a Pair class and records the passed objects to one another through the Pair object
     * @param p1 The first, or 'key' object
     * @param p2 The second, or 'value' object
     */
    public Pair(T p1, K p2) {
        pair1 = p1;
        pair2 = p2;
    }

    public Pair(Pair<T, K> pair) {
        this.pair1 = pair.getKey();
        this.pair2 = pair.getValue();
    }

    public T getKey() {
        return pair1;
    }
    public K getValue() {
        return pair2;
    }

}
