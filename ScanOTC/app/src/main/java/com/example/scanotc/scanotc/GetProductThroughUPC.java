package com.example.scanotc.scanotc;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sham on 7/15/17.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetProductThroughUPC extends AsyncTask<String, String, String> {
    Context context;
    AlertDialog alertDialog;
    GetProductThroughUPC(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params){
        // If key fails, sign up for api key and swap at "apiKey" field in url
        // Walmart API URL Format: http://api.walmartlabs.com/v1/items?apiKey={apiKey}&upc=035000521019
        // Key: w35vtdxspscd2sfhczjca3wt
        String upc = params[0];
        String login_url = "http://api.walmartlabs.com/v1/items?apiKey=w35vtdxspscd2sfhczjca3wt&upc=" + upc;
        try{
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            Log.i("Response", httpURLConnection.getResponseMessage());
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String result = "";
            String line = "";
            int count = 0;
            while((line = bf.readLine()) != null){
                Log.i("Line " + count, line);
                count++;
                result += line;
            }
            bf.close();
            inputStream.close();
            httpURLConnection.disconnect();
            Log.i("Result", result);

            return result;
        }
        catch(Exception e){
            Log.i("Error", e.toString());
        }

        return "";
    }
}
