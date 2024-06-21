package com.example.todosejercicios.ut07.dataApiExamen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CryptomonedaRepo {
    @GET("{id}")
    Call<Cryptomoneda> getNombre(@Path("id") String id);
}
