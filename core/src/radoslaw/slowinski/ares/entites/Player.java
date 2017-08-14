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
    private TextureRegion playerTexture;
    private Vector2 pos;
    private Vector2 size;
    private Body body;
    private FixtureDef fixtureDef;
    private World world;
    private TextureRegion[] walkTextures;
    private TextureRegion jumpTexture;
    private float animationDelay;
    private float time;
    private int currentFrame;

    public Player(World world, Vector2 pos) {
        this.world = world;
        this.pos = pos;
        fixtureDef = new FixtureDef();
        size = new Vector2();
        createPlayer();
        animationDelay = 1 / 14f;
    }

    private void drawPlayer() {
        walkTextures = new TextureRegion[2];
        walkTextures[0] = Assets.instance.defaultPlayerSkin.walk1;
        walkTextures[1] = Assets.instance.defaultPlayerSkin.walk2;
        jumpTexture = Assets.instance.defaultPlayerSkin.jump;
        playerTexture = walkTextures[0];
        size.set(walkTextures[0].getRegionWidth(), walkTextures[0].getRegionHeight());
    }

    public void jump() {
        if (GameContactListener.instance.isPlayerOnGround()) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyForceToCenter(0, 2000, true);
        }
    }

    public void updatePlayerTexture(float deltaTime) {
        if (!GameContactListener.instance.isPlayerOnGround()) {
            playerTexture = jumpTexture;
        } else {
            handlePlayerWalkAnimation(deltaTime);
        }
    }

    private void handlePlayerWalkAnimation(float deltaTime) {
        if (animationDelay <= 0)
            return;

        time += deltaTime;
        while (time >= animationDelay) {
            stepAnimation();
        }
    }

    private void stepAnimation() {
        time -= animationDelay;
        playerTexture = walkTextures[(currentFrame++) % walkTextures.length];
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
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = Constant.BIT_PLAYER;
        fixtureDef.filter.maskBits = Constant.BIT_BLUE_BLOCK | Constant.BIT_GREEN_BLOCK | Constant.BIT_RED_BLOCK;

        body.createFixture(fixtureDef).setUserData(Constant.DATA_PLAYER_SENSOR);
        shape.dispose();
    }

    private void createPlayerFixtureDef() {
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(size.x * 0.25f / PPM / 2, size.y * 0.38f / PPM / 2, new Vector2(size.x * 0.08f / 2 / PPM, 0f), 0);
        fixtureDef.shape = shape;
        fixtureDef.density = 60;
        fixtureDef.isSensor = false;
        fixtureDef.friction = 0.4f;
        fixtureDef.filter.categoryBits = Constant.BIT_PLAYER;
        fixtureDef.filter.maskBits = Constant.BIT_BLUE_BLOCK | Constant.BIT_GREEN_BLOCK | Constant.BIT_RED_BLOCK;

        body.createFixture(fixtureDef).setUserData(Constant.DATA_PLAYER);
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
