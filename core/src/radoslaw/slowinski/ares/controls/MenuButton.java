package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;
import radoslaw.slowinski.ares.utils.MapTitles;

/**
 * Created by ares on 23/08/17.
 */
public class MenuButton {
    private HallucinatoryRushGame myGame;
    private MyButton button;

    public MenuButton(HallucinatoryRushGame myGame) {
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
