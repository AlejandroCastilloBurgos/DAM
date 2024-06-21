package Ejercicio2;

public class Main {
    public static void main(String[] args) {
        // Iniciar el servidor en un hilo separado
        Thread servidorThread = new Thread(() -> Procesador.main(args));
        servidorThread.start();

        // Crear e iniciar 5 sensores
        for (int i = 1; i <= 5; i++) {
            Thread sensorThread = new Thread(new Sensor(i, "localhost", 9876));
            sensorThread.start();
        }
    }
}
