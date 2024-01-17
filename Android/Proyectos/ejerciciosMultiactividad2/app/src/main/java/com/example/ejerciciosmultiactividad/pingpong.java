package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pingpong extends AppCompatActivity {

    private static final String JUGADOR_DEFAULT = "Jugador X";
    private static final String RESULTADO_DEFAULT = "0 - 0";
    private static final int MARCADOR_INICIAL = 0;
    pingPongFragment contadorN, contadorA, partido3, partido4;
    Button btResetear, btIniciar;
    TextView tvGanador1, tvGanador2, tvGanador3, tvGanador4, tvResultado1, tvResultado2, tvResultado3, tvResultado4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingpong);
        btResetear = findViewById(R.id.ut04f1idbtResetear);

        tvResultado2 = findViewById(R.id.ut04f1idtvResultadoPartido2);


        contadorN = (pingPongFragment) getSupportFragmentManager().findFragmentById(R.id.ut4a3idfrPartido1);
        contadorA = (pingPongFragment) getSupportFragmentManager().findFragmentById(R.id.ut4a3idfrPartido2);

        btIniciar.setOnClickListener((View v) -> {
            contadorN.setText("Partido 1");
            contadorA.setText("Partido 2");
        });
        btResetear.setOnClickListener((View v) -> {
            tvGanador1.setText(JUGADOR_DEFAULT);
            tvGanador2.setText(JUGADOR_DEFAULT);
            tvGanador4.setText(JUGADOR_DEFAULT);
            tvResultado1.setText(RESULTADO_DEFAULT);
            tvResultado2.setText(RESULTADO_DEFAULT);
            tvResultado3.setText(RESULTADO_DEFAULT);
            tvResultado4.setText(RESULTADO_DEFAULT);
            contadorN.setButtonEnabled(true);
            contadorA.setButtonEnabled(true);
            partido3.setButtonEnabled(true);
            partido4.setButtonEnabled(true);
            contadorN.setMarcador(MARCADOR_INICIAL, MARCADOR_INICIAL);
            contadorA.setMarcador(MARCADOR_INICIAL, MARCADOR_INICIAL);
            partido3.setMarcador(MARCADOR_INICIAL, MARCADOR_INICIAL);
            partido4.setMarcador(MARCADOR_INICIAL, MARCADOR_INICIAL);
        });

        contadorN.setCambiarDatosListener(
                (String JGanador, String marcador) -> {
                    tvGanador1.setText(String.valueOf(JGanador));
                    tvResultado1.setText(String.valueOf(marcador));
                });
        contadorA.setCambiarDatosListener(
                (String JGanador, String marcador) -> {
                    tvGanador2.setText(String.valueOf(JGanador));
                    tvResultado2.setText(String.valueOf(marcador));
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}