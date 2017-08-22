package radoslaw.slowinski.ares.screens.gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.BlockHandler;
import radoslaw.slowinski.ares.handlers.ScoreHandler;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLoader;
import radoslaw.slowinski.ares.utils.MapTitles;

/**
 * Created by ares on 16/08/17.
 */
public class HUD extends OrthographicCamera {

    public static HUD instance = new HUD();
    private HallucinatoryRushGame myGame;
    private ShapeRenderer shapeRenderer;

    private HUD() {
        super();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        setToOrtho(false,
                Constant.GAME_WIDTH,
                Constant.GAME_HEIGHT);
        position.set(0, 0, 0);
    }

    public void setMyGame(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
    }

    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(this.combined);

        batch.begin();
        renderScore(batch);
        renderCurrentBlock(batch);
        if (MapLoader.instance.getMapTitle().equals(MapTitles.HELP.getTitle()))
            renderHelpHUD(batch);
        batch.end();
        drawBlockColor();
    }

    public void renderGeneralScore(SpriteBatch batch) {
        batch.setProjectionMatrix(this.combined);
        batch.begin();
        batch.draw(AssetHandler.instance.coins.coins[0],
                -15, Constant.GAME_HEIGHT - 80, 50, 50, 35, 35, 0.35f, -0.35f, 0);

        AssetHandler.instance.fonts.defaultNormal.draw(batch,
                "" + ScoreHandler.instance.getCoins(), 35, Constant.GAME_HEIGHT - 13);
        batch.end();
    }

    private void renderCurrentBlock(SpriteBatch batch) {
        batch.draw(BlockHandler.instance.getCurrentBlockTexture(),
                70, Constant.GAME_HEIGHT - 35,
                0, 0, 50, 30, 1, 1, 0);
    }

    public void drawBlockColor(){
        shapeRenderer.setProjectionMatrix(this.combined);
        shapeRenderer.begin();
        shapeRenderer.setColor(BlockHandler.instance.getCurrentBlockType().getColor());
        shapeRenderer.rect(0.25f,0.25f,
                Constant.GAME_WIDTH-0.3f,Constant.GAME_HEIGHT-0.7f);
        shapeRenderer.end();
    }

    public void renderHelpHUD(SpriteBatch batch) {
        batch.draw(AssetHandler.instance.items.tapTick,
                Constant.GAME_WIDTH / 6, Constant.GAME_HEIGHT / 1.6f);
        AssetHandler.instance.fonts.defaultMedium.draw(
                batch, "tap on the left side\nto change block", Constant.GAME_WIDTH / 10f, Constant.GAME_HEIGHT / 1.65f);

        batch.draw(AssetHandler.instance.items.tapTick,
                Constant.GAME_WIDTH - Constant.GAME_WIDTH / 4, Constant.GAME_HEIGHT / 1.6f);
        AssetHandler.instance.fonts.defaultMedium.draw(
                batch, "tap on the right side\ndato jump", Constant.GAME_WIDTH - Constant.GAME_WIDTH / 3.5f, Constant.GAME_HEIGHT / 1.65f);
    }

    public void renderScore(SpriteBatch batch) {
        batch.draw(AssetHandler.instance.coins.coins[0],
                -15, Constant.GAME_HEIGHT - 80, 50, 50, 35, 35, 0.35f, -0.35f, 0);
        AssetHandler.instance.fonts.defaultNormal.draw(batch,
                "" + ScoreHandler.instance.getCurrentLevelCoins(), 35, Constant.GAME_HEIGHT - 13);

    }


}
