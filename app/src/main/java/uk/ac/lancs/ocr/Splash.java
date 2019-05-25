package uk.ac.lancs.ocr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * This class is used for splash.
 * When user open the app at first time, our app could show our logo and copyright.
 * @author Miao Cai
 * @since 7/5/2019
 */
public class Splash extends AppCompatActivity {
    /**
     * This method implements the super method.
     * The main purpose of this method is to show the splash.
     * The duration is 3 seconds.
     * @param savedInstanceState current state of the instance.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Splash.this.startActivity(new Intent(Splash.this, MainActivity.class));
                Splash.this.finish();
            }
        }, 3000);
    }
}
