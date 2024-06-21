package com.example.todosejercicios.ut02.Ej2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoDestino {
    @GET("/api/destino/")
    Call<List<Destino>> getDestino();
}
