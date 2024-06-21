import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost"; // Dirección del servidor (IP o hostname)
        int portNumber = 12345; // Puerto al que se conectará, debe ser el mismo que el del servidor

        try (Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Introduzca su mensaje:");
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput); // Enviar mensaje al servidor
                System.out.println("Respuesta del servidor: " + in.readLine()); // Leer respuesta del servidor
                if ("fin".equalsIgnoreCase(userInput)) {
                    break; // Salir del bucle si el usuario escribe "fin"
                }
                System.out.println("Introduzca su mensaje:");
            }
        } catch (UnknownHostException e) {
            System.err.println("No se conoce el host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo obtener E/S para la conexión a " + hostName);
            System.exit(1);
        }
    }
}
