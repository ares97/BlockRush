package radoslaw.slowinski.ares.controls;

import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class BackToMenuButton {
    private RushGame myGame;
    private MyButton button;

    public BackToMenuButton(RushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setListener(getListener());
        updateIcon();
    }

    private void updateIcon() {
        button.setDrawableUp(button.menuUI.getDrawable("icon_back"));
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                myGame.setMenuScreen();
            }
        };
    }

    public MyButton getButton() {
        return button;
    }
}
