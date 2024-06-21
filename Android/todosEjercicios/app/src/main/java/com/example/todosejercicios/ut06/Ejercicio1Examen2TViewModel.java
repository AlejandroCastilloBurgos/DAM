package com.example.todosejercicios.ut06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import com.example.todosejercicios.R;

public class Ejercicio1Examen2TViewModel extends ViewModel {

    private static final double DELAY = 2000;
    public static final Integer FAIL = -1;

    private MutableLiveData<String> misDatos = new MutableLiveData<>();

    private int Indice = 0;
    String[] miPoema = {
            "Si", "hay", "hombres", "que", "contienen", "un", "alma", "sin", "fronteras",
            "una", "esparcida", "frente", "de", "mundiales", "cabellos", "cubierta", "de",
            "horizontes", "barcos", "y", "cordilleras,", "con", "arena", "y", "con", "nieve",
            "tu", "eres", "uno", "de", "aquellos.", "FIN"
    };

    public void procesarPoema(){
        new Thread(() -> {
            Indice = 0;
            try {
                //Hacemos un bucle
                while (Indice < miPoema.length) {
                    // Simula un retraso en la operación
                    Thread.sleep((long) (Math.random() * DELAY + DELAY));
                    String frasePoema = miPoema[Indice];

                    /*if (frasePoema.equals("FIN")){
                        Indice = 0;
                        break;
                    }
                     */

                    //public en el live data
                    misDatos.postValue(frasePoema);
                    //si la palabra es fin no la pone
                    Indice++;
                }
            } catch (InterruptedException e) {
                // En caso de interrupción, publica un valor de fallo
                misDatos.postValue(FAIL.toString());
            }
        }).start();
    }

    public MutableLiveData<String> getMisDatos() {
        return misDatos;
    }

}



