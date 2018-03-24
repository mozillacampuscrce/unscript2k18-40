package com.example.somesh.myapplication_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Somesh on 12/28/2017.
 */

public class Information extends Fragment {

    private WebView myview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.info,container,false);
        myview=(WebView)v.findViewById(R.id.myview);
        WebSettings webSettings=myview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myview.loadUrl("https://justas-ashutosh.github.io/about.html");

        myview.setWebViewClient(new MyWebViewClient());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("About Us");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(Uri.parse(url).getHost().equals("www.justas-ashutosh.github.io/about.html"))
            return false;
            else {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
                return true;
            }

        }
    }


}
