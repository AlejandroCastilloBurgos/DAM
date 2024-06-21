
//servidor
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

    public static void main(String[] args) {
        // Verificar los argumentos
        if (args.length < 2) { // ponemos aqui 2 de args que seran ip servidor, puerto y mensaje
            System.out.println(
                    "Uso: java UDPServer <puerto> <ruta_log>");
            return;

        }

        final int PORT = Integer.parseInt(args[0]);
        final String LOG_FILE = args[1];

        try (DatagramSocket socket = new DatagramSocket(PORT);
                BufferedWriter logWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {

            byte[] buffer = new byte[256];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String mensaje = new String(packet.getData(), 0, packet.getLength());
                String tiempo = LocalDateTime.now().format(formatter);

                String logEntry = tiempo + " - " + mensaje;
                System.out.println(logEntry);
                logWriter.write(logEntry);
                logWriter.newLine();
                logWriter.flush();

                // Enviar respuesta al cliente
                String response = "Mensaje recibido y almacenado, nos acercaremos con ayuda.";
                byte[] sendBuffer = response.getBytes();
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress,
                        clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}