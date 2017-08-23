package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;
import radoslaw.slowinski.ares.utils.MapTitles;

/**
 * Created by ares on 21/08/17.
 */
public class PlayButton {
    private HallucinatoryRushGame myGame;
    private MyButton button;
    private String string;

    public PlayButton(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        string = "Play";
        init();
    }

    public PlayButton(HallucinatoryRushGame myGame, String string) {
        this.myGame = myGame;
        this.string = string;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setString(string, Color.GOLD);
        button.setListener(getListener());
        button.applyDefaultSkin();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                myGame.setGameScreen(MapTitles.FREE_RUN.getTitle());
            }
        };
    }

    public MyButton getButton() {
        return button;
    }

}
