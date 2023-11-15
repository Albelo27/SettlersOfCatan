package com.example.util;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * PlayerData is a class that contains ArrayLists and Hashtables for each player's roads, development cards and buildindings
 *
 * @author Anthony Albelo
 * @author Eric Su
 * @author Connor Santa Monica
 * @author Reiss Oliveros
 * @author Ryley vargas
 *
 * @version November 12th 2023
 */

public class PlayerData {
    public ArrayList<Integer> devCards;
    public ArrayList<Building> buildings;
    public ArrayList<float[]> roads;

    /**
     * Creates a new PLayerData class
     */
    public PlayerData() {
        devCards = new ArrayList<Integer>();
        buildings = new ArrayList<Building>();
        roads = new ArrayList<float[]>();
    }

    /**
     * Copy constructor that creates a new instance of PlayerData containing the same data as the instance passed into the constructor
     * @param copy the PlayerData you wish to copy
     */
    public PlayerData(PlayerData copy) {
        this.devCards = new ArrayList<Integer>();
        this.devCards.addAll(copy.devCards);
        this.buildings = new ArrayList<Building>();
        this.buildings.addAll(copy.buildings);
        this.roads = new ArrayList<float[]>();
        this.roads.addAll(copy.roads);
    }
}
