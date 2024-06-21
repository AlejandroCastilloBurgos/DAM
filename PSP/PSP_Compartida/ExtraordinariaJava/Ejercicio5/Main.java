package Ejercicio5;

public class Main {
    public static void main(String[] args) {
        Floristeria floristeria = new Floristeria();
        Thread[] empleados = new Thread[5];

        for (int i = 0; i < empleados.length; i++) {
            empleados[i] = new Thread(new Empleado(floristeria), "Empleado" + (i + 1));
            empleados[i].start();
        }

        Thread servidor = new Thread(new Servidor(floristeria), "ServidorUDP");
        servidor.start();
    }
}
