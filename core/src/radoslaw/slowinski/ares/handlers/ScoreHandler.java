package radoslaw.slowinski.ares.handlers;

/**
 * Created by ares on 16/08/17.
 */
public class ScoreHandler {
    public static final ScoreHandler instance = new ScoreHandler();
    private int coins;
    private int currentLevelCoins;

    private ScoreHandler() {
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
}
