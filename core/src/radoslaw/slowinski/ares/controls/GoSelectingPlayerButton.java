package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class GoSelectingPlayerButton {
    private HallucinatoryRushGame myGame;
    private MyButton button;

    public GoSelectingPlayerButton(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setString("Select player", Color.GOLD);
        button.setListener(getListener());
        button.applyDefaultSkin();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                myGame.setSelectPlayerScreen();
            }
        };
    }

    public MyButton getButton() {
        return button;
    }

}
