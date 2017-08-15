package radoslaw.slowinski.ares;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import radoslaw.slowinski.ares.screens.GameScreen;
import radoslaw.slowinski.ares.utils.Assets;

public class HallucinatoryRushGame extends Game {


    @Override
    public void create() {
        Assets.instance.load(new AssetManager());
        new GameScreen(this);
    }
}
