package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;

import static radoslaw.slowinski.ares.utils.Constant.PPM;

/**
 * Created by ares on 12.08.17.
 */
public class MapLoader implements Disposable {

    private TiledMap tiledMap;
    private int tileMapWidth;
    private int tileMapHeight;
    private int tileSize;
    private World world;
    private OrthogonalTiledMapRenderer mapRenderer;

    public static final MapLoader instance = new MapLoader();

    private MapLoader() {}

    public void loadMap(World world, String mapPath) {
        this.world = world;
        try {
            tiledMap = new TmxMapLoader().load("maps/test.tmx");
        } catch (Exception e) {
            System.out.println("FAIL!");
            Gdx.app.exit();
        }

        tileMapHeight = tiledMap.getProperties().get("height", Integer.class);
        tileMapWidth = tiledMap.getProperties().get("width", Integer.class);
        tileSize = tiledMap.getProperties().get("tilewidth", Integer.class);

        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        drawMap();
    }

    private void drawMap() {
        TiledMapTileLayer layer;
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("red");
        createBlocks(layer, Constant.BIT_RED_BLOCK, Constant.DATA_RED_BLOCK);
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("green");
        createBlocks(layer, Constant.BIT_GREEN_BLOCK, Constant.DATA_GREEN_BLOCK);
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("blue");
        createBlocks(layer, Constant.BIT_BLUE_BLOCK, Constant.DATA_BLUE_BLOCK);
    }

    private void createBlocks(TiledMapTileLayer layer, short categoryBits, String userData) {

        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {

                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if (cell == null) continue;
                if (cell.getTile() == null) continue;

                BodyDef bdef = new BodyDef();
                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set(
                        (col + 0.5f) * tileSize / PPM,
                        (row + 0.5f) * tileSize / PPM);

                ChainShape cs = new ChainShape();
                Vector2[] v = new Vector2[3];
                v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
                v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
                v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
                cs.createChain(v);

                FixtureDef fdef = new FixtureDef();
                fdef.shape = cs;
                fdef.friction=0;
                fdef.filter.categoryBits = categoryBits;
                fdef.filter.maskBits = Constant.BIT_PLAYER;

                world.createBody(bdef).createFixture(fdef).setUserData(userData);

                cs.dispose();
            }
        }
    }

    public void renderMap(OrthographicCamera cam){
        mapRenderer.setView(cam);
        mapRenderer.render();
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }


}
