package com.example.todosejercicios.ut02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.todosejercicios.R;
import com.example.todosejercicios.ut03.PredictExamenExtraordinaria1;

import java.util.Random;

public class Ejercicio1BExtraordinaria extends AppCompatActivity {

    public static final int CODIGO_RESET = 3;

    TextView tvMuestraCuadrado;
    Button btVolverCuadrado, btReiniciarCuadrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1_bextraordinaria);
        tvMuestraCuadrado = findViewById(R.id.tvMuestraCuadrado);
        btReiniciarCuadrado = findViewById(R.id.btReiniciarCuadrado);
        btVolverCuadrado = findViewById(R.id.btVolverCuadrado);
        Ejercicio1Extraordinaria.Cuadrado cuadrado = (Ejercicio1Extraordinaria.Cuadrado)getIntent().getSerializableExtra(Ejercicio1Extraordinaria.CUADRADO);
        //tvMuestraCuadrado.setText(cuadrado.toString());
        listenerVolver();
        listenerReiniciar();
        int alto = Integer.parseInt(cuadrado.getN_columnas());
        int ancho =  Integer.parseInt(cuadrado.getN_filas());
        int caracter = Integer.parseInt(cuadrado.getN_maximo());

        tvMuestraCuadrado.setText(generarCuadrado(alto, ancho, caracter));





    }

        public static String generarCuadrado(int alto, int ancho, int caracter) {
            StringBuilder cuadrado = new StringBuilder(); // Usamos StringBuilder para construir el cuadrado

            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    if (i == 0 || i == alto - 1 || j == 0 || j == ancho - 1) {
                        cuadrado.append(caracter);
                    } else {
                        cuadrado.append(" ");
                    }
                }
                if (i < alto - 1) {
                    cuadrado.append("\n");
                }
            }

            return cuadrado.toString(); // Convertir el StringBuilder a String y retornarlo
        }





    private void listenerVolver(){
        btVolverCuadrado.setOnClickListener(v ->{
            setResult(RESULT_CANCELED);
            finish();
        });
    }
    private void listenerReiniciar(){
        btReiniciarCuadrado.setOnClickListener(v ->{
            setResult(CODIGO_RESET);
            finish();
        });
    }
}