
package com.example.ejerciciosmultiactividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class monstruoB extends AppCompatActivity {

    TextView tvMonstruo, tvNombreMonstruo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monstruo_b);
        tvMonstruo = findViewById(R.id.u3a8bidtvMonstruo);
        tvNombreMonstruo = findViewById(R.id.u3a8bidtvnomMonstruo);

        Intent intent = getIntent();
        Monstruo monstruito = (Monstruo) intent.getSerializableExtra(Monstruo.CLAVE_MONSTRUO);
        String color = monstruito.getColor();
        String nombre = monstruito.getNombre();
        tvNombreMonstruo.setText(nombre);
        tvMonstruo.setText(monstruito.toString());
        tvMonstruo.setTextColor(Color.parseColor(color));
    }
}