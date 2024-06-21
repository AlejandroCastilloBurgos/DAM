package com.example.todosejercicios.ut02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

    public class Monstruo implements Serializable {
    public static final String CLAVE_MONSTRUO = "monstruo";
    private String NombreMonstruo;
    private int ExtremidadesMonstruo;
    private String ColorMonstruo;
    int brazosIzquierdos;
    int brazosDerechos;
    int piernasIzquierdas;
    int piernasDerechas;

    public Monstruo(String NombreMonstruo, int ExtremidadesMonstruo, String ColorMonstruo) {
        this.NombreMonstruo = NombreMonstruo;
        this.ExtremidadesMonstruo = ExtremidadesMonstruo;
        this.ColorMonstruo = ColorMonstruo;
        Random random = new Random();
        brazosIzquierdos = random.nextInt(ExtremidadesMonstruo + 1);
        brazosDerechos = random.nextInt(ExtremidadesMonstruo - brazosIzquierdos + 1);
        piernasIzquierdas = random.nextInt(ExtremidadesMonstruo - brazosIzquierdos - brazosDerechos + 1);
        piernasDerechas = ExtremidadesMonstruo - brazosIzquierdos - brazosDerechos - piernasIzquierdas;
    }
    public String getNombre() {
        return NombreMonstruo;
    }
    public String getColor() {
        return ColorMonstruo;
    }



    @Override
    public String toString() {
        StringBuilder monstruo = new StringBuilder();

        monstruo.append(NombreMonstruo+"\n");
        // Dibujar el monstruo con ASCII art
        monstruo.append("*\n");

        // Dibujar brazos izquierdos
        for (int i = 0; i < brazosIzquierdos; i++) {
            monstruo.append("/");
        }

        // Dibujar torso
        monstruo.append("o");

        // Dibujar brazos derechos
        for (int i = 0; i < brazosDerechos; i++) {
            monstruo.append("\\");
        }
        monstruo.append("\n");

        // Dibujar piernas izquierdas
        for (int i = 0; i < piernasIzquierdas; i++) {
            monstruo.append("/");
        }
        monstruo.append("   ");

        // Dibujar piernas derechas
        for (int i = 0; i < piernasDerechas; i++) {
            monstruo.append("\\");
        }
        monstruo.append("\n");

        return monstruo.toString();
    }
}