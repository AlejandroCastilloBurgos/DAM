package com.example.todosejercicios.ut06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import com.example.todosejercicios.R;

public class ParesNonesViewModel extends ViewModel {
    private static final double DELAY = 2000;
    private static final int LIMITE = 11;
    public static final Integer FAIL = -1;
    private MutableLiveData<Integer> misDatos = new MutableLiveData<>();
    private MutableLiveData<Boolean> ParNone = new MutableLiveData<>();

    public void procesarNumero(int numero1) {
        new Thread(() -> {
            try {
                // Simula un retraso en la operación
                Thread.sleep((long) (Math.random() * DELAY + DELAY));
                int numero2 = (int) (Math.random() * LIMITE);
                int resultadoProcesado = calcularResultado(numero1, numero2);
                misDatos.postValue(resultadoProcesado);
                boolean ParImpar = comprueba(resultadoProcesado);
                ParNone.postValue(ParImpar);
            } catch (InterruptedException e) {
                // En caso de interrupción, publica un valor de fallo
                misDatos.postValue(FAIL);
            }
        }).start();
    }
    // Método para obtener el LiveData
    public MutableLiveData<Integer> getMisDatos() {
        return misDatos;
    }
    public MutableLiveData<Boolean> getParNone() {
        return ParNone;
    }

    // Método privado para calcular un resultado basado en un número
    private int calcularResultado(int numero1, int numero2) {
        // Por ejemplo, podríamos multiplicar el número por algún factor
        return numero1 + numero2;
    }
    // Método privado para determinar si el número es par o impar
    private boolean comprueba(int resultadoProcesado) {
        return resultadoProcesado % 2 == 0;
    }

}