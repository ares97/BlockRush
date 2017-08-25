package radoslaw.slowinski.ares.controls;

import radoslaw.slowinski.ares.utils.BadgeTypes;
import radoslaw.slowinski.ares.utils.Constant;
import radoslaw.slowinski.ares.utils.MapLevels;

/**
 * Created by ares on 25/08/17.
 */
public class BadgeIcon {
    private int coins;
    private boolean finished;
    private BadgeTypes badge;
    private MapLevels mapLevel;

    public BadgeIcon(MapLevels mapLevel, int badgeTypeOrdinal) {
        this.mapLevel = mapLevel;
        badge = BadgeTypes.values()[badgeTypeOrdinal];
    }

    private boolean isBadgeImproved() {
        return badge.ordinal() < getUpdatedBadge().ordinal(); // badges are organized from worst to best
    }

    private void applyNewBadge() {
        badge = getUpdatedBadge();
    }

    private BadgeTypes getUpdatedBadge() {
        if (!finished) {
            return BadgeTypes.NONE;
        } else {
            if (coins <= 0.4 * mapLevel.getCoinsOnMap()){
                return BadgeTypes.BRONZE;
            }
            else if(coins < mapLevel.getCoinsOnMap()){
                return BadgeTypes.SILVER;
            }
            else {
                return BadgeTypes.GOLD;
            }
        }
    }

    public void setScore(int coins, boolean finished) {
        this.coins = coins / Constant.REWARD_FOR_COIN;
        this.finished = finished;
        if(isBadgeImproved()){
            applyNewBadge();
        }
    }

    public BadgeTypes getBadge() {
        return badge;
    }

    public MapLevels getMapLevel() {
        return mapLevel;
    }
}
