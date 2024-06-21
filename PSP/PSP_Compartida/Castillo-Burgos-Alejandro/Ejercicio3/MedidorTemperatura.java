package Ejercicio3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MedidorTemperatura implements Runnable {
    private final int id;
    private int grados;
    private boolean estadoMedicion = false;
    private int RANGO_MAXIMO = 45;
    private int RANGO_MINIMO = -10;
    private int TIEMPO_MAXIMO = 3000;
    private int TIEMPO_MINIMO = 2000;

    // constructor
    public MedidorTemperatura(int id, int grados) {
        this.id = id;
        this.grados = grados;
    }

    public void setEstadoMedicion(boolean estadoMedicion) {
        this.estadoMedicion = estadoMedicion;
    }

    // comienza el thread
    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                // Comienza medir temperatura
                System.out.println("Termometro " + id + ": Comienza analisis de temperatura.");
                Thread.sleep(random.nextInt((TIEMPO_MAXIMO - TIEMPO_MINIMO) + 1) + TIEMPO_MINIMO);
                grados = random.nextInt(RANGO_MAXIMO) + RANGO_MINIMO;

                synchronized (this) {
                    // Finaliza ensamblado de coche
                    estadoMedicion = true;
                    System.out.println("Termometro " + id + ": Finaliza analisis de temperatura.");
                    notify(); // Notifica al teletipo
                    wait(); // Espera a que el teletipo escriba
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void EscribeDatos() throws InterruptedException {
        while (!estadoMedicion) {
            wait(); // Espera a que el medidor mida
        }
        // Inspecciona el coche
        // System.out.println("Teletipo: Nueva medicion: " + grados + "º.");
        String mensaje = "Teletipo: Nueva medicion: " + grados + "º.";
        System.out.println(mensaje);
        int cantidad_letras = mensaje.length();
        for (int i = 0; i < cantidad_letras; i++) {

        }
        estadoMedicion = false;
        grados = 0;
        notify(); // Notifica a la linea de ensamblaje que puede continuar

    }
}
