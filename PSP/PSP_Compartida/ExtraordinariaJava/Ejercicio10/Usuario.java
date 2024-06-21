package Ejercicio10;

import java.util.Random;

public class Usuario implements Runnable {
    private final Biblioteca biblioteca;
    private final Random random = new Random();

    public Usuario(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                biblioteca.prestarLibro();
                Thread.sleep(random.nextInt(1000) + 500);
            }

            for (int i = 0; i < 2; i++) {
                biblioteca.devolverLibro(new Libro("Libro devuelto por " + Thread.currentThread().getName()));
                Thread.sleep(random.nextInt(1000) + 500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
