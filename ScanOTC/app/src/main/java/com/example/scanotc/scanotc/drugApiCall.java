package com.example.scanotc.scanotc;

/**
 * Created by rfigueroa on 7/15/17.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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

    @Override
    protected String doInBackground(String... params){

        // Hardcoded api endpoint for example:
        // example endpoint for ndc #:
        //  https://api.fda.gov/drug/event.json?search=patient.drug.openfda.product_ndc=[ndc_#_here_ex:0573-01504]
        //
        // example endpoint for drug brand_name:
        //  https://api.fda.gov/drug/event.json?search=patient.drug.brand_name=[brand_name_ex:advil]
        //
        // example endpoint for drug generic_name:
        // https://api.fda.gov/drug/event.json?search=patient.drug.generic_name=[generic_name_ex:IBUPFROFEN]

        String login_url = "https://api.fda.gov/drug/event.json?search=patient.reaction.reactionmeddrapt:\"fatigue\"&limit=1";
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
}
