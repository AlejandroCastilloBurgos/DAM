package com.example.todosejercicios.ut03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.todosejercicios.R;

public class PresupuestoFragment extends Fragment {

    private int SUMA10 = 10;
    private int RESTA10 = 10;

    private Button btSumaPresupuesto, btRestaPresupuesto;
    private TextView tvPresupuestoFinal;

    public PresupuestoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_presupuesto_fragment, container, false);

        btRestaPresupuesto = view.findViewById(R.id.btRestaPresupuesto);
        btSumaPresupuesto = view.findViewById(R.id.btSumaPresupuesto);
        tvPresupuestoFinal = view.findViewById(R.id.tvPresupuestoFinal);

        btSumaPresupuesto.setOnClickListener(v -> {
            int dinero = Integer.parseInt(tvPresupuestoFinal.getText().toString());
            tvPresupuestoFinal.setText(String.valueOf(dinero + SUMA10));
        });

        btRestaPresupuesto.setOnClickListener(v -> {
            int dinero = Integer.parseInt(tvPresupuestoFinal.getText().toString());
            tvPresupuestoFinal.setText(String.valueOf(dinero - RESTA10));
        });

        // Obtener los datos del Bundle
        if (getArguments() != null) {
            PrincipalOrdinaria1T.Presupuesto presupuesto = (PrincipalOrdinaria1T.Presupuesto) getArguments().getSerializable("PRESUPUESTO");
            if (presupuesto != null) {
                int p1 = Integer.parseInt(presupuesto.getPresupuestoMaximo());
                int p2 = Integer.parseInt(presupuesto.getPresupuestoMinimo());
                int presupuestoAleatorio = presupuestoRandom(p1, p2);
                tvPresupuestoFinal.setText(String.valueOf(presupuestoAleatorio));
            }
        }

        return view;
    }

    private int presupuestoRandom(int p1, int p2) {
        if (p1 > p2) {
            // Intercambiar p1 y p2 si p1 es mayor que p2
            int temp = p1;
            p1 = p2;
            p2 = temp;
        }
        return (int) (Math.random() * (p2 - p1 + 1)) + p1;
    }
}
