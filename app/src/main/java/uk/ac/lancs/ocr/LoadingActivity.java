package uk.ac.lancs.ocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import com.github.ybq.android.spinkit.style.FadingCircle;

/**
 * This class is used to render the loading page of the LinkedInFinder app.
 * The user could wait a moment and our app will turn to the query page automatically.
 * @author Miao Cai
 * @since 7/5/2019
 */
public class LoadingActivity extends AppCompatActivity {

    /**
     * This method implements the super method.
     * The main purpose of this method is to show the query page.
     * @param savedInstanceState current state of the instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ProgressBar progressBar = findViewById(R.id.progress);
        FadingCircle circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);
    }

    /**
     * This method is used to overwrite the onBackPressed method.
     * It can go back to the query page.
     */
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, QueryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
        finish();
    }
}
