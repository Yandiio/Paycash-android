package com.nawasena.dev.paycash.Activities.Checkout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.LocalDataHandler;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.UserAddress;
import com.midtrans.sdk.corekit.models.UserDetail;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
import com.nawasena.dev.paycash.Activities.Barcode.BarcodeActivity;
import com.nawasena.dev.paycash.R;
import com.nawasena.dev.paycash.Retrofit.Constant;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity implements TransactionFinishedCallback {

    Button onClickchange;
    FirebaseUser mAuth;
    FirebaseDatabase firebaseDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        onClickchange = findViewById(R.id.nextOrder);

        onClickchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CheckoutActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_payment_method,null);
                final RelativeLayout relLay1 = (RelativeLayout) mView.findViewById(R.id.ewalletPay);
                final RelativeLayout relLay2 = (RelativeLayout) mView.findViewById(R.id.cashPay);

                relLay1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initMidtransSnap();
                        transactionRequester();
                        MidtransSDK.getInstance().startPaymentUiFlow(v.getContext());
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                    }
                });

                relLay2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(CheckoutActivity.this, BarcodeActivity.class);
                        startActivity(in);
                        finish();
                    }
                });


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void transactionRequester() {
        UserDetail userDetail = LocalDataHandler.readObject("user_details", UserDetail.class);
        if (userDetail == null) {
            userDetail = new UserDetail();
            userDetail.setUserFullName("Muhammad Yandi Fenanda");
            userDetail.setEmail("yandifenanda95@gmail.com");
            userDetail.setPhoneNumber("083821316090");
            userDetail.setUserId("yandiU2iP3w32");

            ArrayList<UserAddress> userAddresses = new ArrayList<>();
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress("Jalan Cikawao Dalam No. 3");
            userAddress.setCity("Bandung");
            userAddress.setAddressType(com.midtrans.sdk.corekit.core.Constants.ADDRESS_TYPE_BOTH);
            userAddress.setZipcode("12345");
            userAddress.setCountry("IDN");
            userAddresses.add(userAddress);
            userDetail.setUserAddresses(userAddresses);
            LocalDataHandler.saveObject("user_details", userDetail);
        }

        TransactionRequest transactionRequest = new TransactionRequest(System.currentTimeMillis() + "", 400000);
        ItemDetails itemDetails1 = new ItemDetails("BP1", 200000, 1, "SPP Bulan 1");
        ItemDetails itemDetails2 = new ItemDetails("BP2", 200000, 1, "SPP Bulan 2");


        ArrayList<ItemDetails> itemDetailsList = new ArrayList<>();
        itemDetailsList.add(itemDetails1);
        itemDetailsList.add(itemDetails2);

        transactionRequest.setItemDetails(itemDetailsList);
        MidtransSDK.getInstance().setTransactionRequest(transactionRequest);


    }

    @Override
    public void onTransactionFinished(TransactionResult transactionResult) {
        if (transactionResult.getResponse() != null) {
            switch (transactionResult.getStatus()) {
                case TransactionResult.STATUS_SUCCESS:
                    Toast.makeText(this, "Success transaction", Toast.LENGTH_LONG).show();
                case TransactionResult.STATUS_PENDING:
                    Toast.makeText(this, "Pending transaction", Toast.LENGTH_LONG).show();
                case TransactionResult.STATUS_FAILED:
                    Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
            }
            Log.w("tamvan", transactionResult.getResponse().getStatusMessage());
        }
    }

    public void initMidtransSnap(){
        SdkUIFlowBuilder.init()
                .setClientKey(Constant.KEY_AUTH)
                .setContext(this)
                .setMerchantBaseUrl(Constant.BASE_URL)
                .enableLog(true)
                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
                .buildSDK();
    }

}
