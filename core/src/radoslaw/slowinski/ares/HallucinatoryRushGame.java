package radoslaw.slowinski.ares;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.screens.gameplay.GameScreen;
import radoslaw.slowinski.ares.screens.menu.MenuScreen;
import radoslaw.slowinski.ares.screens.menu.SelectPlayerScreen;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.MapLoader;

public class HallucinatoryRushGame extends Game {
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private SelectPlayerScreen selectPlayerScreen;

    @Override
    public void create() {
        AssetHandler.instance.load(new AssetManager());
        GamePreferences.instance.load();

        menuScreen = new MenuScreen(this);
        selectPlayerScreen = new SelectPlayerScreen(this);

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
        if(selectPlayerScreen != null)
        setScreen(selectPlayerScreen);
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
