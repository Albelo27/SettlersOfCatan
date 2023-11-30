package com.example.settlersofcatan;

import com.example.util.DoNotTouch;
import com.example.util.Road;

import junit.framework.TestCase;

public class CatanGameStateTest extends TestCase {

    public void testRollDice() {
    }

    public void testGetRes() {
    }

    public void testGiveRes() {
        CatanGameState testState = new CatanGameState();
        testState.giveRes(0,testState.WHEAT, true);//WHEAT
        testState.giveRes(1,testState.BRICK, false);//BRICK
        testState.giveRes(2,testState.SHEEP, true);//SHEEP
        testState.giveRes(3,testState.WOOD, false);//WOOD
        assertEquals(3, testState.playerWheat[0]);//3 bc player 1 starts with 1 wheat on turn 0
        assertEquals(2, testState.playerBrick[1]);//2 bc player 2 starts with 1 brick on turn 0
        assertEquals(2, testState.playerSheep[2]);
        assertEquals(1, testState.playerWood[3]);
    }

    public void testBuildRoad() {
        CatanGameState testState = new CatanGameState();
        DoNotTouch c = new DoNotTouch();
        testState.buildRoad(0, c.X[7], c.Y[4], c.X[7], c.Y[3]);
        Road test = new Road(c.X[7], c.Y[4], c.X[7], c.Y[3]);
        Road newRoad = testState.data[0].roads.get(testState.data[0].roads.size()-1);
        assertEquals(test.x, newRoad.x);
        assertEquals(test.y, newRoad.y);
        assertEquals(test.z, newRoad.z);
        assertEquals(test.q, newRoad.q);
    }

    public void testCheckLegalRoad() {
    }

    public void testBuildSettlement() {
    }

    public void testCheckLegalSettlement() {
    }

    public void testCheckRoadLength() {
    }

    public void testUpgradeToCity() {
        CatanGameState testState = new CatanGameState();
        DoNotTouch c = new DoNotTouch();
        testState.playerOre[0] = 3;
        testState.playerWheat[0] = 2;
        testState.setPlayerUp(0);
        assertTrue(testState.upgradeToCity(0, c.X[3], c.Y[4]));

    }

    public void testCheckLegalCity() {
    }

    public void testPurchaseDC() {
    }

    public void testPlayMonopoly() {
    }

    public void testPlayKnight() {
    }

    public void testPlayRoadBuilder() {
    }

    public void testPlayYearOfPlenty() {
    }

    public void testPlayVictoryPoint() {
    }

    public void testMoveRobber() {
    }

    public void testFindAdjacent() {
    }
}