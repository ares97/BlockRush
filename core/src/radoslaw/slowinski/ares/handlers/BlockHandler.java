package radoslaw.slowinski.ares.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import radoslaw.slowinski.ares.utils.BlockTypes;

/**
 * Created by ares on 17/08/17.
 */
public class BlockHandler {
    public static final BlockHandler instance = new BlockHandler();
    private TextureRegion currentBlockTexture;
    private BlockTypes currentBlockType;
    private BlockHandler() {

    }

    public TextureRegion getCurrentBlockTexture() {
        return currentBlockTexture;
    }

    public void setCurrentBlockTexture(BlockTypes type) {
        if (type.equals(BlockTypes.RED)) {
            currentBlockTexture = AssetHandler.instance.iconBlocks.red;
        }
        if (type.equals(BlockTypes.GREEN)) {
            currentBlockTexture = AssetHandler.instance.iconBlocks.green;
        }
        if (type.equals(BlockTypes.BLUE)) {
            currentBlockTexture = AssetHandler.instance.iconBlocks.blue;
        }
        currentBlockType = type;
    }

    public BlockTypes getCurrentBlockType() {
        return currentBlockType;
    }
}

