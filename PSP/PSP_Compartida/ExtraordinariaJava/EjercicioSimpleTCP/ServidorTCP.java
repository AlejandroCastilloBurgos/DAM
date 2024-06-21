import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        int portNumber = 12345; // Puerto donde el servidor estará escuchando

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Servidor iniciado. Escuchando en el puerto " + portNumber);
            try (Socket clientSocket = serverSocket.accept(); // Aceptar conexiones de clientes
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) { // Leer el mensaje del cliente
                    System.out.println("Mensaje recibido del cliente: " + inputLine);
                    out.println("Echo del servidor: " + inputLine); // Enviar respuesta al cliente
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "Excepción al intentar escuchar en el puerto " + portNumber + " o al escuchar la conexión");
            System.out.println(e.getMessage());
        }
    }
}
