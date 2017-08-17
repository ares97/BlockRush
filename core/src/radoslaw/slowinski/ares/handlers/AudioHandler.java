package radoslaw.slowinski.ares.handlers;

import radoslaw.slowinski.ares.utils.Assets;

/**
 * Created by ares on 17/08/17.
 */
public class AudioHandler {

    public static final AudioHandler instance = new AudioHandler();
    private AudioHandler(){}

    public void playJump(){
        Assets.instance.sound.jump.play();
    }

    public void playCoin(){
        Assets.instance.sound.coin.play();
    }

    public void playBackgroundMusic(){
        Assets.instance.music.background.play();
    }

    public void stopBackgroundMusic(){
        Assets.instance.music.background.stop();
    }
}
