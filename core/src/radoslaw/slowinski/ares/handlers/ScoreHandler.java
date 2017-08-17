package radoslaw.slowinski.ares.handlers;

/**
 * Created by ares on 16/08/17.
 */
public class ScoreHandler {
    private int coins;
    private int currentLevelCoins;

    public static ScoreHandler instance = new ScoreHandler();
    private ScoreHandler(){}



    public void addCoins(int amount) {
        coins += amount;
    }

    public int getCurrentLevelCoins() {
        return currentLevelCoins;
    }

    public void addToCurrentLevelCoins(int amount){
        currentLevelCoins += amount;
    }

    public void setCurrentLevelCoins(int currentLevelCoins) {
        this.currentLevelCoins = currentLevelCoins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
