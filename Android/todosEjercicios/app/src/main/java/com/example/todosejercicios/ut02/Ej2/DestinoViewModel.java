package com.example.todosejercicios.ut02.Ej2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Destino>> destinos;

    public LiveData<ArrayList<Destino>> getDestinos() {
        if (destinos == null) {
            destinos = new MutableLiveData<ArrayList<Destino>>();
            generarDestinos();
        }
        return destinos;
    }

    public void generarDestinos() {
        new Thread(() -> {

            ServicioApiDestinos ser = ServicioApiDestinos.getInstancia();
            Call<List<Destino>> llamada = ser.getRepo().getDestino();
            llamada.enqueue(new Callback<List<Destino>>() {
                @Override
                public void onResponse(Call<List<Destino>> call, Response<List<Destino>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<Destino> listaPeliculas = new ArrayList<>(response.body());

                        // Utiliza el método generador() de Consejo para procesar los consejos
                        ArrayList<Destino> peliculasProcesadas = Destino.generador(listaPeliculas);
                        destinos.postValue(peliculasProcesadas);
                    }
                }

                @Override
                public void onFailure(Call<List<Destino>> call, Throwable t) {
                    System.out.println("Error en la llamada: " + t.getMessage());
                }
            });

        }
        ).start();
    }
}