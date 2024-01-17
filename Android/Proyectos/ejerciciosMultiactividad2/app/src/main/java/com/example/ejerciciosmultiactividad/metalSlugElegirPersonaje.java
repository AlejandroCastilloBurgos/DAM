package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class metalSlugElegirPersonaje extends AppCompatActivity {

    public static final String ELECCIONJUGADOR = "ELECCION";
    public static final String NOMBRE_PERSONAJE = "PERSONAJE";
    ImageButton imgbtEri, imgbtMarco, imgbtTarma, imgbtFio;
    TextView tvPersonaje1, tvPersonaje2, tvPersonaje3;
    Button btLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metal_slug_elegir_personaje);

        imgbtEri = findViewById(R.id.u3a7aidimgbtEri);
        imgbtFio = findViewById(R.id.u3a7aidimgbtFio);
        imgbtMarco = findViewById(R.id.u3a7aidimgbtMarco);
        imgbtTarma = findViewById(R.id.u3a7aidimgbtTarma);
        tvPersonaje1 = findViewById(R.id.tvPersonaje1);
        tvPersonaje2 = findViewById(R.id.tvPersonaje2);
        tvPersonaje3 = findViewById(R.id.tvPersonaje3);
        btLimpiar = findViewById(R.id.u3a7bidbtLimpiar);

        String nombrePersonaje = getIntent().getStringExtra(metalSlugA.NOMBRE_PERSONAJE);
        if(nombrePersonaje.equals("Eri")){
            imgbtEri.setEnabled(false);
            imgbtEri.setBackgroundColor(Color.RED);
        }
        if(nombrePersonaje.equals("Fio")){
            imgbtFio.setEnabled(false);
            imgbtFio.setBackgroundColor(Color.RED);
        }
        if(nombrePersonaje.equals("Marco")){
            imgbtMarco.setEnabled(false);
            imgbtMarco.setBackgroundColor(Color.RED);
        }
        if(nombrePersonaje.equals("Tarma")){
            imgbtTarma.setEnabled(false);
            imgbtTarma.setBackgroundColor(Color.RED);
        }
        imgbtEri.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(ELECCIONJUGADOR, R.drawable.eri);
            data.putExtra(NOMBRE_PERSONAJE, "Eri");
            setResult(Activity.RESULT_OK, data);
            finish();
        });

        imgbtFio.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(ELECCIONJUGADOR, R.drawable.fio);
            data.putExtra(NOMBRE_PERSONAJE, "Fio");
            setResult(Activity.RESULT_OK, data);
            finish();
        });

        imgbtMarco.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(ELECCIONJUGADOR, R.drawable.marco);
            data.putExtra(NOMBRE_PERSONAJE, "Marco");
            setResult(Activity.RESULT_OK, data);
            finish();
        });

        imgbtTarma.setOnClickListener(view -> {
            Intent data = new Intent();
            data.putExtra(ELECCIONJUGADOR, R.drawable.tarma);
            data.putExtra(NOMBRE_PERSONAJE, "Tarma");
            setResult(Activity.RESULT_OK, data);
            finish();
        });

        btLimpiar.setOnClickListener(view -> {
            Intent data = new Intent();
            setResult(Activity.RESULT_FIRST_USER, data);
            finish();
        });
    }
}