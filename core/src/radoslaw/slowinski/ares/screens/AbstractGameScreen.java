package radoslaw.slowinski.ares.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 12.08.17.
 */
public abstract class AbstractGameScreen implements Screen {

    protected HallucinatoryRushGame myGame;
    protected Stage stage;
    protected OrthographicCamera mainCam;
    protected OrthographicCamera b2dCam;
    protected SpriteBatch batch;
    protected boolean paused;

    public AbstractGameScreen(HallucinatoryRushGame myGame) {
        this.myGame = myGame;

        batch = new SpriteBatch();
        createCameras();

        stage = new Stage(new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        init();
    }

    private void createCameras() {
        mainCam = new OrthographicCamera();
        mainCam.setToOrtho(false,
                Constant.GAME_WIDTH,
                Constant.GAME_HEIGHT);

        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false,
                Constant.GAME_WIDTH / Constant.PPM,
                Constant.GAME_HEIGHT / Constant.PPM);
    }

    protected abstract void init();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        b2dCam.update();
        mainCam.update();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        myGame.dispose();
    }
}
