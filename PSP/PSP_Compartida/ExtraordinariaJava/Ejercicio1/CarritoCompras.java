package Ejercicio1;

import java.util.ArrayList;
import java.util.List;

class CarritoCompras {
    private final List<Producto> productos = new ArrayList<>(); // Lista para almacenar los productos en el carrito
    private final int capacidad; // definimos capacidad maxima del carrito

    public CarritoCompras(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void agregarProducto(Producto producto) throws InterruptedException {
        while (productos.size() >= capacidad) {
            wait();
        }
        productos.add(producto);
        System.out.println(Thread.currentThread().getName() + " agregó: " + producto);
        notifyAll();
    }

    public synchronized void eliminarProducto() throws InterruptedException {
        while (productos.size() == 0) {
            wait();
        }
        Producto eliminado = productos.remove(productos.size() - 1);
        System.out.println(Thread.currentThread().getName() + " eliminó: " + eliminado);
        notifyAll();
    }

    public synchronized List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }
}
