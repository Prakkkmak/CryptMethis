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

    private TextView encrypted_result;
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        but = (Button)findViewById(R.id.button);
        encrypted_result = (TextView)findViewById(R.id.textView3);

        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                encrypter_numer = Integer.parseInt(((EditText) findViewById(R.id.editText)).getText().toString());
                encrypter_text = ((EditText) findViewById(R.id.editText2)).getText().toString();
                encrypted_result.setText(Encrypt(encrypter_numer, encrypter_text));
            }
        });

    }

    public String Encrypt(int encrypter_number, String str){
        StringBuilder encrypted_result = new StringBuilder(str);
        int init_encrypter_number = encrypter_number; // Initialisation du nombre de départ
        int code; // chiffre éctrait à chaque fois ( Pour 458 on aura 4 , 5 , 8)
        for(int i=0;i<encrypted_result.length();i++){
            if (encrypter_number ==0){ // Pour reinitialiser le nombre
                encrypter_number = init_encrypter_number;
            }
            code = encrypter_number%10;// On prend le code
            encrypter_number/=10;//On on transforme le nombre
        }
        return encrypted_result.toString();
    }
}
