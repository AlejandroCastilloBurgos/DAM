package com.example.todosejercicios.ut02.Ej2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todosejercicios.R;

import java.util.ArrayList;

public class MainDestinos extends AppCompatActivity {
    public static final String INFO_DESTINOS = "info de un destino ";
    RecyclerView rvDestinos;
    DestinosAdapter destinosAdapter;
    DestinoViewModel destinoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_destinos);

        rvDestinos = findViewById(R.id.rvDestinos);
        rvDestinos.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        destinosAdapter = new DestinosAdapter(new ArrayList<>());

        rvDestinos.setAdapter(destinosAdapter);

        // Inicializa y observa los cambios en el ViewModel
        destinoViewModel = new ViewModelProvider(this).get(DestinoViewModel.class);
        destinoViewModel.getDestinos().observe(this, destinos -> {
            // Actualiza los datos del adaptador cuando cambia la lista de consejos en el ViewModel
            destinosAdapter.setDestinos(destinos);
        });

        destinosAdapter.setClickListener(new DestinosAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Destino unDestino) {
                Intent i = new Intent(String.valueOf(MainDestinos.this));//no se si esto funciona la verdad eh esta apañado por los pelos y sin sentido
                i.putExtra(MainDestinos.INFO_DESTINOS,unDestino);

                startActivity(i);
            }
        });


    }
}