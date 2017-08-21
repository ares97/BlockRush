package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class MyButton extends Button {
    Skin menuUI;
    private ButtonStyle style;

    public MyButton() {
        super();
        init();
    }

    void setString(String string, Color color) {
        add(getLabel(string, color));
    }

    void setListener(final IOnclickCallback callback) {
        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                callback.onClick();
            }
        });
    }

    void applyDefaultSkin() {
        style.down = menuUI.getDrawable("button_01");
        style.up = menuUI.getDrawable("button_03");
    }

    void setDrawableUp(Drawable up){
        style.up = up;
    }

    void setDrawableDown(Drawable down){
        style.down = down;
    }

    private void init() {
        menuUI = AssetHandler.instance.skin.menuUI;
        style = new ButtonStyle();
        setStyle(style);
    }

    private Label getLabel(String string, Color color) {
        Label.LabelStyle style = new Label.LabelStyle(
                AssetHandler.instance.fonts.defaultNormal, color);
        Label label = new Label(string, style);
        label.setStyle(style);

        return label;
    }

}
