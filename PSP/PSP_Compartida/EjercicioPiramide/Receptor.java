
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * LectorParaSpam
 */

public class Receptor {

    int puertoEscucha;
    int puertoEnvio;

    ArrayList<mensajeListener> listeners;

    public interface mensajeListener {
        void piramideRecibida(int alto, char caracter);
    }

    public void addListener(mensajeListener listener) {
        listeners.add(listener);
    }

    Receptor(int puertoEscucha, int puertoEnvio) {
        this.puertoEscucha = puertoEscucha;
        this.puertoEnvio = puertoEnvio;
        this.listeners = new ArrayList<>();
    }

    public void comenzarLectura() {
        try {
            // El puerto en el que el receptor escuchará los mensajes de broadcast
            int port = puertoEscucha;
            // Crear un socket para escuchar en el puerto especificado.
            DatagramSocket socket = new DatagramSocket(port, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);

            while (true) {
                System.out.println("Esperando mensaje de broadcast en el puerto " + port + "...");

                // Preparar el paquete para recibir el mensaje.
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                // Esperar a recibir un paquete
                socket.receive(packet);

                // Convertir el mensaje recibido a un String y mostrarlo.
                String received = new String(packet.getData(), 0, packet.getLength());
                // Supongamos que el mensaje recibido es del formato "alto,ancho,caracter"
                String[] parts = received.split(" ");
                if (parts.length == 2) {
                    int alto = Integer.parseInt(parts[0]);
                    char caracter = parts[1].charAt(0);

                    System.out.println("Notificando a listeners...");

                    for (mensajeListener listener : listeners) {
                        listener.piramideRecibida(alto, caracter);
                    }
                }

                // Aquí implementas la lógica para abrir el editor si el mensaje coincide
                if ("fin".equals(received.toLowerCase())) {
                    // Código a ejecutar si received es igual a "fin", ignorando
                    // mayúsculas/minúsculas
                    break;
                }
                // Opcional: si deseas terminar el bucle después de recibir un mensaje, puedes
                // descomentar la siguiente línea.
                // break;
                System.out.println(received);
            }
        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
