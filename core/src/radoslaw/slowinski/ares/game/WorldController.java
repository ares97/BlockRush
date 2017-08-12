package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.entites.Player;
import radoslaw.slowinski.ares.utils.MapLoader;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 12.08.17.
 */
public class WorldController extends InputAdapter implements Disposable {

    private HallucinatoryRushGame myGame;
    public World b2dWorld;
    private Body circle;
    private Player player;

    public WorldController(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {

        b2dWorld = new World(new Vector2(0, -9.81f), true);
        createB2DObjects();
        player = new Player(b2dWorld,new Vector2(50/Constant.PPM,150/Constant.PPM));
        MapLoader.instance.loadMap(b2dWorld,"map path");

    }

    private void createB2DObjects() {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();


        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(120 / Constant.PPM, 160 / Constant.PPM);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(8 / Constant.PPM);

        fdef.shape = circleShape;
        fdef.filter.categoryBits = Constant.BIT_PLAYER;
        fdef.restitution = 0.5f;

        circle = b2dWorld.createBody(bdef);
        circle.createFixture(fdef);


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

    public void renderPlayer(SpriteBatch batch){
        player.render(batch);
    }


}
