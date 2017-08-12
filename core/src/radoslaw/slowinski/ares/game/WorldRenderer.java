package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;

/**
 * Created by ares on 12.08.17.
 */
public class WorldRenderer extends AbstractGameScreen {

    private static final boolean B2D_DEBUG = true;
    private WorldController worldController;
    private Box2DDebugRenderer b2dDebugRender;


    public WorldRenderer(WorldController worldController) {
        super();
        this.worldController = worldController;
    }

    @Override
    protected void init() {
        b2dDebugRender = new Box2DDebugRenderer();
    }

    @Override
    public void render(float dt) {
        super.render(dt);

        worldController.update(dt);

        if (B2D_DEBUG)
            b2dDebugRender.render(worldController.b2dWorld, b2dCam.combined);
    }
}
