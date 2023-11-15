package com.example.util;

public class DoNotTouch {
    public DoNotTouch() {
        //this ctor is doing its best
    }
    //X and Y arrays that represent the coordinates of the intersections of the hexagons
    public final float[] X = {732, 785, 838, 891, 944, 997, 1050, 1103, 1156, 1209, 1262}; //length = 11
    public final float[] Y = {0, 31, 93, 125, 186, 217, 279, 310, 372, 403, 465, 496};//length = 12
    //arrays that represent the six coordinate pairs of each hex
    //don't ask why I did it this way I was stressed and under pressure
    public float[] h0 = {X[2], Y[1], X[3], Y[0], X[4], Y[1], X[4], Y[2], X[3], Y[3], X[2], Y[2]};
    public float[] h1 = {X[4], Y[1], X[5], Y[0], X[6], Y[1], X[6], Y[2], X[5], Y[3], X[4], Y[2]};
    public float[] h2 = {X[6], Y[1], X[7], Y[0], X[8], Y[1], X[8], Y[2], X[7], Y[3], X[6], Y[2]};
    public float[] h3 = {X[1], Y[3], X[2], Y[2], X[3], Y[3], X[3], Y[4], X[2], Y[5], X[1], Y[4]};
    public float[] h4 = {X[3], Y[3], X[4], Y[2], X[5], Y[3], X[5], Y[4], X[4], Y[5], X[3], Y[4]};
    public float[] h5 = {X[5], Y[3], X[6], Y[2], X[7], Y[3], X[7], Y[4], X[6], Y[5], X[5], Y[4]};
    public float[] h6 = {X[7], Y[3], X[8], Y[2], X[9], Y[3], X[9], Y[4], X[8], Y[5], X[7], Y[4]};
    public float[] h7 = {X[0], Y[5], X[1], Y[4], X[2], Y[5], X[2], Y[6], X[1], Y[7], X[0], Y[6]};
    public float[] h8 = {X[2], Y[5], X[3], Y[4], X[4], Y[5], X[4], Y[6], X[3], Y[7], X[2], Y[6]};
    public float[] h9 = {X[4], Y[5], X[5], Y[4], X[6], Y[5], X[6], Y[6], X[5], Y[7], X[4], Y[6]};
    public float[] h10 = {X[6], Y[5], X[7], Y[4], X[8], Y[5], X[8], Y[6], X[7], Y[7], X[6], Y[6]};
    public float[] h11 = {X[8], Y[5], X[9], Y[4], X[10], Y[5], X[10], Y[6], X[9], Y[7], X[8], Y[6]};
    public float[] h12 = {X[1], Y[7], X[2], Y[6], X[3], Y[7], X[3], Y[8], X[2], Y[9], X[1], Y[8]};
    public float[] h13 = {X[3], Y[7], X[4], Y[6], X[5], Y[7], X[5], Y[8], X[4], Y[9], X[3], Y[8]};
    public float[] h14 = {X[5], Y[7], X[6], Y[6], X[7], Y[7], X[7], Y[8], X[6], Y[9], X[5], Y[8]};
    public float[] h15 = {X[7], Y[7], X[8], Y[6], X[9], Y[7], X[9], Y[8], X[8], Y[9], X[7], Y[8]};
    public float[] h16 = {X[2], Y[9], X[3], Y[8], X[4], Y[9], X[4], Y[10], X[3], Y[11], X[2], Y[10]};
    public float[] h17 = {X[4], Y[9], X[5], Y[8], X[6], Y[9], X[6], Y[10], X[5], Y[11], X[4], Y[10]};
    public float[] h18 = {X[6], Y[9], X[7], Y[8], X[8], Y[9], X[8], Y[10], X[7], Y[11], X[6], Y[10]};
}
