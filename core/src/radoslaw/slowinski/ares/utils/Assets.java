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
    private TextureAtlas itemsAtlas;
    public Map<SkinTypes, AssetPlayerSkin> playerSkin;
    public  AssetCoins assetCoins;

    private Assets() {
    }

    public void load(AssetManager assetManager) {
        this.assetManager = assetManager;

        assetManager.setErrorListener(this);
        assetManager.load(Constant.TEXTURE_ATLAS_PLAYERS, TextureAtlas.class);
        assetManager.load(Constant.TEXTURE_ATLAS_ITEMS,TextureAtlas.class);
        assetManager.finishLoading();

        playersAtlas = assetManager.get(Constant.TEXTURE_ATLAS_PLAYERS);
        itemsAtlas = assetManager.get(Constant.TEXTURE_ATLAS_ITEMS);

        for (Texture t : playersAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        for (Texture t : itemsAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }


        initResources();
    }

    private void initResources() {
        playerSkin = new HashMap<SkinTypes, AssetPlayerSkin>();
        addEverySkinToHashmap();

        assetCoins = new Assets.AssetCoins();
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

    public class AssetCoins {
        public final TextureRegion coins[];

        AssetCoins() {
            coins = new TextureRegion[6];
            for (int i=1;i<=6;i++){
                coins[i-1] = itemsAtlas.findRegion("coin"+i);
            }
        }
    }

    public class AssetPlayerSkin {
        public final TextureRegion[] walk;
        public final TextureRegion jump;
        public final TextureRegion stand;

        AssetPlayerSkin(SkinTypes skin) {
            walk = new TextureRegion[2];
            walk[0] = playersAtlas.findRegion(skin.getSkinName() + "_walk1");
            walk[1] = playersAtlas.findRegion(skin.getSkinName() + "_walk2");
            jump = playersAtlas.findRegion(skin.getSkinName() + "_jump");
            stand = playersAtlas.findRegion(skin.getSkinName() + "_stand");
        }

    }
}
