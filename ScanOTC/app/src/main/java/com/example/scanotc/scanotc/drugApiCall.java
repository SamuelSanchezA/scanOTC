package com.example.scanotc.scanotc;

/**
 * Created by rfigueroa on 7/15/17.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//=============================================
    //to call api class:
    //            drugInfo info = new drugInfo(this);
    //            info.execute();

//=============================================
public class drugApiCall extends AsyncTask<String, String, String>{
    Context context;
    AlertDialog alertDialog;
    drugApiCall(Context ctx){
        context = ctx;
    }
    String name;


    @Override
    protected String doInBackground(String... params){

        String nameStr = params[0];
        String arr[] = nameStr.split(" ", 2);
        name = arr[0];

        String login_url = "https://api.fda.gov/drug/label.json?api_key=9TTFqn5NazzM1QRtCUIkRAVYfvFSalPq7pHlMXRC&search=patient.drug.brand_name=" + name;



        try{
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            Log.i("Response", httpURLConnection.getResponseMessage());
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String result = "";
            String line = "";
            while((line = bf.readLine()) != null){
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

    @Override
    protected void onPostExecute(String e) {
        super.onPostExecute(e);

        // Toasts to be replaced with calls to other activities:
        if (name.equals("Advil")){
            Toast.makeText(context.getApplicationContext(),
                    "Name:" + name, Toast.LENGTH_SHORT).show();
        }
        else if (name.equals("Tylenol")){
            Toast.makeText(context.getApplicationContext(),
                    "Name:" + name, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context.getApplicationContext(),
                    "Name:" + name, Toast.LENGTH_SHORT).show();
        }
    }
}
