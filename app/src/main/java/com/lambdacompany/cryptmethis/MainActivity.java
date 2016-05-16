package com.lambdacompany.cryptmethis;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity {

    //Déclarations des entrées
    private String encrypter_text;
    private int encrypter_number;

    //Déclaration des divers résultats
    private TextView encrypted_result;

    //Déclaration du boutton
    private Button but_encrypt;
    private Button but_decrypt;
    private Button but_copy;

    //Déclaration du ClipBoard
    ClipboardManager clipBoard;
    ClipData clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Pubs
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        if(mAdView != null) {
            mAdView.loadAd(adRequest);
        }

        //Initialisations
        but_encrypt = (Button)findViewById(R.id.button);
        but_decrypt = (Button)findViewById(R.id.button2);
        but_copy = (Button)findViewById(R.id.button3);
        encrypted_result = (TextView)findViewById(R.id.encrypted_text_text);
        clipBoard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        but_encrypt.setOnClickListener(new View.OnClickListener(){//Le listener du clic
            @Override
            public void onClick(View v) {//Lors du clic(sur le but)

                String encrypter_number_string;//On déclare le string entré dans le nombre
                encrypter_number_string = ((EditText) findViewById(R.id.editText)).getText().toString(); // On l'affecte
                if (encrypter_number_string.length()==0){return;} //Si il n'a rien été mis dans le nombre
                //On affecte les valeurs pour la fonction
                encrypter_number = Integer.parseInt(encrypter_number_string);
                encrypter_text = ((EditText) findViewById(R.id.editText2)).getText().toString();
                encrypted_result.setText(Encrypt(encrypter_number, encrypter_text,true));
            }
        });
        but_decrypt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String encrypter_number_string;//On déclare le string entré dans le nombre
                encrypter_number_string = ((EditText) findViewById(R.id.editText)).getText().toString(); // On l'affecte
                if (encrypter_number_string.length()==0){return;} //Si il n'a rien été mis dans le nombre
                //On affecte les valeurs pour la fonction
                encrypter_number = Integer.parseInt(encrypter_number_string);
                encrypter_text = ((EditText) findViewById(R.id.editText2)).getText().toString();
                encrypted_result.setText(Encrypt(encrypter_number, encrypter_text,false));
            }
        });
        but_copy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clip = ClipData.newPlainText("encrypted text",encrypted_result.getText().toString());
                clipBoard.setPrimaryClip(clip);
            }
        });

    }
    public int Reverse(int number){ // Fonction pour reverse un nombre 123->321
        int n = number;
        number=0;
        while(n!=0){
            number=number*10;
            number = number+ n%10;
            n = n/10;
        }
        return number;
    }

    /**
     *
     * @param encrypter_number Nombre à encrypter
     * @param str Mot à encrypter
     * @param mode Le mode permet de choisir le cryptage true étant cryptage et false décryptage
     * @return
     *
     */
    public String Encrypt(int encrypter_number, String str, Boolean mode){ // Fonction pour encrypter
        int mode_nbr;
        if(mode){mode_nbr = 1;}else{mode_nbr = -1;}
        encrypter_number = Reverse(encrypter_number);
        StringBuilder encrypted_result = new StringBuilder(str);
        int init_encrypter_number = encrypter_number; // Initialisation du nombre de départ
        int code; // chiffre éctrait à chaque fois ( Pour 458 on aura 4 , 5 , 8)

        char new_char = '0';

        for(int i=0;i<encrypted_result.toString().length();i++){
            if (encrypter_number ==0){ // Pour reinitialiser le nombre
                encrypter_number = init_encrypter_number;
            }
            code = encrypter_number%10;// On prend le code
            encrypter_number/=10;//On on transforme le nombre
            new_char = (char)((int)(encrypted_result.toString().toCharArray()[i]));
            for(int j=0;j<code;j++){// Definition du nouveau char
                new_char = (char)((int)new_char+ mode_nbr);
                if((int)new_char == 91){new_char=(char)65;}//On reviens au début de l'alphabet
                if((int)new_char == 123){new_char=(char)97;}//On reviens au début de l'alphabet

            }
            encrypted_result.setCharAt(i,new_char);//Positionement du char
        }
        return encrypted_result.toString();
    }
}
