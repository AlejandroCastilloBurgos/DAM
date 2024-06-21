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
            while (true) {
                String mensaje = in.readLine();
                if (mensaje == null) {
                    break;
                }
                System.out.println("Respuesta del servidor: " + mensaje); // Leer respuesta del servidor
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
