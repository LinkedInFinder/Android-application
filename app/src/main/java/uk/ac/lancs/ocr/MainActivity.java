package uk.ac.lancs.ocr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import uk.ac.lancs.tool.ToastCenterText;

/**
 * This class is used to render the homepage of the LinkedInFinder app.
 * The user could choose the total number of screenshots and also do some querying.
 * @author Miao Cai
 * @since 7/5/2019
 */
public class MainActivity extends AppCompatActivity {

    private ToastCenterText text = new ToastCenterText();
    private static final int CODE_PERMISSION_REQUEST = 100;
    private EditText edtTimes;

    /**
     * This method implements the super method.
     * The main purpose of this method is to show the homepage.
     * @param savedInstanceState current state of the instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTimes = findViewById(R.id.edt_time);
        Log.e("start","start");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Apply for write external storage permission
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, CODE_PERMISSION_REQUEST);
        }
    }

    /**
     * This method is used to receive the request results.
     * @param requestCode the responding code for the request.
     * @param permissions the permission type.
     * @param grantResults the granted results.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case CODE_PERMISSION_REQUEST:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Do nothing.
                }
                break;
            default:
                break;

        }
    }

    /**
     * This method is used to start the screenshot-program, which has been written by Bowen Yi.
     * @param view the view which could respond to the start request.
     */
    public void start(View view) {
        String count = edtTimes.getText().toString();
        if(count.equals("")) text.displayToast(this, "You input is invalid!");
        else {
            Intent intent = new Intent(this, LoadingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            text.displayToast(this, "Start " + count + " times!");
            startActivity(intent);
            // From now on, the android app will start the screenshot.
        }

    }

    /**
     * This method is used to go to the query activity.
     * @param view the view which could respond to the query request.
     */
    public void query(View view) {
        Intent intent = new Intent(this, QueryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * This method is used to overwrite the onBackPressed method.
     * It can achieve the android's go back function.
     */
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
    }
}
