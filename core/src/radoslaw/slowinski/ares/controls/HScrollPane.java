package radoslaw.slowinski.ares.controls;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import radoslaw.slowinski.ares.handlers.AssetHandler;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 21/08/17.
 */
public class HScrollPane extends ScrollPane {
    private ScrollPaneStyle style;

    public HScrollPane(Actor actor){
        super(actor);
        init();
    }

    private void init() {
        style = new ScrollPaneStyle();
        style.hScrollKnob = AssetHandler.instance.skin.menuUI.getDrawable("knob_05");
        setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);

    }
}
