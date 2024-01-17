package com.example.ejerciciosmultiactividad;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class monstruoA extends AppCompatActivity {

    EditText etNombre, etExtremidades, etColor;
    Button btCrear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monstruo);
        etNombre = findViewById(R.id.u3a8aidetNombre);
        etExtremidades = findViewById(R.id.u3a8aidetExtremidades);
        etColor = findViewById(R.id.u3a8aidetColor);
        btCrear = findViewById(R.id.u3a8aidbtCrear);


        btCrear.setOnClickListener(view -> {
            String nombre = etNombre.getText().toString();
            String color = etColor.getText().toString();
            String etExtrem = etExtremidades.getText().toString();
            int extremidades = Integer.parseInt(etExtrem);
            Monstruo miMonstruo = new Monstruo(nombre, extremidades, color);

            Intent i = new Intent(this, monstruoB.class);
            i.putExtra(Monstruo.CLAVE_MONSTRUO, miMonstruo);
            startActivity(i);
        });
    }
}