package com.example.todosejercicios.ut07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.todosejercicios.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todosejercicios.R;
import com.example.todosejercicios.ut07.dataPokemon.Pokemon;
import com.example.todosejercicios.ut07.dataPokemon.ServicePokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeApi extends AppCompatActivity {

    EditText name;
    Button get;
    TextView info;
    ImageView infoFotosNormal, infoFotosShiny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_api);
        name = findViewById(R.id.nombrePokemon);
        get = findViewById(R.id.getPokemon);
        info = findViewById(R.id.info);
        infoFotosNormal = findViewById(R.id.infoFotosNormal);
        infoFotosShiny = findViewById(R.id.infoFotosShiny);

        get.setOnClickListener((v)->{
            ServicePokemon ser = ServicePokemon.getInstancia();
            //a traves de este call mandamos a la api, asegurarse de que no haya fallos en esta línea
            Call<Pokemon> llamada =  ser.getRepo().getCharacter(String.valueOf(name.getText().toString().trim().toLowerCase().replace(" ","")));

            llamada.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    //comprobar que la llamada ha sido exitosa
                    if (response.isSuccessful() && response.body() != null) {
                        Pokemon p = response.body();
                        info.setText("Número de la Pokédex: " + p.getId() + "\n");
                        info.append("Nombre: " + p.getName() + "\n");
                        //info.append("Sprite Delante: " + p.getSprites().getFrontDefault());
                        //info.append("Sprite Detras: " + p.getSprites().getBack_default());
                        //info.append("Sprite Shiny Delantero: " + p.getSprites().getFront_shiny());
                        //info.append("Sprite Shiny Trasero: " + p.getSprites().getBack_shiny());
                        //Uso de la lista PokemonAbility
                        if (p != null && p.getAbilities() != null) {
                            for (Pokemon.PokemonAbility ability : p.getAbilities()) {
                                info.append("Ability: " + ability.getAbility().getName() + "\n");
                            }
                        }
                        //uso de la lista PokemonStats
                        if (p != null && p.getStats() != null) {
                            for (Pokemon.PokemonStat stat : p.getStats()) {
                                info.append("Stat: " + stat.getStat().getName() + " " + stat.getBase_stat() + "\n");
                            }
                        }

                        //Uso de glide para que envie las imagenes y las coloree, de otra forma saldra un texto
                        if (p.getSprites() != null) {
                            Glide.with(PokeApi.this)
                                    .load(p.getSprites().getFrontDefault())
                                    .into(infoFotosNormal);
                        }
                        if (p.getSprites() != null) {
                            Glide.with(PokeApi.this)
                                    .load(p.getSprites().getFront_shiny())
                                    .into(infoFotosShiny);
                        }


                    }else{
                            info.setText("No se encontró el Pokémon");
                    }
                }


                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    String nuncaToast = "Debug";
                }
            });
        });
    }
    }