package Ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class LineaEnsamblaje implements Runnable {
    private final int id;
    private boolean estadoEnsamblado = false;

    // constructor
    public LineaEnsamblaje(int id) {
        this.id = id;
    }

    public void setEstadoEnsamblado(boolean estadoEnsamblado) {
        this.estadoEnsamblado = estadoEnsamblado;
    }

    // comienza el thread
    @Override
    public void run() {
        Random random = new Random();
        int max = 3000;
        int min = 2000;
        while (true) {
            try {
                // Comienza ensamblaje de coche
                System.out.println("Línea " + id + ": Comienza ensamblaje de coche.");
                Thread.sleep(random.nextInt((max - min) + 1) + min);

                synchronized (this) {
                    // Finaliza ensamblado de coche
                    estadoEnsamblado = true;
                    System.out.println("Línea " + id + ": Finaliza ensamblado de coche.");
                    notify(); // Notifica al inspector
                    wait(); // Espera a que el coche sea inspeccionado
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void inspeccionar() throws InterruptedException {
        while (!estadoEnsamblado) {
            wait(); // Espera a que el coche sea ensamblado
        }
        // Inspecciona el coche
        System.out.println("Inspector: Inspecciona coche de la línea " + id + ".");
        System.out.println("Inspector: Niveles de aceite de: " + id + "correctos.");
        System.out.println("Inspector: Presion de las ruedas de: " + id + "correcto.");
        System.out.println("Inspector: Estado de los discos de freno de: " + id + "correcto.");
        System.out.println("Inspector: Estado del filtro antipolucion de: " + id + "correcto.");

        estadoEnsamblado = false;
        notify(); // Notifica a la linea de ensamblaje que puede continuar

    }
}
