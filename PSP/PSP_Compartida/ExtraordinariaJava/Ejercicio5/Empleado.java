package Ejercicio5;

import java.util.Random;
import java.util.Random;

class Empleado implements Runnable {
    private Floristeria floristeria;
    private Random random = new Random();

    public Empleado(Floristeria floristeria) {
        this.floristeria = floristeria;
    }

    @Override
    public void run() {
        try {
            while (true) {
                floristeria.generarRamo();
                Thread.sleep(random.nextInt(1000) + 3000); // 3-4 segundos
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
