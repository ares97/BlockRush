package radoslaw.slowinski.ares.handlers;

import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.SkinTypes;

/**
 * Created by ares on 20/08/17.
 */
public class UserDataHandler {
    public static final UserDataHandler instance = new UserDataHandler();
    private SkinTypes playerSkin;

    private UserDataHandler() {
        playerSkin = applyPlayerSkin();

    }

    private SkinTypes applyPlayerSkin() {
        String skinName = GamePreferences.instance.getPlayerSkinName();
        for(SkinTypes skin : SkinTypes.values()){
            if(skin.getSkinName().equals(skinName)){
                return skin;
            }
        }
        return SkinTypes.BOY;
    }


    public SkinTypes getPlayerSkin() {
        return playerSkin;
    }

    public void setPlayerSkin(SkinTypes skin) {
        playerSkin = skin;
    }
}
