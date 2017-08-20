package radoslaw.slowinski.ares;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.screens.GameScreen;
import radoslaw.slowinski.ares.screens.MenuScreen;
import radoslaw.slowinski.ares.screens.SelectPlayer;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.MapLoader;

public class HallucinatoryRushGame extends Game {
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private SelectPlayer selectPlayer;

    @Override
    public void create() {
        AssetHandler.instance.load(new AssetManager());
        GamePreferences.instance.load();

        menuScreen = new MenuScreen(this);
        selectPlayer = new SelectPlayer(this);

        setMenuScreen();
    }

    public void setMenuScreen() {
        setScreen(menuScreen);
    }

    public void setGameScreen() {
        if (gameScreen != null)
            gameScreen.dispose();

        gameScreen = new GameScreen(this);
        gameScreen.setGameScreen();
    }

    public void setSelectPlayerScreen() {
        setScreen(selectPlayer);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (gameScreen != null)
            gameScreen.dispose();
        AssetHandler.instance.dispose();
        MapLoader.instance.dispose();
    }
}
