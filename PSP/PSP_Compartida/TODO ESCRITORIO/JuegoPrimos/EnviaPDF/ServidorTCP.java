import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        int portNumber = 12345; // Puerto donde el servidor estará escuchando

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Servidor iniciado. Escuchando en el puerto " + portNumber);

            while (true) { // Bucle infinito para aceptar múltiples clientes
                try (Socket clientSocket = serverSocket.accept()) { // Aceptar conexión del cliente
                    System.out.println("Cliente conectado.");

                    // Enviar archivo
                    File file = new File("/home/casti/Desktop/dummy.pdf"); // Asegúrate de poner la ruta correcta
                    try (FileInputStream fis = new FileInputStream(file);
                            OutputStream out = clientSocket.getOutputStream()) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;

                        while ((bytesRead = fis.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                        System.out.println("Archivo enviado correctamente.");
                    }
                } catch (IOException e) {
                    System.out.println("Error al manejar la conexión del cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "Excepción al intentar escuchar en el puerto " + portNumber + " o al escuchar la conexión");
            e.printStackTrace();
        }
    }
}
