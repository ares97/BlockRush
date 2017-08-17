package radoslaw.slowinski.ares.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import radoslaw.slowinski.ares.utils.Assets;

/**
 * Created by ares on 17/08/17.
 */
public class BlockHandler {
    private TextureRegion currentBlock;

    public static final BlockHandler instance = new BlockHandler();

    private BlockHandler() {
    }

    public void setCurrentBlock(BlockTypes type) {
        if (type.equals(BlockTypes.RED))
            currentBlock = Assets.instance.blocks.red;
        if (type.equals((BlockTypes.GREEN)))
            currentBlock = Assets.instance.blocks.green;
        if (type.equals(BlockTypes.BLUE))
            currentBlock = Assets.instance.blocks.blue;
    }

    public TextureRegion getCurrentBlock(){
        return currentBlock;
    }
}

