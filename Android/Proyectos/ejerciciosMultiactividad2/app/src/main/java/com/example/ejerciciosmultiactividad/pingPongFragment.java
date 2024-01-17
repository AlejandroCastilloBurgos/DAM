package com.example.ejerciciosmultiactividad;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pingPongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pingPongFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PUNTUACION_FINAL = 11;
    private static final int DIFERENCIA = 1;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pingPongFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ut04f3_PingPongFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static pingPongFragment newInstance(String param1, String param2) {
        pingPongFragment fragment = new pingPongFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Button btPuntoJugador1, btPuntoJugador2, btResetear;
    TextView tvPartido;

    int contadorJ1=0;
    int contadorJ2=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_ping_pong, container, false);
        btPuntoJugador1 = layout.findViewById(R.id.botonSumaAdultos);
        btPuntoJugador2 = layout.findViewById(R.id.botonRestaAdultos);
        tvPartido = layout.findViewById(R.id.ut04f3idtvPartido);

        btPuntoJugador1.setOnClickListener((View v)->{
            contadorJ1++;
            String puntosJ1 = String.valueOf(contadorJ1);
            btPuntoJugador1.setText(puntosJ1);
            if(contadorJ1 >= PUNTUACION_FINAL && contadorJ1 > contadorJ2+DIFERENCIA){
                btPuntoJugador1.setEnabled(false);
                btPuntoJugador2.setEnabled(false);
                String ptsJ1 = btPuntoJugador1.getText().toString();
                String ptsJ2 = btPuntoJugador2.getText().toString();
                String marcador = ptsJ1 + " - " + ptsJ2;
                String Jganador = "Jugador 1";
                if (observer != null) {
                    observer.CambiarDatos(Jganador, marcador);
                }
            }
        });
        btPuntoJugador2.setOnClickListener((View v)->{
            contadorJ2++;
            String puntosJ2 = String.valueOf(contadorJ2);
            btPuntoJugador2.setText(puntosJ2);
            if(contadorJ2 >= PUNTUACION_FINAL && contadorJ2 > contadorJ1+DIFERENCIA){
                btPuntoJugador1.setEnabled(false);
                btPuntoJugador2.setEnabled(false);
                String ptsJ1 = btPuntoJugador1.getText().toString();
                String ptsJ2 = btPuntoJugador2.getText().toString();
                String marcador = ptsJ1 + " - " + ptsJ2;
                String Jganador = "Jugador 2";
                if (observer != null) {
                    observer.CambiarDatos(Jganador, marcador);
                }
            }
        });



        return layout;
    }
    public void setText(String msg) {tvPartido.setText(msg);}
    public void setButtonEnabled(Boolean enabled){
        btPuntoJugador1.setEnabled(enabled);
        btPuntoJugador2.setEnabled(enabled);
    }
    public void setMarcador(int marcJ1, int marcJ2){
        btPuntoJugador1.setText(String.valueOf(marcJ1));
        btPuntoJugador2.setText(String.valueOf(marcJ2));
        contadorJ1 = marcJ1;
        contadorJ2 = marcJ2;
    }

    public interface FinPartido {
        public void CambiarDatos(String JGanador, String marcador);
    }

    FinPartido observer;

    public void setCambiarDatosListener(FinPartido objetoReceptor) {
        observer = objetoReceptor;
    }
}