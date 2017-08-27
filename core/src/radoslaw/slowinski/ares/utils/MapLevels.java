package radoslaw.slowinski.ares.utils;

/**
 * Created by ares on 21/08/17.
 */
public enum MapLevels {
    HELP("help", "help",0),
    FREE_RUN("level0", "Long Run",100),
    LEVEL1("level1", "1",8),
    LEVEL2("level2", "2",9),
    LEVEL3("level3", "3",10),
    LEVEL4("level4", "4",11),
    LEVEL5("level5", "5",11),
    LEVEL6("level6", "6",12),
    LEVEL7("level7", "7",10),
    LEVEL8("level8", "8",10),
    LEVEL9("level9", "9",10),
    LEVEL10("level10", "10",10);

    private String mapName;

    private String mapLevel;
    private int coinsOnMap;
    MapLevels(String mapName, String mapLevel,int coins) {
        this.mapName = mapName;
        this.mapLevel = mapLevel;
        coinsOnMap = coins;
    }

    public String getMapLevel() {
        return mapLevel;
    }

    public String getMapName() {
        return mapName;
    }

    public int getCoinsOnMap() {
        return coinsOnMap;
    }
}
