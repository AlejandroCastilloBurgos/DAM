package Ejercicio8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor implements Runnable {
    private Fabrica fabrica;

    public Servidor(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(9876)) {
            byte[] buffer = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                int cantidad = Integer.parseInt(mensaje.trim());

                int entregados;
                synchronized (fabrica) {
                    if (fabrica.contarBotellas() >= cantidad) {
                        fabrica.entregarBotella(cantidad);
                        entregados = cantidad;
                    } else {
                        entregados = -1;
                    }
                }

                String respuesta = String.valueOf(entregados);
                byte[] responseBuffer = respuesta.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, address,
                        port);
                socket.send(responsePacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
