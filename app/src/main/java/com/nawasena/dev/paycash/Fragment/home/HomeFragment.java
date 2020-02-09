package com.nawasena.dev.paycash.Fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.facebook.shimmer.ShimmerFrameLayout;
import com.nawasena.dev.paycash.Activities.News.Models.Articles;
import com.nawasena.dev.paycash.Activities.News.Models.News;
import com.nawasena.dev.paycash.Activities.News.NewsActivity;
import com.nawasena.dev.paycash.Activities.SendMoney.SendMoneyActivity;
import com.nawasena.dev.paycash.Activities.TopUpActivity;
import com.nawasena.dev.paycash.Fragment.home.Adapter.NewsCardHolderAdapter;
import com.nawasena.dev.paycash.R;
import com.nawasena.dev.paycash.Retrofit.ApiClient;
import com.nawasena.dev.paycash.material.ModalBottomSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    public ImageView img1, img2, img3, img4;
    private ShimmerFrameLayout shimmerFrameLayout;
    private TextView txtView;
    RecyclerView recyclerView;
    final String API_KEY = "62ca3ccb59494c8ba61e86151b116624";
    NewsCardHolderAdapter adapter;
    List<Articles> articles = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        img1 = v.findViewById(R.id.imgPay);
        img2 = v.findViewById(R.id.imgTopup);
        img3 = v.findViewById(R.id.imgReq);
        img4 = v.findViewById(R.id.imgTransfer);
        txtView = v.findViewById(R.id.txViewExplore);

        recyclerView = v.findViewById(R.id.recyclerExplore);
        shimmerFrameLayout = v.findViewById(R.id.shimmer_frame);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final String Country = getCountry();


        retrieveData(Country, API_KEY);

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModalBottomSheet modalBottomSheet = new ModalBottomSheet();
                modalBottomSheet.show(getFragmentManager(), "ExampleTagBottomDialog");
                ;
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(getActivity(), TopUpActivity.class);
                startActivity(n);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent n = new Intent(getActivity(), TopUpActivity.class);
//                startActivity(n);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(getActivity(), SendMoneyActivity.class);
                startActivity(n);
            }
        });


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        RecyclerView myList = (RecyclerView) v.findViewById(R.id.recyclerExplore);
        myList.setLayoutManager(layoutManager);

        return v;
    }


    public void retrieveData(String Country, String apiKey) {
        Call<News> call = ApiClient.getInstance().getApi().getHeadlines(Country, apiKey);
        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new NewsCardHolderAdapter(getActivity(), articles);
                    recyclerView.setAdapter(adapter);

                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }



    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}