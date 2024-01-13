package ejerciciosImportantes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Productor implements Runnable {
    private String nombreArchivo;
    private BlockingQueue<Character> cola;

    public Productor(String nombreArchivo, BlockingQueue<Character> cola) {
        this.nombreArchivo = nombreArchivo;
        this.cola = cola;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            int c;
            while ((c = reader.read()) != -1) {
                char caracter = (char) c;
                cola.put(caracter);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Proceso productor ha finalizado.");
        }
    }
}

class Consumidor implements Runnable {
    private BlockingQueue<Character> cola;

    public Consumidor(BlockingQueue<Character> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char caracter = cola.take();
                System.out.print(caracter);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("\nProceso consumidor ha finalizado.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Character> cola = new LinkedBlockingQueue<>();

        Productor productor = new Productor("nombre_archivo.txt", cola);
        Consumidor consumidor1 = new Consumidor(cola);
        Consumidor consumidor2 = new Consumidor(cola);

        Thread hiloProductor = new Thread(productor);
        Thread hiloConsumidor1 = new Thread(consumidor1);
        Thread hiloConsumidor2 = new Thread(consumidor2);

        hiloProductor.start();
        hiloConsumidor1.start();
        hiloConsumidor2.start();

        // Esperar a que el productor termine antes de comprobar el estado de los
        // consumidores
        try {
            hiloProductor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Comprobar el estado de los consumidores
        System.out.println("Estado del consumidor 1: " + hiloConsumidor1.getState());
        System.out.println("Estado del consumidor 2: " + hiloConsumidor2.getState());

        // Esperar a que todos los consumidores terminen antes de salir
        try {
            hiloConsumidor1.join();
            hiloConsumidor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
