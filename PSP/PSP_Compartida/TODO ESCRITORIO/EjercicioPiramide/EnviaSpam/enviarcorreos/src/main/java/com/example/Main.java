package com.example;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {

        if (args.length < 4) {
            System.out.println("Faltan parámetros");
            return;
        }

        String rutaMensajes = args[0];
        String rutaDirecciones = args[1];

        String usuario = args[2];
        String password = args[3];

        LectorParaSpam lector = new LectorParaSpam(rutaMensajes, rutaDirecciones);

        EnviadorSpam enviador = new EnviadorSpam(usuario, password, lector);

        lector.comenzarLectura();
    }

}