package radoslaw.slowinski.ares.utils;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by ares on 17/08/17.
 */
public enum BlockTypes {
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE);


    private Color blockColor;
    BlockTypes(Color blockColor) {
        this.blockColor = blockColor;
    }

    public Color getColor(){
        return blockColor;
    }
}
