package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.entites.Coin;
import radoslaw.slowinski.ares.entites.player.Player;
import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.listeners.GameContactListener;
import radoslaw.slowinski.ares.screens.gameplay.HUD;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.MapLoader;
import radoslaw.slowinski.ares.utils.MapTitles;

/**
 * Created by ares on 12.08.17.
 */
public class WorldController extends InputAdapter implements Disposable {

    public World b2dWorld;
    private HallucinatoryRushGame myGame;
    private Player player;
    private Array<Coin> coinsOnMap;
    private String mapTitle;

    public WorldController(HallucinatoryRushGame myGame, String mapTitle) {
        this.myGame = myGame;
        this.mapTitle = mapTitle;
        init();

    }

    private void init() {
        b2dWorld = new World(new Vector2(0, -9.81f), true);
        b2dWorld.setContactListener(GameContactListener.instance);
        prepareGame();
    }

    private void prepareGame() {
        MapLoader.instance.loadMap(b2dWorld, mapTitle);
        coinsOnMap = MapLoader.instance.getCoins();
        player = new Player(b2dWorld);
        AudioHandler.instance.playBackgroundMusic();
    }

    public void update(float deltaTime) {
        b2dWorld.step(deltaTime, 6, 2);
        player.update(deltaTime);
        updateCoinsOnMap(deltaTime);
        HUD.instance.update(deltaTime);
        handlePlayerBeingDead();
    }

    private void handlePlayerBeingDead() {
        if (player.isDead()) {
                AudioHandler.instance.stopBackgroundMusic();
                myGame.setMenuScreen();
        }
    }

    private void updateCoinsOnMap(float deltaTime) {
        if(coinsOnMap == null) return;
        for (int i = 0; i < coinsOnMap.size; i++) {
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
        GamePreferences.instance.save();
        b2dWorld.dispose();
    }

    public Player getPlayer() {
        return player;
    }
}
