package com.example.todosejercicios.ut07.dataPokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonRepo{
        @GET("{name}")
        Call<Pokemon> getCharacter(@Path("name") String name);
    }
