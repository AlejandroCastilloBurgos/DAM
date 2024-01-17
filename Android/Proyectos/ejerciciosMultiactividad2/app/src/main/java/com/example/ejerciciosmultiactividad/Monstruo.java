package com.example.ejerciciosmultiactividad;

import java.io.Serializable;
import java.util.Random;

public class Monstruo implements Serializable {
    public static final String CLAVE_MONSTRUO = "monstruo";
    private String nombre;
    private int extremidades;
    private String color;
    int brazosIzquierdos;
    int brazosDerechos;
    int piernasIzquierdas;
    int piernasDerechas;

    public Monstruo(String nombre, int extremidades, String color) {
        this.nombre = nombre;
        this.extremidades = extremidades;
        this.color = color;
        Random random = new Random();
        brazosIzquierdos = random.nextInt(extremidades + 1);
        brazosDerechos = random.nextInt(extremidades - brazosIzquierdos + 1);
        piernasIzquierdas = random.nextInt(extremidades - brazosIzquierdos - brazosDerechos + 1);
        piernasDerechas = extremidades - brazosIzquierdos - brazosDerechos - piernasIzquierdas;
    }
    public String getNombre() {
        return nombre;
    }
    public String getColor() {
        return color;
    }



    @Override
    public String toString() {
        StringBuilder monstruo = new StringBuilder();

        monstruo.append(nombre+"\n");
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