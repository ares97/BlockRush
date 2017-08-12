package radoslaw.slowinski.ares.entites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by ares on 12.08.17.
 */
public abstract class AbstractGameObject {
    protected Body body;
    protected Vector2 pos;

    public AbstractGameObject(Body body, Vector2 pos){
        this.body = body;
        this.pos = pos;
    }
}
