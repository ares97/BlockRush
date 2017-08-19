package radoslaw.slowinski.ares.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 19/08/17.
 */
public class MenuScreen extends AbstractGameScreen {

    private HallucinatoryRushGame myGame;
    private Stage stage;
    private Skin libgdxSkin;
    private Skin menuUI;
    private Button playButton;
    private Button menuButton;

    public MenuScreen(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
    }

    @Override
    protected void init() {
        libgdxSkin = AssetHandler.instance.skin.libgdxSkin;
        menuUI = AssetHandler.instance.skin.menuUI;
    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    private void rebuildStage() {
        // Table layerBackground = buildBackgroundLayer();
        //Table layerObjects = buildObjectsLayer();
        //Table layerLogos = buildLogosLayer();
        Table layerControls = buildControlsLayer();
        //Table layerOptionsWindow = buildOptionsWindowLayer();

        stage.clear();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        stack.add(layerControls);


    }

    private Table buildControlsLayer() {
        Table layer = new Table();
        layer.center();

        layer.add(getPlayButton());
        layer.add(getMenuButton());


        return layer;
    }

    private Button getPlayButton() {
        playButton = new Button(menuUI.getDrawable("blue_button01"),
                menuUI.getDrawable("blue_button03"));
        playButton.add(getLabel("PLAY",Color.GOLD));

        setButtonListener(playButton, getOnClickPlayCallback());

        return playButton;
    }

    private Button getMenuButton() {
        menuButton = new Button(menuUI.getDrawable("blue_button01"),
                menuUI.getDrawable("blue_button03"));
        menuButton.add(getLabel("TODO",Color.GOLDENROD));

        setButtonListener(menuButton, getOnClickButtonCallback());

        return menuButton;
    }

    private IOnclickCallback getOnClickButtonCallback() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {

            }
        };
    }

    private Label getLabel(String string, Color color){
        Label.LabelStyle style = new Label.LabelStyle(
                AssetHandler.instance.fonts.defaultBig,color);
        Label label = new Label(string,style);
        label.setStyle(style);

        return label;
    }

    private IOnclickCallback getOnClickPlayCallback() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                new GameScreen(myGame);
            }
        };
    }

    private void setButtonListener(Button button, final IOnclickCallback callback) {
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                callback.onClick();
            }
        });
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        rebuildStage();
    }

    @Override
    public void hide() {
        stage.dispose();
    }
}
