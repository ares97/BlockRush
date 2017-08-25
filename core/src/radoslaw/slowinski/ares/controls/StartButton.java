package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 24/08/17.
 */
public class StartButton {
    private RushGame myGame;
    private MyButton button;
    private MapLevels mapLevel;

    public StartButton(RushGame myGame,MapLevels mapLevel) {
        this.myGame = myGame;
        this.mapLevel = mapLevel;
        init();
    }


    private void init() {
        button = new MyButton();
        button.setString("Start", Color.GOLD);
        button.setListener(getListener());
        button.applyDefaultSkin();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                myGame.setGameScreen(mapLevel);
            }
        };
    }

    public MyButton getButton() {
        return button;
    }
}
