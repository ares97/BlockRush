package radoslaw.slowinski.ares.handlers;

import radoslaw.slowinski.ares.utils.GamePreferences;
import radoslaw.slowinski.ares.utils.SkinTypes;

import java.util.HashMap;

/**
 * Created by ares on 20/08/17.
 */
public class UserDataHandler {
    public static final UserDataHandler instance = new UserDataHandler();
    private SkinTypes playerSkin;
    private HashMap<SkinTypes, Boolean> boughtPlayer;

    private UserDataHandler() {
        updateBoughtPlayers();
        playerSkin = applyPlayerSkin();
    }

    private void updateBoughtPlayers() {
        boughtPlayer = GamePreferences.instance.getBoughtPlayers();
        boughtPlayer.put(SkinTypes.GIRL, true);
        boughtPlayer.put(SkinTypes.BOY, true);
    }

    private SkinTypes applyPlayerSkin() {
        String skinName = GamePreferences.instance.getPlayerSkinName();
        for (SkinTypes skin : SkinTypes.values()) {
            if (skin.getSkinName().equals(skinName)) {
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

    public HashMap<SkinTypes, Boolean> getBoughtPlayer() {
        return boughtPlayer;
    }

    public void setPlayerAsBought(SkinTypes skinType) {
        boughtPlayer.put(skinType, true);
    }

}
