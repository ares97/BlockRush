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
import radoslaw.slowinski.ares.controls.InfoDialog;
import radoslaw.slowinski.ares.controls.LeaderboardButton;
import radoslaw.slowinski.ares.controls.MenuButton;
import radoslaw.slowinski.ares.controls.PlayButton;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.ScoreHandler;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.GamePreferences;

/**
 * Created by ares on 23/08/17.
 */
public class FreeRunEndScreen extends AbstractGameScreen {
    private RushGame myGame;
    private Stage stage;
    private MenuButton menuButton;
    private InfoDialog infoDialog;
    private LeaderboardButton leaderboardButton;
    private PlayButton playAgainButton;


    public FreeRunEndScreen(RushGame myGame) {
        super();
        this.myGame = myGame;
        init();
    }

    private void init() {
        menuButton = new MenuButton(myGame);
        infoDialog = new InfoDialog(myGame);
        leaderboardButton = new LeaderboardButton(myGame);
        playAgainButton = new PlayButton(myGame,"Play again");
    }

    private void rebuildStage() {
        Stack stack = new Stack();
        stack.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        stack.add(getBackgroundImage());
        stack.add(getScores());
        stack.add(getButtons());
        //stack.add(getAdOption());

        stage.clear();
        stage.addActor(stack);
    }

    private Table getScores() {
        Table layer = new Table();
        layer.center().top();
        layer.add(getLabel("LONGEST DISTANCE: "+ ScoreHandler.instance.getLongestRun() +" m",
                Color.ROYAL,AssetHandler.instance.fonts.defaultNormal));
        layer.row();
        layer.add(getEmptyColumn());
        layer.row();
        layer.add(getLabel("distance : "+ ScoreHandler.instance.getLastRunDistance() + " m",
                Color.GOLD,AssetHandler.instance.fonts.defaultBig));
        layer.row();
        layer.add(getCoinScore());

        return layer;
    }

    private Table getCoinScore() {
        Table layer = new Table();
        Image coin = new Image(AssetHandler.instance.coins.coins[0]);
        layer.add(coin);
        layer.add(getLabel(" "+ScoreHandler.instance.getLastRunCoins(),
                Color.GOLD,AssetHandler.instance.fonts.defaultBig));
        layer.center();

        return layer;
    }

    private Table getAdOption() {
        Table layer = new Table();
        layer.center();
        layer.add(infoDialog.getDialog());
        layer.setSize(50, 50);
        return layer;
    }

    private Table getButtons() {
        Table layer = new Table();
        layer.center();
        layer.add(menuButton.getButton());
        layer.add(getEmptyColumn());
        layer.add(leaderboardButton.getButton());
        layer.add(getEmptyColumn());
        layer.add(playAgainButton.getButton());

        return layer;
    }

    private Image getEmptyColumn() {
        Image img =new Image(AssetHandler.instance.blocks.blue);
        img.setVisible(false);
        return img;
    }


    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();

    }


    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        rebuildStage();
        if(MathUtils.randomBoolean(0.3f)) myGame.showRewardedVideo();
    }

    @Override
    public void hide() {
        GamePreferences.instance.save();
        stage.dispose();
    }

    private Label getLabel(String string, Color color,BitmapFont font) {
        Label.LabelStyle style = new Label.LabelStyle(
                font, color);
        Label label = new Label(string, style);
        label.setStyle(style);

        return label;
    }
}
