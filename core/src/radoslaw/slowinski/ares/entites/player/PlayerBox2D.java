package radoslaw.slowinski.ares.entites.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.BlockHandler;
import radoslaw.slowinski.ares.utils.BlockTypes;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLoader;
import radoslaw.slowinski.ares.utils.SkinTypes;

import static radoslaw.slowinski.ares.utils.Constant.BIT_COIN;
import static radoslaw.slowinski.ares.utils.Constant.PPM;

/**
 * Created by ares on 21/08/17.
 */
public class PlayerBox2D {
    private World world;
    private Body body;
    private FixtureDef fixtureDef;
    private short maskBits;
    private int currentMaskBit;
    private Vector2 size;

    public PlayerBox2D(World world) {
        this.world = world;
        size = getPlayerSize();
        applyMaskBits();
        createBodyDef();
        createFixtureDef();
        createSensor();
    }

    public void changeMaskBits() {
        short maskBits = getNextMaskBit();
        Array<Fixture> fixtures = body.getFixtureList();
        for (int i = 0; i < fixtures.size; i++) {
            Filter filter = fixtures.get(i).getFilterData();
            filter.maskBits = maskBits;
            fixtures.get(i).setFilterData(filter);
            fixtures.get(i).refilter();
        }
    }

    public void jump() {
        body.applyLinearImpulse(0, body.getMass() * 4.5f,
                body.getPosition().x, body.getPosition().y, true);
    }

    public boolean isMoving() {
        return body.getLinearVelocity().x >= Constant.INIT_MOVEMENT_SPEED * 0.5f;
    }

    public boolean isInTheAir() {
        return body.getLinearVelocity().y != 0;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void refreshLinearVelocity() {
        body.setLinearVelocity(Constant.INIT_MOVEMENT_SPEED, 0);
    }

    public boolean playerFellOff() {
        return body.getPosition().y < 0;
    }

    public Vector2 getSize() {
        return size;
    }

    private void createSensor() {
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(size.x * 0.25f / PPM / 2, size.y * 0.05f / PPM, new Vector2(size.x * 0.08f / 2 / PPM, -size.y * 0.38f / 2 / PPM), 0);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = Constant.BIT_PLAYER;
        fixtureDef.filter.maskBits = maskBits;

        body.createFixture(fixtureDef).setUserData(Constant.DATA_PLAYER_SENSOR);
        shape.dispose();
    }

    private void createFixtureDef() {
        PolygonShape shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        shape.setAsBox(size.x * 0.25f / PPM / 2, size.y * 0.38f / PPM / 2, new Vector2(size.x * 0.08f / 2 / PPM, 0f), 0);
        fixtureDef.shape = shape;
        fixtureDef.density = 60;
        fixtureDef.isSensor = false;
        fixtureDef.friction = 0.4f;
        fixtureDef.filter.categoryBits = Constant.BIT_PLAYER;
        fixtureDef.filter.maskBits = maskBits;

        body.createFixture(fixtureDef).setUserData(Constant.DATA_PLAYER_SENSOR);
        shape.dispose();
    }

    private void createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(MapLoader.instance.getSpawnPoint());
        bodyDef.linearVelocity.set(Constant.INIT_MOVEMENT_SPEED, 0);
        body = world.createBody(bodyDef);
        body.setFixedRotation(true);
    }

    private void applyMaskBits() {
        maskBits = Constant.BIT_RED_BLOCK | Constant.BIT_COIN;
        BlockHandler.instance.setCurrentBlock(BlockTypes.RED);
    }

    private short getNextMaskBit() {
        currentMaskBit++;
        if (currentMaskBit > 2) // red/green/blue blocks
            currentMaskBit = 0;

        if (currentMaskBit == 0) {
            BlockHandler.instance.setCurrentBlock(BlockTypes.RED);
            return Constant.BIT_RED_BLOCK | BIT_COIN;
        } else if (currentMaskBit == 1) {
            BlockHandler.instance.setCurrentBlock(BlockTypes.GREEN);
            return Constant.BIT_GREEN_BLOCK | BIT_COIN;
        } else {
            BlockHandler.instance.setCurrentBlock(BlockTypes.BLUE);
            return Constant.BIT_BLUE_BLOCK | BIT_COIN;
        }
    }

    private Vector2 getPlayerSize() {
        TextureRegion tex = AssetHandler.instance.playerSkin.get(SkinTypes.BOY).jump;
        return new Vector2(tex.getRegionWidth(), tex.getRegionHeight());
    }

}
