package com.example.cmd_f2019app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScanBarcode extends AppCompatActivity implements OnClickListener {

    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);
        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
    }

    public void onClick(View v){
    //respond to clicks
        if(v.getId()==R.id.scan_button){
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.recycle_symbol) {
            Intent i = new Intent(ScanBarcode.this, Symbol_meaning.class);
            startActivity(i);
        }
    }

    public void onBinClick(View v) {
        if (v.getId() == R.id.bins) {
            Intent i = new Intent(ScanBarcode.this, Bin_meaning.class);
            startActivity(i);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            String result = "Return-it Beverage Containers - Drop-Off";

            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);
            contentTxt.setText("OPTION: " + result);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
        Intent i = new Intent(ScanBarcode.this, Scan_result.class);
        startActivity(i);
    }
}
