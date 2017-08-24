package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 21/08/17.
 */
public class HelpButton {
    private RushGame myGame;
    private MyButton button;

    public HelpButton(RushGame myGame){
        this.myGame = myGame;

        init();
    }

    private void init() {
        button = new MyButton();
        button.setString("Help", Color.GOLD);
        button.applyDefaultSkin();
        button.setListener(getListener());
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                myGame.setGameScreen(MapLevels.HELP.getMapName());
            }
        };
    }
    public MyButton getButton() {
        return button;
    }
}
