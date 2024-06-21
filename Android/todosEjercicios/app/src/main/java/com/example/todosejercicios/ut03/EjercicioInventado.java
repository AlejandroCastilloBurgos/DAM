package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class EjercicioInventado extends AppCompatActivity   {
    private int totalPerfil1 = 0;
    private int totalperfil2 = 0;
    private String NAME1 = "Alejandro";
    private String NAME2 = "Unknown";
    private int EDAD1 = 22;
    private int EDAD2 = 33;
    private int Pr1 = 0;
    private int Pr2 = 0;
    TextView tvEdadTotal, tvSeekBarProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_inventado);
        tvEdadTotal = findViewById(R.id.tvEdadTotal);
        tvSeekBarProgress = findViewById(R.id.tvSeekBarProgress);

        EjercicioInventadoFragment fragmentoPerfil1 = EjercicioInventadoFragment.newInstance(NAME1, EDAD1);
        EjercicioInventadoFragment fragmentoPerfil2 = EjercicioInventadoFragment.newInstance(NAME2, EDAD2);

        fragmentoPerfil1.setOnPerfilChangeListener(this::onPerfilCambio);
        fragmentoPerfil2.setOnPerfilChangeListener(this::onPerfilCambio);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentPerfil1, fragmentoPerfil1)
                .replace(R.id.fragmentPerfil2, fragmentoPerfil2)
                .commit();
        updateEdadTotal();
    }
    private void updateEdadTotal() {
        int totalEdades = EDAD1 + EDAD2;
        tvEdadTotal.setText("Total edad: " + totalEdades);
    }



    public void onPerfilCambio(String nombrePerfil, int edadPerfil, int Progreso) {
        //obtiene la informacion de las barras directamente del fragment cada vez que se modifica una de ellas;
        if ("Alejandro".equals(NAME1)){
            tvSeekBarProgress.setText(String.valueOf(Progreso));

        }
        else if("Unknown".equals(NAME2)){
            tvSeekBarProgress.setText(String.valueOf(Progreso));
        }

    }
}