
// Importación de librerías necesarias
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

// Definición de la clase MulticastReceive
public class MulticastRecieve {

    // Método principal
    public static void main(String[] args) {
        try {
            // Variable para almacenar el nombre de la interfaz de red
            String iName = "";
            // Si no se proporcionan argumentos, se lista y pide al usuario elegir una
            // interfaz
            if (args.length == 0) {
                // Enumeración de todas las interfaces de red disponibles
                Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
                for (NetworkInterface netint : Collections.list(nets)) {
                    System.out.println(netint);
                }
                // Solicitar al usuario que especifique una interfaz de red
                Scanner in = new Scanner(System.in);
                System.out.println("Especifica el nombre del interfaz");
                iName = in.nextLine();
            } else {
                // Si se proporciona un argumento, se usa como nombre de la interfaz de red
                iName = args[0];
            }

            // Obtener la interfaz de red por nombre
            NetworkInterface netIf = NetworkInterface.getByName(iName);
            // Imprimir la interfaz de red seleccionada
            System.out.println(netIf);

            // Puerto al cual el socket se unirá para escuchar los mensajes multicast
            int port = 1234;

            // Dirección IP multicast
            InetAddress mcastaddr = InetAddress.getByName("230.0.0.1");
            // Grupo multicast, combinación de dirección IP y puerto
            InetSocketAddress group = new InetSocketAddress(mcastaddr, port);
            // Crear un socket multicast que escuchará en el puerto especificado
            MulticastSocket s = new MulticastSocket(port);

            // Unir el socket al grupo multicast en la interfaz de red especificada
            s.joinGroup(group, netIf);

            // Preparar un buffer para recibir los mensajes
            byte[] buf = new byte[1000];
            // Crear un DatagramPacket para recibir los mensajes
            DatagramPacket recv = new DatagramPacket(buf, buf.length);
            // Esperar y recibir un mensaje
            s.receive(recv);
            // Convertir el mensaje recibido a String y imprimirlo
            System.out.println(new String(recv.getData(), 0, recv.getLength()));

            // Salir del grupo multicast
            s.leaveGroup(group, netIf);

        } catch (IOException e) {
            // Manejar las posibles excepciones de IO
            e.printStackTrace();
        }
    }
}
