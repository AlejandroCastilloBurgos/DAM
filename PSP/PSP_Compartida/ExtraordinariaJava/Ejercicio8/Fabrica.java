package Ejercicio8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Queue;

public class Fabrica {
    private final Queue<String> almacenamiento = new LinkedList<>();
    private final int capacidad = 30;

    public synchronized void generarBotella() throws InterruptedException {
        while (almacenamiento.size() == capacidad) {
            wait();
        }

        almacenamiento.add("Botella");
        System.out.println(Thread.currentThread().getName() + " generó una botella de agua");
    }

    public synchronized void entregarBotella(int cantidad) throws InterruptedException {
        while (almacenamiento.size() < cantidad) {
            wait();
        }
        for (int i = 0; i < cantidad; i++) {
            almacenamiento.poll();
        }
        System.out.println(Thread.currentThread().getName() + "Entrego" + cantidad + "de botellas de agua.");
        System.out.println("Quedan " + almacenamiento.size() + " botellas de agua.");
        notifyAll();

    }

    public synchronized int contarBotellas() {
        return almacenamiento.size();
    }
}
