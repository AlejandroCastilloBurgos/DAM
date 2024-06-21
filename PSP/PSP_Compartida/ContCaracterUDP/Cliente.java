package ContCaracterUDP;

// UDPClient.java
import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String args[]) {
        // Verificar los argumentos
        if (args.length < 3) { // ponemos aqui 3 de args que seran ip servidor, puerto y mensaje
            System.out.println("Uso: java UDPClient <ip_servidor> <puerto_servidor> <mensaje>");
            return;
        }

        String serverIP = args[0]; // definimos que el argumento 0 (El de la primera posicion ser el ip del
                                   // servidor)
        int serverPort = Integer.parseInt(args[1]); // El segundo sera el puerto
        String messageToSend = args[2]; // el tercero sera el mensaje

        try {
            // Crear un socket UDP
            DatagramSocket clientSocket = new DatagramSocket(); // creamos un nuevo socket que es el encargado de mandar
                                                                // informacion via UDP
            InetAddress IPAddress = InetAddress.getByName(serverIP);// asignamos la direccion ip que sale de serverIP
                                                                    // que es la variable de args que hemos metido
            byte[] sendData = new byte[1024];// declaramos un array de datos

            sendData = messageToSend.getBytes();// metemos dentro del array que hemos creado anteriormente el mensaje
                                                // que hemos escrito en la variable
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
