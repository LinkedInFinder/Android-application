package uk.ac.lancs.ocr;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import uk.ac.lancs.database.MySQLiteOpenHelper;

/**
 * This method is used to achieve the query operation.
 * @author Miao Cai
 * @since 7/5/2019
 */
public class QueryActivity extends AppCompatActivity {

    /* Make sure the location of the required database*/
    public static final String FILE_DIR = "/sdcard/Download/persons.db";
    private ArrayList<String> infoList;
    private EditText edtName;
    private TextView name;
    private TextView headline;
    private TextView region;
    private TextView exp_1;
    private TextView exp_1_org;
    private TextView exp_2;
    private TextView exp_2_org;
    private TextView exp_3;
    private TextView exp_3_org;
    private TextView edu_1;
    private TextView edu_1_major;
    private TextView edu_2;
    private TextView edu_2_major;
    private TextView exp_hint;
    private TextView edu_hint;
    private MySQLiteOpenHelper helper;

    /**
     * The main purpose of this method is to show the query page.
     * @param savedInstanceState current state of the instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        edtName = findViewById(R.id.edt_set_name);
        name = findViewById(R.id.txt_name);
        headline = findViewById(R.id.txt_headline);
        region = findViewById(R.id.txt_region);
        exp_hint = findViewById(R.id.exp_hint);
        edu_hint = findViewById(R.id.edu_hint);
        exp_1 = findViewById(R.id.txt_exp_1);
        exp_1_org = findViewById(R.id.txt_exp_1_org);
        exp_2 = findViewById(R.id.txt_exp_2);
        exp_2_org = findViewById(R.id.txt_exp_2_org);
        exp_3 = findViewById(R.id.txt_exp_3);
        exp_3_org = findViewById(R.id.txt_exp_3_org);
        edu_1 = findViewById(R.id.txt_edu_1);
        edu_1_major = findViewById(R.id.txt_edu_1_major);
        edu_2 = findViewById(R.id.txt_edu_2);
        edu_2_major = findViewById(R.id.txt_edu_2_major);
    }

    /**
     * Before start query operation, all widgets should be cleared.
     */
    public void initialization(){
        name.setText("");
        headline.setText("");
        region.setText("");
        exp_hint.setText("");
        edu_hint.setText("");
        exp_1.setText("");
        exp_1_org.setText("");
        exp_2.setText("");
        exp_2_org.setText("");
        exp_3.setText("");
        exp_3_org.setText("");
        edu_1.setText("");
        edu_1_major.setText("");
        edu_2.setText("");
        edu_2_major.setText("");
        exp_2.setHeight(54);
        exp_2_org.setHeight(54);
        exp_3.setHeight(54);
        exp_3_org.setHeight(54);
        edu_2.setHeight(54);
        edu_2_major.setHeight(54);
    }

    /**
     * This method could achieve query operation.
     * @param view the view which could respond to the query request.
     */
    public void startQuery(View view) {
        //First, we need to initialize all widgets.
        initialization();
        helper = new MySQLiteOpenHelper(this,FILE_DIR);
        SQLiteDatabase database = helper.getReadableDatabase();
        infoList = helper.queryInfo(database, edtName.getText().toString(),
                "SELECT * FROM person WHERE name = ?");
        try {
            name.setText(infoList.get(1));
            headline.setText(infoList.get(2));
            region.setText(infoList.get(3));
            infoList = helper.queryInfo(database, edtName.getText().toString(),
                    "SELECT title, org FROM experience INNER JOIN person ON experience.p_id = person.id WHERE person.name = ?");
            exp_hint.setText("Latest experience");
            /*
             * Check the result of experience and automatically change the layout.
             */
            if (infoList.size() == 0) exp_1.setText("No experience yet");
            else {
                exp_1.setText(infoList.get(0));
                exp_1_org.setText(infoList.get(1));
                if (infoList.size() >= 4) {
                    exp_2.setText(infoList.get(2));
                    exp_2_org.setText(infoList.get(3));
                    if (infoList.size() >= 6) {
                        exp_3.setText(infoList.get(4));
                        exp_3_org.setText(infoList.get(5));
                    } else {
                        exp_3.setHeight(0);
                        exp_3_org.setHeight(0);
                    }
                } else {
                    exp_2.setHeight(0);
                    exp_2_org.setHeight(0);
                    exp_3.setHeight(0);
                    exp_3_org.setHeight(0);
                }
            }
            infoList = helper.queryInfo(database, edtName.getText().toString(),
                    "SELECT school,major FROM education INNER JOIN person ON education.p_id = person.id WHERE person.name = ?");
            edu_hint.setText("Education");
            /*
             * Check the result of education and automatically change the layout.
             */
            if (infoList.size() == 0) edu_1.setText("No education yet");
            else {
                edu_1.setText(infoList.get(0));
                edu_1_major.setText(infoList.get(1));
                if (infoList.size() >= 4) {
                    edu_2.setText(infoList.get(2));
                    edu_2_major.setText(infoList.get(3));

                } else {
                    edu_2.setHeight(0);
                    edu_2_major.setHeight(0);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            headline.setText("LinkedIn hasn't recommended this person to you.");
        }
    }

    /**
     * This method is used to overwrite the onBackPressed method.
     * It can go back to the homepage page.
     */
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
        finish();
    }

    /**
     * This method is used to overwrite go back to the homepage page.
     */
    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
        finish();
    }
}
