package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class Ejercicio1Examen1TB extends AppCompatActivity {
    public static final int CODIGO_RESET=3;
    TextView tvMuestraViaje;
    Button btVolverViaje, btReiniciarViaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1_examen1_tb);
        //initviews
        initViews();
        //listeners
        listenerVolver();
        listenerReiniciar();
        Ejercicio1Examen1T.Viaje viaje = (Ejercicio1Examen1T.Viaje) getIntent().getSerializableExtra(Ejercicio1Examen1T.VIAJE);
        tvMuestraViaje.setText(viaje.toString());

    }
    private void initViews(){
        tvMuestraViaje = findViewById(R.id.tvMuestraViaje);
        btVolverViaje = findViewById(R.id.btVolverViaje);
        btReiniciarViaje = findViewById(R.id.btReiniciarViaje);

    }
    private void listenerVolver(){
        btVolverViaje.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
    private void listenerReiniciar(){
        btReiniciarViaje.setOnClickListener(v -> {
            setResult(CODIGO_RESET);
            finish();
        });

    }
}