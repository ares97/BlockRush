package radoslaw.slowinski.ares;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.screens.GameScreen;

public class HallucinatoryRushGame extends Game {


    @Override
    public void create() {
        AssetHandler.instance.load(new AssetManager());
        new GameScreen(this);
    }
}
