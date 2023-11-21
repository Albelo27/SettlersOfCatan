package com.example.util;

public class Hex {
    //which resource does this hex produce
    private int resource;
    //when does this hex produce that resource
    private int genNum;
    private float[] corners = new float[12];
    private boolean robber = false;

    /**
     * Create a new Hex
     * @param res the resource produced by the hex
     * @param num the number that must be rolled for the hex to produce said resource
     * @param vertex the 12 coordinate pairs of the 6 corners of the hex. These should be passed as x0, y0, x1, y1, x2, y2...x11, y11. (x0,y0) should be the top left corner.
     */
    public Hex(int res, int num, float[] vertex) {
        resource = res;
        genNum = num;
        for (int i = 0; i < corners.length; i++) {
            corners[i] = vertex[i];
        }
    }

    /**
     * deep copy constructor for the hex class
     * @param copy the Hex instance that is being copied
     */
    public Hex(Hex copy) {
        this.resource = copy.getResource();
        this.genNum = copy.getGenNum();
        this.robber = copy.hasRobber();
        for (int a = 0; a < this.corners.length; a++) {
            this.corners[a] = copy.getCorners()[a];
        }
    }

    public boolean hasCorner(float x, float y) {
        for (int q = 0; q < corners.length; q+=2) {
            if (x == corners[q] && y == corners[q+1]) {
                return true;
            }
        }
        return false;
    }
    public float[] getCenter() {
        float[] pair = new float[2];
        pair[0] = corners[2];
        pair[1] = corners[1] + ((corners[11] - corners[1])/2);
        return pair;
    }
    public boolean hasRobber() {
        return robber;
    }

    public void putRobber() {
        robber = true;
    }
    public void takeRobber() {robber = false;}

    public int getResource() {
        return resource;
    }

    public int getGenNum() {
        return genNum;
    }

    public float[] getCorners() {
        return corners;
    }
}
