package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 23/08/17.
 */
public class WatchAdButton {
    private RushGame myGame;
    private MyButton button;

    public WatchAdButton(RushGame myGame) {
        this.myGame = myGame;

        init();
    }

    private void init() {
        button = new MyButton();
        button.setString("Watch", Color.GOLD);
        button.setListener(getListener());
        button.applyDefaultSkin();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                // TODO open videoAD
            }
        };
    }

    public MyButton getButton() {
        return button;
    }
}
