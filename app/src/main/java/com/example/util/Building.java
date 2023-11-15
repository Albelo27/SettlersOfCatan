package com.example.util;

public class Building {

    private String name;
    private float xPos;
    private float yPos;
    private Hex[] closeTiles = new Hex[3];

    /**
     * Constructor that creates a new Building object
     * @param name is it a city or a settlement?
     * @param x x coordinate
     * @param y y coordinate
     * @param tiles the three Hex objects that are adjacent to the building
     */
    public Building(String name, float x, float y, Hex[] tiles) {
        this.name = name;
        xPos = x;
        yPos = y;
        for (int a = 0; a < closeTiles.length; a++) {
            closeTiles[a] = tiles[a];
        }
    }

    public String getName() {
        return name;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }
}
