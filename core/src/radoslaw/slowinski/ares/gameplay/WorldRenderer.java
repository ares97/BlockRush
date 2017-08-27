package radoslaw.slowinski.ares.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import radoslaw.slowinski.ares.entites.Coin;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.screens.gameplay.HUD;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLoader;

/**
 * Created by ares on 12.08.17.
 */
public class WorldRenderer extends AbstractGameScreen {

    private WorldController worldController;
    private TextureRegion background;
    private int currentBackgroundArea;

    public WorldRenderer(WorldController worldController) {
        super();
        this.worldController = worldController;
        randomizeBackground();
    }

    private void randomizeBackground() {
        background = AssetHandler.instance.backgrounds.background[MathUtils.random(0,3)];
    }


    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        drawBackground();
        update(deltaTime);
        MapLoader.instance.renderMap(mainCam);
        renderGUI();
        renderPlayer();
        renderItems();
    }

    private void drawBackground() {
        int bgOffset = -Constant.GAME_WIDTH/2;
        if (mainCam.position.x > Constant.GAME_WIDTH * (currentBackgroundArea + 1) - bgOffset) {
            currentBackgroundArea++;
        }
        batch.begin();
        batch.draw(background,bgOffset + Constant.GAME_WIDTH*currentBackgroundArea,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        batch.draw(background,bgOffset+Constant.GAME_WIDTH*(currentBackgroundArea+1),0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        batch.draw(background,bgOffset+Constant.GAME_WIDTH*(currentBackgroundArea+2),0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        batch.end();
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

    @Override
    public void hide() {
        super.hide();
        worldController.stopGameplay();
    }

    @Override
    public void show() {
        super.show();
        worldController.awakeInputListener();
    }
}
