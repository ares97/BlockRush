package radoslaw.slowinski.ares;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import radoslaw.slowinski.ares.screens.GameScreen;
import radoslaw.slowinski.ares.utils.Assets;

public class HallucinatoryRushGame extends Game {


    @Override
    public void create() {
        Assets.instance.load(new AssetManager());
        new GameScreen(this);
    }
}
