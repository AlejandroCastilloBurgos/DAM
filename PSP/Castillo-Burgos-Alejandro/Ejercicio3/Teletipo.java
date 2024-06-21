package Ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Teletipo implements Runnable {
    // lineas para poder ir haciendo los ciclos
    private final MedidorTemperatura[] lineas;

    public Teletipo(MedidorTemperatura[] lineas) {
        this.lineas = lineas;
    }

    @Override
    public void run() {
        while (true) {
            for (MedidorTemperatura linea : lineas) {
                try {
                    synchronized (linea) {
                        linea.EscribeDatos(); // Inspecciona la línea de ensamblaje
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}