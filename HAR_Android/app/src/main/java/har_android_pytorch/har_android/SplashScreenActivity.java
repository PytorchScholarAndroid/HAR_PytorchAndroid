package har_android_pytorch.har_android;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView mBackground_GIF;
    private Context mContext = SplashScreenActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View mContentView = findViewById(R.id.splash_screen);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        mBackground_GIF = (ImageView) findViewById(R.id.splash_screen);
        loadBackgroundGIF();

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }, 2400);
    }

    private void loadBackgroundGIF(){
        GlideApp.with(mContext).load(R.drawable.background_gif).error(R.drawable.background_err).into(mBackground_GIF);
    }
}
