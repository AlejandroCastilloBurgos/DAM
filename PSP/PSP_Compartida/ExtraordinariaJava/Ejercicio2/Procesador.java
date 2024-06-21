package Ejercicio2;

//servidor
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Procesador {
    private static final int PORT = 9876;
    private static final String LOG_FILE = "distancia_log.txt";

    public static void main(String[] args) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
