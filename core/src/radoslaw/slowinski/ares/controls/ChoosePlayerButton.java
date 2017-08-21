package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.graphics.Color;
import radoslaw.slowinski.ares.handlers.UserDataHandler;
import radoslaw.slowinski.ares.utils.IOnclickCallback;
import radoslaw.slowinski.ares.utils.SkinTypes;

/**
 * Created by ares on 21/08/17.
 */
public class ChoosePlayerButton {
    private MyButton button;
    SkinTypes playerSkin;

    public ChoosePlayerButton(SkinTypes playerSkin){
        this.playerSkin = playerSkin;
        init();
    }


    private void init() {
        button = new MyButton();
        button.setString("choose", Color.GOLDENROD);
        button.setListener(getListener());
        button.setDrawableUp(button.menuUI.getDrawable("button_05"));
        button.setDrawableDown(button.menuUI.getDrawable("button_03"));
    }


    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                UserDataHandler.instance.setPlayerSkin(playerSkin);
            }
        };
    }

    public MyButton getButton(){
        return button;
    }
}
