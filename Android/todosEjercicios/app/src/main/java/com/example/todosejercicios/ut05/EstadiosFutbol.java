package com.example.todosejercicios.ut05;
//1ero

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class EstadiosFutbol {

    public String nombre;
    public String estadio;
    public String aforo;
    public static final int EQUIPOS_INICIALES = 4;
    private static ArrayList<String> equipos = new ArrayList<String>(Arrays.asList(new String[]{"Real Madrid", "Inter Milan", "Inter Promesas", "Liverpool", "Bayern", "Manchester City", "Manchester United", "PSG", "Atletico De Madrid", "Valencia"}));
    public static EstadiosFutbol generateDulce(){
        Collections.shuffle(equipos);
        EstadiosFutbol p = new EstadiosFutbol();
        p.nombre = equipos.get(0);
        if(p.nombre.equals("Real Madrid")){
            p.estadio = "Santiago Bernabeu";
            p.aforo = "81.000";
        }else
        if(p.nombre.equals("Inter Milan")){
            p.estadio = "San Siro";
            p.aforo = "75.817";
        }else
        if(p.nombre.equals("Inter Promesas")){
            p.estadio = "La perla";
            p.aforo = "1.000";
        }else
        if(p.nombre.equals("Liverpool")){
            p.estadio = "Anfield";
            p.aforo = "61.276";
        }else
        if(p.nombre.equals("Bayern")){
            p.estadio = "Allianz Arena";
            p.aforo = "75.024";
        }else
        if(p.nombre.equals("Manchester City")){
            p.estadio = "Etihad stadium";
            p.aforo = "53.400";
        }else
        if(p.nombre.equals("Manchester United")){
            p.estadio = "Old Trafford";
            p.aforo = "74.310";
        }else
        if(p.nombre.equals("PSG")){
            p.estadio = "Parque de los Principes";
            p.aforo = "48.583";
        }else
        if(p.nombre.equals("Valencia")){
            p.estadio = "Mestalla";
            p.aforo = "49.430";
        }
        if(p.nombre.equals("Atletico De Madrid")){
            p.estadio = "Civitas Metropolitano";
            p.aforo = "70.460";
        }
        return p;
    }
/*
    public static ut06_DulcesNavideños[] generateDulces(int n) {
        ut06_DulcesNavideños[] dulcesNavideños = new ut06_DulcesNavideños[n];
        for(int i = 0; i< n; i++){
            dulcesNavideños[i] = ut06_DulcesNavideños.generateDulce();
        }
        return dulcesNavideños;
    }
 */
    public static EstadiosFutbol[] generateEstadios(int n) {
        EstadiosFutbol[] estadiosFutbol = new EstadiosFutbol[n];
        for(int i = 0; i< n; i++){
            estadiosFutbol[i] = EstadiosFutbol.generateDulce();
        }
        return estadiosFutbol;
    }
}