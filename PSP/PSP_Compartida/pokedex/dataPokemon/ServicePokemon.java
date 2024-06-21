package com.example.todosejercicios.ut07.dataPokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;

import com.example.todosejercicios.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicePokemon {
    private static ServicePokemon instancia;
    private static PokemonRepo repo;
    private ServicePokemon(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(PokemonRepo.class);
    }
    public static PokemonRepo getRepo(){
        return repo;
    }
    public static ServicePokemon getInstancia(){
        if(instancia == null){
            instancia = new ServicePokemon();
        }
        return instancia;
    }
}