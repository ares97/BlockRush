package radoslaw.slowinski.ares.controls;

import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class SoundButton {
    private MyButton button;

    public SoundButton() {
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
                AudioHandler.instance.changeSoundState();
                updateIcon();
            }
        };
    }

    private void updateIcon() {
        if (AudioHandler.instance.getMuteSound()) {
            button.setDrawableUp(button.menuUI.getDrawable("icon_sound_off"));
        } else {
            button.setDrawableUp(button.menuUI.getDrawable("icon_sound_on"));
        }
    }

    public MyButton getButton() {
        return button;
    }
}
