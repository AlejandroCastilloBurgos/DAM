package Ejercicio4;

// UDPServer.java
import java.io.*;
import java.net.*;

public class servidorUDP {
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
                int acaba = Integer.parseInt(message);
                if (acaba == -1) {
                    break;
                }

                // Si se desea enviar una respuesta al cliente, se puede hacer aquí
                // Obtener la dirección IP y el puerto del cliente para enviar la respuesta
                InetAddress IPAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                // Preparar y enviar la respuesta
                String response = "\n" + generaPiramide(message) + "\n" + generaRombo(message) + "\n"
                        + esPrimo(message);
                sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
                serverSocket.send(sendPacket); // Enviar la respuesta

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generaPiramide(String mensaje) {

        int alto = Integer.parseInt(mensaje);
        String asterisco = "*";

        StringBuilder piramide = new StringBuilder();

        for (int i = 0; i < alto; i++) {
            // Espacios en blanco a la izquierda
            for (int j = 0; j < alto - i - 1; j++) {
                piramide.append(" ");
            }
            // Caracteres del piso actual de la pirámide
            for (int k = 0; k < (2 * i + 1); k++) {
                piramide.append(asterisco);
            }
            // Saltar a la siguiente línea si no es el último piso
            if (i < alto - 1) {
                piramide.append("\n");
            }
        }

        return piramide.toString();
    }

    public static String generaRombo(String mensaje) {

        int alto = Integer.parseInt(mensaje);
        String asterisco = "*";

        StringBuilder rombo = new StringBuilder();

        for (int i = 0; i < alto; i++) {
            // Espacios en blanco a la izquierda
            for (int j = 0; j < alto - i - 1; j++) {
                rombo.append(" ");
            }
            // Caracteres del piso actual de la pirámide
            for (int k = 0; k < (2 * i + 1); k++) {
                rombo.append(asterisco);
            }
            // Saltar a la siguiente línea si no es el último piso
            if (i < alto - 1) {
                rombo.append("\n");
            }
        }
        for (int i = 0; i < alto; i++) {
            // Espacios en blanco a la izquierda
            for (int j = 0; j < alto - i - 1; j++) {
                rombo.append(" ");
            }
            // Caracteres del piso actual de la pirámide
            for (int k = 0; k < (2 * i + 1); k++) {
                rombo.append(asterisco);
            }
            // Saltar a la siguiente línea si no es el último piso
            if (i < alto - 1) {
                rombo.append("\n");
            }
        }

        return rombo.toString() + "\n" + rombo.reverse();
    }

    public static String esPrimo(String mensaje) {

        int n = Integer.parseInt(mensaje);
        if (n == 2 || n == 1) {
            return "Este numero es primo";
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return "Este numero no es primo";
            }
        }
        return "Este numero es primo";
    }

}
