package Ejercicio5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Queue;

class Floristeria {
    private final Queue<String> almacenamiento = new LinkedList<>();
    private final int capacidad = 30;

    public synchronized void generarRamo() throws InterruptedException {
        while (almacenamiento.size() == capacidad) {
            wait();
        }
        almacenamiento.add("Ramo");
        System.out.println(Thread.currentThread().getName() + " generó un ramo.");
        notifyAll();
    }

    public synchronized void entregarRamos(int cantidad) throws InterruptedException {
        while (almacenamiento.size() < cantidad) {
            wait();
        }
        for (int i = 0; i < cantidad; i++) {
            almacenamiento.poll();
        }
        System.out.println(Thread.currentThread().getName() + " entregó " + cantidad + " ramos.");
        notifyAll();
    }

    public synchronized int contarRamos() {
        return almacenamiento.size();
    }
}
