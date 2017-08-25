package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.UserDataHandler;
import radoslaw.slowinski.ares.utils.BadgeTypes;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 24/08/17.
 */
public class LevelDialog {
    private RushGame myGame;
    private Stack stack;
    private MapLevels mapLevel;

    public LevelDialog(RushGame myGame,MapLevels mapLevel) {
        this.myGame = myGame;
        this.mapLevel = mapLevel;
        init();
    }

    public Stack getStack() {
        return stack;
    }

    private void init() {
        stack = new Stack();
        stack.setSize(100,50);
        stack.add(getBackground());
        stack.add(getForeground());
        stack.addListener(getListener());
    }

    private Table getForeground() {
        Table layer = new Table();
        layer.center().top();
        layer.setSize(100,30);
        layer.add(getBagde()).row();
        layer.add(getLabel(mapLevel.getMapLevel(),Color.FIREBRICK));
        return layer;
    }

    private Image getBagde() {
        Image img;
        if(UserDataHandler.instance.getBadgeType(mapLevel).equals(BadgeTypes.GOLD)){
            img = new Image(AssetHandler.instance.items.goldStar);
        }
        else if(UserDataHandler.instance.getBadgeType(mapLevel).equals(BadgeTypes.SILVER)){
            img = new Image(AssetHandler.instance.items.silverStar);
        }
        else if(UserDataHandler.instance.getBadgeType(mapLevel).equals(BadgeTypes.BRONZE)){
            img = new Image(AssetHandler.instance.items.bronzeStar);
        }
        else {
            img = new Image(AssetHandler.instance.items.bronzeStar);
            img.setVisible(false);
        }

        img.setScale(0.5f);
        img.setOrigin(img.getWidth()/2,img.getHeight()/2);
        return img;
    }

    private Image getBackground() {
        return new Image(AssetHandler.instance.skin.menuUI.getDrawable("window_02"));

    }

    private ClickListener getListener() {
        return new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                myGame.setGameScreen(mapLevel);
                return super.touchDown(event, x, y, pointer, button);
            }
        };
    }

    private Label getLabel(String string, Color color) {
        Label.LabelStyle style = new Label.LabelStyle(
                AssetHandler.instance.fonts.defaultNormal, color);
        Label label = new Label(string, style);
        label.setStyle(style);
        return label;
    }

    private Image getEmptyColumn() {
        Image emptyColumn = new Image(AssetHandler.instance.blocks.blue);
        emptyColumn.setVisible(false);
        return emptyColumn;
    }
}
