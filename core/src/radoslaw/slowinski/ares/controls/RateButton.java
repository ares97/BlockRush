package radoslaw.slowinski.ares.controls;

import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class RateButton {
    private MyButton button;

    public RateButton() {
        init();
    }

    public MyButton getButton() {
        return button;
    }

    private void init() {
        button = new MyButton();
        button.setListener(getListener());
        updateIcon();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                RushGame.iGoogleServices.rateGame();
            }
        };
    }

    private void updateIcon() {
        button.setDrawableUp(button.menuUI.getDrawable("icon_star"));

    }
}
