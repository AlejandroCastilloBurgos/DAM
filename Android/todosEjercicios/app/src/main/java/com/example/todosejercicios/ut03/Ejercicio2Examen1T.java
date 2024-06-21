package com.example.todosejercicios.ut03;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import com.example.todosejercicios.R;

public class Ejercicio2Examen1T extends AppCompatActivity implements PasajerosFragment.OnPasajerosChangeListener {

    private TextView tvContadorTotal;
    private int contadorpuntos = 0;
    private int totalAdultos = 0;
    private int totalJovenes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2_examen1_t);
        tvContadorTotal = findViewById(R.id.tvTotalPasajeros);
        PasajerosFragment fragmentoAdultos = PasajerosFragment.newInstance("Adultos");
        PasajerosFragment fragmentoJovenes = PasajerosFragment.newInstance("Jóvenes");

        fragmentoAdultos.setOnPasajerosChangeListener(this);
        fragmentoJovenes.setOnPasajerosChangeListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView4, fragmentoAdultos)
                .replace(R.id.fragmentContainerView5, fragmentoJovenes)
                .commit();
    }

    @Override
    public void onContadorCambio(String tipo, int contador) {
        if ("Adultos".equals(tipo)) {
            totalAdultos = contador;
        } else if ("Jóvenes".equals(tipo)) {
            totalJovenes = contador;
        }
        //tvContadorTotal.setText("Total Pasajeros: " + (totalAdultos + totalJovenes));
        actualizarContadorTotal();
    }
    private void actualizarContadorTotal() {
        int totalPasajeros = totalAdultos + totalJovenes;
        StringBuilder puntos = new StringBuilder("Total Pasajeros ");

        // Agregar una 'o' por cada cinco pasajeros y un '.' por cada pasajero restante
        int numeroDeOs = totalPasajeros / 5;
        int numeroDePuntos = totalPasajeros % 5;

        for (int i = 0; i < numeroDeOs; i++) {
            puntos.append("o");
        }
        for (int i = 0; i < numeroDePuntos; i++) {
            puntos.append(".");
        }

        tvContadorTotal.setText(puntos.toString());
    }

}
