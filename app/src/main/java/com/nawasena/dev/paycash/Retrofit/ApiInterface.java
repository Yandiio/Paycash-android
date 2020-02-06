package com.nawasena.dev.paycash.Retrofit;

import com.nawasena.dev.paycash.Activities.News.Models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface    {

    @GET("top-headlines")
    Call<News> getHeadlines(
            @Query("Country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<News> getSpecificData(
            @Query("v2") String v2,
            @Query("apiKey") String apiKey
    );
}