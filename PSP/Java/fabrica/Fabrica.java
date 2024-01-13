import java.util.LinkedList;

class Almacen {
    private static final int CAPACIDAD_MAXIMA = 5;
    private LinkedList<Integer> productos = new LinkedList<>();

    public synchronized void producir(int producto) {
        while (productos.size() == CAPACIDAD_MAXIMA) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        productos.add(producto);
        System.out.println("Producto producido: " + producto);
        notify();
    }

    public synchronized int consumir() {
        while (productos.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int producto = productos.remove();
        System.out.println("Producto consumido: " + producto);
        notify();
        return producto;
    }
}

class Productor implements Runnable {
    private Almacen almacen;

    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            almacen.producir(i);
        }
    }
}

class Consumidor implements Runnable {
    private Almacen almacen;

    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            almacen.consumir();
        }
    }
}

public class Fabrica {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();

        Thread productorThread = new Thread(new Productor(almacen));
        Thread consumidorThread = new Thread(new Consumidor(almacen));

        productorThread.start();
        consumidorThread.start();
    }
}
