package radoslaw.slowinski.ares.handlers;

/**
 * Created by ares on 17/08/17.
 */
public class AudioHandler {

    public static final AudioHandler instance = new AudioHandler();

    private AudioHandler() {
    }

    public void playJump() {
        AssetHandler.instance.sound.jump.play();
    }

    public void playCoin() {
        AssetHandler.instance.sound.coin.play();
    }

    public void playBackgroundMusic() {
        AssetHandler.instance.music.background.play();
    }

    public void stopBackgroundMusic() {
        AssetHandler.instance.music.background.stop();
    }
}
