package Ejercicio10;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Thread[] usuarios = new Thread[5];

        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = new Thread(new Usuario(biblioteca), "Usuario-" + (i + 1));
            usuarios[i].start();
        }

        for (Thread usuario : usuarios) {
            try {
                usuario.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Número final de libros en la biblioteca: " + biblioteca.contarLibros());
    }
}
