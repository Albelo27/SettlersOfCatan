package com.example.util;

public class Pair<T, K> {

    private T pair1;
    private K pair2;

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
