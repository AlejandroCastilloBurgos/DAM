package com.example.todosejercicios.ut02.Ej2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiDestinos {
    private static ServicioApiDestinos instancia;
    private static RepoDestino repo;

    private ServicioApiDestinos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(RepoDestino.class);
    }
    public static RepoDestino getRepo(){
        return repo;
    }

    public static ServicioApiDestinos getInstancia() {
        if(instancia == null){
            instancia =  new ServicioApiDestinos();
        }
        return instancia;
    }
}
