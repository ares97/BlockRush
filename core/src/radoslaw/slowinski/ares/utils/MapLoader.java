package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.entites.Coin;

import static radoslaw.slowinski.ares.utils.Constant.PPM;

/**
 * Created by ares on 12.08.17.
 */
public class MapLoader implements Disposable {

    public static final MapLoader instance = new MapLoader();
    private TiledMap tiledMap;
    private int tileSize;
    private World world;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Array<Coin> coins;
    private MapLevels map;

    private MapLoader() {
    }

    public void loadMap(World world, MapLevels map) {
        dispose();
        this.map = map;
        String mapPath = ("maps/"+ map.getMapName() +".tmx");
        this.world = world;
        try {
            tiledMap = new TmxMapLoader().load(mapPath);
        } catch (Exception e) {
            Gdx.app.exit();
        }
        tileSize = tiledMap.getProperties().get("tilewidth", Integer.class);

        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        drawMap();
    }

    private void drawMap() {
        drawBlocks();
        drawCoins();
    }

    public void drawCoins() {
        MapLayer layer = tiledMap.getLayers().get("coins");
        if (layer == null) return;
        if(coins != null) coins.clear();
        coins = new Array<Coin>();
        for (MapObject object : layer.getObjects()) {
            float x = object.getProperties().get("x", Float.class) / PPM;
            float y = object.getProperties().get("y", Float.class) / PPM;

            Coin coin = new Coin(world, new Vector2(x, y));
            coins.add(coin);
        }
    }

    private void drawBlocks() {
        TiledMapTileLayer layer;
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("red");
        createBlocks(layer, Constant.BIT_RED_BLOCK, Constant.DATA_RED_BLOCK);
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("green");
        createBlocks(layer, Constant.BIT_GREEN_BLOCK, Constant.DATA_GREEN_BLOCK);
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("blue");
        createBlocks(layer, Constant.BIT_BLUE_BLOCK, Constant.DATA_BLUE_BLOCK);
    }


    public Vector2 getSpawnPoint() {
        MapLayer layer = tiledMap.getLayers().get("spawnPoint");
        float x = layer.getObjects().get(0).getProperties().get("x", Float.class) / PPM;
        float y = layer.getObjects().get(0).getProperties().get("y", Float.class) / PPM;

        return new Vector2(x, y);
    }


    public Vector2 getEndPoint() {
        MapLayer layer = tiledMap.getLayers().get("end");
        float x = layer.getObjects().get(0).getProperties().get("x", Float.class) / PPM;
        float y = layer.getObjects().get(0).getProperties().get("y", Float.class) / PPM;

        return new Vector2(x, y);
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
                fdef.friction = 0;
                fdef.filter.categoryBits = categoryBits;
                fdef.filter.maskBits = Constant.BIT_PLAYER;

                world.createBody(bdef).createFixture(fdef).setUserData(userData);

                cs.dispose();
            }
        }
    }

    public void renderMap(OrthographicCamera cam) {
        mapRenderer.setView(cam);
        mapRenderer.render();
    }

    public Array<Coin> getCoins() {
        return coins;
    }


    @Override
    public void dispose() {
        if (tiledMap != null)
            tiledMap.dispose();
        if (mapRenderer != null)
            mapRenderer.dispose();
        if(coins != null)
            coins.clear();
    }

    public MapLevels getMap() {
        return map;
    }

    public String getMapTitle() {
        return map.getMapName();

    }


}
