package radoslaw.slowinski.ares.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.controls.*;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.UserDataHandler;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.screens.gameplay.HUD;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.SkinTypes;

import java.util.Calendar;

/**
 * Created by ares on 19/08/17.
 */
public class SelectPlayerScreen extends AbstractGameScreen {
    private Stage stage;
    private RushGame myGame;
    private BackToMenuButton backButton;
    private int year;
    private int month;

    public SelectPlayerScreen(RushGame myGame) {
        this.myGame = myGame;
        backButton = new BackToMenuButton(myGame);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();
        HUD.instance.renderGeneralScore(batch);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
            myGame.setMenuScreen();
    }

    private void rebuildStage() {
        setDate();
        HScrollPane scrollPane = new HScrollPane(getLayerSkins());
        Stack stack = new Stack(getBackgroundImage(), scrollPane, getBackToMenuButton());
        stack.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        stage.addActor(stack);
    }

    private Container getSpecialCodeField() {
        TextField textField = new PromoCodeField(myGame).getTextField();
        Container<TextField> field = new Container<TextField>(textField);
        field.top().right();
        field.size(75, 20);
        return field;
    }

    private void setDate() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
    }

    private Table getBackToMenuButton() {
        Table layer = new Table();
        layer.add(getSpecialCodeField()).size(15, 30);
        layer.add(getEmptyColumn());
        layer.add(backButton.getButton());
        layer.top().right();
        return layer;
    }

    private Table getLayerSkins() {
        Table layer = new Table();

        addSkinsToLayer(layer);
        layer.row();
        addButtonsToLayer(layer);
        layer.center();
        return layer;
    }

    private void addButtonsToLayer(Table layer) {
        for (SkinTypes skin : SkinTypes.values()) {
            if (skin.equals(SkinTypes.LANTERN) && year <= 2017 && month < 9 && !UserDataHandler.instance.isBought(skin)) {
                continue;
            }
            if (UserDataHandler.instance.getBoughtPlayer().get(skin)) {
                ChoosePlayerButton selectPlayer = new ChoosePlayerButton(skin, myGame);
                layer.add(selectPlayer.getButton()).width(130);
            } else {
                BuyPlayerButton selectPlayer = new BuyPlayerButton(skin, myGame);
                layer.add(selectPlayer.getButton()).width(130);
            }
        }
    }

    private void addSkinsToLayer(Table layer) {
        for (SkinTypes skin : SkinTypes.values()) {
            if (skin.equals(SkinTypes.LANTERN) && year <= 2017 && month < 9 && !UserDataHandler.instance.isBought(skin)) {
                continue;
            }
            Image player = new Image(AssetHandler.instance.playerSkin.get(skin).stand);
            layer.add(player).size(140);
        }
    }


    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);


        rebuildStage();
    }

    @Override
    public void hide() {
        GamePreferences.instance.save();
        stage.dispose();
    }

    private Image getEmptyColumn() {
        Image img = new Image(AssetHandler.instance.items.bronzeStar);
        img.setVisible(false);
        return img;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
        super.resize(width, height);
    }
}
