package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * LectorParaSpam
 */
public class LectorParaSpam {

    String rutaMensajes;
    String rutaDirecciones;

    ArrayList<SpamListener> listeners;

    public interface SpamListener {
        public void emailPreparado(String direccion, String mensaje);
    }

    public void addListener(SpamListener listener) {
        listeners.add(listener);
    }

    LectorParaSpam(String rutaMensajes, String rutaDirecciones) {
        this.rutaMensajes = rutaMensajes;
        this.rutaDirecciones = rutaDirecciones;
        this.listeners = new ArrayList<>();
    }

    public void comenzarLectura() {
        try (BufferedReader readerMensajes = new BufferedReader(new FileReader(rutaMensajes));
                BufferedReader readerDirecciones = new BufferedReader(new FileReader(rutaDirecciones));) {

            String lineaMensaje;
            String lineaDireccion;

            while ((lineaMensaje = readerMensajes.readLine()) != null
                    && (lineaDireccion = readerDirecciones.readLine()) != null) {

                for (SpamListener listener : listeners) {
                    listener.emailPreparado(lineaDireccion, lineaMensaje);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/*
 * public void comenzarLectura() {
 * try (BufferedReader readerMensajes = new BufferedReader(new
 * FileReader(rutaMensajes));
 * BufferedReader readerDirecciones = new BufferedReader(new
 * FileReader(rutaDirecciones))) {
 * 
 * List<String> mensajes = new ArrayList<>();
 * List<String> direcciones = new ArrayList<>();
 * 
 * // Leer todos los mensajes y almacenarlos en una lista
 * String lineaMensaje;
 * while ((lineaMensaje = readerMensajes.readLine()) != null) {
 * mensajes.add(lineaMensaje);
 * }
 * 
 * // Leer todas las direcciones y almacenarlas en otra lista
 * String lineaDireccion;
 * while ((lineaDireccion = readerDirecciones.readLine()) != null) {
 * direcciones.add(lineaDireccion);
 * }
 * 
 * // Revisar si hay la misma cantidad de mensajes y direcciones
 * if (mensajes.size() != direcciones.size()) {
 * System.out.println("El número de mensajes y direcciones no coincide.");
 * return;
 * }
 * 
 * // Enviar mensajes desde el primero al último, a direcciones de la última a
 * la primera
 * for (int i = 0; i < mensajes.size(); i++) {
 * String mensajeActual = mensajes.get(i);
 * String direccionActual = direcciones.get(direcciones.size() - 1 - i); //
 * Acceder a la lista de direcciones en orden inverso
 * 
 * for (SpamListener listener : listeners) {
 * listener.emailPreparado(direccionActual, mensajeActual);
 * }
 * }
 * 
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * }
 */