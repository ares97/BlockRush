package radoslaw.slowinski.ares.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.SkinTypes;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by ares on 12.08.17.
 */
public class AssetHandler implements Disposable, AssetErrorListener {

    public static final AssetHandler instance = new AssetHandler();
    public Map<SkinTypes, AssetPlayerSkin> playerSkin;
    public AssetCoins coins;
    public AssetFonts fonts;
    public AssetBlocks blocks;
    public AssetIconBlocks iconBlocks;
    public AssetSound sound;
    public AssetMusic music;
    public AssetUIskin skin;
    public AssetItems items;
    public AssetBackgrounds backgrounds;
    private AssetManager assetManager;
    private TextureAtlas playersAtlas;
    private TextureAtlas itemsAtlas;
    private TextureAtlas bgAtlas;

    private AssetHandler() {
    }

    public void load(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);

        loadToAssetManager();

        initResources();
        applyLinearFilter();
    }

    private void applyLinearFilter() {
        for (Texture t : playersAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        for (Texture t : itemsAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    private void loadToAssetManager() {
        assetManager.load(Constant.TEXTURE_ATLAS_PLAYERS, TextureAtlas.class);
        assetManager.load(Constant.TEXTURE_ATLAS_ITEMS, TextureAtlas.class);
        assetManager.load(Constant.TEXTURE_ATLAS_BG, TextureAtlas.class);
        assetManager.load(Constant.SOUND_COIN, Sound.class);
        assetManager.load(Constant.SOUND_JUMP, Sound.class);
        assetManager.load(Constant.MUSIC_BACKGROUND, Music.class);
        assetManager.finishLoading();
    }

    private void initResources() {
        playersAtlas = assetManager.get(Constant.TEXTURE_ATLAS_PLAYERS);
        itemsAtlas = assetManager.get(Constant.TEXTURE_ATLAS_ITEMS);
        bgAtlas = assetManager.get(Constant.TEXTURE_ATLAS_BG);

        playerSkin = new EnumMap<SkinTypes, AssetPlayerSkin>(SkinTypes.class);
        addPlayerSkinsToEnumMap();

        coins = new AssetHandler.AssetCoins();
        fonts = new AssetFonts();
        blocks = new AssetBlocks();
        iconBlocks = new AssetIconBlocks();
        music = new AssetMusic();
        sound = new AssetSound();
        skin = new AssetUIskin();
        items = new AssetItems();
        backgrounds = new AssetBackgrounds();
    }

    private void addPlayerSkinsToEnumMap() {
        for (SkinTypes skin : SkinTypes.values()) {
            playerSkin.put(skin, new AssetHandler.AssetPlayerSkin(skin));
        }
    }


    @Override
    public void dispose() {
        assetManager.dispose();
        music.background.dispose();
        sound.jump.dispose();
        sound.coin.dispose();
        skin.menuUI.dispose();
        fonts.defaultSmall.dispose();
        fonts.defaultNormal.dispose();
        fonts.defaultMedium.dispose();
        fonts.defaultBig.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(AssetHandler.class.getName(), "Couldn't load asset '" + asset.fileName + "'",
                (Exception) throwable);
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
        public final TextureRegion cheer;

        AssetPlayerSkin(SkinTypes skin) {
            walk = new TextureRegion[2];
            walk[0] = playersAtlas.findRegion(skin.getSkinName() + "_walk1");
            walk[1] = playersAtlas.findRegion(skin.getSkinName() + "_walk2");
            jump = playersAtlas.findRegion(skin.getSkinName() + "_jump");
            stand = playersAtlas.findRegion(skin.getSkinName() + "_stand");
            cheer = playersAtlas.findRegion(skin.getSkinName()+"_cheer1");
        }

    }

    public class AssetFonts {
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultMedium;
        public final BitmapFont defaultBig;

        public AssetFonts() {
            defaultSmall = new BitmapFont(Gdx.files.internal(Constant.ARIAL_FONT), false);
            defaultNormal = new BitmapFont(Gdx.files.internal(Constant.ARIAL_FONT), false);
            defaultMedium = new BitmapFont(Gdx.files.internal(Constant.ARIAL_FONT), false);
            defaultBig = new BitmapFont(Gdx.files.internal(Constant.ARIAL_FONT), false);

            adjustScale();
            enableLinearFiltering();
        }

        private void adjustScale() {
            defaultSmall.getData().setScale(0.75f);
            defaultNormal.getData().setScale(1.0f);
            defaultMedium.getData().setScale(1.35f);
            defaultBig.getData().setScale(2.0f);
        }

        private void enableLinearFiltering() {
            defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultMedium.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
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

    public class AssetIconBlocks {
        public final TextureRegion red;
        public final TextureRegion green;
        public final TextureRegion blue;

        AssetIconBlocks() {
            red = itemsAtlas.findRegion("blockRedIcon");
            green = itemsAtlas.findRegion("blockGreenIcon");
            blue = itemsAtlas.findRegion("blockBlueIcon");
        }

    }

    public class AssetSound {
        public final Sound jump;
        public final Sound coin;

        AssetSound() {
            coin = assetManager.get(Constant.SOUND_COIN);
            jump = assetManager.get(Constant.SOUND_JUMP);
        }

    }

    public class AssetMusic {
        public final Music background;

        AssetMusic() {
            background = assetManager.get(Constant.MUSIC_BACKGROUND);
        }

    }

    public class AssetUIskin {
        public final Skin menuUI;

        AssetUIskin() {
            menuUI = new Skin(new TextureAtlas(
                    Gdx.files.internal(Constant.MENU_UI_SKIN)));
        }
    }

    public class AssetItems {
        public final TextureRegion tapTick;
        public final TextureRegion bronzeStar;
        public final TextureRegion silverStar;
        public final TextureRegion goldStar;
        public final TextureRegion key;

        AssetItems() {
            tapTick = itemsAtlas.findRegion("tapTick");
            bronzeStar = itemsAtlas.findRegion("starBronze");
            silverStar = itemsAtlas.findRegion("starSilver");
            goldStar = itemsAtlas.findRegion("starGold");
            key = itemsAtlas.findRegion("key");
        }
    }

    public class AssetBackgrounds{
        public final TextureRegion[] background;
        public AssetBackgrounds(){
            background = new TextureRegion[4];
            for(int i=1;i<=4;i++){
                background[i-1] = bgAtlas.findRegion("bg"+i);
            }
        }
    }
}




