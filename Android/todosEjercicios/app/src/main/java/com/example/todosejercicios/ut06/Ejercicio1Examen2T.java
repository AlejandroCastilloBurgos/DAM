package com.example.todosejercicios.ut06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class Ejercicio1Examen2T extends AppCompatActivity {

    Button btRecitar;
    TextView tvPoema;
    ProgressBar pbCargarPoema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1_examen2_t);
        btRecitar = findViewById(R.id.btRecitar);
        tvPoema = findViewById(R.id.tvPoema);
        pbCargarPoema = findViewById(R.id.pbCargarPoema);

        Ejercicio1Examen2TViewModel vm = new ViewModelProvider(this).get(Ejercicio1Examen2TViewModel.class);
        vm.getMisDatos().observe(this, String ->{
            //Actualizar la interfaz
                if (String.equals(Ejercicio1Examen2TViewModel.FAIL)) {
                    tvPoema.setText("Error, esto no va");
                } else if (!String.equals("FIN")) {
                    tvPoema.append(" " + String);
                } else {
                    //mientras carga
                    pbCargarPoema.setVisibility(View.INVISIBLE);
                    btRecitar.setEnabled(true);
                }
        });


        //por defecto
        tvPoema.setText("");
        pbCargarPoema.setVisibility(View.INVISIBLE);
        btRecitar.setEnabled(true);

        btRecitar.setOnClickListener((v) ->{
            tvPoema.setText("");
            pbCargarPoema.setVisibility(View.VISIBLE);
            btRecitar.setEnabled(false);
            vm.procesarPoema();
        });
    }
}