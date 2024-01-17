package com.example.ejerciciosmultiactividad;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class metalSlugA extends AppCompatActivity {

    ImageButton imgbtJugador1, imgbtJugador2, imgbtArmaJugador1, imgbtArmaJugador2;
    Button btLimpiar;

    ActivityResultLauncher<Intent> lanzadorJ1,lanzadorJ2, lanzadorA1, lanzadorA2;
    public static final String NOMBRE_PERSONAJE = "PERSONAJE";
    String nombrePersonaje1 = "";
    String nombrePersonaje2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metal_slug);
        imgbtJugador1 = findViewById(R.id.u3a7aidimgbtJugador1);
        imgbtJugador2= findViewById(R.id.u3a7aidimgbtJugador2);
        imgbtArmaJugador1 = findViewById(R.id.u3a7aidimgbtArmaJugador1);
        imgbtArmaJugador2 = findViewById(R.id.u3a7aidimgbtArmaJugador2);
        btLimpiar = findViewById(R.id.u3a7aidbtLimpiar);


        lanzadorJ1 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if(result.getResultCode() == Activity.RESULT_OK){
                        imgbtJugador1.setImageResource(data.getExtras().getInt(metalSlugElegirPersonaje.ELECCIONJUGADOR));
                        nombrePersonaje1 = data.getExtras().getString(metalSlugElegirPersonaje.NOMBRE_PERSONAJE);
                    }
                    if(result.getResultCode() == Activity.RESULT_FIRST_USER){
                        imgbtJugador1.setImageResource(R.drawable.icono);
                    }
                });
        lanzadorJ2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if(result.getResultCode() == Activity.RESULT_OK){
                        imgbtJugador2.setImageResource(data.getExtras().getInt(metalSlugElegirPersonaje.ELECCIONJUGADOR));
                        nombrePersonaje2 = data.getExtras().getString(metalSlugElegirPersonaje.NOMBRE_PERSONAJE);
                    }
                    if(result.getResultCode() == Activity.RESULT_FIRST_USER){
                        imgbtJugador2.setImageResource(R.drawable.icono);
                    }
                });
        lanzadorA1 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if(result.getResultCode() == Activity.RESULT_OK){
                        imgbtArmaJugador1.setImageResource(data.getExtras().getInt(metalSlugElegirArma.ELECCIONARMA));
                    }
                    if(result.getResultCode() == Activity.RESULT_FIRST_USER){
                        imgbtArmaJugador1.setImageResource(R.drawable.arma);
                    }
                });
        lanzadorA2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if(result.getResultCode() == Activity.RESULT_OK){
                        imgbtArmaJugador2.setImageResource(data.getExtras().getInt(metalSlugElegirArma.ELECCIONARMA));

                    }
                    if(result.getResultCode() == Activity.RESULT_FIRST_USER){
                        imgbtArmaJugador2.setImageResource(R.drawable.arma);
                    }
                });

        imgbtJugador1.setOnClickListener(view ->{
            Intent i = new Intent(this, metalSlugElegirPersonaje.class);
            i.putExtra(NOMBRE_PERSONAJE, nombrePersonaje2);
            lanzadorJ1.launch(i);
        });

        imgbtJugador2.setOnClickListener(view ->{
            Intent i = new Intent(this, metalSlugElegirPersonaje.class);
            i.putExtra(NOMBRE_PERSONAJE, nombrePersonaje1);
            lanzadorJ2.launch(i);
        });


        imgbtArmaJugador1.setOnClickListener(view ->{
            Intent i = new Intent(this, metalSlugElegirArma.class);
            lanzadorA1.launch(i);
        });
        imgbtArmaJugador2.setOnClickListener(view ->{
            Intent i = new Intent(this, metalSlugElegirArma.class);
            lanzadorA2.launch(i);
        });
        btLimpiar.setOnClickListener(view -> {
            imgbtJugador1.setImageResource(R.drawable.icono);
            imgbtJugador2.setImageResource(R.drawable.icono);
            imgbtArmaJugador1.setImageResource(R.drawable.arma);
            imgbtArmaJugador2.setImageResource(R.drawable.arma);
            nombrePersonaje1 = "";
            nombrePersonaje2 = "";
        });




    }
}