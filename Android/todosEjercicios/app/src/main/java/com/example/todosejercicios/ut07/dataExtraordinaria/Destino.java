package com.example.todosejercicios.ut07.dataExtraordinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import java.io.Serializable;
import java.util.List;

public class Destino {
    private String nombre;
    private double euros;
    private String ciudad;
    private String pais;
    private List<Comentarios> comentarios;

    public String getNombre() {
        return nombre;
    }

    public double getEuros() {
        return euros;
    }

    public List<Comentarios> getComentarios() {
        return comentarios;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public static class Comentarios implements Serializable {
        private String texto;

        public String getTexto() {
            return texto;
        }
    }
}
