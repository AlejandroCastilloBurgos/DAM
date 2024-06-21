import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ReceptorBroadcast {
    public static void main(String[] args) {
        try {
            // El puerto en el que el receptor escuchará los mensajes de broadcast
            int port = 4321;
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
                System.out.println("Mensaje recibido: " + received);

                // Aquí implementas la lógica para abrir el editor si el mensaje coincide
                if ("editor".equals(received.trim())) {
                    // Asegúrate de que este comando sea seguro y solo permita acciones esperadas
                    // Runtime.getRuntime().exec("notepad.exe"); // Comando para Windows
                    Runtime.getRuntime().exec("gedit");
                    // En macOS podría ser Runtime.getRuntime().exec("open -a TextEdit");
                }

                // Opcional: si deseas terminar el bucle después de recibir un mensaje, puedes
                // descomentar la siguiente línea.
                // break;
            }
        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
