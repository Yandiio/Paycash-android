package com.nawasena.dev.paycash.Activities.ToS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.nawasena.dev.paycash.Activities.Authentication.RegisterActivity;
import com.nawasena.dev.paycash.MainFragment;
import com.nawasena.dev.paycash.R;

public class TermsActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageButton btnAccept,btnDecline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        btnAccept = findViewById(R.id.btnAgree);
        btnAccept.setOnClickListener(this);
        btnDecline = findViewById(R.id.btnDecline);
        btnDecline.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgree :
                Intent acc = new Intent(this, MainFragment.class);
                startActivity(acc);
            case R.id.btnDecline:
                Intent dec = new Intent(this, RegisterActivity.class);
                startActivity(dec);
        }
    }
}

