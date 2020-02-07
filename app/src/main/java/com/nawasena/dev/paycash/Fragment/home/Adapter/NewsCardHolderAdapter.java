package com.nawasena.dev.paycash.Fragment.home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.nawasena.dev.paycash.Activities.News.DetailNews;
import com.nawasena.dev.paycash.Activities.News.Models.Articles;
import com.nawasena.dev.paycash.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsCardHolderAdapter extends RecyclerView.Adapter<NewsCardHolderAdapter.ViewHolder> {

    Context mContext;
    List<Articles> articles;

    public NewsCardHolderAdapter(Context mContext, List<Articles> articles) {
        this.mContext = mContext;
        this.articles = articles;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_list,parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Articles a =  articles.get(position);
        holder.textView.setText(a.getTitle());
        holder.textSource.setText(a.getSource().getName());

        String imgUrl = a.getUrlToImage();

        Picasso.get().load(imgUrl).into(holder.imageView);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailNews.class);
                intent.putExtra("title",a.getTitle());
                intent.putExtra("source",a.getSource().getName());
                intent.putExtra("time",dateTime(a.getPublishedAt()));
                intent.putExtra("desc",a.getDescription());
                intent.putExtra("url",a.getUrl());

                mContext.startActivity(intent);
            }
        });
    }

    public String dateTime(String t){
        PrettyTime pretty = new PrettyTime(new Locale(getCountry()));
        String time = null;

        try{
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date dt = date.parse(t);
            time = pretty.format(dt);
        }catch(Exception e){
            e.printStackTrace();
        }

        return time;
    }

    public String getCountry()
    {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        CardView cardView;
        ImageView imageView;
        TextView textView,textDate,textSource;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardExplore);
            imageView = itemView.findViewById(R.id.imgExp);
            textSource = itemView.findViewById(R.id.tvDesc);
            textView = itemView.findViewById(R.id.tvExp);
            button = itemView.findViewById(R.id.btnMore);
        }


    }
}
