package radoslaw.slowinski.ares.utils;

/**
 * Created by ares on 29/08/17.
 */
public enum PromoCodes {
    WYKOP("wykop"),
    DEVRANT("devrant"),
    REDDIT("reddit"),
    PEPPER("pepper"),
    ANDROID("android"), HALLOWEEN("hallowen2017");

    private String code;
    PromoCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
