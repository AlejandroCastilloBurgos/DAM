package com.example.todosejercicios.ut07.dataExtraordinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;

import com.example.todosejercicios.R;
import com.example.todosejercicios.ut07.dataApiExamen.CryptomonedaRepo;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceDestino {
    private static ServiceDestino instancia;
    private static DestinoRepo repo;

    private ServiceDestino() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/api/destino/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(DestinoRepo.class);
    }

    public static DestinoRepo getRepo() {

        return repo;
    }

    public static ServiceDestino getInstancia() {
        if (instancia == null) {
            instancia = new ServiceDestino();
        }
        return instancia;
    }
}
