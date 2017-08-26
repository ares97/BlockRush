package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private boolean canBePlayed;
    private BadgeTypes badgeType;

    public LevelDialog(RushGame myGame, MapLevels mapLevel, boolean canBePlayed) {
        this.myGame = myGame;
        this.mapLevel = mapLevel;
        this.canBePlayed = canBePlayed;
        init();
    }

    public Stack getStack() {
        return stack;
    }

    private void init() {
        stack = new Stack();
        stack.setSize(100, 50);
        stack.add(getBackground());
        stack.add(getForeground());
        stack.addListener(getListener());
    }

    private Table getForeground() {
        Table layer = new Table();
        layer.center().top();
        layer.setSize(100, 30);
        layer.add(getBadge()).row();
        if (!canBePlayed) {
         Image key = new Image(AssetHandler.instance.items.key);
         key.setScale(0.7f);
         key.setOrigin(key.getWidth()/2,key.getHeight());
         key.setColor(255,255,255,0.7f);
            layer.add(key);
        }
        else
            layer.add(getLabel(mapLevel.getMapLevel(), Color.FIREBRICK));
        return layer;
    }

    private Image getBadge() {
        Image img;
        if (UserDataHandler.instance.getBadgeType(mapLevel).equals(BadgeTypes.GOLD)) {
            img = new Image(AssetHandler.instance.items.goldStar);
            badgeType = BadgeTypes.GOLD;
        } else if (UserDataHandler.instance.getBadgeType(mapLevel).equals(BadgeTypes.SILVER)) {
            img = new Image(AssetHandler.instance.items.silverStar);
            badgeType = BadgeTypes.SILVER;
        } else if (UserDataHandler.instance.getBadgeType(mapLevel).equals(BadgeTypes.BRONZE)) {
            img = new Image(AssetHandler.instance.items.bronzeStar);
            badgeType = BadgeTypes.BRONZE;
        } else {
            img = new Image(AssetHandler.instance.items.bronzeStar);
            img.setVisible(false);
            badgeType = BadgeTypes.NONE;
        }

        img.setScale(0.5f);
        img.setOrigin(img.getWidth() / 2, img.getHeight() / 2);
        return img;
    }

    private Image getBackground() {
        return new Image(AssetHandler.instance.skin.menuUI.getDrawable("window_02"));

    }

    private ClickListener getListener() {
        return new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (canBePlayed) {
                    myGame.setGameScreen(mapLevel);
                }
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

    public BadgeTypes getBadgeType() {
        return badgeType;
    }
}
