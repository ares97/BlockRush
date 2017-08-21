package radoslaw.slowinski.ares.utils;

/**
 * Created by ares on 21/08/17.
 */
public enum MapTitles {
    HELP("help"),
    FREE_RUN("level0");

    private String title;
    MapTitles(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
