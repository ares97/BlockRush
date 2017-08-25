package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class PlayButton {
    private RushGame myGame;
    private MyButton button;
    private String buttonText;

    public PlayButton(RushGame myGame) {
        this.myGame = myGame;
        buttonText = "Play";
        init();
    }

    public PlayButton(RushGame myGame, String buttonText) {
        this.myGame = myGame;
        this.buttonText = buttonText;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setString(buttonText, Color.GOLD);
        button.setListener(getListener());
        button.applyDefaultSkin();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                myGame.setSelectLevelScreen();
            }
        };
    }

    public MyButton getButton() {
        return button;
    }

}
