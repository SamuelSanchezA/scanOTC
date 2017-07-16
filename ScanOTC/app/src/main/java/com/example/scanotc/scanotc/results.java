package com.example.scanotc.scanotc;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ImageButton;

public class results extends AppCompatActivity {

    private ImageButton toCam;
    private ImageButton toList;
    private ImageButton toSearch;
    private String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        toCam = (ImageButton) findViewById(R.id.cam_button);
        toList = (ImageButton) findViewById(R.id.list_button);
        toSearch = (ImageButton) findViewById(R.id.search_button);

        toCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity();
            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        resultString = getIntent().getStringExtra("resultString");
        Log.i("Return Value", resultString);
    }
}
