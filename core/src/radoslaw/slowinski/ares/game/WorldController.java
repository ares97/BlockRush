package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.entites.Player;
import radoslaw.slowinski.ares.handlers.GameContactListener;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLoader;

/**
 * Created by ares on 12.08.17.
 */
public class WorldController extends InputAdapter implements Disposable {

    private HallucinatoryRushGame myGame;
    public World b2dWorld;
    private Player player;

    public WorldController(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {

        b2dWorld = new World(new Vector2(0, -9.81f), true);
        b2dWorld.setContactListener(GameContactListener.instance);
        player = new Player(b2dWorld, new Vector2(50 / Constant.PPM, 150 / Constant.PPM));
        MapLoader.instance.loadMap(b2dWorld, "map path");

    }


    public void update(float deltaTime) {
        b2dWorld.step(deltaTime, 6, 2);
    }


    @Override
    public void dispose() {
        b2dWorld.dispose();
    }

    public World getB2dWorld() {
        return b2dWorld;
    }

    public void renderPlayer(SpriteBatch batch) {
        player.render(batch);
    }

    public void renderFollowingCamera(OrthographicCamera camera) {
        camera.position.set(Constant.GAME_WIDTH / 4 + player.getBody().getPosition().x * Constant.PPM,
                Constant.GAME_HEIGHT / 2, 0);
    }
}
