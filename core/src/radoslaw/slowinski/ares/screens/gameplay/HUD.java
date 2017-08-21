package radoslaw.slowinski.ares.screens.gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.controls.BackToMenuButton;
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
    private int shownCoins;
    private HallucinatoryRushGame myGame;

    private HUD() {
        super();
        setToOrtho(false,
                Constant.GAME_WIDTH,
                Constant.GAME_HEIGHT);
        position.set(0, 0, 0);
    }

    public void setMyGame(HallucinatoryRushGame myGame){
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
    }

    private void renderCurrentBlock(SpriteBatch batch) {
        batch.draw(BlockHandler.instance.getCurrentBlock(),
                70, Constant.GAME_HEIGHT - 35,
                0, 0, 50, 30, 1, 1, 0);
    }

    public void update(float deltaTime) {
        if (shownCoins < ScoreHandler.instance.getCurrentLevelCoins()) {
            shownCoins = (int) Math.min(ScoreHandler.instance.getCurrentLevelCoins(),
                    shownCoins + Constant.REWARD_FOR_COIN * deltaTime * 5);
        }
    }

    public void renderHelpHUD(SpriteBatch batch) {
        batch.draw(AssetHandler.instance.items.tapTick,
                Constant.GAME_WIDTH / 6, Constant.GAME_HEIGHT / 1.6f);
        AssetHandler.instance.fonts.defaultMedium.draw(
                batch,"tap to change block",Constant.GAME_WIDTH/10f,Constant.GAME_HEIGHT/1.65f);

        batch.draw(AssetHandler.instance.items.tapTick,
                Constant.GAME_WIDTH - Constant.GAME_WIDTH / 5, Constant.GAME_HEIGHT / 1.6f);
        AssetHandler.instance.fonts.defaultMedium.draw(
                batch,"tap to jump",Constant.GAME_WIDTH - Constant.GAME_WIDTH/4.5f,Constant.GAME_HEIGHT/1.65f);

    }

    private void renderScore(SpriteBatch batch) {
        float offsetX = 50;
        float offsetY = 50;

        if (shownCoins < ScoreHandler.instance.getCurrentLevelCoins()) {
            float shakeDist = 1.5f;
            long shakeAlpha = System.currentTimeMillis() % 360;
            offsetX += MathUtils.sinDeg(shakeAlpha * 2.2f) * shakeDist;
            offsetY += MathUtils.sinDeg(shakeAlpha * 2.9f) * shakeDist;
        }
        batch.draw(AssetHandler.instance.coins.coins[0],
                -15, Constant.GAME_HEIGHT - 80, offsetX, offsetY, 35, 35, 0.35f, -0.35f, 0);
        AssetHandler.instance.fonts.defaultNormal.draw(batch,
                "" + shownCoins, 35, Constant.GAME_HEIGHT - 13);
    }


}
