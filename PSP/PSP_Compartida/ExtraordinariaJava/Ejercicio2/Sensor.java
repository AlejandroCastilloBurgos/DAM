package Ejercicio2;
//cliente

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sensor implements Runnable {
    private final int sensorId;
    private final String serverAddress;
    private final int serverPort;

    public Sensor(int sensorId, String serverAddress, int serverPort) {
        this.sensorId = sensorId;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(serverAddress);
            Random random = new Random();

            while (true) {
                // Generar una distancia aleatoria entre 0 y 100
                int distancia = random.nextInt(101);
                String mensaje = "Sensor " + sensorId + ": " + distancia;

                byte[] buffer = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, serverPort);
                socket.send(packet);

                // Esperar 1 segundo antes de enviar el siguiente dato
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
