package com.nawasena.dev.paycash.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paycash.Activities.ToS.PasscodeActivity;
import com.example.paycash.R;


public class PhoneNumberActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        btnContinue = findViewById(R.id.btnContinue_pn);
        btnContinue.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnContinue_pn :
                Intent in = new Intent(this, PasscodeActivity.class);
                startActivity(in);
                finish();
        }
    }
}
