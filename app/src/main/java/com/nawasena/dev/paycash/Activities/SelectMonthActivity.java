package com.nawasena.dev.paycash.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nawasena.dev.paycash.Activities.Checkout.CheckoutActivity;
import com.nawasena.dev.paycash.R;

import java.util.ArrayList;

public class SelectMonthActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    private static final String KEY_MONTH = "month";
    private static final String KEY_PRICE = "price";
    private CheckBox checkBox;
    private TextView textView;

    ArrayList<String> selection = new ArrayList<>();

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> newSelection = new ArrayList<String>();
        setContentView(R.layout.activity_select_month);


        View v = getLayoutInflater().inflate(R.layout.item_select_month, null);

        textView = (TextView) findViewById(R.id.textView);
        checkBox = (CheckBox) v.findViewById(R.id.month);


        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(SelectMonthActivity.this, CheckoutActivity.class);
                startActivity(in);
                finish();
            }
        });

    }

//    public static String getCheckboxValue(String bulan) {
//        CheckBox[] nameString = new CheckBox[]{array0, array1, array2, array3, array4};
//        for (int i = 0; i>=7;i++){
//            if(nameString[i].isChecked()){
//                bulan = nameString[i].getText().toString();
//            }
//        }
//        return bulan;
//    }

    public void setValue(View v) {
        String Harga = textView.getText().toString();
//        boolean Bulan = ((checkBox) getText());
    }

    public void selectedItems(View view){
        boolean checked = ((CheckBox) view).isSelected();
        switch (view.getId()){
            case R.id.month :
                if(checked){
                    selection.add("Januari");
                }
        }
    }
}
