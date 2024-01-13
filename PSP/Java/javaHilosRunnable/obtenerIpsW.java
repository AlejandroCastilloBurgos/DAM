
/*Crea un programa Java que describa las ips que tiene en ordenador en linux */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class obtenerIpsW {
    public static void main(String[] args) {

        String[] comandos = { "ifconfig", "-a" }; // Array que contiene los comandos que se ejecutarán.

        try {
            // Crea un objeto ProcessBuilder que representa un proceso
            ProcessBuilder procesoCreado = new ProcessBuilder(comandos);

            // Inicio del proceso
            Process proceso = procesoCreado.start();

            // Lectura de la salida del Proceso
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error intentando crear un proceso: " + e.getMessage());
        }
    }
}