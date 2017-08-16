package radoslaw.slowinski.ares.handlers;

/**
 * Created by ares on 16/08/17.
 */
public class ScoreHandler {
    private int coins;

    public static ScoreHandler instance = new ScoreHandler();

    private ScoreHandler(){}

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int amount) {
        coins += amount;
    }
}
