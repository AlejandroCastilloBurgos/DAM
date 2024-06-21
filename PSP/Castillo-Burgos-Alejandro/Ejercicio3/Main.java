package Ejercicio3;

public class Main {

    public static void main(String[] args) {
        int numLineas = 1; // Número de termometros
        MedidorTemperatura[] lineas = new MedidorTemperatura[numLineas];

        // Crear y empezar termometro
        for (int i = 0; i < numLineas; i++) {
            lineas[i] = new MedidorTemperatura(i + 1, i);
            new Thread(lineas[i]).start(); // Iniciar cada termometro en su propio hilo, irrelevante porque en este caso
                                           // solo hay 1
        }

        // Crear y empezar el Teletipo
        Teletipo Teletipo = new Teletipo(lineas);
        new Thread(Teletipo).start(); // Iniciar el Teletipo en su propio hilo
    }
}
