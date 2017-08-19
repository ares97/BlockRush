package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


/**
 * Created by ares on 19/08/17.
 */
public class GamePreferences {
    private Preferences prefs;

    public static final GamePreferences instance = new GamePreferences();

    private GamePreferences() {
        prefs = Gdx.app.getPreferences(Constant.PREFERENCES);
    }

    public void save(){

    }

    public void load(){

    }


}
