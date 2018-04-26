package chumrat.arit.srru.ac.th.showaritsrru.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import chumrat.arit.srru.ac.th.showaritsrru.R;

public class MyAlert {

    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void normalDialog(String titleSring, String massageSring) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_error);
        builder.setTitle(titleSring);
        builder.setMessage(massageSring);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }


}
