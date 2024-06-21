package com.example.todosejercicios.ut01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.todosejercicios.R;

import java.util.Random;

public class piedraPapelTijera extends AppCompatActivity {

    private int marcador1 = 0;
    private int marcador2 = 0;
    private Random random = new Random();

    ImageButton imgbtPiedra, imgbtPapel, imgbtTijeras;
    Button btReiniciar;
    TextView tvMarcador1, tvMarcador2, tvJugador1, tvJugador2, tvSelecciona, tvResultadoRonda, tvEleccionIA, tvFinal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piedra_papel_tijera);
        //INITVIEWS
        initViews();
        //listeners de los botones, handlers de las funciones
        setListeners();
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (marcador1 == 3  && marcador2 == 0) {
            //vibracion larga
            VibrationEffect effect = VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);
        }else if(marcador2 == 3){
            //vibracion media pierdes
            VibrationEffect effect = VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);

        }else if (marcador1 == 3) {
            //vibracion corta ganas
            VibrationEffect effect = VibrationEffect.createOneShot(750, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);
        }
    }
    private void initViews() {
        imgbtPiedra = findViewById(R.id.imgbtPiedra);
        imgbtPapel = findViewById(R.id.imgbtPapel);
        imgbtTijeras = findViewById(R.id.imgbtTijeras);
        btReiniciar = findViewById(R.id.btReiniciar);
        tvMarcador1 = findViewById(R.id.tvMarcador1);
        tvMarcador2 = findViewById(R.id.tvMarcador2);
        tvJugador1 = findViewById(R.id.tvJugador1);
        tvJugador2 = findViewById(R.id.tvJugador2);
        tvSelecciona = findViewById(R.id.tvSelecciona);
        tvResultadoRonda = findViewById(R.id.tvResultadoRonda);
        tvEleccionIA = findViewById(R.id.tvEleccionIA);
        tvFinal = findViewById(R.id.tvFinal);

    }

    private void setListeners(){

        imgbtPapel.setOnClickListener((View v) -> {
            if (marcador1 < 3 && marcador2 < 3) {
                int randomNum = random.nextInt(3) + 1;
                if (randomNum == 1){
                    //1 sera tijeras
                    tvEleccionIA.setText("La IA eligio Tijeras");
                    marcador2++;
                    tvResultadoRonda.setText("Has Perdido!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" +marcador1);

                }else if (randomNum == 2){
                    //2 sera piedra
                    tvEleccionIA.setText("La IA eligio Piedra");
                    marcador1++;
                    tvResultadoRonda.setText("Has ganado!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" + marcador1);

                }else if (randomNum == 3){
                    //3 sera papel
                    tvEleccionIA.setText("La IA eligio Papel");
                    tvResultadoRonda.setText("Empate tecnico!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" + marcador1);
                }
            }else{
                System.out.print("error");
            }
        });

        imgbtPiedra.setOnClickListener((View v) -> {
            if (marcador1 < 3 && marcador2 < 3) {
                int randomNum = random.nextInt(3) + 1;
                if (randomNum == 1){
                    //1 sera tijeras
                    tvEleccionIA.setText("La IA eligio Tijeras");
                    marcador1++;
                    tvResultadoRonda.setText("Has Ganado!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" +marcador1);

                }else if (randomNum == 2){
                    //2 sera piedra
                    tvEleccionIA.setText("La IA eligio Piedra");
                    tvResultadoRonda.setText("Empate tecnico!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" + marcador1);

                }else if (randomNum == 3){
                    //papel
                    tvEleccionIA.setText("La IA eligio Papel");
                    marcador2++;
                    tvResultadoRonda.setText("Has Perdido!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" + marcador1);
                }
            }else{
                System.out.print("error");
            }
        });

        imgbtTijeras.setOnClickListener((View v) -> {
            if (marcador1 < 3 && marcador2 < 3) {
                int randomNum = random.nextInt(3) + 1;
                if (randomNum == 1){
                    //1 sera tijeras
                    tvEleccionIA.setText("La IA eligio Tijeras");
                    tvResultadoRonda.setText("Empate tecnico!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" + marcador1);
                }else if (randomNum == 2){
                    //2 sera piedra
                    tvEleccionIA.setText("La IA eligio Piedra");
                    marcador2++;
                    tvResultadoRonda.setText("Has Perdido!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" + marcador1);


                }else if (randomNum == 3){
                    //3 sera papel
                    tvEleccionIA.setText("La IA eligio Papel");
                    marcador1++;
                    tvResultadoRonda.setText("Has Ganado!");
                    tvMarcador1.setText(marcador1 + "-" + marcador2);
                    tvMarcador2.setText(marcador2 + "-" +marcador1);
                }
            }else{
                System.out.print("error");
            }
        });



        btReiniciar.setOnClickListener((View v) -> {
            if(marcador1 == 3  || marcador2 == 3){
                tvEleccionIA.setText("");
                tvResultadoRonda.setText("");
                tvMarcador2.setText("");
                tvMarcador1.setText("");
                marcador1=0;
                marcador2=0;
            }else if (marcador1 !=0  || marcador2 !=0){
                //hacer alert para confirmar si o confirmar no
                new AlertDialog.Builder(piedraPapelTijera.this)
                        .setTitle("Confirmación")
                        .setMessage("¿Seguro que quieres reiniciar?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Aquí va el código para reiniciar
                                tvEleccionIA.setText("");
                                tvResultadoRonda.setText("");
                                tvMarcador2.setText("");
                                tvMarcador1.setText("");
                                marcador1=0;
                                marcador2=0;
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }


        });




    }

}