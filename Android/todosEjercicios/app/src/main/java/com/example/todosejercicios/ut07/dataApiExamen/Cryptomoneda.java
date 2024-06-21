package com.example.todosejercicios.ut07.dataApiExamen;

import java.io.Serializable;
import java.util.List;

public class Cryptomoneda {
    private String nombre;
    private double euros;
    private List<Estafados> estafados;

    public String getNombre() {
        return nombre;
    }

    public double getEuros() {
        return euros;
    }

    public List<Estafados> getEstafados() {
        return estafados;
    }

    public static class Estafados implements Serializable {
        private String nombre;

        public String getNombre() {
            return nombre;
        }
    }
}
