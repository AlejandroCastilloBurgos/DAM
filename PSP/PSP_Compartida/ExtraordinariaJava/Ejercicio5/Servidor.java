package Ejercicio5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Servidor implements Runnable {
    private Floristeria floristeria;

    public Servidor(Floristeria floristeria) {
        this.floristeria = floristeria;
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(9876)) {
            byte[] buffer = new byte[256];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                int cantidad = Integer.parseInt(message.trim());

                int entregados;
                synchronized (floristeria) {
                    if (floristeria.contarRamos() >= cantidad) {
                        floristeria.entregarRamos(cantidad);
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
