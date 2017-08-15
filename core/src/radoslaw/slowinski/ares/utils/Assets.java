package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ares on 12.08.17.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final Assets instance = new Assets();
    private AssetManager assetManager;
    private TextureAtlas playersAtlas;
    public Map<SkinTypes, AssetPlayerSkin> playerSkin;

    private Assets() {
    }

    public void load(AssetManager assetManager) {
        this.assetManager = assetManager;

        assetManager.setErrorListener(this);
        assetManager.load(Constant.TEXTURE_ATLAS_PLAYERS, TextureAtlas.class);
        assetManager.finishLoading();

        playersAtlas = assetManager.get(Constant.TEXTURE_ATLAS_PLAYERS);

        for (Texture t : playersAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }


        initResources();
    }

    private void initResources() {
        playerSkin = new HashMap<SkinTypes, AssetPlayerSkin>();
        addEverySkinToHashmap();
    }

    private void addEverySkinToHashmap() {
        for (SkinTypes skin : SkinTypes.values()) {
            playerSkin.put(skin, new Assets.AssetPlayerSkin(skin));
        }
    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(Assets.class.getName(), "Couldn't load asset '" + asset.fileName + "'",
                (Exception) throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class AssetPlayerSkin {
        public TextureRegion[] walk;
        public TextureRegion jump;
        public TextureRegion stand;

        public AssetPlayerSkin(SkinTypes skin) {
            walk = new TextureRegion[2];
            setTextures(skin.getSkinName());
        }

        private void setTextures(String skinName) {
            walk[0] = playersAtlas.findRegion(skinName + "_walk1");
            walk[1] = playersAtlas.findRegion(skinName + "_walk2");
            jump = playersAtlas.findRegion(skinName + "_jump");
            stand = playersAtlas.findRegion(skinName + "_stand");
        }
    }
}
