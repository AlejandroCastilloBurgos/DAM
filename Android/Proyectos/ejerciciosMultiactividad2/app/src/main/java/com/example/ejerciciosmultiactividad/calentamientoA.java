package com.example.ejerciciosmultiactividad; //control alt l

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class calentamientoA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonCalentamiento = findViewById(R.id.botonCalentamiento);
        botonCalentamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(calentamientoA.this, calentamientoB.class);
                startActivity(intent);
            }
        });
    }
}
