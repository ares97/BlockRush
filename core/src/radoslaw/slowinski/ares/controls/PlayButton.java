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
    private String onButtonString;

    public PlayButton(RushGame myGame) {
        this.myGame = myGame;
        onButtonString = "Play";
        init();
    }

    public PlayButton(RushGame myGame, String onButtonString) {
        this.myGame = myGame;
        this.onButtonString = onButtonString;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setString(onButtonString, Color.GOLD);
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
