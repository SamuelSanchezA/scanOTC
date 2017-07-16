package com.example.scanotc.scanotc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetProductThroughUPC getProductThroughUPC = new GetProductThroughUPC(this);
        String universalProductCode = "";
        // Initialize universalProductCode with Mario's barcode scanner
        // Here!
        
//        getProductThroughUPC.execute();
    }
}
