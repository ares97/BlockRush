package radoslaw.slowinski.ares;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.screens.gameplay.GameOverScreen;
import radoslaw.slowinski.ares.screens.gameplay.GameScreen;
import radoslaw.slowinski.ares.screens.gameplay.HUD;
import radoslaw.slowinski.ares.screens.menu.MenuScreen;
import radoslaw.slowinski.ares.screens.menu.SelectLevelScreen;
import radoslaw.slowinski.ares.screens.menu.SelectPlayerScreen;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.MapLoader;

public class RushGame extends Game {
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private SelectPlayerScreen selectPlayerScreen;
    private GameOverScreen gameOverScreen;
    private SelectLevelScreen selectLevelScreen;

    private boolean isPlaying;
    private boolean isMenuScreenOn;

    @Override
    public void create() {
        AssetHandler.instance.load(new AssetManager());
        GamePreferences.instance.load();

        menuScreen = new MenuScreen(this);
        selectPlayerScreen = new SelectPlayerScreen(this);
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        HUD.instance.setMyGame(this);

        setMenuScreen();
    }

    public void setMenuScreen() {
        isMenuScreenOn = true;
        setScreen(menuScreen);
    }

    public void setGameScreen(String mapTitle) {
        gameScreen.setGameScreen(mapTitle);
    }

    public void setSelectPlayerScreen() {
        if (selectPlayerScreen != null)
            setScreen(selectPlayerScreen);
    }

    public void setGameOverScreen(){
        setScreen(gameOverScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        gameScreen.dispose();
        AssetHandler.instance.dispose();
        MapLoader.instance.dispose();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isMenuScreenOn() {
        return isMenuScreenOn;
    }

    public void setMenuScreenOn(boolean menuScreenOn) {
        isMenuScreenOn = menuScreenOn;
    }

    public void setSelectLevelScreen() {
        if(selectLevelScreen != null)
            selectLevelScreen.dispose();
        selectLevelScreen = new SelectLevelScreen(this);
        setScreen(selectLevelScreen);
    }
}
