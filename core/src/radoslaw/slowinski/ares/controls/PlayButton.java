package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class PlayButton {
    private HallucinatoryRushGame myGame;
    private MyButton button;

    public PlayButton(HallucinatoryRushGame myGame) {
        this.myGame = myGame;

        init();
    }

    private void init() {
        button = new MyButton();
        button.setString("Play", Color.GOLD);
        button.setListener(getListener());
        button.applyDefaultSkin();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                myGame.setGameScreen();
            }
        };
    }

    public MyButton getButton() {
        return button;
    }

}
