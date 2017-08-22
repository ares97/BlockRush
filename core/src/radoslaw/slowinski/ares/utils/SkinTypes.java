package radoslaw.slowinski.ares.utils;

/**
 * Created by ares on 15/08/17.
 */
public enum SkinTypes {
    GIRL("female", 0),
    BOY("player", 0),
    ADVENTURER("adventurer", 500),
    SOLDIER("soldier", 1000),
    ZOMBIE("zombie", 1000);

    private final String skinName;
    private final int cost;

    SkinTypes(String skinName, int cost) {
        this.skinName = skinName;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public String getSkinName() {
        return skinName;
    }
}
