package com.nawasena.dev.paycash.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.LocalDataHandler;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.SdkCoreFlowBuilder;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.UserAddress;
import com.midtrans.sdk.corekit.models.UserDetail;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.nawasena.dev.paycash.R;
import com.nawasena.dev.paycash.Retrofit.Constant;

import java.util.ArrayList;

public class TopUpActivity extends AppCompatActivity implements TransactionFinishedCallback {

    EditText tvAmountMoney;
    Button btnSubmit;
    TextView ceban, jicap, gocap, cepek;
    FirebaseAuth mAuthFirebase;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        castItem();
    }

    public void castItem() {
        tvAmountMoney = findViewById(R.id.etAmountMoney);
        btnSubmit = findViewById(R.id.btnTopUp);
        ceban = findViewById(R.id.ceban);
        jicap = findViewById(R.id.duapuluh);
        gocap = findViewById(R.id.goban);
        cepek = findViewById(R.id.cepek);

        ceban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isi = "10.000";
                tvAmountMoney.setText(isi);
            }
        });

        jicap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jicap = "20.000";
                tvAmountMoney.setText(jicap);
            }
        });

        gocap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gocap = "50.000";
                tvAmountMoney.setText(gocap);
            }
        });

        cepek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cepek = "100.000";
                tvAmountMoney.setText(cepek);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMidtrans();
                getAmountTopUp();
            }
        });


    }

    private void getAmountTopUp() {
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
        ItemDetails itemDetails1 = new ItemDetails("BP1", 50000, 1, "Top Up MeCash");

        ArrayList<ItemDetails> itemDetailsList = new ArrayList<>();
        itemDetailsList.add(itemDetails1);

        transactionRequest.setItemDetails(itemDetailsList);
        MidtransSDK.getInstance().setTransactionRequest(transactionRequest);
    }

    public void initMidtrans() {
        SdkCoreFlowBuilder.init()
                .setClientKey(Constant.KEY_AUTH)
                .setContext(this)
                .setMerchantBaseUrl(Constant.BASE_URL)
                .enableLog(true)
                .buildSDK();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTransactionFinished(TransactionResult transactionResult) {
        if (transactionResult != null) {
            switch (transactionResult.getStatus()) {
                case TransactionResult.STATUS_SUCCESS:
                    Toast.makeText(getApplicationContext(), "Transaksi Berhasil", Toast.LENGTH_LONG).show();
                case TransactionResult.STATUS_PENDING:
                    Toast.makeText(getApplicationContext(),"Transaksi Sedang Diproses",Toast.LENGTH_LONG).show();
                case TransactionResult.STATUS_FAILED:
                    Toast.makeText(getApplicationContext(),"Transaksi Gagal",Toast.LENGTH_SHORT).show();
            }
            Log.w("tamvan", transactionResult.getResponse().getStatusMessage());
        }
    }

    public void TopUpRequester(){
        mAuthFirebase = FirebaseAuth.getInstance();
    }



}
