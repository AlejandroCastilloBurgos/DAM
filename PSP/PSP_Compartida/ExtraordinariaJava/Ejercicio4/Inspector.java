package Ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Inspector implements Runnable {
    // lineas para poder ir haciendo los ciclos
    private final LineaEnsamblaje[] lineas;

    public Inspector(LineaEnsamblaje[] lineas) {
        this.lineas = lineas;
    }

    @Override
    public void run() {
        while (true) {
            for (LineaEnsamblaje linea : lineas) {
                try {
                    synchronized (linea) {
                        linea.inspeccionar(); // Inspecciona la línea de ensamblaje
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}