package radoslaw.slowinski.ares.game;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import radoslaw.slowinski.ares.utils.MapLoader;
import radoslaw.slowinski.ares.screens.AbstractGameScreen;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 12.08.17.
 */
public class WorldRenderer extends AbstractGameScreen {

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
        MapLoader.instance.renderMap(mainCam);
        worldController.renderFollowingCamera(mainCam);
        worldController.renderPlayer(batch);
        batch.setProjectionMatrix(mainCam.combined);


        if (Constant.DEBUG_MODE)
            b2dDebugRender.render(worldController.b2dWorld, b2dCam.combined);
    }
}
