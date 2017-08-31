package com.example.fabio.hummingbirdtest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by EUROCOM on 20/08/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ctx = this;
    }

    public void makeFailureDialogBox() {

        final Dialog dialog = new Dialog(ctx);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.ok_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.tvDescription);

        text.setText(getString(R.string.noConectivity));


        Button dialogButton = (Button) dialog.findViewById(R.id.btOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    public void makeSuccessDialogBox() {
        final Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.ok_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView text = (TextView) dialog.findViewById(R.id.tvDescription);
        text.setText(getString(R.string.operationSucceeded));

        Button dialogButton = (Button) dialog.findViewById(R.id.btOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void hideLoadingDialog() {

        if(dialog.isShowing())
        {
            dialog.hide();
        }

    }

    public void showLoadingDialog() {
        if(dialog == null) {
            dialog = new ProgressDialog(ctx);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
        }
        if(!dialog.isShowing()) {
            dialog.show();
        }
    }


    public void makeFailureToast() {
        Toast.makeText(this,getText(R.string.noConectivity),Toast.LENGTH_SHORT).show();
    }
}
