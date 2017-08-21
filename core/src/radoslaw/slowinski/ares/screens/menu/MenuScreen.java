package radoslaw.slowinski.ares.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.controls.*;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.GamePreferences;

/**
 * Created by ares on 19/08/17.
 */
public class MenuScreen extends AbstractGameScreen {

    private HallucinatoryRushGame myGame;
    private Stage stage;
    private PlayButton playButton;
    private GoSelectingPlayerButton goSelectingPlayerButton;
    private SoundButton soundButton;
    private MusicButton musicButton;
    private ExitButton exitButton;
    private RateButton rateButton;

    public MenuScreen(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        initButtons();
    }

    private void initButtons() {
        playButton = new PlayButton(myGame);
        goSelectingPlayerButton = new GoSelectingPlayerButton(myGame);
        soundButton = new SoundButton();
        musicButton = new MusicButton();
        exitButton = new ExitButton();
        rateButton = new RateButton();
    }


    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    private void rebuildStage() {
        Stack stack = new Stack();
        stack.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        stack.add(buildControlsLayer());
        stack.add(getOptionsControls());

        stage.clear();
        stage.addActor(stack);
    }


    private Table buildControlsLayer() {
        Table layer = new Table();
        layer.add(playButton.getButton(), goSelectingPlayerButton.getButton());
        return layer;
    }

    private Table getOptionsControls() {
        Table layer = new Table().top().right();
        layer.add(exitButton.getButton()).row();
        layer.add(getEmptyColumn()).row();
        layer.add(soundButton.getButton()).row();
        layer.add(musicButton.getButton()).row();
        layer.add(rateButton.getButton()).row();
        return layer;
    }

    private Image getEmptyColumn() {
        Image emptyColumn = new Image(AssetHandler.instance.blocks.blue);
        emptyColumn.setVisible(false);
        return emptyColumn;
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        rebuildStage();
    }

    @Override
    public void hide() {
        GamePreferences.instance.save();
        stage.dispose();
    }
}
