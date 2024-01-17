package com.example.heladeria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SegundaActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        // Obtener la información enviada desde la MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String choco = extras.getString("choco");
            String fresa = extras.getString("fresa");
            String vainilla = extras.getString("vainilla");

            // Obtener el tipo de cucurucho seleccionado
            String tipoCucurucho = extras.getString("tipoCucurucho");

            // Inicializar contadores
            int contadorChoco = Integer.parseInt(choco);
            int contadorFresa = Integer.parseInt(fresa);
            int contadorVainilla = Integer.parseInt(vainilla);

            // Obtener referencias de vistas
            TextView tvResultado = findViewById(R.id.tvResultado);
            LinearLayout llResultado = findViewById(R.id.llResultado);
            Button btnFinalizar = findViewById(R.id.btnFinalizar);

            // Mostrar resultados en TextView
            tvResultado.setText(String.format("Resultado:\nChocolate: %d\nFresa: %d\nVainilla: %d", contadorChoco, contadorFresa, contadorVainilla));

            // Añadir 'O's amarillas por cada vainilla
            for (int i = 0; i < contadorVainilla; i++) {
                TextView oVainilla = new TextView(this);
                oVainilla.setText("O");
                oVainilla.setTextColor(getResources().getColor(R.color.yellow));
                llResultado.addView(oVainilla);
            }

            // Añadir 'O's marrones por cada chocolate
            for (int i = 0; i < contadorChoco; i++) {
                TextView oChocolate = new TextView(this);
                oChocolate.setText("O");
                oChocolate.setTextColor(getResources().getColor(R.color.brown));
                llResultado.addView(oChocolate);
            }

            // Añadir 'O's rosas por cada fresa
            for (int i = 0; i < contadorFresa; i++) {
                TextView oFresa = new TextView(this);
                oFresa.setText("O");
                oFresa.setTextColor(getResources().getColor(R.color.pink));
                llResultado.addView(oFresa);
            }

            // Añadir '\' marrón claro si el cucurucho es de chocolate o normal, 'U' si es tarrina
            TextView cucurucho = new TextView(this);
            cucurucho.setText((tipoCucurucho.equals("Chocolate") || tipoCucurucho.equals("Normal")) ? "\\" : "U");
            cucurucho.setTextColor(getResources().getColor(R.color.brown));
            llResultado.addView(cucurucho);

            // Configurar el botón para finalizar la actividad
            btnFinalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); // Cierra la actividad
                }
            });
        }
    }
}
