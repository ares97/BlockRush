package radoslaw.slowinski.ares.screens.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.controls.MenuButton;
import radoslaw.slowinski.ares.controls.PlayButton;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.ScoreHandler;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 26/08/17.
 */
public class LevelMapEndScreen extends AbstractGameScreen {
    private RushGame myGame;
    private Stage stage;
    private MenuButton menuButton;
    private PlayButton playAgainButton;
    private MapLevels lastMap;

    public LevelMapEndScreen(RushGame myGame) {
        super();
        this.myGame = myGame;
        init();
    }

    private void init() {
        menuButton = new MenuButton(myGame);
        playAgainButton = new PlayButton(myGame, "Play again");
    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    private void rebuildStage() {
        Stack stack = new Stack();
        stack.add(getScores());
        stack.add(getButtons());
        stack.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        stage.clear();
        stage.addActor(stack);
    }

    private Table getButtons() {
        Table layer = new Table();
        layer.add(menuButton.getButton()).center();
        layer.add(getEmptyColumn());
        layer.add(playAgainButton.getButton());

        return layer;
    }

    private Table getScores() {
        Table layer = new Table();
        layer.center().top();
        layer.add(getCoinScore());

        return layer;
    }

    private Table getCoinScore() {
        Table layer = new Table();
        Image coin = new Image(AssetHandler.instance.coins.coins[0]);
        layer.add(getEmptyColumn()).row();
        layer.add(getEmptyColumn()).row();
        layer.add(coin);
        layer.add(getLabel(" " + ScoreHandler.instance.getLastRunCoins(),
                Color.GOLD, AssetHandler.instance.fonts.defaultBig));
        layer.center().row();

        return layer;
    }

    private Label getLabel(String string, Color color, BitmapFont font) {
        Label.LabelStyle style = new Label.LabelStyle(
                font, color);
        Label label = new Label(string, style);
        label.setStyle(style);

        return label;
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        rebuildStage();
        if(MathUtils.randomBoolean()) myGame.showRewardedVideo();
    }

    @Override
    public void hide() {
        GamePreferences.instance.save();
        stage.dispose();
    }

    private Image getEmptyColumn() {
        Image img = new Image(AssetHandler.instance.blocks.blue);
        img.setVisible(false);
        return img;
    }

    public void setMap(MapLevels map) {
        this.lastMap = map;
    }
}
