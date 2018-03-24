package com.example.somesh.chat;

import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpDataHandler {
    static String stream= null;

    public HttpDataHandler() {
    }

    public String GetHTTPData(String url, String key,String message)
    {
        StringBuilder urlstr = new StringBuilder(
                url + URLEncoder.encode(message));

        // System.out.println("Making GET:" + urlstr.toString());
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            if(message==null)
                message="FireCloud";
            URL Url = new URL(urlstr.toString());

            // (set connection and read timeouts on the connection)
            conn = (HttpURLConnection) Url.openConnection();
            conn.setReadTimeout(30 * 1000);
            conn.setConnectTimeout(30 * 1000);
            conn.setRequestProperty("Authorization", "Bearer "+key);

            String resp = new String(
                    ReadStream.readFully(conn.getInputStream()), "UTF-8");
            return resp;


        } catch (Exception ex) {
            System.out.println("Making GET:" + urlstr.toString());
            ex.printStackTrace();
        }
     return null;
    }
}
