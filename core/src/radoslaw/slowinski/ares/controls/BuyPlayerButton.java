package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.handlers.ScoreHandler;
import radoslaw.slowinski.ares.handlers.UserDataHandler;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.IOnclickCallback;
import radoslaw.slowinski.ares.utils.SkinTypes;

/**
 * Created by ares on 22/08/17.
 */
public class BuyPlayerButton {
    private MyButton button;
    private HallucinatoryRushGame myGame;
    SkinTypes playerSkin;

    public BuyPlayerButton(SkinTypes playerSkin,HallucinatoryRushGame myGame){
        this.playerSkin = playerSkin;
        this.myGame = myGame;
        init();
    }

    private void init() {
        button = new MyButton();
        button.setString(playerSkin.getCost()+" coins", Color.FOREST);
        button.setListener(getListener());
        button.setDrawableUp(button.menuUI.getDrawable("button_05"));
        button.setDrawableDown(button.menuUI.getDrawable("button_03"));
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                if(ScoreHandler.instance.getCoins() >= playerSkin.getCost()) {
                    buyPlayer();
                    refreshScreen();
                }
            }
        };
    }

    private void refreshScreen() {
        myGame.setSelectPlayerScreen();
    }

    private void buyPlayer() {
        ScoreHandler.instance.addCoins(-playerSkin.getCost());
        UserDataHandler.instance.setPlayerAsBought(playerSkin);
        GamePreferences.instance.save();
    }

    public MyButton getButton(){
        return button;
    }

}
