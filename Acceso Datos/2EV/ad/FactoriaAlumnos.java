package com.mycompany.ad;

public class FactoriaAlumnos {
    public static AlumnoInterface getAlumnoDao() {
        return new AlumnoBean();
    }
}