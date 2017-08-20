package radoslaw.slowinski.ares.screens;

import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.game.WorldController;
import radoslaw.slowinski.ares.game.WorldRenderer;

/**
 * Created by ares on 12.08.17.
 */
public class GameScreen implements Disposable{
    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private HallucinatoryRushGame myGame;

    public GameScreen(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {
        worldController = new WorldController(myGame);
        worldRenderer = new WorldRenderer(worldController);
    }

    public void setGameScreen(){
        myGame.setScreen(worldRenderer);
    }


    @Override
    public void dispose() {
        worldController.dispose();
        worldRenderer.dispose();
    }
}
