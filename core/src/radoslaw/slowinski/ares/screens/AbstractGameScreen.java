package radoslaw.slowinski.ares.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 12.08.17.
 */
public abstract class AbstractGameScreen implements Screen {

    protected OrthographicCamera mainCam;
    protected SpriteBatch batch;

    public AbstractGameScreen() {
        batch = new SpriteBatch();
        createCameras();
    }

    private void createCameras() {
        mainCam = new OrthographicCamera();
        mainCam.setToOrtho(false,
                Constant.GAME_WIDTH,
                Constant.GAME_HEIGHT);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        mainCam.update();
    }

    protected void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
