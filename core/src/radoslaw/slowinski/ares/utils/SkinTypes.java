package radoslaw.slowinski.ares.utils;

/**
 * Created by ares on 15/08/17.
 */
public enum SkinTypes {
    GIRL("female"),
    BOY("player"),
    ADVENTURER("adventurer"),
    SOLDIER("soldier"),
    ZOMBIE("zombie");

    private final String skinName;

    SkinTypes(String skinName) {
        this.skinName = skinName;
    }

    public String getSkinName() {
        return skinName;
    }
}
