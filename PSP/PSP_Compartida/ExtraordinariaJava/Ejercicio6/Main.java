package Ejercicio6;

public class Main {
    public static void main(String[] args) {
        Transaccion transaccion = new Transaccion(); // Instancia de transacción
        Thread[] usuarios = new Thread[5]; // Thread para crear 5 usuarios

        // for que crea los usuarios
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = new Thread(new Usuario(transaccion), "Usuario-" + (i + 1));
            usuarios[i].start();
        }

        // Esperar a que todos los threads terminen
        for (Thread usuario : usuarios) {
            try {
                usuario.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
/*
 * Simulación de un Banco con Transacciones Concurrentes:
 * Desarrolla un programa en Java que simule el funcionamiento de un banco con
 * múltiples clientes que realizan transacciones concurrentemente, como
 * depósitos, retiros y consultas de saldo. Utiliza mecanismos de
 * sincronización, como locks o semáforos, para garantizar la consistencia de
 * los datos compartidos, como el saldo de las cuentas bancarias.
 */