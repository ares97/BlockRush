package radoslaw.slowinski.ares;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.google.android.gms.ads.*;
import radoslaw.slowinski.ares.handlers.ScoreHandler;

public class AndroidLauncher extends AndroidApplication implements AdHandler {
    private static final String APP_ID = "ca-app-pub-5239160686197539~7939588790";
    private static final String BANNER_ID = "ca-app-pub-5239160686197539/6800254170";
    private AdView adView;
    private RelativeLayout layout;
    private Handler handler;
    final int SHOW_STATIC_INTERS = 3;
    final int SHOW_VID_REW = 2;
    final int SHOW_BANNER = 1;
    final int HIDE_BANNER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initChartboost();

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        layout = new RelativeLayout(this);
        layout.setGravity(Gravity.BOTTOM);
        View gameView = initializeForView(new RushGame(new AdHandler() {
            @Override
            public void showAd(int ad) {
                handler.sendEmptyMessage(ad);
            }
        }), config);
        layout.addView(gameView);
        initAdHandler();
        MobileAds.initialize(this, APP_ID);
        createBanner();

        Chartboost.setDelegate(new ChartboostDelegate() {
            @Override
            public void didCompleteRewardedVideo(String location, int reward) {
                super.didCompleteRewardedVideo(location, reward);
                ScoreHandler.instance.addCoins(reward);
            }
        });
    }

    private void initChartboost() {
        Chartboost.startWithAppId(this, "599d6edd04b0164f95417b68", "af3260c693144a1566b1cb1a0bd7b1d885ec07d7");
        Chartboost.onCreate(this);
    }

    private void initAdHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SHOW_BANNER:
                        adView.setVisibility(View.VISIBLE);
                        break;
                    case HIDE_BANNER:
                        adView.setVisibility(View.GONE);
                        break;
                    case SHOW_STATIC_INTERS:
                        showStaticIntersAd();
                        break;
                    case SHOW_VID_REW:
                        showRewardedVideo();
                        break;
                }
            }
        };
    }

    private void showRewardedVideo() {
        if(Chartboost.hasRewardedVideo(CBLocation.LOCATION_DEFAULT)){
            Chartboost.showRewardedVideo(CBLocation.LOCATION_DEFAULT);
        }
        else {
            Chartboost.cacheRewardedVideo(CBLocation.LOCATION_DEFAULT);
        }
    }

    private void showStaticIntersAd() {
        if (Chartboost.hasInterstitial(CBLocation.LOCATION_DEFAULT)) {
            Chartboost.showInterstitial(CBLocation.LOCATION_DEFAULT);
        }
        else {
            Chartboost.cacheInterstitial(CBLocation.LOCATION_DEFAULT);
        }
    }

    private void createBanner() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(BANNER_ID);

        AdRequest.Builder builder = new AdRequest.Builder();

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        layout.addView(adView, adParams);
        adView.loadAd(builder.build());
        setContentView(layout);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                int visibility = adView.getVisibility();
                adView.setVisibility(AdView.GONE);
                adView.setVisibility(visibility);
            }
        });
    }

    @Override
    public void showAd(int ad) {
        handler.sendEmptyMessage(ad);
    }

    @Override
    public void onStart() {
        super.onStart();
        Chartboost.onStart(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Chartboost.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Chartboost.onPause(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Chartboost.onStop(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Chartboost.onDestroy(this);
    }

    @Override
    public void onBackPressed() {
        // If an interstitial is on screen, close it.
        if (Chartboost.onBackPressed())
            return;
        else
            super.onBackPressed();
    }
}
