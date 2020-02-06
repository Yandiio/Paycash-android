package com.nawasena.dev.paycash.Activities.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.nawasena.dev.paycash.MainFragment;
import com.nawasena.dev.paycash.R;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView tvRegist;
    public EditText editMail, editPassw;
    Button btnLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvRegist = (TextView) findViewById(R.id.tv_signup);
        btnLogin = (Button) findViewById(R.id.tombolLogin);

        mAuth = FirebaseAuth.getInstance();

        editPassw = (EditText) findViewById(R.id.editPass);
        editMail = (EditText) findViewById(R.id.editEmail);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(signInButton.SIZE_STANDARD);

        tvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(g);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail = editMail.getText().toString();
                final String password = editPassw.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                updateUI();
                            } else {
                                showMessage(task.getException().getMessage());
                                btnLogin.setVisibility(View.VISIBLE);

                            }


                        }
                    });
                }
            }
        });
    }


    private void updateUI() {
        Intent in = new Intent(getApplicationContext(), MainFragment.class);
        startActivity(in);
        finish();
    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }


}



