package com.example.scanotc.scanotc;

//package com.scandit.simplesample;
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied
 * See the License for the specific language governing premissions and
 * limitations under the License.
 */
        import android.Manifest;
        import android.annotation.TargetApi;
        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.content.pm.PackageManager;
        import android.os.Build;
        import android.os.Bundle;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Toast;

        import com.scandit.barcodepicker.BarcodePicker;
        import com.scandit.barcodepicker.OnScanListener;
        import com.scandit.barcodepicker.ScanSession;
        import com.scandit.barcodepicker.ScanSettings;
        import com.scandit.barcodepicker.ScanditLicense;
        import com.scandit.recognition.Barcode;
        import com.scandit.recognition.SymbologySettings;

        import java.util.Locale;

/**
 * Simple demo application illustrating the use of the Scandit BarcodeScanner SDK.
 */


public class MainActivity extends Activity implements OnScanListener {

    // Enter your Scandit SDK App key here.
    // Your Scandit SDK App key is available via your Scandit SDK web account.
    //public static final String sScanditSdkAppKey = "ASqKwRRuDvInJ4cPHEFASkQEBHR0AbRajy926Lh6iXlpSGEtBlx1RvlA7jAAI9u5hHBlzaIF73jwRSUlY2fmtPFLcTTzQAQ6ByC4wmN2FkAEcD5iSHcxU4V64JY9UaetSS2uJ4Q1lccMT2FEqkEDYwJVNiFOaAv8mX0hfvtd2O/+RD2qQEWjvfJfoDGDSXF/L3K4sRNqjEqcRd2fR2PipCkLXFcaTuarzHv+u0YlYfwEVGvDy2goBvxfCO4ScChonU2fgPVshWbFNZ2EW0kf3dktfutgUzLaaUKFduJc2IH/DS/ROf4RacMaVSVJefCI4WYNiLWE/ElfWAOKfmLwy12q4YmWuzV5oSM4G0bJwfRZnID2HdhKEIwbifngJb1YmMNFBxkNu8WMMbyMOhSMCyd2FuUZP/HD1dxfqnEynK/p2ccSa0HKKKWMFUgTb3C8YtLmMFUqdTl52mK/ytkUeMYgucRxo7Vl9KX24vXWslVY/16VulO4VvUX9o0L22Ir3VaOVMJsHk8uC085CtObeHHxB+4i8o2t9HOApSvZN1I0bUVamugA05UWzijw6a+EJSyFlhbcLndqPSXBt6vJR8GSQharktp1Q992yaLR2a+w+wLtAhG04QIvm3lBp/I3OwR9Wv1qyHaBW7oKVFdNRHzbLOXyA1Z5Axn40wK7TrrvsXPhAfK6T7yEW2rA4UQZ8ZO+ZORATceqb7X12OqKqMYvnM47oNFH5FeEer9OObA4MlkGxBR8Bu+vd1NjtjsLngvt3ztj73848PweLItpGb6Wao/jTHPu8QmPR/cUAWWThxbwM3s9Eb2H/WKgdJNXSstHjkn1Fh62UKnAe1F5g8k+IU6rWtoghwO72euIYYAzeLOmduq5aQnz1zeMk6PBRo9Td7tngqedCTWHZE2Ya+a5xZegYgkQ92KH1uzjotsvKk9eA8SPBBTOaQy245j4mPAhavOlgkU0eFXVTrmc3i5z+OM=";
    public static final String sScanditSdkAppKey = "AUIKdg9uFUZ0CgRf+RI1kxga6IF8PPxE/BMFwqNwIh9cUkTpiERrt2lxfh8RD7/XQ2lEn1QYlak/bB8qViRJf/525qfUZUMXTAC1G+9M/l4PR+TIgWxsludQb74XJzUo9FO4MFETwQk9PRfADdOAJHnqQGXfa37/CD5fIC+sqVfZBoSFoyHhCLM7lU5eUxS5ef529o+Eg3r6VvXhgPHYD6tj8vJH51WW0Ff+IR/Z93higX0Cr5wJTpnzkmhVUTPFO0UOnxYnrEzEiM+rEodooMypLrk+LU3y6AzBBoztSIOdf2vUEaTwiJtsCiAImmlZSRLgGM1/MsH4rARuHsybd7MBGCHX5YlkEU2Ag6v07SsFDTZ3+0a5iuwWlRWf20XizTzeNqLfvP/HastU7ueN88z7+pVvlrJuk9p/u1p/0Q7ogVK1ERipYxWuH20umo8V5L460YXpkwVDLUu5dgze7hY2wpjVjTx5CG15DBZ1jVSoGE6g3/9Gtc+Wct1nwdk6f7NR+H4TQewjWxgbBzk59LQgrWzUZZ/s+drRCSFdeVmQ7GckohW2211je0SFrQSL7iM0zCr3oIUGymUD4IcY6JvZC7CR8Al0p3Gu76SwrnBtXiYceOoCYHsqBomXUUTQU5P8pbvEFcGgOlYDmvQIPn4P6shMCrx6XkWDmvkPUfR1lilk4iy96HsTPFVavHVYUr4VU7zMOYMvOUxLWhGlsdvA1eYNhwGX7mXRYmXfXMGTn250YIyYllGQ7vNS+4WOiEDGaWjd2hXFUMb9grBS6a7YeJkR+qV9wbgW+/HJJa0oh/IMyNj59Q==";


    private final int CAMERA_PERMISSION_REQUEST = 0;

    // The main object for recognizing and displaying barcodes.
    private BarcodePicker mBarcodePicker;
    private boolean mDeniedCameraAccess = false;
    private boolean mPaused = true;
    private Toast mToast = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScanditLicense.setAppKey(sScanditSdkAppKey);

        // Initialize and start the bar code recognition.
        initializeAndStartBarcodeScanning();
        GetProductThroughUPC getProductThroughUPC = new GetProductThroughUPC(this);
        String universalProductCode = "";
        // Initialize universalProductCode with Mario's barcode scanner
        // Here!
        
//        getProductThroughUPC.execute(universalProductCode);

    }

    @Override
    protected void onPause() {
        super.onPause();

        // When the activity is in the background immediately stop the
        // scanning to save resources and free the camera.
        mBarcodePicker.stopScanning();
        mPaused = true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void grantCameraPermissionsThenStartScanning() {
        if (this.checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (mDeniedCameraAccess == false) {
                // It's pretty clear for why the camera is required. We don't need to give a
                // detailed reason.
                this.requestPermissions(new String[]{ Manifest.permission.CAMERA },
                        CAMERA_PERMISSION_REQUEST);
            }

        } else {
            // We already have the permission.
            mBarcodePicker.startScanning();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mDeniedCameraAccess = false;
                if (!mPaused) {
                    mBarcodePicker.startScanning();
                }
            } else {
                mDeniedCameraAccess = true;
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPaused = false;
        // Handle permissions for Marshmallow and onwards...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            grantCameraPermissionsThenStartScanning();
        } else {
            // Once the activity is in the foreground again, restart scanning.
            mBarcodePicker.startScanning();
        }
    }


    /**
     * Initializes and starts the bar code scanning.
     */
    public void initializeAndStartBarcodeScanning() {
        // Switch to full screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        // The scanning behavior of the barcode picker is configured through scan
        // settings. We start with empty scan settings and enable a very generous
        // set of symbologies. In your own apps, only enable the symbologies you
        // actually need.
        ScanSettings settings = ScanSettings.create();
        int[] symbologiesToEnable = new int[] {
                Barcode.SYMBOLOGY_EAN13,
                Barcode.SYMBOLOGY_EAN8,
                Barcode.SYMBOLOGY_UPCA,
                Barcode.SYMBOLOGY_DATA_MATRIX,
                Barcode.SYMBOLOGY_QR,
                Barcode.SYMBOLOGY_CODE39,
                Barcode.SYMBOLOGY_CODE128,
                Barcode.SYMBOLOGY_INTERLEAVED_2_OF_5,
                Barcode.SYMBOLOGY_UPCE
        };
        for (int sym : symbologiesToEnable) {
            settings.setSymbologyEnabled(sym, true);
        }


        // Some 1d barcode symbologies allow you to encode variable-length data. By default, the
        // Scandit BarcodeScanner SDK only scans barcodes in a certain length range. If your
        // application requires scanning of one of these symbologies, and the length is falling
        // outside the default range, you may need to adjust the "active symbol counts" for this
        // symbology. This is shown in the following few lines of code.

        SymbologySettings symSettings = settings.getSymbologySettings(Barcode.SYMBOLOGY_CODE39);
        short[] activeSymbolCounts = new short[] {
                7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
        };
        symSettings.setActiveSymbolCounts(activeSymbolCounts);
        // For details on defaults and how to calculate the symbol counts for each symbology, take
        // a look at http://docs.scandit.com/stable/c_api/symbologies.html.



        // Prefer the back-facing camera, is there is any.
        settings.setCameraFacingPreference(ScanSettings.CAMERA_FACING_BACK);


        // Some Android 2.3+ devices do not support rotated camera feeds. On these devices, the
        // barcode picker emulates portrait mode by rotating the scan UI.
        boolean emulatePortraitMode = !BarcodePicker.canRunPortraitPicker();
        if (emulatePortraitMode) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        BarcodePicker picker = new BarcodePicker(this, settings);

        setContentView(picker);
        mBarcodePicker = picker;

        // Register listener, in order to be notified about relevant events
        // (e.g. a successfully scanned bar code).
        mBarcodePicker.setOnScanListener(this);

    }

    /**
     *  Called when a barcode has been decoded successfully.
     */
    public void didScan(ScanSession session) {
        String message = "";
        for (Barcode code : session.getNewlyRecognizedCodes()) {
            String data = code.getData();
            // Truncate code to certain length.
            String cleanData = data;
            if (data.length() > 30) {
                cleanData = data.substring(0, 25) + "[...]";
            }
            if (message.length() > 0) {
                message += "\n\n\n";
            }
            message += cleanData;
            message += "\n\n(" + code.getSymbologyName().toUpperCase(Locale.US) + ")";
        }
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        mToast.show();
    }

    @Override
    public void onBackPressed() {
        mBarcodePicker.stopScanning();
        finish();
    }
}

