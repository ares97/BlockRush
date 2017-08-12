package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import radoslaw.slowinski.ares.HallucinatoryRushGame;
import radoslaw.slowinski.ares.MapLoader;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 12.08.17.
 */
public class WorldController extends InputAdapter implements Disposable {

    private HallucinatoryRushGame myGame;
    private World b2dWorld;

    public WorldController(HallucinatoryRushGame myGame) {
        this.myGame = myGame;
        init();
    }

    private void init() {

        b2dWorld = new World(new Vector2(0, -9.81f), true);
        MapLoader.instance.loadMap(b2dWorld,"map path");
        //createB2DObjects();

    }

    private void createB2DObjects() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(150 / Constant.PPM, 15 / Constant.PPM);
        bdef.position.add(100 / Constant.PPM, 0);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(150 / Constant.PPM, 10 / Constant.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;

        b2dWorld.createBody(bdef).createFixture(fdef);

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(180 / Constant.PPM, 250 / Constant.PPM);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(6 / Constant.PPM);

        fdef.shape = circleShape;
        fdef.restitution = 0.5f;

        b2dWorld.createBody(bdef).createFixture(fdef);
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


}
