package com.example.todosejercicios.ut02.Ej2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Destino implements Serializable {
    public String nombre;
    public int precio;
    public String pais;
    public String ciudad;


    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public int getPrecio() {
        return precio;
    }

    public static ArrayList<Destino> generador(ArrayList<Destino> listadoDestinos){

        ArrayList<Destino> listadoApiDestinos= new ArrayList<Destino>();

        return listadoApiDestinos;
    }
}
