package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.handlers.ScoreHandler;


/**
 * Created by ares on 19/08/17.
 */
public class GamePreferences {
    public static final GamePreferences instance = new GamePreferences();

    private Preferences prefs;
    private boolean music;
    private boolean sound;
    private float longestDistance;

    private GamePreferences() {
        prefs = Gdx.app.getPreferences(Constant.PREFERENCES);
    }

    public void save() {
        prefs.putBoolean("music", AudioHandler.instance.getMuteMusic());
        prefs.putBoolean("sound", AudioHandler.instance.getMuteSound());
        prefs.putFloat("distance", ScoreHandler.instance.getLongestRun());

        prefs.flush();
    }

    public void load() {
        music = prefs.getBoolean("music");
        sound = prefs.getBoolean("sound");
        longestDistance = prefs.getFloat("distance");
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


}
