package radoslaw.slowinski.ares.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.controls.BackToMenuButton;
import radoslaw.slowinski.ares.controls.LevelDialog;
import radoslaw.slowinski.ares.controls.VScrollPane;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.utils.BadgeTypes;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 24/08/17.
 */
public class SelectLevelScreen extends AbstractGameScreen {
    private RushGame myGame;
    private Stage stage;
    private BackToMenuButton backButton;
    private LevelDialog[] levels;

    public SelectLevelScreen(RushGame myGame) {
        super();
        this.myGame = myGame;
        init();
    }

    private void init() {
        backButton = new BackToMenuButton(myGame);
    }


    @Override
    public void render(float delta) {
        clearScreen();

        stage.act(delta);
        stage.draw();
    }

    private void rebuildStage() {

        Stack stack = new Stack(getLevelDialogs(), getButtonContainer());
        stack.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        stage.addActor(stack);
    }

    private Container<Button> getButtonContainer() {
        Container<Button> backContainer = new Container<Button>(backButton.getButton());
        backContainer.top().right();
        return backContainer;
    }

    private VScrollPane getLevelDialogs() {
        return new VScrollPane(getLevelTable());
    }

    private Table getLevelTable() {
        setLevels();
        Table layer = new Table();
        layer.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        layer.add(getEmptyColumn());
        layer.add(getEmptyColumn());
        layer.add(levels[1].getStack()).row();
        layer.add(getEmptyColumn()).row();
        for (int i = 2; i < levels.length; i++) {
            if((i-2)%5 == 0)
                layer.row();
            layer.add(levels[i].getStack());
        }

        return layer;
    }

    private Image getEmptyColumn() {
        Image img = new Image(AssetHandler.instance.items.bronzeStar);
        img.setVisible(false);
        return img;
    }

    private void setLevels() {
        levels = new LevelDialog[MapLevels.values().length];
        levels[1] = new LevelDialog(myGame, MapLevels.values()[1], true);
        levels[2] = new LevelDialog(myGame,MapLevels.values()[2],true);
        for (int i = 3; i < levels.length; i++) {
            if (levels[i - 1].getBadgeType().equals(BadgeTypes.NONE)) {
                levels[i] = new LevelDialog(myGame, MapLevels.values()[i], false);
            } else {
                levels[i] = new LevelDialog(myGame, MapLevels.values()[i], true);
            }
        }
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
