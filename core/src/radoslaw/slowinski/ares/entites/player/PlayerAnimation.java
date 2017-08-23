package radoslaw.slowinski.ares.entites.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.handlers.UserDataHandler;
import radoslaw.slowinski.ares.listeners.GameContactListener;

import static radoslaw.slowinski.ares.utils.Constant.PPM;

/**
 * Created by ares on 21/08/17.
 */
public class PlayerAnimation {
    private static final float spriteScaleX = 0.40f;
    private static final float spriteScaleY = 0.48f;
    private final float animationDelay = 1 / 16f;

    private TextureRegion currentPlayerTexture;
    private TextureRegion[] walkTextures;
    private TextureRegion jumpTexture;
    private TextureRegion standTexture;
    private int currentFrame;
    private float time;
    private PlayerBox2D playerB2D;

    public PlayerAnimation(PlayerBox2D playerBox2D) {
        this.playerB2D = playerBox2D;
        applyPlayerSkin(getPlayerSkin());
    }

    public void update(float deltaTime) {
        updatePlayerTexture(deltaTime);
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(
                currentPlayerTexture,
                getTextureX(), getTextureY(),
                getTextureWidth(), getTextureHeight());
        batch.end();
    }

    private void updatePlayerTexture(float deltaTime) {
        if (!GameContactListener.instance.isPlayerOnGround()) {
            currentPlayerTexture = jumpTexture;
        } else {
            handlePlayerWalkAnimation(deltaTime);
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
        if (playerB2D.isMoving())
            currentPlayerTexture = walkTextures[(currentFrame++) % walkTextures.length];
        else
            currentPlayerTexture = standTexture;
    }

    private void applyPlayerSkin(AssetHandler.AssetPlayerSkin skin) {
        walkTextures = skin.walk;
        jumpTexture = skin.jump;
        standTexture = skin.stand;
        currentPlayerTexture = standTexture;
    }

    private float getTextureX() {
        return playerB2D.getPosition().x * PPM - playerB2D.getSize().x * spriteScaleX / 2 + 2;
    }

    private float getTextureY() {
        return playerB2D.getPosition().y * PPM - playerB2D.getSize().x * spriteScaleY / 2 - 5;
    }

    private float getTextureWidth() {
        return playerB2D.getSize().x * spriteScaleX;
    }

    private float getTextureHeight() {
        return playerB2D.getSize().y * spriteScaleY;
    }

    private AssetHandler.AssetPlayerSkin getPlayerSkin() {
        return AssetHandler.instance.playerSkin.get(
                UserDataHandler.instance.getPlayerSkin());
    }


}
