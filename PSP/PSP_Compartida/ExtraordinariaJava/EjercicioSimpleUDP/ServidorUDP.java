package EjercicioSimpleUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(9876)) {
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                // Recibir mensaje del cliente
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Contar número de letras
                int letterCount = message.replaceAll("[^a-zA-Z]", "").length();
                String response = "Número de letras: " + letterCount;

                // Enviar respuesta al cliente
                byte[] sendBuffer = response.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress,
                        clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
