import java.io.*;
import java.net.*;

public class ServidorTCP {

    // Método para contar las vocales y consonantes de un texto
    public static String contarVocalesYConsonantes(String texto) {
        int contadorVocales = 0, contadorConsonantes = 0;
        texto = texto.toLowerCase(); // Convertir a minúsculas para simplificar la comprobación

        for (int i = 0; i < texto.length(); i++) {
            char ch = texto.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') { // Comprobar si es vocal
                contadorVocales++;
            } else if (ch >= 'a' && ch <= 'z') { // Comprobar si es consonante (letra que no es vocal)
                contadorConsonantes++;
            }
        }

        // Crear y retornar el resultado como un String
        return "El texto contiene " + contadorVocales + " vocales y " + contadorConsonantes + " consonantes.";
    }

    public static void main(String[] args) throws IOException {
        int portNumber = 12345; // Puerto donde el servidor escuchará

        // Intentar crear un ServerSocket y aceptar conexiones
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Servidor iniciado. Escuchando en el puerto " + portNumber);

            // Bucle infinito para aceptar múltiples conexiones
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // Aceptar conexión del cliente
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String inputLine;
                    // Leer mensajes del cliente y responder
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Mensaje recibido del cliente: " + inputLine);
                        String resultado = contarVocalesYConsonantes(inputLine); // Procesar el texto recibido
                        out.println(resultado); // Enviar el resultado al cliente
                    }
                } catch (IOException e) {
                    System.out.println("Error al manejar la conexión del cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Excepción al intentar escuchar en el puerto " + portNumber);
            System.out.println(e.getMessage());
        }
    }
}
