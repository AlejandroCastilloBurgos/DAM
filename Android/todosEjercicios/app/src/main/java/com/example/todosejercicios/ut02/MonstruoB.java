package com.example.todosejercicios.ut02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class MonstruoB extends AppCompatActivity {

    TextView tvMuestraMonstruo, tvMostrarNombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monstruo_b);
        //initviews
        initViews();
        //recibirInfo
        recibirIntents();
    }
    private void initViews(){
        tvMuestraMonstruo = findViewById(R.id.tvMuestraMonstruo);
        tvMostrarNombre = findViewById(R.id.tvMuestraNombre);

    }
    private void recibirIntents(){
        //Para recibir
        Intent intent = getIntent();
        Monstruo monstruito = (Monstruo) getIntent().getSerializableExtra(Monstruo.CLAVE_MONSTRUO);
        String color = monstruito.getColor();
        String nombre = monstruito.getNombre();
        tvMostrarNombre.setText(nombre);
        tvMuestraMonstruo.setText(monstruito.toString());
        tvMuestraMonstruo.setTextColor(Color.parseColor(color));


    }
}