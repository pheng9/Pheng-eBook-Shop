package thailand.cns.pheng.phengebookshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by User on 9/7/2559.
 */
public class MyAlert {

    public void myDialog(Context context, String strTitle, String strMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context); // Create Constance or Object
        builder.setCancelable(false);
        builder.setIcon(R.drawable.nobita48);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }   // void คือเมธอดไม่ return ค่ากลับ
        // context คือ การต่อท่อ
        // Class object = new Class(); คือ กฎการสืบทอด

}   // Main Class
