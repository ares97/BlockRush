package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 23/08/17.
 */
public class MenuButton {
    private RushGame myGame;
    private MyButton button;

    public MenuButton(RushGame myGame) {
        this.myGame = myGame;

        init();
    }

    private void init() {
        button = new MyButton();
        button.setString("Menu", Color.GOLD);
        button.setListener(getListener());
        button.applyDefaultSkin();
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
