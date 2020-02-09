package com.nawasena.dev.paycash.Activities.News;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nawasena.dev.paycash.R;
import com.squareup.picasso.Picasso;

public class DetailNews extends AppCompatActivity {

    public WebView webView;
    public TextView txtTitleDetail,txtDescDetail,txtSourceDetail,txtTimeDetail;
    ImageView img;
    ProgressBar loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        castedItems();
    }

    public void castedItems(){
        webView = (WebView) findViewById(R.id.webView_detail_news);

        txtTimeDetail = (TextView) findViewById(R.id.tvDateHeadline_details);
        txtSourceDetail = (TextView) findViewById(R.id.tvSourceHeadline_details);
        txtTitleDetail = (TextView) findViewById(R.id.titleHeadline_details);
        txtDescDetail = (TextView) findViewById(R.id.tv_desc_details);
        img = findViewById(R.id.image_details);

        loader = findViewById(R.id.webview_loader);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("desc");
        String source = intent.getStringExtra("source");
        String date = intent.getStringExtra("publishedAt");
        String imageUrl = intent.getStringExtra("imageUrl");
        String url = intent.getStringExtra("url");

        txtTitleDetail.setText(title);
        txtSourceDetail.setText(source);
        txtDescDetail.setText(description);
        txtTimeDetail.setText(date);

        Picasso.get().load(imageUrl).into(img);
        webView.getSettings().getDomStorageEnabled();
        webView.getSettings().getJavaScriptEnabled();
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        if(webView.isShown()){
            loader.setVisibility(View.INVISIBLE);
        }
    }
}
