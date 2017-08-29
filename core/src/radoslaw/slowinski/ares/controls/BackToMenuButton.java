package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class BackToMenuButton {
    private RushGame myGame;
    private MyButton button;

    public BackToMenuButton(RushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setListener(getListener());
        updateIcon();
    }

    private void updateIcon() {
        button.setDrawableUp(button.menuUI.getDrawable("icon_back"));
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                Gdx.input.setOnscreenKeyboardVisible(false);
                myGame.setMenuScreen();
                if(MathUtils.randomBoolean(0.4f)) myGame.showStaticInterstitialAd();
            }
        };
    }

    public MyButton getButton() {
        return button;
    }
}
