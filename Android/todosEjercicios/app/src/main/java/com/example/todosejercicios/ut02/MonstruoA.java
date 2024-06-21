package com.example.todosejercicios.ut02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todosejercicios.R;

public class MonstruoA extends AppCompatActivity {
    TextView tvPatasMonstruo, tvManosMonstruo, tvNombreMonstruo, tvColorMonstruo;
    EditText etPatasMonstruo, etCantidadManos, etNombreMonstruo, etColorMonstruo;

    Button btCreaMonstruo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monstruo);
        //init
        initViews();
        //listener
        listeners();
    }

    private void initViews(){
        tvPatasMonstruo = findViewById(R.id.tvPatasMonstruo);
        tvManosMonstruo = findViewById(R.id.tvManosMonstruo);
        tvNombreMonstruo = findViewById(R.id.tvNombreMonstruo);
        tvColorMonstruo = findViewById(R.id.tvColorMonstruo);
        etPatasMonstruo = findViewById(R.id.etPatasMonstruo);
        etCantidadManos = findViewById(R.id.etCantidadManos);
        etNombreMonstruo = findViewById(R.id.etNombreMonstruo);
        etColorMonstruo = findViewById(R.id.etColorMonstruo);
        btCreaMonstruo = findViewById(R.id.btCreaMonstruo);

    }

    private void listeners(){
        btCreaMonstruo.setOnClickListener(view -> {
            //creamos la clase monstruo en otro .java a parte
            String NombreMonstruo = etNombreMonstruo.getText().toString();
            String ColorMonstruo = etColorMonstruo.getText().toString();
            String ManosMonstruo = etCantidadManos.getText().toString();
            String PatasMonstruo = etPatasMonstruo.getText().toString();
            int Manos = Integer.parseInt(ManosMonstruo);
            int Piernas = Integer.parseInt(PatasMonstruo);
            int ExtremidadesMonstruo = Manos + Piernas;
            Monstruo mimonstruo = new Monstruo(NombreMonstruo, ExtremidadesMonstruo, ColorMonstruo);
            Intent i = new Intent(this, MonstruoB.class);
            i.putExtra(Monstruo.CLAVE_MONSTRUO, mimonstruo);
            startActivity(i);
        });

    }
}