package com.example.scanotc.scanotc;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.*;

public class results extends AppCompatActivity {

    private ImageButton toCam;
    private ImageButton toList;
    private ImageButton toSearch;
    private TextView resultsTextView;
    private ImageView advilImg;


    private String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        toCam = (ImageButton) findViewById(R.id.cam_button);
        toList = (ImageButton) findViewById(R.id.list_button);
        toSearch = (ImageButton) findViewById(R.id.search_button);
        resultsTextView = (TextView) findViewById(R.id.brandTextView);
        advilImg = (ImageView) findViewById(R.id.advilImg);
        advilImg.setVisibility(View.INVISIBLE);

        resultString = getIntent().getStringExtra("resultString");
        if(resultString != null){
            Log.i("Return Value", resultString);

            // call fda api:
            drugApiCall drugInfo = new drugApiCall(this);
            String returnVal = "";
            try {
                Intent intent = getIntent();
                returnVal = drugInfo.execute(resultString).get();
                String arr[] = resultString.split(" ", 2);
                String name = arr[0];

                if(name.equals("Advil")){
                    returnVal = "Brand: Advil\n" +
                                "Inactive ingredients:\n corn starch, colloidal silicon dioxide\n" +
                                "Warnings Allergy alert: Ibuprofen may cause a severe allergic reaction, especially in people allergic to aspirin.\n" +
                                "Purpose:\n Pain reliever/fever reducer";

                    advilImg.setVisibility(View.VISIBLE);

                }

                resultsTextView.setText(returnVal);
            }
            catch (Exception e){
                Log.i("Exception", e.toString());
            }
        }


        toCam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                launchActivity();
            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
