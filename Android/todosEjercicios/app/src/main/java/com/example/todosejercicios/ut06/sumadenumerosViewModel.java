package com.example.todosejercicios.ut06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import com.example.todosejercicios.R;

public class sumadenumerosViewModel extends ViewModel {

    private static final double DELAY = 2000;
    public static final Integer FAIL = -1;

    private MutableLiveData<Integer> misDatos = new MutableLiveData<>();


    // Método público para iniciar el procesamiento
    public void procesarNumero(int numero1, int numero2) {
        new Thread(() -> {
            try {
                // Simula un retraso en la operación
                Thread.sleep((long) (Math.random() * DELAY + DELAY));
                int resultadoProcesado = calcularResultado(numero1, numero2);
                misDatos.postValue(resultadoProcesado);
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

    // Método privado para calcular un resultado basado en un número
    private int calcularResultado(int numero1, int numero2) {
        // Por ejemplo, podríamos multiplicar el número por algún factor
        return numero1 + numero2;
    }



}