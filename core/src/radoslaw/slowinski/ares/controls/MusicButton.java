package radoslaw.slowinski.ares.controls;

import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.utils.IOnclickCallback;

/**
 * Created by ares on 21/08/17.
 */
public class MusicButton {
    private MyButton button;

    public MusicButton() {
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
                AudioHandler.instance.changeMusicState();
                updateIcon();
            }
        };
    }

    private void updateIcon() {
        if (AudioHandler.instance.getMuteMusic()) {
            button.setDrawableUp(button.menuUI.getDrawable("icon_pause"));
        } else {
            button.setDrawableUp(button.menuUI.getDrawable("icon_music"));
        }
    }

    public MyButton getButton() {
        return button;
    }
}
