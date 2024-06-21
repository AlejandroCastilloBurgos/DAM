package com.example.todosejercicios.ut07.dataExtraordinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;
import com.example.todosejercicios.ut07.dataApiExamen.Cryptomoneda;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DestinoRepo {
    @GET("{id}")
    Call<Destino> getNombre(@Path("id") String id);
}
