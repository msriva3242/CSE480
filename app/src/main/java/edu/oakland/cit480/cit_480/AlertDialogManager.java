package edu.oakland.cit480.cit_480;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogManager {

     //Function to display simple Alert Dialog - outlined as follows:
     //@param context - application context
     //@param title - alert dialog title
     //@param message - alert message
     //@param status - success/failure (used to set icon)
     //               - pass null if you don't want icon

    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null)

            //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
