
// UDPClient.java
import java.io.*;
import java.net.*;

public class ClienteUDP {
    public static void main(String args[]) {
        // Verificar los argumentos
        if (args.length < 3) {
            System.out.println("Uso: java UDPClient <ip_servidor> <puerto_servidor> <mensaje>");
            return;
        }

        String serverIP = args[0];
        int serverPort = Integer.parseInt(args[1]);
        String messageToSend = args[2];

        try {
            // Crear un socket UDP
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(serverIP);
            byte[] sendData = new byte[1024];

            sendData = messageToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);

            System.out.println("Enviando mensaje al servidor...");
            clientSocket.send(sendPacket);

            // Continuación del UDPClient.java después de enviar el mensaje

            byte[] receiveData = new byte[1024]; // Buffer para recibir la respuesta
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket); // Esperar la respuesta
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Respuesta del servidor: " + response);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
