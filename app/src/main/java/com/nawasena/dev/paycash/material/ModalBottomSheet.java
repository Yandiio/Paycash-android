package com.nawasena.dev.paycash.material;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.paycash.Activities.SelectMonthActivity;
import com.example.paycash.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ModalBottomSheet extends BottomSheetDialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modal_bottom_sheet, container, false);
        final ImageView circleImageView = (ImageView) v.findViewById(R.id.payMenu);
        final ImageView circleImageView1 = (ImageView) v.findViewById(R.id.sendMenu);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), SelectMonthActivity.class);
                startActivity(in);
            }
        });

        circleImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent(getActivity())
            }
        });


        return v;


    }

}
