package radoslaw.slowinski.ares.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.RushGame;
import radoslaw.slowinski.ares.controls.BadgeIcon;
import radoslaw.slowinski.ares.entites.Coin;
import radoslaw.slowinski.ares.entites.player.Player;
import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.handlers.ScoreHandler;
import radoslaw.slowinski.ares.handlers.UserDataHandler;
import radoslaw.slowinski.ares.listeners.GameContactListener;
import radoslaw.slowinski.ares.listeners.InputListener;
import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.MapLevels;
import radoslaw.slowinski.ares.utils.MapLoader;

/**
 * Created by ares on 12.08.17.
 */
public class WorldController extends InputAdapter implements Disposable {

    public World b2dWorld;
    private RushGame myGame;
    private Player player;
    private Array<Coin> coinsOnMap;
    private MapLevels map;
    private InputListener inputListener;

    public WorldController(RushGame myGame, MapLevels map) {
        this.myGame = myGame;
        this.map = map;
        init();
    }

    private void init() {
        b2dWorld = new World(new Vector2(0, -9.81f), true);
        b2dWorld.setContactListener(GameContactListener.instance);
        prepareGame();
    }

    private void prepareGame() {
            MapLoader.instance.loadMap(b2dWorld, map);
            coinsOnMap = MapLoader.instance.getCoins();
        player = new Player(b2dWorld);
        inputListener = new InputListener(player, myGame);
    }

    public void update(float deltaTime) {
        b2dWorld.step(deltaTime, 6, 2);
        player.update(deltaTime);
        updateCoinsOnMap(deltaTime);
        handlePlayerBeingDead();
        handleEndOfMap();
    }

    private void handleEndOfMap() {
        if (player.reachedEnd()) {
            if (map.equals(MapLevels.HELP)) {
                stopGameplay();
                myGame.setMenuScreen();
                return;
            }
            if (myGame.isPlaying())
                setBadge();
            stopGameplay();
            if (map.equals(MapLevels.FREE_RUN))
                myGame.setFreeRunEndScreen();
            else {
                myGame.setMapLevelEndScreen(map);
            }
        }
    }

    private void setBadge() {
        BadgeIcon badge = UserDataHandler.instance.getBadge(map);
        if (badge == null) return;

        badge.setScore(ScoreHandler.instance.getCurrentLevelCoins(), true);
    }

    private void handlePlayerBeingDead() {
        if (player.isDead()) {
            stopGameplay();
            if (map.equals(MapLevels.FREE_RUN)) {
                myGame.setFreeRunEndScreen();
                RushGame.iGoogleServices.submitScore(player.getRunDistance());
            } else if (map.equals(MapLevels.HELP)) {
                myGame.setMenuScreen();
            } else {
                myGame.setMapLevelEndScreen(map);
            }
        }
    }

    public void stopGameplay() {
        if (myGame.isPlaying()) {
            AudioHandler.instance.stopBackgroundMusic();
            ScoreHandler.instance.transferCurrentLevelCoinsToCoins();
            myGame.setPlaying(false);
        }
    }

    private void updateCoinsOnMap(float deltaTime) {
        if (coinsOnMap == null) return;
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

    public void awakeInputListener() {
        Gdx.input.setInputProcessor(inputListener);
    }
}
