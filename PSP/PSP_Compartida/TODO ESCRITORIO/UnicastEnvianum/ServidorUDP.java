
// UDPServer.java
import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String args[]) {
        // Verificar los argumentos
        if (args.length < 1) {
            System.out.println("Uso: java UDPServer <puerto>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try {
            // Crear un socket UDP
            DatagramSocket serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024]; // Agregado para enviar datos

            System.out.printf("Servidor escuchando en el puerto %d...\n", port);

            while (true) {
                // Recibir el paquete
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Mensaje recibido: " + message);

                // Si se desea enviar una respuesta al cliente, se puede hacer aquí
                // Obtener la dirección IP y el puerto del cliente para enviar la respuesta
                InetAddress IPAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                // Preparar y enviar la respuesta
                String response = "Mensaje recibido: " + message;
                sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
                serverSocket.send(sendPacket); // Enviar la respuesta
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}