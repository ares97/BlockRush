package radoslaw.slowinski.ares.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import radoslaw.slowinski.ares.handlers.AssetHandler;
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

    protected Image getBackgroundImage() {
        Image img = new Image(AssetHandler.instance.backgrounds.bgCastle);
        img.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        img.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.setOnscreenKeyboardVisible(false);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        return img;
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
        mainCam.update();

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
