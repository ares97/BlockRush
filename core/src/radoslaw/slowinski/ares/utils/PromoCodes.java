package radoslaw.slowinski.ares.utils;

/**
 * Created by ares on 29/08/17.
 */
public enum PromoCodes {
    WYKOP("wykop"),
    DEVRANT("devrant"),
    REDDIT("reddit"),
    PEPPER("pepper"),
    ANDROID("android"),
    HALLOWEEN("hallowen2017"),
    PROGRAMMERS4("4programmers"),
    ZELENT("pasjainformatyki"),
    JAVADEVMATT("javadevmatt"),
    LOL("leagueoflegends"),
    TIBIA("tibia"),
    MARGONEM("margonem"),
    CSGO("counterstrike"),
    CIABAS("ciabas"),
    GYM("wuka"),
    KFD("kaefde"),
    GIFT("gift");

    private String code;
    PromoCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
