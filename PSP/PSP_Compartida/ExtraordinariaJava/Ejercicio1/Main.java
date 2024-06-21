package Ejercicio1;

public class Main {
    public static void main(String[] args) {
        CarritoCompras carrito = new CarritoCompras(10); // capacidad del carrito
        Thread[] usuarios = new Thread[5]; // Thread para crear n usuarios
        // for que crea los usuarios
        for (int i = 0; i < 5; i++) {
            usuarios[i] = new Thread(new Usuario(carrito), "Usuario-" + (i + 1));
            usuarios[i].start();
        }
        // implementa el thread
        for (Thread usuario : usuarios) {
            try {
                usuario.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Contenido final del carrito: " + carrito.getProductos());
    }
}