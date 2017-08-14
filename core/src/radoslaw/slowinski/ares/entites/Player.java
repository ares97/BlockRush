package radoslaw.slowinski.ares.entites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import radoslaw.slowinski.ares.handlers.GameContactListener;
import radoslaw.slowinski.ares.utils.Assets;
import radoslaw.slowinski.ares.utils.Constant;

import static radoslaw.slowinski.ares.utils.Constant.PPM;

/**
 * Created by ares on 12.08.17.
 */
public class Player {
    public static final float spriteScaleX = 0.40f;
    public static final float spriteScaleY = 0.45f;
    private final FixtureDef fdef;
    TextureRegion playerTexture;
    private Vector2 pos;
    private Vector2 size;
    private Body body;
    private World world;
    private TextureRegion[] walk;

    public Player(World world, Vector2 pos) {
        this.world = world;
        this.pos = pos;
        fdef = new FixtureDef();
        size = new Vector2();
        createPlayer();
    }

    private void drawPlayer() {
        walk = new TextureRegion[2];
        walk[0] = Assets.instance.defaultPlayerSkin.walk1;
        walk[1] = Assets.instance.defaultPlayerSkin.walk2;
        playerTexture = walk[0];
        size.set(walk[0].getRegionWidth(), walk[0].getRegionHeight());
    }

    public void jump() {
        if (GameContactListener.instance.playerCanJump()) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyForceToCenter(0, 2000, true);
        }
    }


    private void createPlayer() {
        drawPlayer();
        createPlayerBodyDef();
        createPlayerFixtureDef();
        createSensorFixtureDef();
    }

    private void createSensorFixtureDef() {
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(size.x * 0.25f / PPM / 2, size.y * 0.05f / PPM, new Vector2(size.x * 0.08f / 2 / PPM, -size.y * 0.38f / 2 / PPM), 0);
        fdef.shape = shape;
        fdef.isSensor = true;
        fdef.filter.categoryBits = Constant.BIT_PLAYER;
        fdef.filter.maskBits = Constant.BIT_BLUE_BLOCK | Constant.BIT_GREEN_BLOCK | Constant.BIT_RED_BLOCK;

        body.createFixture(fdef).setUserData(Constant.DATA_PLAYER_SENSOR);
        shape.dispose();
    }

    private void createPlayerFixtureDef() {
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(size.x * 0.25f / PPM / 2, size.y * 0.38f / PPM / 2, new Vector2(size.x * 0.08f / 2 / PPM, 0f), 0);
        fdef.shape = shape;
        fdef.density = 60;
        fdef.isSensor = false;
        fdef.friction = 0.4f;
        fdef.filter.categoryBits = Constant.BIT_PLAYER;
        fdef.filter.maskBits = Constant.BIT_BLUE_BLOCK | Constant.BIT_GREEN_BLOCK | Constant.BIT_RED_BLOCK;

        body.createFixture(fdef).setUserData(Constant.DATA_PLAYER);
        shape.dispose();
    }

    private void createPlayerBodyDef() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(pos);
        bdef.fixedRotation = true;
        bdef.linearVelocity.set(Constant.CAMERA_MOV_SPEED, 0);
        body = world.createBody(bdef);
    }

    public TextureRegion getTexture() {
        return playerTexture;
    }

    public float getTextureX() {
        return body.getPosition().x * PPM - size.x * spriteScaleX / 2 + 2;
    }

    public float getTextureY() {
        return body.getPosition().y * PPM - size.x * spriteScaleY / 2 - 5;
    }

    public float getTextureWidth() {
        return size.x * spriteScaleX;
    }

    public float getTextureHeight() {
        return size.y * spriteScaleY;
    }

    public Body getBody() {
        return body;
    }
}
