package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class FragmentPinpon extends Fragment {

    private int count1 = 0;
    private int count2 = 0;
    private String ganador = "Ganador";
    private String perdedor = "Perdedor";
    private TextView tvMarcadorJuegos1, tvMarcadorJuegos2, tvMarcador, tvMarcadorPinpon1, tvMarcadorPinpon2, tvJuegos1, tvJuegos2;
    private Button btPuntoJ1, btPuntoJ2;

    public FragmentPinpon() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.activity_fragment_pinpon, container, false);
        tvMarcadorJuegos1 = view.findViewById(R.id.tvMarcadorJuegos1);
        tvMarcadorJuegos2 = view.findViewById(R.id.tvMarcadorJuegos2);
        btPuntoJ1 = view.findViewById(R.id.btPuntoJ1);
        btPuntoJ2 = view.findViewById(R.id.btPuntoJ2);
/*
        if (count1 > 11 && count1 - count2 >=2 || count2 > 11 && count2 - count1 >=2){
            //terminaria la partida, ganaria uno de los dos por ende debemos deshabilitar los botones.
            btPuntoJ1.setEnabled(false);
            btPuntoJ2.setEnabled(false);
        }

 */


        btPuntoJ1.setOnClickListener(v -> {
            if ( count1 < 11){
                count1++;
                btPuntoJ1.setText(String.valueOf(count1));
            }
            else if (count1 >= 11 && count1 - count2 >= 2) {
                btPuntoJ1.setEnabled(false);
                btPuntoJ2.setEnabled(false);
                btPuntoJ1.setText(String.valueOf(ganador));
                btPuntoJ2.setText(String.valueOf(perdedor));
            }


        });
        btPuntoJ2.setOnClickListener(v -> {
            if (count2 > 11 && count2 - count1 >= 2) {
                count2++;
                btPuntoJ2.setText(String.valueOf(count1));
            }
            else {
                //manejar errores
            }
        });

        return view;
    }
}
