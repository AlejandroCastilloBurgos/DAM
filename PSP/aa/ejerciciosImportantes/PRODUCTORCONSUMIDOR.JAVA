/*Este código utiliza la interfaz Runnable y la clase Thread para crear dos hilos:
 uno para el productor y otro para el consumidor. El productor lee caracteres del archivo de texto y los coloca en un BlockingQueue,
 mientras que el consumidor obtiene los caracteres del BlockingQueue y los muestra por pantalla. Al finalizar cada hilo, se imprime un mensaje indicando que ha finalizado. */

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
        Consumidor consumidor = new Consumidor(cola);

        Thread hiloProductor = new Thread(productor);
        Thread hiloConsumidor = new Thread(consumidor);

        hiloProductor.start();
        hiloConsumidor.start();

        // Esperar a que ambos hilos terminen antes de salir
        try {
            hiloProductor.join();
            hiloConsumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
