import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        int portNumber = 12345; // Puerto donde el servidor estará escuchando

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Servidor iniciado. Escuchando en el puerto " + portNumber);
            try (Socket clientSocket = serverSocket.accept(); // Aceptar conexiones de clientes
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) { // Leer el mensaje del cliente
                    System.out.println("Cliente: " + inputLine);
                    System.out.println("Introduzca su mensaje:");
                    String userInput;
                    while ((userInput = stdIn.readLine()) != null) {
                        out.println(userInput); // Enviar mensaje al cliente
                        System.out.println("Respuesta del servidor: " + in.readLine()); // Leer respuesta del servidor
                        if ("fin".equalsIgnoreCase(userInput)) {
                            break; // Salir del bucle si el usuario escribe "fin"
                        }
                        System.out.println("Introduzca su mensaje:");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "Excepción al intentar escuchar en el puerto " + portNumber + " o al escuchar la conexión");
            System.out.println(e.getMessage());
        }
    }
}
