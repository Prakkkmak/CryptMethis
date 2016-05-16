package com.lambdacompany.cryptmethis;

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
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Pubs
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Initialisations
        but = (Button)findViewById(R.id.button);
        encrypted_result = (TextView)findViewById(R.id.encrypted_text_text);

        but.setOnClickListener(new View.OnClickListener(){//Le listener du clic
            @Override
            public void onClick(View v) {//Lors du clic(sur le but)

                String encrypter_number_string;//On déclare le string entré dans le nombre
                encrypter_number_string = ((EditText) findViewById(R.id.editText)).getText().toString(); // On l'affecte
                if (encrypter_number_string.length()==0){return;} //Si il n'a rien été mis dans le nombre
                //On affecte les valeurs pour la fonction
                encrypter_number = Integer.parseInt(encrypter_number_string);
                encrypter_text = ((EditText) findViewById(R.id.editText2)).getText().toString();
                encrypted_result.setText(Encrypt(encrypter_number, encrypter_text));
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

    public String Encrypt(int encrypter_number, String str){ // Fonction pour encrypter
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
                new_char = (char)((int)new_char+1);
                if((int)new_char == 91){new_char=(char)65;}//On reviens au début de l'alphabet
                if((int)new_char == 123){new_char=(char)97;}//On reviens au début de l'alphabet
                //TODO activer le space ? 
            }
            encrypted_result.setCharAt(i,new_char);//Positionement du char
        }
        return encrypted_result.toString();
    }
}
