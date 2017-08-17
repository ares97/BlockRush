package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.entites.Coin;
import radoslaw.slowinski.ares.entites.Player;
import radoslaw.slowinski.ares.handlers.GameContactListener;
import radoslaw.slowinski.ares.screens.GUI;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLoader;
import radoslaw.slowinski.ares.utils.SkinTypes;

/**
 * Created by ares on 12.08.17.
 */
public class WorldController extends InputAdapter implements Disposable {

    public World b2dWorld;
    private HallucinatoryRushGame myGame;
    private Player player;
    private Array<Coin> coinsOnMap;

    public WorldController(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {

        b2dWorld = new World(new Vector2(0, -9.81f), true);
        b2dWorld.setContactListener(GameContactListener.instance);
        MapLoader.instance.loadMap(b2dWorld, "maps/level0.tmx");
        player = new Player(b2dWorld, MapLoader.instance.getSpawnPoint(), SkinTypes.BOY);
        coinsOnMap = MapLoader.instance.getCoins();

    }

    public void update(float deltaTime) {
        b2dWorld.step(deltaTime, 6, 2);
        player.update(deltaTime);
        updateCoinsOnMap(deltaTime);
        GUI.instance.update(deltaTime);
    }

    private void updateCoinsOnMap(float deltaTime) {
        for (int i = 0; i< coinsOnMap.size; i++) {
            if (coinsOnMap.get(i).isToDelete()) {
                coinsOnMap.removeIndex(i);
            } else {
                coinsOnMap.get(i).update(deltaTime);
            }
        }
    }

    public Array<Coin> getCoinsOnMap() {
        return coinsOnMap;
    }

    @Override
    public void dispose() {
        b2dWorld.dispose();
    }

    public Player getPlayer() {
        return player;
    }
}
