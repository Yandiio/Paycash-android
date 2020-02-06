package com.nawasena.dev.paycash.Fragment.inbox.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paycash.Fragment.inbox.Models.Inbox;
import com.example.paycash.R;
import com.nawasena.dev.paycash.Fragment.inbox.Models.Inbox;

import java.util.ArrayList;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxHolder> {

    Context mContext;
    ArrayList<Inbox> inboxList;

    public InboxAdapter(Context mContext, ArrayList<Inbox> inboxList) {
        this.mContext = mContext;
        this.inboxList = inboxList;
    }


    @NonNull
    @Override
    public InboxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbox_list,parent,false);
        return new InboxHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxHolder holder, int position) {
        Inbox inbox = inboxList.get(position);
        holder.title.setText(inbox.getHistory_name());
        holder.desc.setText(inbox.getDesc());
        holder.date.setText(inbox.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return inboxList.size();
    }

    public class InboxHolder extends RecyclerView.ViewHolder {
        TextView title,desc,date,sender;

        public InboxHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txSubject);
            desc =  itemView.findViewById(R.id.txDesk);
            date = itemView.findViewById(R.id.txTime);
        }
    }
}
