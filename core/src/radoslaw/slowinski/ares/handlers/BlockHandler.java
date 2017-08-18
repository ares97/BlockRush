package radoslaw.slowinski.ares.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import radoslaw.slowinski.ares.utils.BlockTypes;

/**
 * Created by ares on 17/08/17.
 */
public class BlockHandler {
    public static final BlockHandler instance = new BlockHandler();
    private TextureRegion currentBlock;

    private BlockHandler() {
    }

    public TextureRegion getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(BlockTypes type) {
        if (type.equals(BlockTypes.RED))
            currentBlock = AssetHandler.instance.iconBlocks.red;
        if (type.equals(BlockTypes.GREEN))
            currentBlock = AssetHandler.instance.iconBlocks.green;
        if (type.equals(BlockTypes.BLUE))
            currentBlock = AssetHandler.instance.iconBlocks.blue;
    }
}

