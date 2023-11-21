package com.example.util;

public class Road {
    public float x;
    public float y;
    public float z;
    public float q;

    public Road(float xIn, float yIn, float zIn, float qIn) {
        x = xIn;
        y = yIn;
        z = zIn;
        q = qIn;
    }

    public Road(Road copy) {
        this.x = copy.x;
        this.y = copy.y;
        this.z = copy.z;
        this.q = copy.q;
    }
}
