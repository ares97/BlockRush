package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 23/08/17.
 */
public class LeaderboardButton {

    private MyButton button;
    private RushGame myGame;

    public LeaderboardButton(RushGame myGame) {
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
                if(MathUtils.randomBoolean(0.5f))myGame.showStaticInterstitialAd();
            }
        };
    }


    public MyButton getButton() {
        return button;
    }
}
