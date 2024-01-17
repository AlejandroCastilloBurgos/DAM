package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class calculadora2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora2);

        // Recuperar datos del Intent
        Intent intent = getIntent();
        String numero1 = intent.getStringExtra("numero1");
        String numero2 = intent.getStringExtra("numero2");
        boolean esSuma = intent.getBooleanExtra("esSuma", false);
        boolean esResta = intent.getBooleanExtra("esResta", false);
        boolean esMulti = intent.getBooleanExtra("esMulti", false);
        boolean esDiv = intent.getBooleanExtra("esDiv", false);

        // Realizar cálculos
        double resultado = 0;
        if (esSuma) {
            resultado = Double.parseDouble(numero1) + Double.parseDouble(numero2);
        } else if (esResta) {
            resultado = Double.parseDouble(numero1) - Double.parseDouble(numero2);
        } else if (esMulti) {
            resultado = Double.parseDouble(numero1) * Double.parseDouble(numero2);
        } else if (esDiv) {
            // Manejar la división por cero, por ejemplo
            if (Double.parseDouble(numero2) != 0) {
                resultado = Double.parseDouble(numero1) / Double.parseDouble(numero2);
            } else {
                // Manejar la división por cero según sea necesario
            }
        }

        // Mostrar el resultado en un TextView u otro componente según tu diseño
        TextView tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setText("Resultado: " + resultado);
    }
}
