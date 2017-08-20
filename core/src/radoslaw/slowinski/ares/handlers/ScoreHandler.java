package radoslaw.slowinski.ares.handlers;

import radoslaw.slowinski.ares.utils.GamePreferences;

/**
 * Created by ares on 16/08/17.
 */
public class ScoreHandler {
    public static final ScoreHandler instance = new ScoreHandler();
    private int coins;
    private int currentLevelCoins;
    private float longestRun;

    private ScoreHandler() {
        longestRun = GamePreferences.instance.getLongestDistance();
    }

    public void addCoins(int amount) {
        coins += amount;
    }


    public int getCurrentLevelCoins() {
        return currentLevelCoins;
    }

    public void setCurrentLevelCoins(int currentLevelCoins) {
        this.currentLevelCoins = currentLevelCoins;
    }

    public void addToCurrentLevelCoins(int amount) {
        currentLevelCoins += amount;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public float getLongestRun() {
        return longestRun;
    }

    public void setLongestRun(float longestRun) {
        if (this.longestRun < longestRun)
            this.longestRun = longestRun;
    }
}
