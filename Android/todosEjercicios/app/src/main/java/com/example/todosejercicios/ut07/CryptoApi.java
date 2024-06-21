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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoApi extends AppCompatActivity {

    Spinner spCrypto;
    TextView tvInfoCrypto;
    Button btSeleccionaCrypto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_api);
        spCrypto = findViewById(R.id.spCrypto);
        tvInfoCrypto = findViewById(R.id.tvInfoCrypto);
        btSeleccionaCrypto = findViewById(R.id.btSeleccionaCrypto);

        btSeleccionaCrypto.setOnClickListener(v -> {
            ServiceCryptomoneda ser = ServiceCryptomoneda.getInstancia();
            String selectedCrypto = String.valueOf(spCrypto.getSelectedItemPosition()); // Asumimos que el Spinner empieza en 0 y API en 1
            tvInfoCrypto.setText(selectedCrypto);

            // Asegúrate de que el Spinner contenga los nombres de las criptomonedas
            Call<Cryptomoneda> llamada = ser.getRepo().getNombre(selectedCrypto);
            llamada.enqueue(new Callback<Cryptomoneda>() {
                @Override
                public void onResponse(Call<Cryptomoneda> call, Response<Cryptomoneda> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        tvInfoCrypto.setTextColor(BLACK);
                        Cryptomoneda c = response.body();
                        tvInfoCrypto.setText("Nombre de la criptomoneda: " + c.getNombre() + "\n");
                        tvInfoCrypto.append("Euros: " + c.getEuros() + "\n");

                        // Mostrar los estafados
                        List<Cryptomoneda.Estafados> estafadosList = c.getEstafados();
                        StringBuilder estafadosStr = new StringBuilder();
                        for (Cryptomoneda.Estafados estafado : estafadosList) {
                            estafadosStr.append(estafado.getNombre()).append(", ");
                        }
                        if (estafadosStr.length() > 0) {
                            estafadosStr.setLength(estafadosStr.length() - 2); // Quitar la última coma y espacio
                        }
                        tvInfoCrypto.append("Estafados: " + estafadosStr.toString() + "\n");
                    } else {
                        tvInfoCrypto.setTextColor(RED);
                        tvInfoCrypto.setText("Error en la selección de crypto");
                    }
                }

                @Override
                public void onFailure(Call<Cryptomoneda> call, Throwable t) {
                    tvInfoCrypto.setTextColor(RED);
                    tvInfoCrypto.setText("Error en la conexión: " + t.getMessage());
                }
            });
        });
    }
}
