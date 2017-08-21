package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.Gdx;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class ExitButton {
    private MyButton button;

    public ExitButton() {
        init();
    }

    private void init() {
        button = new MyButton();
        button.setListener(getListener());
        updateIcon();
    }

    private IOnclickCallback getListener() {
        return new IOnclickCallback() {
            @Override
            public void onClick() {
                Gdx.app.exit();
            }
        };
    }

    private void updateIcon() {
        button.setDrawableUp(button.menuUI.getDrawable("icon_cross"));
    }

    public MyButton getButton() {
        return button;
    }
}
