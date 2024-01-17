package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;



public class fibonacciB extends AppCompatActivity {

    public static final String CLAVE_SUMA = "VALOR_SUMA" ;
    TextView tvSuma;
    Button btOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_b);

        tvSuma = findViewById(R.id.u3a5idTvSuma);
        btOk = findViewById(R.id.u3a5idBtVolver);
        Bundle info = getIntent().getExtras();
        int suma = info.getInt(fibonacciA.CLAVE_N1)+info.getInt(fibonacciA.CLAVE_N2);
        tvSuma.setText(String.valueOf(suma));
        btOk.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(CLAVE_SUMA,tvSuma.getText().toString());
            setResult(Activity.RESULT_OK,data);
            finish();
        });
    }
}