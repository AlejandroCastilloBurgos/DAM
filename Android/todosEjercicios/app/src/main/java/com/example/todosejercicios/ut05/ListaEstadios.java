package com.example.todosejercicios.ut05;
//3ro
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.todosejercicios.R;

public class ListaEstadios extends AppCompatActivity {
    RecyclerView recyclerViewUser;
    Button btAdd;
    EstadiosAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estadios);
        //hay que crear el recyclerview en el activity_lista_estadios para que la linea de abajo tenga sentido
        recyclerViewUser = (RecyclerView) findViewById(R.id.ListaContenedorEstadios);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerViewUser.setLayoutManager(layoutManager);

        adapter = new EstadiosAdapter(EstadiosFutbol.generateEstadios(EstadiosFutbol.EQUIPOS_INICIALES));
            recyclerViewUser.setAdapter(adapter);

            btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(view -> adapter.add(EstadiosFutbol.generateEstadios(EstadiosFutbol.EQUIPOS_INICIALES)));

    }
}