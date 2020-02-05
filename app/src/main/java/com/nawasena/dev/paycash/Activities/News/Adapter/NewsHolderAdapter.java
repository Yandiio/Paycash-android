package com.nawasena.dev.paycash.Activities.News.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paycash.Activities.News.DetailNews;
import com.example.paycash.Activities.News.Models.Articles;
import com.example.paycash.R;
import com.nawasena.dev.paycash.Activities.News.Models.Articles;
import com.nawasena.dev.paycash.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsHolderAdapter extends RecyclerView.Adapter<NewsHolderAdapter.ViewHolder> {

    Context mContext;
    List<Articles> articles;

    public NewsHolderAdapter(Context mContext, List<Articles> articles) {
        this.mContext = mContext;
        this.articles = articles;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Articles a = articles.get(position);
        holder.textView.setText(a.getTitle());
        holder.textSource.setText(a.getSource().getName());
        holder.textDate.setText("\u2022" + dateTime(a.getPublishedAt()));

        String imgUrl = a.getUrlToImage();

        Picasso.get().load(imgUrl).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailNews.class);
                intent.putExtra("title", a.getTitle());
                intent.putExtra("source", a.getSource().getName());
                intent.putExtra("time", dateTime(a.getPublishedAt()));
                intent.putExtra("desc", a.getDescription());
                intent.putExtra("url", a.getUrl());

                mContext.startActivity(intent);
            }
        });
}

    public String dateTime(String t) {
        PrettyTime pretty = new PrettyTime(new Locale(getCountry()));
        String time = null;

        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:", Locale.ENGLISH);
            Date dt = date.parse(t);
            time = pretty.format(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }

    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView imageView;
    TextView textView, textDate, textSource;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardViewheadline);
        imageView = itemView.findViewById(R.id.imageHeadline);
        textDate = itemView.findViewById(R.id.tvDateHeadline);
        textSource = itemView.findViewById(R.id.tvSourceHeadline);
        textView = itemView.findViewById(R.id.titleHeadline);
    }


}
}
