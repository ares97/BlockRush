package radoslaw.slowinski.ares.screens.gameplay;

import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.gameplay.WorldController;
import radoslaw.slowinski.ares.gameplay.WorldRenderer;
import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.listeners.GameContactListener;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 12.08.17.
 */
public class GameScreen implements Disposable {
    private RushGame myGame;
    private WorldController worldController;
    private WorldRenderer worldRenderer;

    public GameScreen(RushGame myGame) {
        this.myGame = myGame;
    }


    public void setGameScreen(MapLevels mapTitle) {
        dispose();

        myGame.setPlaying(true);
        GameContactListener.instance.setNumFootContacts(0);

        worldController = new WorldController(myGame, mapTitle);
        worldRenderer = new WorldRenderer(worldController);

        AudioHandler.instance.playBackgroundMusic();
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
