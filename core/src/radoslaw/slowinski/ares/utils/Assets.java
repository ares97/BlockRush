package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by ares on 12.08.17.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final Assets instance = new Assets();
    public AssetDeafultPlayerSkin defaultPlayerSkin;
    private AssetManager assetManager;
    private TextureAtlas playersAtlas;

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
        defaultPlayerSkin = new AssetDeafultPlayerSkin();
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

    public class AssetDeafultPlayerSkin {
        public final TextureRegion walk1;
        public final TextureRegion walk2;
        public final TextureRegion jump;
        public final TextureRegion fall;
        public final TextureRegion stand;

        public AssetDeafultPlayerSkin() {
            walk1 = playersAtlas.findRegion("player_walk1");
            walk2 = playersAtlas.findRegion("player_walk2");
            jump = playersAtlas.findRegion("player_jump");
            fall = playersAtlas.findRegion("player_fall");
            stand = playersAtlas.findRegion("player_stand");
        }
    }
}
