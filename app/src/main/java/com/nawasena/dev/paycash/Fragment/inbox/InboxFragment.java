package com.nawasena.dev.paycash.Fragment.inbox;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

//import com.example.paycash.Fragment.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nawasena.dev.paycash.Fragment.inbox.Adapter.InboxAdapter;
import com.nawasena.dev.paycash.Fragment.inbox.Models.Inbox;
import com.nawasena.dev.paycash.R;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private InboxAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Inbox> dataInbox;
    DatabaseReference reference;
    View v;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        loadData();
    }


    private void loadData() {
        recyclerView = (RecyclerView) v.findViewById(R.id.kotak_masuk_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataInbox = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference().child("inbox");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Inbox inbox = snapshot.getValue(Inbox.class);
                    dataInbox.add(inbox);
                }
                adapter = new InboxAdapter(getActivity(),dataInbox);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), "Data Gagal Dimuat", Toast.LENGTH_LONG).show();
                Log.e("MyListActivity", databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });
    }
}
