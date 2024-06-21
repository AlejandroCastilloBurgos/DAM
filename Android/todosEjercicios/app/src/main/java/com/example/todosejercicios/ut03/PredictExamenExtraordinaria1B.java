package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class PredictExamenExtraordinaria1B extends AppCompatActivity {

    public static final int CODIGO_RESET = 3;

    TextView tvMuestraFiesta;
    Button btVolverFiesta, btReiniciarFiesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict_examen_extraordinaria1_b);
        tvMuestraFiesta = findViewById(R.id.tvMuestraFiesta);
        btVolverFiesta = findViewById(R.id.btVolverFiesta);
        btReiniciarFiesta = findViewById(R.id.btReiniciarFiesta);
        PredictExamenExtraordinaria1.Fiesta fiesta = (PredictExamenExtraordinaria1.Fiesta) getIntent().getSerializableExtra(PredictExamenExtraordinaria1.FIESTA);
        tvMuestraFiesta.setText(fiesta.toString());
        listenerVolver();
        listenerReiniciar();
    }
    private void listenerVolver(){
        btVolverFiesta.setOnClickListener(v ->{
            setResult(RESULT_CANCELED);
            finish();
        });
    }
    private void listenerReiniciar(){
        btReiniciarFiesta.setOnClickListener(v ->{
            setResult(CODIGO_RESET);
            finish();
        });
    }
}