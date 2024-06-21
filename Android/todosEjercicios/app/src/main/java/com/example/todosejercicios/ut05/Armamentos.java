package com.example.todosejercicios.ut05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class Armamentos extends AppCompatActivity {

    RecyclerView rv;
    ArmamentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clase_recuperacion_armamentos);
        rv = findViewById(R.id.crarvArmamento);

        rv.setLayoutManager(new LinearLayoutManager(Armamentos.this));
        adapter = new ArmamentoAdapter();
        rv.setAdapter(adapter);
        adapter.add(Armas.generarDatos());

        String primerTipo = adapter.getListaArmas().get(0).getTipo();

        if (primerTipo.equals("Dron")){

        } else if (primerTipo.equals("Tierra")) {

        } else if (primerTipo.equals("Misil")) {

        }
    }
}