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
                if(MathUtils.randomBoolean(0.3f))myGame.showStaticInterstitialAd();
                if(!RushGame.iGoogleServices.isSignedIn())
                    RushGame.iGoogleServices.signIn();
                RushGame.iGoogleServices.showScores();
            }
        };
    }


    public MyButton getButton() {
        return button;
    }
}
