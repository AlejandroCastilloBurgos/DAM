package com.example.todosejercicios.ut07.dataApiExamen;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCryptomoneda {
    private static ServiceCryptomoneda instancia;
    private static CryptomonedaRepo repo;

    private ServiceCryptomoneda() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.101:8000/api/cripto/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repo = retrofit.create(CryptomonedaRepo.class);
    }

    public static CryptomonedaRepo getRepo() {
        return repo;
    }

    public static ServiceCryptomoneda getInstancia() {
        if (instancia == null) {
            instancia = new ServiceCryptomoneda();
        }
        return instancia;
    }
}
