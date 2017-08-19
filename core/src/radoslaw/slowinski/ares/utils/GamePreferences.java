package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import radoslaw.slowinski.ares.handlers.AudioHandler;


/**
 * Created by ares on 19/08/17.
 */
public class GamePreferences {
    private Preferences prefs;
    public boolean music;
    public boolean sound;

    public static final GamePreferences instance = new GamePreferences();

    private GamePreferences() {
        prefs = Gdx.app.getPreferences(Constant.PREFERENCES);
    }

    public void save(){
        prefs.putBoolean("music", AudioHandler.instance.getMuteMusic());
        prefs.putBoolean("sound",AudioHandler.instance.getMuteSound());

        prefs.flush();
    }

    public void load(){
        music = prefs.getBoolean("music");
        sound = prefs.getBoolean("sound");
    }


}
