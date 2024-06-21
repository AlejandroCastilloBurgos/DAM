import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloAscensores implements Runnable {
    private Socket socket;

    public HiloAscensores(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Comando recibido: " + inputLine);
                // Aquí procesarías el comando recibido y realizarías las acciones necesarias.
                // Por ejemplo, podrías decidir enviar un comando de vuelta al ascensor, si
                // fuera necesario.
            }
        } catch (IOException e) {
            System.out.println("Error manejando el socket del ascensor: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error cerrando el socket: " + e.getMessage());
            }
        }
    }
}
