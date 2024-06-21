package com.example.todosejercicios.ut02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.todosejercicios.R;

public class Actividad9 extends AppCompatActivity {
    Button btMandaSMS, btLlamada, btAbreNavegador, btAbreMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad9);
        //intents
        initView();
        //listeners
        listenerSMS();
        listenerLlamada();
        listenerAbreNavegador();
        listenerAbreMaps();

    }
    private void initView(){
        btMandaSMS = findViewById(R.id.btMandaSMS);
        btLlamada = findViewById(R.id.btLlamada);
        btAbreMaps = findViewById(R.id.btAbreMaps);
        btAbreNavegador = findViewById(R.id.btAbreNavegador);

    }

    private void listenerSMS(){
        btMandaSMS.setOnClickListener( (View v) -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("smsto:" + 66666)); //Si se cambia a mailto  manda emails
            i.putExtra("sms_body", "perrrrrrrrra"); //mail_body
            if (getIntent().resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            } else {
            }
            });
    }
    private void listenerLlamada(){

        btLlamada.setOnClickListener( (View v) -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + "666666666"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }else{
                Log.d("DIAL", "NO RESUELVE");
            }
        });

    }
    private void listenerAbreNavegador(){
        btAbreNavegador.setOnClickListener( (View v) -> {
        String url = "http://www.youtube.com";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }
    private void listenerAbreMaps(){
        btAbreMaps.setOnClickListener( (View v) -> {
            Uri gmmIntentUri = Uri.parse("geo:37.7749, -122.4194");
            Intent intent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }else{
                Log.d("DIAL", "NO RESUELVE");
            }
        });

    }
}