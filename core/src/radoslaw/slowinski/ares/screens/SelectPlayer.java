package radoslaw.slowinski.ares.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.UserDataHandler;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.SkinTypes;

/**
 * Created by ares on 19/08/17.
 */
public class SelectPlayer extends AbstractGameScreen {
    private Skin menuUI;
    private Stage stage;
    private ScrollPane scrollPane;
    private HallucinatoryRushGame myGame;

    public SelectPlayer(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
    }

    @Override
    protected void init() {
        menuUI = AssetHandler.instance.skin.menuUI;
    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    private void rebuildStage() {
        Table layerSkins = getLayerSkins();
        layerSkins.center();

        Table layerControlls = getLayerControlls();
        layerControlls.top().left();


        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        style.hScrollKnob = AssetHandler.instance.skin.menuUI.getDrawable("knob_05");
        scrollPane = new ScrollPane(layerSkins,style);
        scrollPane.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);

        Stack stack = new Stack(scrollPane,layerControlls);
        stack.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);

        stage.addActor(stack);
    }

    private Table getLayerControlls(){
        Table layer = new Table();
        Button getBackButton = new Button
                (AssetHandler.instance.skin.menuUI.getDrawable("icon_back"));
        getBackButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                myGame.setMenuScreen();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        layer.add(getBackButton);
        return layer;
    }

    private Table getLayerSkins() {
        Table layer = new Table();
        for(AssetHandler.AssetPlayerSkin skin : AssetHandler.instance.playerSkin.values()){
            Image player = new Image(skin.stand);
            layer.add(player).size(140);
        }
        layer.row();
        for(final SkinTypes skin : SkinTypes.values()){
            Button btn = new Button(
                    AssetHandler.instance.skin.menuUI.getDrawable("button_05"),
                    AssetHandler.instance.skin.menuUI.getDrawable("button_01"));
            btn.add(getLabel("SELECT",Color.GOLD));

            btn.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    UserDataHandler.instance.setPlayerSkin(skin);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });

            layer.add(btn).width(130);
        }
        return layer;
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

    private Label getLabel(String string, Color color){
        Label.LabelStyle style = new Label.LabelStyle(
                AssetHandler.instance.fonts.defaultNormal,color);
        Label label = new Label(string,style);
        label.setStyle(style);

        return label;
    }
}
