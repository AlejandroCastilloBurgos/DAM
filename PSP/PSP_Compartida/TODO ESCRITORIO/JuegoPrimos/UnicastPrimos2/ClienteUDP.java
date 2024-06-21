
// UDPClient.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String args[]) {
        // Verificar los argumentos
        if (args.length < 2) { // ponemos aqui 2 de args que seran ip servidor, puerto y mensaje
            System.out.println("Uso: java UDPClient <ip_servidor> <puerto_servidor>");
            return;
        }

        String serverIP = args[0]; // definimos que el argumento 0 (El de la primera posicion ser el ip del
                                   // servidor)
        int serverPort = Integer.parseInt(args[1]); // El segundo sera el puerto

        try {
            // creamos el scanner
            Scanner scanner = new Scanner(System.in);
            // Crear un socket UDP
            DatagramSocket clientSocket = new DatagramSocket(); // creamos un nuevo socket que es el encargado de mandar
            // informacion via UDP
            while (true) {
                int numeroAleatorio = (int) (Math.random() * 99 + 1); // Genera un número aleatorio entre 1 y 99
                String mensaje = String.valueOf(numeroAleatorio); // Convierte el número a String

                if ("salir".equalsIgnoreCase(mensaje)) {
                    break; // Salir del bucle si el usuario escribe "salir"
                }

                InetAddress IPAddress = InetAddress.getByName(serverIP); // asignamos la direccion ip que sale de
                // serverIP que es la variable de args que hemos
                // metido
                byte[] sendData = new byte[1024]; // declaramos un array de datos

                sendData = mensaje.getBytes(); // metemos dentro del array que hemos creado anteriormente el mensaje que
                // hemos escrito en la variable
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort); // datagram
                // packet para crear un paquete con el mensaje, la ip que queremos y por el
                // puerto que queremos
                System.out.println("Enviando mensaje al servidor...");
                clientSocket.send(sendPacket); // al principio hemos creado el socket clientSocket, luego hemos creado
                                               // el datagrampacket sendPacket entonces el socket client envia el
                                               // paquete sendPacket

                // Aqui metemos un timer para que no spamee

                // Thread.sleep(5000);

                // espera respuesta, es decir tenemos que poner en escucha

                // Prepararse para recibir la respuesta
                byte[] receiveData = new byte[1024]; // Ajusta el tamaño según lo esperado
                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);
                // Esperar y recibir el paquete de respuesta del servidor
                System.out.println("Esperando respuesta...");
                clientSocket.receive(receivePacket);
                // Convertir la respuesta a String
                String response = new String(receivePacket.getData(), 0,
                        receivePacket.getLength());
                System.out.println(response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}