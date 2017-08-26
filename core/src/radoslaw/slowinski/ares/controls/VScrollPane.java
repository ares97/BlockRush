package radoslaw.slowinski.ares.controls;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 26/08/17.
 */
public class VScrollPane extends ScrollPane{
    private ScrollPane.ScrollPaneStyle style;

    public VScrollPane(Actor actor) {
        super(actor);
        init();
    }

    private void init() {
        style = new ScrollPane.ScrollPaneStyle();
        style.vScrollKnob = AssetHandler.instance.skin.menuUI.getDrawable("knob_05");
        setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

    }
}
