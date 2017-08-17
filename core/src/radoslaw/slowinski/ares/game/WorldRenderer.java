package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import radoslaw.slowinski.ares.entites.Coin;
import radoslaw.slowinski.ares.handlers.InputHandler;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.screens.GUI;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLoader;

/**
 * Created by ares on 12.08.17.
 */
public class WorldRenderer extends AbstractGameScreen {

    private WorldController worldController;
    private Box2DDebugRenderer b2dDebugRender;


    public WorldRenderer(WorldController worldController) {
        super();
        this.worldController = worldController;
        InputHandler inputHandler = new InputHandler(worldController.getPlayer());
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    protected void init() {
        b2dDebugRender = new Box2DDebugRenderer();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);

        update(deltaTime);
        MapLoader.instance.renderMap(mainCam);
        renderPlayer();
        renderItems();
        renderGUI();

        if (Constant.DEBUG_MODE) {
            b2dDebugRender.render(worldController.b2dWorld, b2dCam.combined);
        }
    }

    private void renderGUI() {
        GUI.instance.render(batch);
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

        batch.begin();
        batch.draw(
                worldController.getPlayer().getTexture(),
                worldController.getPlayer().getTextureX(),
                worldController.getPlayer().getTextureY(),
                worldController.getPlayer().getTextureWidth(),
                worldController.getPlayer().getTextureHeight());
        batch.end();
    }
}
