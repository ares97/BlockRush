package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.handlers.AssetHandler;

/**
 * Created by ares on 23/08/17.
 */
public class InfoDialog {
    private RushGame myGame;
    private Dialog dialog;
    private ExitInfoDialogButton exitButton;
    private WatchAdButton watchButton;

    public InfoDialog(RushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {
        dialog = new Dialog("", getWindowStyle());
        dialog.setMovable(true);
        dialog.setSize(50,50);

        watchButton = new WatchAdButton(myGame);
        exitButton = new ExitInfoDialogButton(this);

        dialog.add(getLabel("Watch ad and double your coins", Color.GOLD));
        dialog.add(watchButton.getButton(),exitButton.getButton());
    }

    private Window.WindowStyle getWindowStyle() {
        return new Window.WindowStyle(
                AssetHandler.instance.fonts.defaultNormal,
                Color.GOLD,
                AssetHandler.instance.skin.menuUI.getDrawable("window_03"));
    }

    private Label getLabel(String string, Color color) {
        Label.LabelStyle style = new Label.LabelStyle(
                AssetHandler.instance.fonts.defaultNormal, color);
        Label label = new Label(string, style);
        label.setStyle(style);
        return label;
    }

    public Dialog getDialog(){
        return dialog;
    }
}
