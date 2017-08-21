package radoslaw.slowinski.ares.screens.gameplay;

import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.game.WorldController;
import radoslaw.slowinski.ares.game.WorldRenderer;

/**
 * Created by ares on 12.08.17.
 */
public class GameScreen implements Disposable {
    private HallucinatoryRushGame myGame;
    private WorldController worldController;
    private WorldRenderer worldRenderer;

    public GameScreen(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
    }


    public void setGameScreen(String mapTitle) {
        dispose();

        worldController = new WorldController(myGame, mapTitle);
        worldRenderer = new WorldRenderer(worldController);
        myGame.setScreen(worldRenderer);
    }


    @Override
    public void dispose() {
        if (worldController != null)
            worldController.dispose();
        if (worldRenderer != null)
            worldRenderer.dispose();
    }
}
