package radoslaw.slowinski.ares.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.handlers.BlockHandler;
import radoslaw.slowinski.ares.listeners.GameContactListener;
import radoslaw.slowinski.ares.utils.BlockTypes;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.SkinTypes;

import static radoslaw.slowinski.ares.utils.Constant.*;

/**
 * Created by ares on 12.08.17.
 */
public class Player {
    public static final float spriteScaleX = 0.40f;
    public static final float spriteScaleY = 0.45f;
    private final float animationDelay = 1 / 14f;
    private TextureRegion currentPlayerTexture;
    private Vector2 startingPos;
    private Vector2 size;
    private Body body;
    private FixtureDef fixtureDef;
    private World world;
    private TextureRegion[] walkTextures;
    private TextureRegion jumpTexture;
    private TextureRegion standTexture;
    private float time;
    private int currentFrame;
    private Vector2 linearVelocity;
    private int currentMaskBit;
    private short maskBits;

    public Player(World world, Vector2 startingPos, SkinTypes type) {
        this.world = world;
        this.startingPos = startingPos;

        linearVelocity = new Vector2(INIT_MOVEMENT_SPEED, 0);
        fixtureDef = new FixtureDef();
        size = new Vector2();

        handlePlayerSkin(type);
        handlePlayerWithBox2D();
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(
                currentPlayerTexture,
                getTextureX(), getTextureY(),
                getTextureWidth(), getTextureHeight());
        batch.end();
    }

    private void handlePlayerSkin(SkinTypes types) {
        applyPlayerSkin(AssetHandler.instance.playerSkin.get(types));
        size.set(currentPlayerTexture.getRegionWidth(),
                currentPlayerTexture.getRegionHeight());
    }

    private void applyPlayerSkin(AssetHandler.AssetPlayerSkin skin) {
        walkTextures = skin.walk;
        jumpTexture = skin.jump;
        standTexture = skin.stand;

        currentPlayerTexture = standTexture;
    }

    public void jump() {
        if (!isPlayerJumping()) {
            AudioHandler.instance.playJump();
            body.setLinearVelocity(linearVelocity);
            body.applyLinearImpulse(0, body.getMass() * 5,
                    body.getPosition().x,
                    body.getPosition().y, true);
        } else if (body.getLinearVelocity().x < linearVelocity.x * 0.5f) {
            handlePlayerBeingStuck();
        }
    }

    private boolean isPlayerJumping() {
        return !GameContactListener.instance.isPlayerOnGround();
    }

    private void updatePlayerTexture(float deltaTime) {
        if (!GameContactListener.instance.isPlayerOnGround()) {
            currentPlayerTexture = jumpTexture;
        } else {
            handlePlayerWalkAnimation(deltaTime);
        }
    }

    public void update(float deltaTime) {
        updatePlayerTexture(deltaTime);
        handlePlayerBeingStuck();
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

    private void handlePlayerWalkAnimation(float deltaTime) {
        time += deltaTime;
        while (time >= animationDelay) {
            stepAnimation();
        }
    }

    private void stepAnimation() {
        time -= animationDelay;
        if (body.getLinearVelocity().x >= linearVelocity.x * 0.5f)
            currentPlayerTexture = walkTextures[(currentFrame++) % walkTextures.length];
        else
            currentPlayerTexture = standTexture;
    }

    private void handlePlayerWithBox2D() {
        adjustMaskBits();

        createPlayerBodyDef();
        createPlayerFixtureDef();
        createSensorFixtureDef();
    }

    private void adjustMaskBits() {
        maskBits = Constant.BIT_RED_BLOCK | Constant.BIT_COIN;
        BlockHandler.instance.setCurrentBlock(BlockTypes.RED);
    }

    private void createSensorFixtureDef() {
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(size.x * 0.25f / PPM / 2, size.y * 0.05f / PPM, new Vector2(size.x * 0.08f / 2 / PPM, -size.y * 0.38f / 2 / PPM), 0);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = Constant.BIT_PLAYER;
        fixtureDef.filter.maskBits = maskBits;


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
        fixtureDef.filter.maskBits = maskBits;


        body.createFixture(fixtureDef).setUserData(Constant.DATA_PLAYER_SENSOR);
        shape.dispose();
    }

    private void createPlayerBodyDef() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(startingPos);
        bdef.linearVelocity.set(linearVelocity);
        body = world.createBody(bdef);
        body.setFixedRotation(true);
    }

    private float getTextureX() {
        return body.getPosition().x * PPM - size.x * spriteScaleX / 2 + 2;
    }

    private float getTextureY() {
        return body.getPosition().y * PPM - size.x * spriteScaleY / 2 - 5;
    }

    private float getTextureWidth() {
        return size.x * spriteScaleX;
    }

    private float getTextureHeight() {
        return size.y * spriteScaleY;
    }

    public Body getBody() {
        return body;
    }


    public boolean isDead() {
        if (body.getPosition().y <= 0) {
            return true;
        }
        return false;
    }

    private void handlePlayerBeingStuck() {
        body.setLinearVelocity(linearVelocity.x, body.getLinearVelocity().y);
    }

}
