package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class heladeriaA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heladeria);

        Button btGenerar = findViewById(R.id.btGenerar);
        EditText etVainilla = findViewById(R.id.etVainilla);
        EditText etChoco = findViewById(R.id.etChoco);
        Spinner spOpciones = findViewById(R.id.spOpciones);
        EditText etFresa = findViewById(R.id.etFresa);

        btGenerar.setOnClickListener(view -> {
            // Obtener los valores ingresados por el usuario
            int cantidadVainilla = Integer.parseInt(etVainilla.getText().toString());
            int cantidadChoco = Integer.parseInt(etChoco.getText().toString());
            int cantidadFresa = Integer.parseInt(etFresa.getText().toString());
            String tipoRecipiente = spOpciones.getSelectedItem().toString();

            // Pasar a la segunda actividad con los datos
            Intent intent = new Intent(heladeriaA.this, heladeriaB.class);
            intent.putExtra("cantidadVainilla", cantidadVainilla);
            intent.putExtra("cantidadChoco", cantidadChoco);
            intent.putExtra("cantidadFresa", cantidadFresa);
            intent.putExtra("tipoRecipiente", tipoRecipiente);
            startActivity(intent);
        });
    }
}