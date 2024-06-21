package ContCaracterUDP;

// UDPServer.java
import java.io.*;
import java.net.*;

public class Servidor {
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
                String response = "Mensaje recibido: " + contarCaracteres(message);
                sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
                serverSocket.send(sendPacket); // Enviar la respuesta

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String contarCaracteres(String texto) {
        int vocales = 0, consonantes = 0, restoCaracteres = 0;

        // Convertimos el texto a minúsculas para simplificar las comparaciones.
        texto = texto.toLowerCase();

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // Comprobamos si el carácter es una vocal.
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vocales++;
            }
            // Comprobamos si el carácter es una letra (consonante).
            else if ((c >= 'a' && c <= 'z')) {
                consonantes++;
            }
            // Si no es ni vocal ni consonante, incrementamos el resto de caracteres.
            else {
                restoCaracteres++;
            }
        }

        // Construimos el string de resultado y lo retornamos.
        return vocales + ":" + consonantes + ":" + restoCaracteres;
    }
}
