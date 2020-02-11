package com.nawasena.dev.paycash.Fragment.profile;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nawasena.dev.paycash.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

//import com.example.paycash.Fragment.R;

public class ProfileFragment extends Fragment {
    private Uri pickedImgUri = null;

    TextView name, email, google;
    FirebaseAuth firebaseAuth;
    ImageView imgView;
    FirebaseUser firebaseUser;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View views = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        name = (TextView) views.findViewById(R.id.txName);
        email = (TextView) views.findViewById(R.id.txtEmail);
        imgView = (ImageView) views.findViewById(R.id.imgProfile);


        if (imgView != null) {
            name.setText(firebaseUser.getDisplayName());
            email.setText(firebaseUser.getEmail());
            Picasso.get()
                    .load(firebaseUser.getPhotoUrl())
                    .into(imgView);

        } else {
            String nama = "users";
            name.setText(nama);

        }
        return views;
    }


}