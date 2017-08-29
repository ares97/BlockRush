package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.UserDataHandler;
import radoslaw.slowinski.ares.utils.PromoCodes;
import radoslaw.slowinski.ares.utils.SkinTypes;

/**
 * Created by ares on 29/08/17.
 */
public class PromoCodeField {
    private TextField textField;
    private RushGame myGame;

    public PromoCodeField(final RushGame myGame) {
        this.myGame = myGame;
        textField = new TextField("promo code", getStyle());
        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                for(PromoCodes code : PromoCodes.values()){
                    if(textField.getText().equals(code.getCode())) {
                        Gdx.input.setOnscreenKeyboardVisible(false);
                        giveGift(code);
                        myGame.setMenuScreen();
                    }
                }
            }
        });
    }

    private void giveGift(PromoCodes code) {
        if(code.equals(PromoCodes.HALLOWEEN)){
            UserDataHandler.instance.setPlayerSkin(SkinTypes.LANTERN);
            UserDataHandler.instance.setPlayerAsBought(SkinTypes.LANTERN);
        }else {
            UserDataHandler.instance.setPlayerAsBought(SkinTypes.JOYSTICK);
            UserDataHandler.instance.setPlayerSkin(SkinTypes.JOYSTICK);
        }
    }

    private TextField.TextFieldStyle getStyle() {
        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = AssetHandler.instance.fonts.defaultSmall;
        style.fontColor = Color.GRAY;
        style.background = AssetHandler.instance.skin.menuUI.getDrawable("textbox_01");
        style.focusedBackground = AssetHandler.instance.skin.menuUI.getDrawable("textbox_02");
        style.cursor = AssetHandler.instance.skin.menuUI.getDrawable("textbox_cursor_02");
        return style;
    }

    public TextField getTextField() {
        return textField;
    }
}
