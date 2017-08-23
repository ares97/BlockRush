package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 23/08/17.
 */
public class LeaderboardButton {

    private MyButton button;
    private HallucinatoryRushGame myGame;

    public LeaderboardButton(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setString("Add score to leaderboard", Color.GOLD);
        button.applyDefaultSkin();
        button.setListener(getListener());
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                // TODO handle leaderboard
            }
        };
    }


    public MyButton getButton() {
        return button;
    }
}
