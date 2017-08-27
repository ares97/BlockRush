package radoslaw.slowinski.ares;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.*;


public class AndroidLauncher extends AndroidApplication implements AdHandler {
	private static final String APP_ID = "ca-app-pub-5239160686197539~7939588790";
	private static final String BANNER_ID = "ca-app-pub-5239160686197539/6800254170";
	private AdView adView;
	private RelativeLayout layout;
	private Handler handler;
	private final int SHOW = 1;
	private final int HIDE = 0;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		layout = new RelativeLayout(this);
		layout.setGravity(Gravity.BOTTOM);
		View gameView = initializeForView(new RushGame(new AdHandler() {
			@Override
			public void showBanner(boolean show) {
				handler.sendEmptyMessage(show ? SHOW : HIDE);
			}
		}),config);
		layout.addView(gameView);
		initAdHandler();
		MobileAds.initialize(this,APP_ID);
		createBanner();
	}

	private void initAdHandler() {
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what){
					case SHOW:
						adView.setVisibility(View.VISIBLE);
						break;
					case HIDE:
						adView.setVisibility(View.GONE);
						break;
				}
			}
		};
	}

	private void createBanner() {
		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId(BANNER_ID);

		AdRequest.Builder builder = new AdRequest.Builder();

		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		layout.addView(adView,adParams);
		adView.loadAd(builder.build());
		setContentView(layout);

		adView.setAdListener(new AdListener(){
			@Override
			public void onAdLoaded() {
				int visibility = adView.getVisibility();
				adView.setVisibility(AdView.GONE);
				adView.setVisibility(visibility);
			}
		});
	}

	@Override
	public void showBanner(boolean show) {
		handler.sendEmptyMessage(show ? SHOW : HIDE);
	}
}
