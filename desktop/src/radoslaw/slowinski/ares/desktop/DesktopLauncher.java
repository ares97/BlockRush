package radoslaw.slowinski.ares.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import radoslaw.slowinski.ares.HallucinatoryRushGame;

public class DesktopLauncher {
    private static boolean rebuildAtlas = false;
    private static boolean drawDebugOutline = false;

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 480;
        new LwjglApplication(new HallucinatoryRushGame(), config);

        if (rebuildAtlas)
            createTexturePack();
    }

    private static void createTexturePack() {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        settings.duplicatePadding = false;
        settings.debug = drawDebugOutline;
        TexturePacker.process(settings,
                "assets-raw/images/",
                "../android/assets/images",
                "canyonbunny.pack");

        TexturePacker.process(settings,
                "assets-raw/images-ui/",
                "../android/assets/images-ui",
                "canyonbunny-ui.pack");
    }

}

