import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        int portNumber = 12345; // Puerto donde el servidor estará escuchando

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Servidor iniciado. Escuchando en el puerto " + portNumber);

            // Aceptar conexiones de clientes
            while (true) { // Bucle infinito para aceptar múltiples clientes
                try (Socket clientSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    // Enviar listado de archivos al cliente
                    File dir = new File("/var/files/");
                    File[] files = dir.listFiles();

                    if (files != null) {
                        for (File file : files) {
                            if (file.isFile()) {
                                out.println(file.getName());
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al manejar la conexión del cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(
                    "Excepción al intentar escuchar en el puerto " + portNumber + " o al escuchar la conexión");
            System.out.println(e.getMessage());
        }
    }
}