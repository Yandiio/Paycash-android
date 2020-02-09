package com.nawasena.dev.paycash.Activities.Authentication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nawasena.dev.paycash.Activities.PhoneNumberActivity;
import com.nawasena.dev.paycash.MainFragment;
import com.nawasena.dev.paycash.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegist;
    CircleImageView Image;
    static int precCode = 1;
    static int REQUESCODE =1;
    Uri pickImgUri;
    private FirebaseAuth mAuth;

    private EditText userEmail,userPassword,userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.editUsernameRegist);
        userEmail = (EditText) findViewById(R.id.editEmailRegist);
        userPassword = (EditText) findViewById(R.id.editPasswordRegist);


        btnRegist = (Button) findViewById(R.id.sign_up_button);

        mAuth  = FirebaseAuth.getInstance();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString();
                final String name = userName.getText().toString();
                final String pass = userPassword.getText().toString();

                if(email.isEmpty() || name.isEmpty() || pass.isEmpty()){

                    showMessage("Please Fill The blank form for register");

                }else{
                    createAccount(email,name,pass);
                }
            }
        });

        Image = findViewById(R.id.profile_img_up);
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= 22){

                    checkAndRequestPermission();
                }else{

                    openGallery();
                }
            }
        });
    }

    private void createAccount(String email, final String name, String pass) {

        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    showMessage("Account Created");
                    updateUserInfo(name,pickImgUri,mAuth.getCurrentUser());

                }else{
                    showMessage("account creation failed" + task.getException().getMessage());
                }
            }
        });


    }

    private void updateUserInfo(final String name, Uri pickImgUri, final FirebaseUser currentUser) {

        StorageReference storage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = storage.child(pickImgUri.getLastPathSegment());
        imageFilePath.putFile(pickImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest updateUser = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(updateUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    showMessage("Registration Complete");
                                    updateUI();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void updateUI() {
        Intent in = new Intent(getApplicationContext(), MainFragment.class);
        startActivity(in);
        finish();
    }


    private void showMessage(String Message) {
        Toast.makeText(getApplicationContext(),Message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        Intent j = new Intent(this, PhoneNumberActivity.class);
        startActivity(j);
    }


   

    public void openGallery() {
        Intent tent = new Intent(Intent.ACTION_GET_CONTENT);
        tent.setType("image/*");

        startActivityForResult(tent,REQUESCODE);
    }

    private void checkAndRequestPermission(){
        if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){

                Toast.makeText(RegisterActivity.this,"  Please Accept Your Required Permission", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions( RegisterActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},precCode);
            }
        else{
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null){

            pickImgUri = data.getData();
            Image.setImageURI(pickImgUri);
        }
    }
}