package Ejercicio8;

import java.util.Random;

public class Empleado implements Runnable {
    private Fabrica fabrica;
    private Random random = new Random();

    public Empleado(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        try {
            while (true) {
                fabrica.generarBotella();
                Thread.sleep(random.nextInt(1000) + 3000);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
