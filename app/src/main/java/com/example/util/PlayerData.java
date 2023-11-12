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
    public Hashtable<Pair, String> buildings;
    public Hashtable<Pair, Pair> roads;

    /**
     * Creates a new PLayerData class
     */
    public PlayerData() {
        devCards = new ArrayList<Integer>();
        buildings = new Hashtable<Pair, String>();
        roads = new Hashtable<Pair, Pair>();
    }

    /**
     * Copy constructor that creates a new instance of PlayerData containing the same data as the instance passed into the constructor
     * @param copy the PlayerData you wish to copy
     */
    public PlayerData(PlayerData copy) {
        this.devCards = new ArrayList<Integer>();
        this.devCards.addAll(copy.devCards);
        this.buildings = new Hashtable<Pair, String>();
        for (Pair key: copy.buildings.keySet()) {
            this.buildings.put(new Pair(key), new String(copy.buildings.get(key)));
        }
        this.roads = new Hashtable<Pair, Pair>();
        for (Pair key: copy.roads.keySet()) {
            this.roads.put(new Pair(key), new Pair(copy.roads.get(key)));
        }
    }
}
