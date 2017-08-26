package radoslaw.slowinski.ares.utils;

/**
 * Created by ares on 15/08/17.
 */
public enum SkinTypes {
    GIRL("female", 0),
    BOY("player", 0),
    ADVENTURER("adventurer", 1000),
    SOLDIER("soldier", 2500),
    ZOMBIE("zombie", 5000),
    JOYSTICK("joystick",10000),
    LANTERN("lantern", 10);

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
