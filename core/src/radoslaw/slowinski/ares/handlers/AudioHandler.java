package radoslaw.slowinski.ares.handlers;

import radoslaw.slowinski.ares.utils.GamePreferences;

/**
 * Created by ares on 17/08/17.
 */
public class AudioHandler {

    public static final AudioHandler instance = new AudioHandler();
    private boolean muteSound;
    private boolean muteMusic;

    private AudioHandler() {
        getSavedData();
    }

    private void getSavedData() {
        muteSound = GamePreferences.instance.isSound();
        muteMusic = GamePreferences.instance.isMusic();
    }

    public void playJump() {
        if (!muteSound)
            AssetHandler.instance.sound.jump.play();
    }

    public void playCoin() {
        if (!muteSound)
            AssetHandler.instance.sound.coin.play();
    }

    public void playBackgroundMusic() {
        if (!muteMusic)
            AssetHandler.instance.music.background.play();
    }

    public void stopBackgroundMusic() {
        AssetHandler.instance.music.background.stop();
    }

    public boolean getMuteMusic() {
        return muteMusic;
    }

    public void setMuteMusic(boolean mute) {
        muteMusic = mute;
    }

    public boolean getMuteSound() {
        return muteSound;
    }

    public void setMuteSound(boolean mute) {
        muteSound = mute;
    }

    public void changeMusicState() {
        muteMusic = !muteMusic;
    }

    public void changeSoundState() {
        muteSound = !muteSound;
    }
}
