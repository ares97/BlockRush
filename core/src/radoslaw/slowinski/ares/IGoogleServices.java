package radoslaw.slowinski.ares;

/**
 * Created by ares on 28/08/17.
 */
public interface IGoogleServices
{
    public void signIn();
    public void signOut();
    public void rateGame();
    public void submitScore(float score);
    public void showScores();
    public boolean isSignedIn();
}