package uk.ac.lancs.tool;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;
import android.widget.TextView;

/**
 * This method is used to make the toast at the center of the page.
 * @author Miao Cai
 * @since 7/5/2019
 */
public class ToastCenterText{

    private TextView v;
    private Toast toast;

    public ToastCenterText() {
    }

    public void displayToast(Context context, String hint){
        toast = Toast.makeText(context,hint,Toast.LENGTH_SHORT);
        v = toast.getView().findViewById(android.R.id.message);
        v.setGravity(Gravity.CENTER);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

}