package radoslaw.slowinski.ares.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import radoslaw.slowinski.ares.handlers.BlockHandler;
import radoslaw.slowinski.ares.handlers.ScoreHandler;
import radoslaw.slowinski.ares.utils.Assets;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 16/08/17.
 */
public class GUI extends OrthographicCamera {

    public static GUI instance = new GUI();
    private int shownCoins;
    private TextureRegion currentBlock;

    private GUI(){
        super();
        setToOrtho(false,
                Constant.GAME_WIDTH,
                Constant.GAME_HEIGHT);
        position.set(0,0,0);
    }



    public void render(SpriteBatch batch){
        batch.setProjectionMatrix(this.combined);

        batch.begin();
        renderScore(batch);
        renderCurrentBlock(batch);
        batch.end();
    }

    private void renderCurrentBlock(SpriteBatch batch) {
        batch.draw(BlockHandler.instance.getCurrentBlock(),
                70,Constant.GAME_HEIGHT -35,
                0,0,30,30,1,1,0);
    }

    public void update(float deltaTime){
        if(shownCoins < ScoreHandler.instance.getCurrentLevelCoins()) {
            shownCoins = (int) Math.min(ScoreHandler.instance.getCurrentLevelCoins(),
                    shownCoins + Constant.REWARD_FOR_COIN * deltaTime * 3);
        }
    }

    private void renderScore(SpriteBatch batch) {
        float offsetX = 50;
        float offsetY = 50;

        if(shownCoins < ScoreHandler.instance.getCurrentLevelCoins()) {
            float shakeDist = 1.5f;
            long shakeAlpha = System.currentTimeMillis() % 360;
            offsetX += MathUtils.sinDeg(shakeAlpha * 2.2f) * shakeDist;
            offsetY += MathUtils.sinDeg(shakeAlpha * 2.9f) * shakeDist;
        }
        batch.draw(Assets.instance.coins.coins[0],
                -15,Constant.GAME_HEIGHT-80,offsetX,offsetY,35,35,0.35f,-0.35f,0);
        Assets.instance.fonts.defaultNormal.draw(batch,
                ""+ shownCoins,35,Constant.GAME_HEIGHT-13);
    }


}
