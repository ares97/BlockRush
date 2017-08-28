package radoslaw.slowinski.ares.entites.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import radoslaw.slowinski.ares.handlers.AudioHandler;
import radoslaw.slowinski.ares.handlers.ScoreHandler;
import radoslaw.slowinski.ares.listeners.GameContactListener;
import radoslaw.slowinski.ares.utils.MapLoader;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 12.08.17.
 */
public class Player {
    private PlayerBox2D playerB2D;
    private PlayerAnimation playerAnimation;

    public Player(World world) {
        playerB2D = new PlayerBox2D(world);
        playerAnimation = new PlayerAnimation(playerB2D);
    }

    public void render(SpriteBatch batch) {
        playerAnimation.render(batch);
    }

    public void jump() {
        if (!isPlayerJumping() && playerB2D.isMoving()) {
            AudioHandler.instance.playJump();
            playerB2D.jump();
        }
    }

    private boolean isPlayerJumping() {
        return !GameContactListener.instance.isPlayerOnGround();
    }

    public void update(float deltaTime) {
        playerAnimation.update(deltaTime);
        if (!playerB2D.isMoving() && !playerB2D.isInTheAir()) {
            playerB2D.refreshLinearVelocity();
        }
    }

    public void changeMaskBits() {
        playerB2D.changeMaskBits();
    }

    public Vector2 getPosition() {
        return playerB2D.getPosition();
    }

    public boolean isDead() {
        if (playerB2D.playerFellOff()) {
            if(MapLoader.instance.getMapTitle().equals(MapLevels.HELP.getMapName())){
                playerB2D.setToSpawnPoint();
                return false;
            }
            playerB2D.stopPlayer();
            ScoreHandler.instance.setDistance(getRunDistance());
            return true;
        }
        return false;
    }

    public boolean reachedEnd(){
        if(playerB2D.getReachedEnd()){
            return true;
        }
        return false;
    }

    public float getRunDistance() {
        return Math.round((playerB2D.getPosition().x - MapLoader.instance.getSpawnPoint().x) * 100
        - playerB2D.getSize().x - 40f) / 10.0f;
    }

}
