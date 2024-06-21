package Ejercicio6;

import java.util.Random;

public class Usuario implements Runnable {
    private final Transaccion transaccion;
    private final Random random = new Random();

    public Usuario(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    @Override
    public void run() {
        try {
            Cuenta cuenta = new Cuenta(Thread.currentThread().getName(), random.nextInt(500) + 100);
            transaccion.agregarCuenta(cuenta);

            for (int i = 0; i < 2; i++) {
                int accion = random.nextInt(3) + 1;
                if (accion == 1) {
                    transaccion.retiraDinero(cuenta);
                } else if (accion == 2) {
                    transaccion.ingresarDinero(cuenta);
                } else {
                    transaccion.salarioDisponible(cuenta);
                }
                Thread.sleep(random.nextInt(1000) + 500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
