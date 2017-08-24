package radoslaw.slowinski.ares.utils;

/**
 * Created by ares on 21/08/17.
 */
public enum MapLevels {
    HELP("help", "help"),
    FREE_RUN("level0", "Long Run"),
    LEVEL1("level1", "1"),
    LEVEL2("level2", "2"),
    LEVEL3("level3", "3"),
    LEVEL4("level4", "4"),
    LEVEL5("level5", "5"),
    LEVEL6("level6", "6"),
    LEVEL7("level7", "7"),
    LEVEL8("level8", "8");

    private String mapName;
    private String mapLevel;

    MapLevels(String mapName, String mapLevel) {
        this.mapName = mapName;
        this.mapLevel = mapLevel;
    }

    public String getMapLevel() {
        return mapLevel;
    }

    public String getMapName() {
        return mapName;
    }
}
