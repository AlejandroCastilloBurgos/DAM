package Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Usuario implements Runnable {
    private final CarritoCompras carrito;
    private final Random random = new Random();

    public Usuario(CarritoCompras carrito) {
        this.carrito = carrito;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                // Producto producto = new Producto("Producto-" + random.nextInt(100)); //Con
                // esto hacemos que cada producto generado sea "unico" sin necesidad de
                // esscribir;
                int prueba = random.nextInt(100);
                Producto producto = new Producto("Pantalones Vaqueros");
                Producto producto2 = new Producto("Cinturon");
                Producto producto3 = new Producto("AF1");
                Producto producto4 = new Producto("Vapormax");
                Producto producto5 = new Producto("Gorra DSQ2");
                Producto producto6 = new Producto("Riñonera Burberry");
                Producto producto7 = new Producto("Sudadera CK");
                Producto producto8 = new Producto("Carhartt");
                Producto producto9 = new Producto("Camiseta Scuffers");
                Producto producto10 = new Producto("Jersey Lacoste");
                Producto producto11 = new Producto("Polo Ralph Lauren");
                Producto producto12 = new Producto("Vaqueros Rotos");

                if (100 > prueba && prueba > 50) {
                    carrito.agregarProducto(producto);
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.eliminarProducto();
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.agregarProducto(producto2);
                    Thread.sleep(random.nextInt(1000) + 500);
                } else if (prueba < 25 && prueba > 10) {
                    carrito.agregarProducto(producto3);
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.eliminarProducto();
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.agregarProducto(producto4);
                    Thread.sleep(random.nextInt(1000) + 500);
                } else if (prueba < 10 && prueba > 0) {
                    carrito.agregarProducto(producto5);
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.eliminarProducto();
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.agregarProducto(producto6);
                    Thread.sleep(random.nextInt(1000) + 500);
                } else if (prueba < 25 && prueba > 10) {
                    carrito.agregarProducto(producto7);
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.eliminarProducto();
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.agregarProducto(producto8);
                    Thread.sleep(random.nextInt(1000) + 500);
                } else if (prueba < 100 && prueba > 75) {
                    carrito.agregarProducto(producto9);
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.eliminarProducto();
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.agregarProducto(producto10);
                    Thread.sleep(random.nextInt(1000) + 500);
                } else {
                    carrito.agregarProducto(producto11);
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.eliminarProducto();
                    Thread.sleep(random.nextInt(1000) + 500);
                    carrito.agregarProducto(producto12);
                    Thread.sleep(random.nextInt(1000) + 500);
                }

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
/*
 * try {
 * for (int i = 0; i < 2; i++) {
 * // Crear un nuevo producto con un nombre aleatorio
 * Producto producto = new Producto("Producto-" + random.nextInt(100));
 * carrito.agregarProducto(producto); // Agregar el producto al carrito
 * Thread.sleep(random.nextInt(1000) + 500); // Esperar un tiempo aleatorio
 * entre 500 y 1500 ms
 * carrito.eliminarProducto(); // Eliminar un producto del carrito
 * Thread.sleep(random.nextInt(1000) + 500); // Esperar un tiempo aleatorio
 * entre 500 y 1500 ms
 * carrito.agregarProducto(producto); // Agregar nuevamente el producto al
 * carrito
 * Thread.sleep(random.nextInt(1000) + 500); // Esperar un tiempo aleatorio
 * entre 500 y 1500 ms
 * }
 * } catch (InterruptedException e) {
 * Thread.currentThread().interrupt(); // Manejar la interrupción del thread
 * }
 */