package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import radoslaw.slowinski.ares.entites.Coin;
import radoslaw.slowinski.ares.listeners.InputListener;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.screens.HUD;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLoader;

/**
 * Created by ares on 12.08.17.
 */
public class WorldRenderer extends AbstractGameScreen {

    private WorldController worldController;
    private Box2DDebugRenderer b2dDebugRender;
    private FPSLogger fpsLogger;


    public WorldRenderer(WorldController worldController) {
        super();
        this.worldController = worldController;
        InputListener inputListener = new InputListener(worldController.getPlayer());
        Gdx.input.setInputProcessor(inputListener);
    }

    @Override
    protected void init() {
        b2dDebugRender = new Box2DDebugRenderer();
        fpsLogger = new FPSLogger();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        update(deltaTime);
        MapLoader.instance.renderMap(mainCam);
        renderGUI();
        renderPlayer();
        renderItems();
        fpsLogger.log();
        if (Constant.DEBUG_BOX2D) {
            b2dDebugRender.render(worldController.b2dWorld, b2dCam.combined);
        }
    }

    private void renderGUI() {
        HUD.instance.render(batch);
    }

    private void renderItems() {

        for (Coin coin : worldController.getCoinsOnMap()) {
            coin.render(batch);
        }
    }

    private void update(float deltaTime) {
        worldController.update(deltaTime);
        setCameraOnPlayer();
    }

    private void setCameraOnPlayer() {
        mainCam.position.set(Constant.GAME_WIDTH / 4 + worldController.getPlayer().getBody().getPosition().x * Constant.PPM,
                Constant.GAME_HEIGHT / 2, 0);
    }

    private void renderPlayer() {
        batch.setProjectionMatrix(mainCam.combined);
        worldController.getPlayer().render(batch);
    }

}
