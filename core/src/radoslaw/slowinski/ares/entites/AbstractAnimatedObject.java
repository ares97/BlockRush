package radoslaw.slowinski.ares.entites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 15/08/17.
 */
public abstract class AbstractAnimatedObject {
    protected Body body;
    protected boolean toDelete;
    private Vector2 size;
    private TextureRegion[] frames;
    private float animationDelay;


    private float time;
    private int currentFrame;

    public AbstractAnimatedObject(Body body, TextureRegion[] frames) {
        this.body = body;
        this.frames = frames;
        size = new Vector2(frames[0].getRegionWidth(), frames[0].getRegionHeight());
        prepareAnimation();
    }

    private void prepareAnimation() {
        time = 0;
        currentFrame = 0;
        animationDelay = 1 / 8f;
    }

    public void update(float deltaTime) {
        if (animationDelay <= 0) return;
        time += deltaTime;

        while (time >= animationDelay)
            stepFrame();
    }

    private void stepFrame() {
        time -= animationDelay;
        currentFrame++;
        if (currentFrame == frames.length) {
            currentFrame = 0;
        }
    }


    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(getTexture(),
                body.getPosition().x * Constant.PPM - size.x / 2,
                body.getPosition().y * Constant.PPM - size.y / 2);
        sb.end();
    }

    public TextureRegion getTexture() {
        return frames[currentFrame];
    }


    public boolean isToDelete() {
        return toDelete;
    }

}
