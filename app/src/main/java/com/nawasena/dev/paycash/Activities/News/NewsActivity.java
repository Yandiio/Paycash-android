package com.nawasena.dev.paycash.Activities.News;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.nawasena.dev.paycash.Activities.News.Adapter.NewsHolderAdapter;
import com.nawasena.dev.paycash.Activities.News.Models.Articles;
import com.nawasena.dev.paycash.Activities.News.Models.News;
import com.nawasena.dev.paycash.R;
import com.nawasena.dev.paycash.Retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    final String API_KEY = "62ca3ccb59494c8ba61e86151b116624";
    NewsHolderAdapter adapter;
    List<Articles> articles = new ArrayList<>();
    EditText querySearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        querySearch = findViewById(R.id.etQuery);
        btnSearch = findViewById(R.id.btn_search_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String Country = getCountry();

        swipeRefreshLayout = findViewById(R.id.refreshList);



         btnSearch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                if(!querySearch.getText().toString().equals("")){
                    retrieveData(querySearch.getText().toString(),Country,API_KEY);
                }else{

                    retrieveData(querySearch.getText().toString(),Country,API_KEY);
                }
             }
         });
 
    }

    public void retrieveData(String Query,String Country,String apiKey){

        swipeRefreshLayout.setRefreshing(true);
        Call<News> call;
        if(!querySearch.getText().toString().equals("")){
            call = ApiClient.getInstance().getApi().getSpecificData(Query,apiKey);
        }else{
            call = ApiClient.getInstance().getApi().getHeadlines(Country,apiKey);
        }

        call.enqueue(new Callback<News>(){

            @Override
            public void onResponse(Call<News> call, Response<News> response)    {
                if(response.isSuccessful() && response.body().getArticles() != null)
                    articles.clear();
                articles = response.body().getArticles();
                adapter = new NewsHolderAdapter(NewsActivity.this,articles);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public String getCountry()
    {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }



}
