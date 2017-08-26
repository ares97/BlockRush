package radoslaw.slowinski.ares.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.utils.Constant;

public class DesktopLauncher {
    private static boolean rebuildAtlas = false;

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new RushGame(), config);

        config.width = Constant.GAME_WIDTH * Constant.SCALE;
        config.height = Constant.GAME_HEIGHT * Constant.SCALE;

        if (rebuildAtlas)
            createTexturePack();
    }

    private static void createTexturePack() {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.duplicatePadding = false;
        settings.debug = false;
        TexturePacker.process(settings,
                "assets-raw/bg",
                "images/background/",
                "background.pack");
    }

}

