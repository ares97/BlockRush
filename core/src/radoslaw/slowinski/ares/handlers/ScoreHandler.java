package radoslaw.slowinski.ares.handlers;

import radoslaw.slowinski.ares.utils.GamePreferences;

/**
 * Created by ares on 16/08/17.
 */
public class ScoreHandler {
    public static final ScoreHandler instance = new ScoreHandler();
    private int coins;
    private int currentLevelCoins;
    private int lastRunCoins;
    private float longestRun;
    private float lastRunDistance;

    private ScoreHandler() {
        longestRun = GamePreferences.instance.getLongestDistance();
        coins = GamePreferences.instance.getCoins();
    }

    public int getCurrentLevelCoins() {
        return currentLevelCoins;
    }

    public void addToCurrentLevelCoins(int amount) {
        currentLevelCoins += amount;
    }

    public int getCoins() {
        return coins;
    }

    public void transferCurrentLevelCoinsToCoins() {
        if(currentLevelCoins==0) return;
        coins += currentLevelCoins;
        lastRunCoins = currentLevelCoins;
        currentLevelCoins = 0;
    }

    public float getLongestRun() {
        return longestRun;
    }

    private void setLongestRun() {
        if (longestRun < lastRunDistance)
            longestRun = lastRunDistance;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public float getLastRunDistance() {
        return lastRunDistance;
    }

    public void setDistance(float distance){
        lastRunDistance = distance;
        setLongestRun();
    }

    public int getLastRunCoins() {
        return lastRunCoins;
    }

}
