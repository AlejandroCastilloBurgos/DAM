package com.example.todosejercicios.ut07;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todosejercicios.R;
import com.example.todosejercicios.ut07.dataApiExamen.Cryptomoneda;
import com.example.todosejercicios.ut07.dataApiExamen.ServiceCryptomoneda;
import com.example.todosejercicios.ut07.dataExtraordinaria.Destino;
import com.example.todosejercicios.ut07.dataExtraordinaria.DestinoRepo;
import com.example.todosejercicios.ut07.dataExtraordinaria.ServiceDestino;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinoApi extends AppCompatActivity {

    Spinner spDestino;
    TextView tvInfoDestino;
    Button btSeleccionaDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destino2);
        spDestino = findViewById(R.id.spDestino);
        tvInfoDestino = findViewById(R.id.tvInfoDestino);
        btSeleccionaDestino = findViewById(R.id.btSeleccionaDestino);

        btSeleccionaDestino.setOnClickListener(v -> {
            ServiceDestino ser = ServiceDestino.getInstancia();
            String selectedDestino = String.valueOf(spDestino.getSelectedItemPosition()); // Asumimos que el Spinner empieza en 0 y API en 1
            //tvInfoDestino.setText(selectedDestino);

            // Asegúrate de que el Spinner contenga los nombres de las criptomonedas
            Call<Destino> llamada = ser.getRepo().getNombre(selectedDestino);
            llamada.enqueue(new Callback<Destino>() {
                @Override
                public void onResponse(Call<Destino> call, Response<Destino> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        tvInfoDestino.setTextColor(BLACK);
                        Destino d = response.body();
                        tvInfoDestino.setText("Nombre: " + d.getNombre() + "\n");
                        tvInfoDestino.append("Euros: " +d. getEuros() + "\n");
                        tvInfoDestino.append("Ciudad: " + d.getCiudad() + "\n");
                        tvInfoDestino.append("Pais: " + d.getPais() + "\n");

                        // Mostrar los estafados
                        List<Destino.Comentarios> comentariosList = d.getComentarios();
                        StringBuilder comentariosStr = new StringBuilder();
                        for (Destino.Comentarios comentarios : comentariosList) {
                            comentariosStr.append(comentarios.getTexto()).append(", ");
                        }
                        if (comentariosStr.length() > 0) {
                            comentariosStr.setLength(comentariosStr.length() - 2); // Quitar la última coma y espacio
                        }
                        tvInfoDestino.append("Comentarios: " + comentariosStr.toString() + "\n");
                    } else {
                        tvInfoDestino.setTextColor(RED);
                        tvInfoDestino.setText("Error en la selección de crypto");
                    }
                }


                @Override
                public void onFailure(Call<Destino> call, Throwable t) {
                    tvInfoDestino.setTextColor(RED);
                    tvInfoDestino.setText("Error en la conexión: " + t.getMessage());
                }
            });
        });
    }
}
