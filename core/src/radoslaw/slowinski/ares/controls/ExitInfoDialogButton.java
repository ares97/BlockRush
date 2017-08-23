package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;
import radoslaw.slowinski.ares.utils.MapTitles;

/**
 * Created by ares on 23/08/17.
 */
public class ExitInfoDialogButton {
    private MyButton button;
    private InfoDialog infoDialog;

    public ExitInfoDialogButton(InfoDialog infoDialog) {
        this.infoDialog = infoDialog;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setString("No, thanks", Color.GOLD);
        button.setListener(getListener());
        button.applyDefaultSkin();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                infoDialog.getDialog().setVisible(false);
            }
        };
    }

    public MyButton getButton() {
        return button;
    }
}
