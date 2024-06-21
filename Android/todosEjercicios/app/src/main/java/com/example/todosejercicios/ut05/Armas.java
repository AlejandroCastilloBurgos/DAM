package com.example.todosejercicios.ut05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;


import java.util.ArrayList;

public class Armas {
    private String nombre;
    private String pais;
    private String tipo;

    public Armas(String nombre, String pais, String tipo) {
        this.nombre = nombre;
        this.pais = pais;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getTipo() {
        return tipo;
    }

    public static ArrayList<Armas> generarDatos(){
        ArrayList<Armas> armas = new ArrayList<Armas>();
        armas.add(new Armas("Predator", "España", "Dron"));
        armas.add(new Armas("Leclerc", "Francia", "Tierra"));
        armas.add(new Armas("Abrams", "Portugal", "Tierra"));
        armas.add(new Armas("Panzerfaust", "Alemania", "Misil"));
        armas.add(new Armas("Falcón", "Italia", "Dron"));
        armas.add(new Armas("Reaper", "Inglaterra", "Dron"));
        armas.add(new Armas("Condor", "Argentina", "Misil"));
        armas.add(new Armas("Sikorsky", "Brasil", "Misil"));

        return armas;
    }
}