package com.example.scanotc.scanotc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import java.lang.*;

public class results extends AppCompatActivity {

    private String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultString = getIntent().getStringExtra("resultString");
        Log.i("Return Value", resultString);

        // call fda api:
        drugApiCall drugInfo = new drugApiCall(this);
        String returnVal = "";
        try {
            Intent intent = getIntent();
            returnVal = drugInfo.execute(resultString).get();
        }
        catch (Exception e){
            Log.i("Exception", e.toString());
        }
    }
}
