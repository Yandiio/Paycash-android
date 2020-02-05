package com.nawasena.dev.paycash.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paycash.Activities.Authentication.LoginActivity;
import com.example.paycash.R;

public class SplashScreenActivity extends AppCompatActivity {

    public int SET_TIMER = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nt = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(nt);
                finish();
            }
        },SET_TIMER);
    }
}
