package radoslaw.slowinski.ares.handlers;

import com.badlogic.gdx.physics.box2d.*;
import radoslaw.slowinski.ares.entites.Coin;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 12.08.17.
 */
public class GameContactListener implements ContactListener {
    public static final GameContactListener instance =
            new GameContactListener();
    private int numFootContacts;
    private Fixture fb;
    private Fixture fa;

    private GameContactListener() {
    }

    @Override
    public void beginContact(Contact contact) {
        fa = contact.getFixtureA();
        fb = contact.getFixtureB();
        if (fa == null || fb == null) return;

        if ((fa.getUserData() != null && fa.getUserData().equals(Constant.DATA_PLAYER_SENSOR)) ||
                (fb.getUserData() != null && fb.getUserData().equals(Constant.DATA_PLAYER_SENSOR))) {
            if(fa.getUserData() != Constant.DATA_COIN && fb.getUserData() != Constant.DATA_COIN) {      // don't count when collides with coin
                numFootContacts++;
            }
        }

        if ((fa.getUserData() != null && fa.getUserData().equals(Constant.DATA_COIN)) ||
                (fb.getUserData() != null && fb.getUserData().equals(Constant.DATA_COIN))) {

            Coin c = (Coin) fb.getBody().getUserData();
            c.onContact();
        }

    }

    @Override
    public void endContact(Contact contact) {
        fa = contact.getFixtureA();
        fb = contact.getFixtureB();
        if (fa == null || fb == null) return;

        if ((fa.getUserData() != null && fa.getUserData().equals(Constant.DATA_PLAYER_SENSOR)) ||
                (fb.getUserData() != null && fb.getUserData().equals(Constant.DATA_PLAYER_SENSOR))) {
            if(fa.getUserData() != Constant.DATA_COIN && fb.getUserData() != Constant.DATA_COIN) {       // don't count when collides with coin
                numFootContacts--;
            }
        }

    }

    public boolean isPlayerOnGround() {
        return numFootContacts > 0;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
