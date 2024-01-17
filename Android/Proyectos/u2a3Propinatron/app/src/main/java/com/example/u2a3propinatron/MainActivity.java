package com.example.u2a3propinatron;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button u2a3bt0,  u2a3bt1, u2a3bt2, u2a3bt3,  u2a3bt4, u2a3bt5, u2a3bt6, u2a3bt7, u2a3bt8, u2a3bt9, u2a3btdel, u2a3btcalc, u2a3btreset;
    TextView u2a3etnum;
    RadioButton u2a3rbtbad,u2a3rbtgood, u2a3rbtexcelent;
    RadioGroup u2a3rbtgrp;
    String textoActual="";

    public static String borrarUltimoCaracter(String textoActual){
        if(textoActual == null || textoActual.isEmpty()){
            return textoActual;
        }
        return textoActual.substring(0, textoActual.length() - 1);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u2a3bt0 =findViewById(R.id.u2a3bt0);
        u2a3bt1 =findViewById(R.id.u2a3bt1);
        u2a3bt2 =findViewById(R.id.u2a3bt2);
        u2a3bt3 =findViewById(R.id.u2a3bt3);
        u2a3bt4 =findViewById(R.id.u2a3bt4);
        u2a3bt5 =findViewById(R.id.u2a3bt5);
        u2a3bt6 =findViewById(R.id.u2a3bt6);
        u2a3bt7 =findViewById(R.id.u2a3bt7);
        u2a3bt8 =findViewById(R.id.u2a3bt8);
        u2a3bt9 =findViewById(R.id.u2a3bt9);
        u2a3btdel =findViewById(R.id.u2a3btdel);
        u2a3btcalc =findViewById(R.id.u2a3btcalc);
        u2a3btreset =findViewById(R.id.u2a3btreset);
        u2a3rbtbad =findViewById(R.id.u2a3rbtbad);
        u2a3rbtgood =findViewById(R.id.u2a3rbtgood);
        u2a3rbtexcelent =findViewById(R.id.u2a3rbtexcelent);
        u2a3etnum =findViewById(R.id.u2a3etnum);
        u2a3rbtgrp = findViewById(R.id.u2a3rbtgrp);

        u2a3bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "0");
            }
        });
        u2a3bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "1");
            }
        });
        u2a3bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "2");
            }
        });
        u2a3bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "3");
            }
        });
        u2a3bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "4");
            }
        });
        u2a3bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "5");
            }
        });
        u2a3bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "6");
            }
        });
        u2a3bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "7");
            }
        });
        u2a3bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "8");
            }
        });
        u2a3bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();
                u2a3etnum.setText(textoActual + "9");
            }
        });
       u2a3btdel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String textoActual = u2a3etnum.getText().toString();
               String textoBorrado = borrarUltimoCaracter(textoActual);
               u2a3etnum.setText(textoBorrado);
                }
                });
       u2a3btreset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               u2a3etnum.setText("");

           }
       });

        //excluir botones entre ellos del radio group e imprimir cual se selecciona

        u2a3rbtgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup u2a3rbtgrp, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);

                if (radioButton != null && radioButton.isChecked()) {
                    String opcionSeleccionada = radioButton.getText().toString();
                    Toast.makeText(MainActivity.this, "Opción seleccionada: " + opcionSeleccionada, Toast.LENGTH_SHORT).show();
                }
            }
        });
        u2a3btcalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoActual = u2a3etnum.getText().toString();

                if (!textoActual.isEmpty()) {
                    double valor = Double.parseDouble(textoActual);

                    if (u2a3rbtbad.isChecked()) {
                        // No se hace ningún cambio para "Mal"
                    } else if (u2a3rbtgood.isChecked()) {
                        valor = valor * 1.10; // propina un 10%
                    } else if (u2a3rbtexcelent.isChecked()) {
                        valor = valor * 1.20; // propina un 20%
                    }
                    u2a3etnum.setText(String.valueOf(valor));
                }
            }
        });
    }
}

/*
view on click listener manejador

manejador = (View botonPulsado) -> {
    Button boton = (Button)botonPulsado;

    if 8botonPulsado ==btC){
    String numeroActual = tv.getText().ToString();
    String cadenaFinal = numeroactual.substring(0, numeroActual.length() -1);
    tv.setText(cadenaFinal);
    }elseif(botonPulsado == btac{
    tv.setText();
    }else{
    tccuenta.append(boton.gettext();

    u2a3bt0.setOnclickListener(manejador)

 */
