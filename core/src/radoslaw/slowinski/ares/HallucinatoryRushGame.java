package radoslaw.slowinski.ares;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.screens.GameScreen;
import radoslaw.slowinski.ares.screens.MenuScreen;
import radoslaw.slowinski.ares.utils.GamePreferences;

public class HallucinatoryRushGame extends Game {
    private MenuScreen menuScreen;

    @Override
    public void create() {
        AssetHandler.instance.load(new AssetManager());
        GamePreferences.instance.load();

        menuScreen = new MenuScreen(this);
        setMenuScreen();
    }

    public void setMenuScreen(){
        setScreen(menuScreen);
    }

    public void setGameScreen(){
        GameScreen gameScreen = new GameScreen(this);
        gameScreen.setGameScreen();
    }
}
