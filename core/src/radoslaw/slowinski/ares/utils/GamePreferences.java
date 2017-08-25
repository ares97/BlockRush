package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.controls.BadgeIcon;
import radoslaw.slowinski.ares.handlers.ScoreHandler;
import radoslaw.slowinski.ares.handlers.UserDataHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by ares on 19/08/17.
 */
public class GamePreferences {
    public static final GamePreferences instance = new GamePreferences();

    private Preferences prefs;
    private boolean music;
    private boolean sound;
    private float longestDistance;
    private String playerSkinName;
    private HashMap<SkinTypes,Boolean> boughtPlayers;
    private BadgeIcon[] badges;

    private int coins;

    private GamePreferences() {
        prefs = Gdx.app.getPreferences(Constant.PREFERENCES);
        boughtPlayers = new HashMap<SkinTypes, Boolean>(SkinTypes.values().length);
        badges = new BadgeIcon[MapLevels.values().length];
    }

    public void save() {
        prefs.putBoolean("music", AudioHandler.instance.getMuteMusic());
        prefs.putBoolean("sound", AudioHandler.instance.getMuteSound());
        prefs.putFloat("distance", ScoreHandler.instance.getLongestRun());
        prefs.putString("playerSkinName", UserDataHandler.instance.getPlayerSkin().getSkinName());
        prefs.putInteger("coins",ScoreHandler.instance.getCoins());

        for (BadgeIcon badge : badges){
            prefs.putInteger(badge.getMapLevel().getMapName(),badge.getBadge().ordinal());
        }

        for (Map.Entry<SkinTypes,Boolean> entry : boughtPlayers.entrySet()){
            prefs.putBoolean(entry.getKey().getSkinName(),entry.getValue());
        }

        prefs.flush();
    }

    public void load() {
        music = prefs.getBoolean("music");
        sound = prefs.getBoolean("sound");
        longestDistance = prefs.getFloat("distance");
        playerSkinName = prefs.getString("playerSkinName");
        coins = prefs.getInteger("coins");

        for (SkinTypes skin : SkinTypes.values()){
            boughtPlayers.put(skin,prefs.getBoolean(skin.getSkinName()));
        }

        MapLevels[] maps = MapLevels.values();
        for(int i=0;i<MapLevels.values().length;i++){
            badges[i] = new BadgeIcon(
                    maps[i],
                    prefs.getInteger(maps[i].getMapName()));
        }
    }

    public boolean isMusic() {
        return music;
    }

    public boolean isSound() {
        return sound;
    }

    public float getLongestDistance() {
        return longestDistance;
    }

    public String getPlayerSkinName() {
        return playerSkinName;
    }

    public int getCoins() {
        return coins;
    }

    public HashMap<SkinTypes, Boolean> getBoughtPlayers() {
        return boughtPlayers;
    }

    public BadgeIcon[] getBadges() {
        return badges;
    }
}
