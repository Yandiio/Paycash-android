package com.nawasena.dev.paycash.Activities.ToS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paycash.R;

public class PasscodeActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageButton btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        btnOk = findViewById(R.id.btnContinue_cp);
        btnOk.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent nw = new Intent(this,TermsActivity.class);
        startActivity(nw);
        finish();
    }
}
