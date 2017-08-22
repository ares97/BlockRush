package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.Gdx;
import radoslaw.slowinski.ares.entites.Coin;
import radoslaw.slowinski.ares.listeners.InputListener;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.screens.gameplay.HUD;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLoader;

/**
 * Created by ares on 12.08.17.
 */
public class WorldRenderer extends AbstractGameScreen {

    private WorldController worldController;

    public WorldRenderer(WorldController worldController) {
        super();
        this.worldController = worldController;
    }


    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        update(deltaTime);
        MapLoader.instance.renderMap(mainCam);
        renderGUI();
        renderPlayer();
        renderItems();
    }

    private void renderGUI() {
        HUD.instance.render(batch);
    }

    private void renderItems() {
        if (worldController.getCoinsOnMap() == null) return;
        for (Coin coin : worldController.getCoinsOnMap()) {
            coin.render(batch);
        }
    }

    private void update(float deltaTime) {
        worldController.update(deltaTime);
        setCameraOnPlayer();
    }

    private void setCameraOnPlayer() {
        mainCam.position.set(Constant.GAME_WIDTH / 4 + worldController.getPlayer().getPosition().x * Constant.PPM,
                Constant.GAME_HEIGHT / 2, 0);
    }

    private void renderPlayer() {
        batch.setProjectionMatrix(mainCam.combined);
        worldController.getPlayer().render(batch);
    }


}
