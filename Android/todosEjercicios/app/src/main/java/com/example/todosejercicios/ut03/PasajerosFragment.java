package com.example.todosejercicios.ut03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.todosejercicios.R;

public class PasajerosFragment extends Fragment {

    private Button btAnadePasajero, btRestaPasajero;
    private TextView tvTipoPasajero, tvNumeroPasajeros;
    private int contadorPasajeros = 0;
    private String tipoPasajero;
    private OnPasajerosChangeListener pasajerosChangeListener;


    //OnPasajerosChange listener que devuelve tipo adulto o tal y contador, para comunicarse con el main
    public interface OnPasajerosChangeListener {
        void onContadorCambio(String tipo, int contador);
    }
    //newInstance, inicializamos un fragmento con un Bundle de tal manera que
    //Podemos configurar cada fragmento de manera individual;
    public static PasajerosFragment newInstance(String tipo) {
        PasajerosFragment fragment = new PasajerosFragment();
        Bundle args = new Bundle();
        args.putString("tipo", tipo);
        fragment.setArguments(args);
        return fragment;
    }
    //setter en el listener, comunicacion con otros componentes, relaciona los cambios
    public void setOnPasajerosChangeListener(OnPasajerosChangeListener listener) {
        this.pasajerosChangeListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pasajeros_fragment, container, false);
        btAnadePasajero = view.findViewById(R.id.btAnadePasajero);
        btRestaPasajero = view.findViewById(R.id.btRestaPasajero);
        tvTipoPasajero = view.findViewById(R.id.tvTipoPasajero);
        tvNumeroPasajeros = view.findViewById(R.id.tvNumeroPasajeros);
        //Se encarga de ver si existe el "tipo", que obtiene de el instance
        if (getArguments() != null && getArguments().containsKey("tipo")) {
            tipoPasajero = getArguments().getString("tipo");
            tvTipoPasajero.setText(tipoPasajero);
            tvNumeroPasajeros.setText(String.valueOf(contadorPasajeros));
        }

        btAnadePasajero.setOnClickListener(v -> {
            contadorPasajeros++;
            tvNumeroPasajeros.setText(String.valueOf(contadorPasajeros));
            if (pasajerosChangeListener != null) {
                pasajerosChangeListener.onContadorCambio(tipoPasajero, contadorPasajeros);
            }
        });

        btRestaPasajero.setOnClickListener(v -> {
            if (contadorPasajeros > 0) {
                contadorPasajeros--;
                tvNumeroPasajeros.setText(String.valueOf(contadorPasajeros));
                if (pasajerosChangeListener != null) {
                    pasajerosChangeListener.onContadorCambio(tipoPasajero, contadorPasajeros);
                }
            }
        });

        return view;
    }
}
