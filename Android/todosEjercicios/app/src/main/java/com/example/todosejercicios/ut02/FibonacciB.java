package com.example.todosejercicios.ut02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class FibonacciB extends AppCompatActivity {

    public static final String N3 = "0";

    public static final String INFO_OPERANDO1  = "0";
    public static final String INFO_OPERANDO2  = "0";

    TextView tvSuma;
    Button btAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_b);
        initViews();
        recibirIntent();
        listeners();
    }

    private void initViews() {
        tvSuma = findViewById(R.id.tvSuma);
        btAceptar = findViewById(R.id.btAceptar);
    }

    private void recibirIntent() {
        Bundle info = getIntent().getExtras();

        String N1 = info.getString(FibonacciA.INFO_N1);
        int Operando1 = Integer.parseInt(N1);
        String N2 = info.getString(FibonacciA.INFO_N2);
        int Operando2 = Integer.parseInt(N2);
        int Resultado = Operando1 + Operando2;
        tvSuma.setText(String.valueOf(Resultado));
    }

    private void listeners(){
            btAceptar.setOnClickListener(view -> {
                Intent resultIntent = new Intent();
                String resultadoSuma = tvSuma.getText().toString();
                String anteriorN2 = getIntent().getStringExtra(FibonacciA.INFO_N2);  // INFO_N2 es el valor de tvN2 en FibonacciA

                resultIntent.putExtra(FibonacciA.INFO_N1, anteriorN2);
                resultIntent.putExtra(FibonacciA.INFO_N2, resultadoSuma);
                setResult(RESULT_OK, resultIntent);
                finish(); // Cierra FibonacciB y devuelve el resultado a FibonacciA.
            });
        }



    }