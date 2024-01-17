package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class calculadora1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora1);

        Button botonCalculadora = findViewById(R.id.botonCalculadora);
        RadioButton rbtSuma = findViewById(R.id.rbtSuma);
        RadioButton rbtResta = findViewById(R.id.rbtResta);
        RadioButton rbtMulti = findViewById(R.id.rbtMulti);
        RadioButton rbtDiv = findViewById(R.id.rbtDiv);
        EditText etNumero1 = findViewById(R.id.etNumero1);
        EditText etNumero2 = findViewById(R.id.etNumero2);
        RadioGroup RadioGroup = findViewById(R.id.u2a3rbtgrp); //se añaden al grupo en el xml

        botonCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los datos de los EditText y RadioButton
                String numero1 = etNumero1.getText().toString();
                String numero2 = etNumero2.getText().toString();
                boolean esSuma = rbtSuma.isChecked();
                boolean esResta = rbtResta.isChecked();
                boolean esMulti = rbtMulti.isChecked();
                boolean esDiv = rbtDiv.isChecked();

                // Crear un Intent para pasar datos a la siguiente actividad
                Intent intent = new Intent(calculadora1.this, calculadora2.class);

                // Pasar los datos al Intent
                intent.putExtra("numero1", numero1);
                intent.putExtra("numero2", numero2);
                intent.putExtra("esSuma", esSuma);
                intent.putExtra("esResta", esResta);
                intent.putExtra("esMulti", esMulti);
                intent.putExtra("esDiv", esDiv);

                // Iniciar la segunda actividad
                startActivity(intent);
            }
        });
    }
}
