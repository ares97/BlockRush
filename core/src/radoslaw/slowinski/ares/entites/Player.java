package radoslaw.slowinski.ares.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Vector2 pos;
    private Vector2 size;
    private Body body;
    private World world;
    private TextureRegion[] walk;
    TextureRegion player;
    private float scaleX;
    private float scaleY;

    public Player(World world, Vector2 pos) {
        this.world = world;
        this.pos = pos;
        size = new Vector2();
        drawPlayer();
        createPlayer();
    }

    private void drawPlayer() {
        walk = new TextureRegion[2];
        walk[0] = Assets.instance.defaultPlayerSkin.walk1;
        walk[1] = Assets.instance.defaultPlayerSkin.walk2;
        player = walk[0];
        scaleX = 0.25f;
        scaleY = 0.21f;
        size.set(walk[0].getRegionWidth(), walk[0].getRegionHeight());
    }

    public void render(SpriteBatch batch) {
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            body.applyForceToCenter(-1, 0, true);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            body.applyForceToCenter(1, 0, true);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            if (GameContactListener.instance.playerCanJump()) {
                body.setLinearVelocity(body.getLinearVelocity().x,0);
                body.applyForceToCenter(0,50,true);
            }
        batch.begin();
        batch.draw(player,
                (body.getPosition().x * PPM - size.x * 0.45f / 2 + 2),
                (body.getPosition().y * PPM - size.x * 0.45f / 2 - 5),
                size.x * 0.40f, size.y * 0.45f);
        batch.end();

    }

    public Body getBody() {
        return body;
    }


    private void createPlayer() {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(pos);
        bdef.fixedRotation = true;
        bdef.linearVelocity.set(1f, 0);
        body = world.createBody(bdef);

        shape.setAsBox(size.x * 0.33f / PPM / 2, size.y * 0.38f / PPM / 2);
        fdef.shape = shape;
        fdef.density = 1;
        fdef.friction = 0.4f;
        fdef.filter.categoryBits = Constant.BIT_PLAYER;
        fdef.filter.maskBits = Constant.BIT_BLUE_BLOCK | Constant.BIT_GREEN_BLOCK | Constant.BIT_RED_BLOCK;
        body.createFixture(fdef).setUserData(Constant.DATA_PLAYER);
        shape.dispose();

        shape = new PolygonShape();
        shape.setAsBox(size.x * 0.33f / PPM / 2, size.y * 0.05f / PPM, new Vector2(0, -size.y * 0.38f / 2 / PPM), 0);
        fdef.shape = shape;
        fdef.isSensor = true;
        fdef.filter.categoryBits = Constant.BIT_PLAYER;
        fdef.filter.maskBits = Constant.BIT_BLUE_BLOCK | Constant.BIT_GREEN_BLOCK | Constant.BIT_RED_BLOCK;
        body.createFixture(fdef).setUserData(Constant.DATA_PLAYER_SENSOR);
        shape.dispose();

    }
}
