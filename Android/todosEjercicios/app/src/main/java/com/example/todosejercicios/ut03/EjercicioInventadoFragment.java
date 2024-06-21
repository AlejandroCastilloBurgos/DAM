package com.example.todosejercicios.ut03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class EjercicioInventadoFragment extends Fragment {

    TextView tvEdadPerfil, tvNombrePerfil;
    SeekBar skBarColorPerfil;


    private String nombrePerfil;
    private int edadPerfil;

    private int progreso;

    private OnPerfilChangeListener perfilChangeListener;

    //OnPasajerosChange listener que hace callback
    public interface OnPerfilChangeListener{
        void onPerfilCambio(String nombre, int edad, int progreso );
    }
    //newInstance mejor solo tener uno y alojar aqui lo que queramos cambiar
    public static EjercicioInventadoFragment newInstance(String nombrePerfil, int edadPerfil){
        EjercicioInventadoFragment fragment = new EjercicioInventadoFragment();
        Bundle args = new Bundle();
        args.putString("nombre",nombrePerfil);
        args.putInt("edad",edadPerfil);
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnPerfilChangeListener (OnPerfilChangeListener listener) {
        this.perfilChangeListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ejercicio_inventado_fragment, container, false);
        tvEdadPerfil = view.findViewById(R.id.tvEdadPerfil);
        tvNombrePerfil = view.findViewById(R.id.tvNombrePerfil);
        skBarColorPerfil = view.findViewById(R.id.skBarColorPerfil);
        //Se encarga de ver si existe el "tipo", que obtiene de el instance
        if (getArguments() != null && getArguments().containsKey("nombre") && getArguments().containsKey("edad")) {
            nombrePerfil = getArguments().getString("nombre");
            tvNombrePerfil.setText(nombrePerfil);
            edadPerfil = getArguments().getInt("edad");
            tvEdadPerfil.setText(String.valueOf(edadPerfil));

        }
        skBarColorPerfil.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Cambiar el color de fondo del contenedor del fragmento
                view.setBackgroundColor(android.graphics.Color.rgb(progress, 0, 0));
                if (perfilChangeListener != null) {
                    perfilChangeListener.onPerfilCambio(nombrePerfil, edadPerfil, progreso);
                     progreso = skBarColorPerfil.getProgress();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Evento cuando se comienza a mover la SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Evento cuando se deja de mover la SeekBar
            }
        });



        return view;
    }
}
