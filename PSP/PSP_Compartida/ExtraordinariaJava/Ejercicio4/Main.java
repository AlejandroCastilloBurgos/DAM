package Ejercicio4;

public class Main {

    public static void main(String[] args) {
        int numLineas = 3; // Número de líneas de ensamblaje
        LineaEnsamblaje[] lineas = new LineaEnsamblaje[numLineas];

        // Crear y empezar las líneas de ensamblaje
        for (int i = 0; i < numLineas; i++) {
            lineas[i] = new LineaEnsamblaje(i + 1);
            new Thread(lineas[i]).start(); // Iniciar cada línea de ensamblaje en su propio hilo
        }

        // Crear y empezar el inspector
        Inspector inspector = new Inspector(lineas);
        new Thread(inspector).start(); // Iniciar el inspector en su propio hilo
    }
}
