
// UDPServer.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServidorUDP {

    public static boolean esPrimo(int numero) {
        // Un número es primo si es mayor que 1 y no tiene divisores positivos aparte de
        // 1 y sí mismo.
        if (numero <= 1) {
            return false; // 0, 1 y los negativos no son primos
        }
        if (numero == 2) {
            return true; // 2 es el único número par que es primo
        }
        if (numero % 2 == 0) {
            return false; // Excluir los números pares mayores que 2
        }
        // Comprobar divisibilidad desde 3 hasta la raíz cuadrada de numero
        for (int i = 3; i <= Math.sqrt(numero); i += 2) {
            if (numero % i == 0) {
                // Si numero es divisible por i, entonces numero no es primo
                return false;
            }
        }
        // Si no se encontraron divisores, numero es primo
        return true;
    }

    public static void main(String args[]) {
        // Verificar los argumentos
        if (args.length < 1) {
            System.out.println("Uso: java UDPServer <puerto>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try {
            // creamos el scanner
            Scanner scanner = new Scanner(System.in);
            // Crear un socket UDP
            DatagramSocket serverSocket = new DatagramSocket(port);

            System.out.printf("Servidor escuchando en el puerto %d...\n", port);

            while (true) {
                // dentro del bucle para limpiar datos
                byte[] receiveData = new byte[1024];
                // byte[] sendData = new byte[1024]; // Agregado para enviar datos
                // Recibir el paquete
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println(message + " ¿Es un numero primo? " + esPrimo(Integer.parseInt(message)));

                /*
                 * Si se desea enviar una respuesta al cliente, se puede hacer aquí
                 * // Obtener la dirección IP y el puerto del cliente para enviar la respuesta
                 * InetAddress IPAddress = receivePacket.getAddress();
                 * int clientPort = receivePacket.getPort();
                 * // Preparar y enviar la respuesta
                 * System.out.println("Introduce el mensaje que quieras enviar");
                 * // Leer una línea completa de texto
                 * String mensaje = scanner.nextLine();
                 * if ("salir".equalsIgnoreCase(mensaje)) {
                 * break; // Salir del bucle si el usuario escribe "salir"
                 * }
                 * String respuesta = "Mensaje recibido: " + mensaje;
                 * // Es importante cerrar el Scanner cuando ya no se necesita
                 * // para liberar los recursos que pueda estar utilizando
                 * // scanner.close();
                 * sendData = respuesta.getBytes();
                 * DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                 * IPAddress, clientPort);
                 * serverSocket.send(sendPacket); // Enviar la respuesta
                 */

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
