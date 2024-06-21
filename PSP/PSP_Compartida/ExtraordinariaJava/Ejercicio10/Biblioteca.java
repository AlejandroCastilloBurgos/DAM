package Ejercicio10;

import java.util.LinkedList;
import java.util.Queue;

public class Biblioteca {
    private final Queue<Libro> libros = new LinkedList<>();
    private final int capacidad = 20;

    public Biblioteca() {
        for (int i = 1; i <= capacidad; i++) {
            libros.add(new Libro("Libro " + i));
        }
    }

    public synchronized void prestarLibro() throws InterruptedException {
        while (libros.isEmpty()) {
            wait();
        }
        Libro libro = libros.poll();
        System.out.println(Thread.currentThread().getName() + " prestó: " + libro);
        notifyAll();
    }

    public synchronized void devolverLibro(Libro libro) throws InterruptedException {
        while (libros.size() == capacidad) {
            wait();
        }
        libros.add(libro);
        System.out.println(Thread.currentThread().getName() + " devolvió: " + libro);
        notifyAll();
    }

    public synchronized int contarLibros() {
        return libros.size();
    }
}
