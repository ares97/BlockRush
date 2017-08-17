package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    public AssetCoins coins;
    public AssetFonts fonts;
    public AssetBlocks blocks;
    public AssetIconBlocks iconBlocks;

    private Assets() {
    }

    public void load(AssetManager assetManager) {
        this.assetManager = assetManager;

        assetManager.setErrorListener(this);
        assetManager.load(Constant.TEXTURE_ATLAS_PLAYERS, TextureAtlas.class);
        assetManager.load(Constant.TEXTURE_ATLAS_ITEMS, TextureAtlas.class);
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

        coins = new Assets.AssetCoins();
        fonts = new AssetFonts();
        blocks = new AssetBlocks();
        iconBlocks = new AssetIconBlocks();
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
            for (int i = 1; i <= 6; i++) {
                coins[i - 1] = itemsAtlas.findRegion("coin" + i);
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

    public class AssetFonts {
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        public AssetFonts() {
            defaultSmall = new BitmapFont(Gdx.files.internal("images/uiSkins/arial-15.fnt"), false);
            defaultNormal = new BitmapFont(Gdx.files.internal("images/uiSkins/arial-15.fnt"), false);
            defaultBig = new BitmapFont(Gdx.files.internal("images/uiSkins/arial-15.fnt"), false);

            adjustScale();
            enableLinearFiltering();
        }

        private void adjustScale() {
            defaultSmall.getData().setScale(0.75f);
            defaultNormal.getData().setScale(1.0f);
            defaultBig.getData().setScale(2.0f);
        }

        private void enableLinearFiltering() {
            defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public class AssetBlocks {
        public final TextureRegion red;
        public final TextureRegion green;
        public final TextureRegion blue;

        AssetBlocks() {
            red = itemsAtlas.findRegion("redBlock");
            green = itemsAtlas.findRegion("greenBlock");
            blue = itemsAtlas.findRegion("blueBlock");
        }
    }

    public class AssetIconBlocks{
        public final TextureRegion red;
        public final TextureRegion green;
        public final TextureRegion blue;

        AssetIconBlocks(){
            red = itemsAtlas.findRegion("redBlockIcon");
            green = itemsAtlas.findRegion("greenBlockIcon");
            blue = itemsAtlas.findRegion("blueBlockIcon");
        }
    }
}




