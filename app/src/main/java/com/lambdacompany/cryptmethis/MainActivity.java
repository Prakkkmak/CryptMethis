package com.lambdacompany.cryptmethis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity {


    private String encrypter_text;
    private int encrypter_numer;

    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        but = (Button)findViewById(R.id.button);

        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                encrypter_numer = Integer.parseInt(((EditText)findViewById(R.id.editText)).getText().toString());
                encrypter_text = ((EditText)findViewById(R.id.editText2)).getText().toString();
            }
        });

    }

    public String Encrypt(int encrypted_number, String str){
        //TODO
        return "Ok";
    }
}
