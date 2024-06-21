package com.example.todosejercicios.ut02;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todosejercicios.R;
import com.example.todosejercicios.ut03.PredictExamenExtraordinaria1B;

import java.io.Serializable;

public class Ejercicio1Extraordinaria extends AppCompatActivity {

    TextView tvErrorValorMaximo, tvErrorNumeroFilas;
    EditText etNumeroFilas, etNumeroColumnas,  etValorMaximoAleatorio;
    Button btGenerarFilas;

    ActivityResultLauncher<Intent> miResultadoLauncher;

    public static final String CUADRADO = "Cuadrado";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1_extraordinaria);
        tvErrorNumeroFilas = findViewById(R.id.tvErrorNumeroFilas);
        tvErrorValorMaximo = findViewById(R.id.tvErrorValorMaximo);
        etNumeroFilas = findViewById(R.id.etNumeroFilas);
        etNumeroColumnas= findViewById(R.id.etNumeroColumnas);
        etValorMaximoAleatorio = findViewById(R.id.etValorMaximoAleatorio);
        btGenerarFilas = findViewById(R.id.btGenerarFilas);
        listenerGenerarFilas();
        setupActivityResultLauncher();
    }

    //pasamos todo con un objeto

    public static class Cuadrado implements Serializable{
        private String n_filas;
        private String n_columnas;
        private String n_maximo;

        public Cuadrado(String n_filas, String n_columnas, String n_maximo){
            this.n_filas = n_filas;
            this.n_columnas = n_columnas;
            this.n_maximo = n_maximo;
        }
        //getters y setters

        public String getN_columnas() {
            return n_columnas;
        }

        public String getN_filas() {
            return n_filas;
        }

        public String getN_maximo() {
            return n_maximo;
        }

        public void setN_columnas(String n_columnas) {
            this.n_columnas = n_columnas;
        }

        public void setN_filas(String n_filas) {
            this.n_filas = n_filas;
        }

        public void setN_maximo(String n_maximo) {
            this.n_maximo = n_maximo;
        }



        //para retornar
        public String toString(){
            return "Numero Filas: " + n_filas +
                    ", Numero Columnas: " + n_columnas +
                    ", Valor Maximo: " + n_maximo;

        }
    }

    private void setupActivityResultLauncher(){
        miResultadoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    Log.d(ContentValues.TAG, "CUADRADO CANCELADO");
                    int code = result.getResultCode();
                    switch (code) {
                        case RESULT_CANCELED:
                            break;
                        case PredictExamenExtraordinaria1B.CODIGO_RESET:
                            etNumeroColumnas.setText("");
                            etNumeroFilas.setText("");
                            etValorMaximoAleatorio.setText("");

                    }
                        });
    }

    private void listenerGenerarFilas(){
        btGenerarFilas.setOnClickListener(v ->{
            Cuadrado cuadrado = new Cuadrado(
                    etNumeroFilas.getText().toString(),
                    etNumeroFilas.getText().toString(),
                    etValorMaximoAleatorio.getText().toString()
            );

            if (tvErrorValorMaximo.getText().toString().isEmpty()){
                String n_filas = etNumeroFilas.getText().toString();
                String n_columnas = etNumeroColumnas.getText().toString();
                String n_maximo = etValorMaximoAleatorio.getText().toString();

                //intent
                Intent intent = new Intent(Ejercicio1Extraordinaria.this, Ejercicio1BExtraordinaria.class);
                Cuadrado micuadrado = new Cuadrado( n_filas, n_columnas, n_maximo);
                intent.putExtra(CUADRADO, micuadrado);
                miResultadoLauncher.launch(intent);
            }
        });
    }
}