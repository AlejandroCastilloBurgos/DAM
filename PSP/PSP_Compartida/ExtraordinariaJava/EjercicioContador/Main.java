package EjercicioContador;

// Main.java
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Contador contador = new Contador();
        Random random = new Random();

        Runnable incrementador = () -> {
            for (int i = 0; i < 5; i++) {
                contador.incrementar();
                try {
                    Thread.sleep(100 + random.nextInt(400));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable decrementador = () -> {
            for (int i = 0; i < 5; i++) {
                contador.decrementar();
                try {
                    Thread.sleep(100 + random.nextInt(400));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(incrementador, "Thread-1");
        Thread t2 = new Thread(incrementador, "Thread-2");
        Thread t3 = new Thread(incrementador, "Thread-3");
        Thread t4 = new Thread(decrementador, "Thread-4");
        Thread t5 = new Thread(decrementador, "Thread-5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
