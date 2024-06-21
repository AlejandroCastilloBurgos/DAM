package Ejercicio6;

import java.util.ArrayList;
import java.util.List;

class Transaccion {
    private final List<Cuenta> cuentas = new ArrayList<>();

    public synchronized void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public synchronized void salarioDisponible(Cuenta cuenta) {
        int dinero = cuenta.getCantidad();
        System.out.println(
                Thread.currentThread().getName() + " - Los efectivos de los que disponen son de: " + dinero + "$");
        notifyAll();
    }

    public synchronized void retiraDinero(Cuenta cuenta) throws InterruptedException {
        while (cuenta.getCantidad() <= 0) {
            wait();
        }
        int dinero = cuenta.getCantidad() - 100;
        cuenta.setCantidad(dinero);
        System.out.println(Thread.currentThread().getName() + " - Se ha retirado dinero de tu cuenta: " + dinero + "$");
        notifyAll();
    }

    public synchronized void ingresarDinero(Cuenta cuenta) {
        int dinero = cuenta.getCantidad() + 100;
        cuenta.setCantidad(dinero);
        System.out.println(Thread.currentThread().getName() + " - Se han añadido a tu cuenta: " + dinero + "$");
        notifyAll();
    }

    public synchronized List<Cuenta> getCuentas() {
        return new ArrayList<>(cuentas);
    }
}
