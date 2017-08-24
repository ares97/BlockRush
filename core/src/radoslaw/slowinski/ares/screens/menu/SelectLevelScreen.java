package radoslaw.slowinski.ares.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.controls.LevelDialog;
import radoslaw.slowinski.ares.controls.PlayButton;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 24/08/17.
 */
public class SelectLevelScreen extends AbstractGameScreen {
    private RushGame myGame;
    private Stage stage;

    public SelectLevelScreen(RushGame myGame){
        super();
        this.myGame = myGame;

    }

    @Override
    public void render(float delta) {
        clearScreen();

        stage.act(delta);
        stage.draw();
    }

    private void rebuildStage() {
        Table layer = new Table();
        layer.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        layer.top().center();

        layer.add(new LevelDialog(myGame,MapLevels.FREE_RUN).getStack());
        layer.add(new LevelDialog(myGame,MapLevels.LEVEL1).getStack());
        layer.add(new LevelDialog(myGame,MapLevels.LEVEL2).getStack());

        stage.addActor(layer);
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT));
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        rebuildStage();
    }

    @Override
    public void hide() {
        GamePreferences.instance.save();
        stage.dispose();
    }
}
