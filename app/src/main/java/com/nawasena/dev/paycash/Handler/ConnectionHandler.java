package com.nawasena.dev.paycash.Handler;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.nawasena.dev.paycash.R;

public class ConnectionHandler {

    private Context context;

    public ConnectionHandler(){

    }

    public ConnectionHandler(Context context) {
        this.context = context;
    }

    public void setInternetAction() {
        final Dialog dialog = new Dialog(context, R.style.df_dialog_layout);
        dialog.setContentView(R.layout.connection_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public boolean getInternetStatus() {
        ConnectivityManager nt = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = nt.getActiveNetworkInfo();
        boolean isConnected =
                nf != null && nf.isConnectedOrConnecting();

        if (!isConnected) {
            setInternetAction();
        }
        return isConnected;
    }
}
