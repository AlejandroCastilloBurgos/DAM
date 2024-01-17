package com.example.ejerciciosmultiactividad;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class fibonacciA extends AppCompatActivity {

    public static final String CLAVE_N2 = "VALOR_N2";
    public static final String CLAVE_N1 = "VALOR_N1";
    Button btSiguiente;
    TextView tvN1, tvN2, tvSms;
    ActivityResultLauncher<Intent> lanzador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);
        btSiguiente = findViewById(R.id.u3a5idBtSig);
        tvN1 = findViewById(R.id.u3a5idTvN1);
        tvN2 = findViewById(R.id.u3a5idTvN2);
        tvSms = findViewById(R.id.u3a5IdTvSms);
        lanzador = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        tvN1.setText(tvN2.getText().toString());
                        tvN2.setText(data.getStringExtra(fibonacciB.CLAVE_SUMA));
                        tvSms.setText("Sucesión realizada!");
                        tvSms.setTextColor(Color.GREEN);

                    } else {
                        tvSms.setText("El usuario ha cancelado la operación");
                        tvSms.setTextColor(Color.RED);
                    }
                });
        btSiguiente.setOnClickListener(view -> {
            Intent i = new Intent(this, fibonacciB.class);
            i.putExtra(CLAVE_N1, Integer.parseInt(tvN1.getText().toString()));
            i.putExtra(CLAVE_N2, Integer.parseInt(tvN2.getText().toString()));


            lanzador.launch(i);
        });
    }
}