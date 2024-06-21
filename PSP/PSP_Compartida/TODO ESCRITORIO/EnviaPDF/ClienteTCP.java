import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) {
        String hostName = "localhost"; // Dirección del servidor (IP o hostname)
        int portNumber = 12345; // Puerto al que se conectará, debe ser el mismo que el del servidor

        try (Socket socket = new Socket(hostName, portNumber)) {
            InputStream inputStream = socket.getInputStream(); // Obtener el stream de entrada para leer los datos
                                                               // enviados por el servidor
            FileOutputStream fos = new FileOutputStream("archivo_recibido.txt"); // Cambiar la ruta y nombre del archivo
                                                                                 // según necesidad
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Leer los datos del stream y escribirlos en el archivo
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Archivo recibido y guardado.");

            fos.close();
            inputStream.close();
            // No es necesario cerrar el socket aquí, ya que se cierra automáticamente al
            // salir del try-with-resources

        } catch (UnknownHostException e) {
            System.err.println("No se conoce el host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo obtener E/S para la conexión a " + hostName);
            System.exit(1);
        }
    }
}
