package com.nawasena.dev.paycash.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nawasena.dev.paycash.R;

public class TopUpActivity extends AppCompatActivity {

    EditText tvAmountMoney;
    Button btnSubmit;
    TextView ceban, jicap, gocap, cepek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        castItem();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

            }
        });
    }

}
