package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;
import radoslaw.slowinski.ares.utils.MapTitles;

/**
 * Created by ares on 21/08/17.
 */
public class HelpButton {
    private HallucinatoryRushGame myGame;
    private MyButton button;

    public HelpButton(HallucinatoryRushGame myGame){
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
                myGame.setGameScreen(MapTitles.HELP.getTitle());
            }
        };
    }
    public MyButton getButton() {
        return button;
    }
}
