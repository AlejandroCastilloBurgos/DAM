
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

// Definición de la clase MulticastSend
public class MulticastSend {

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

            // Mensaje a enviar
            String msg = "¿Aprobado general o suspenso general? ¿Qué opinas?";
            // Puerto al cual el socket enviará el mensaje
            int port = 1234;

            // Dirección IP multicast
            InetAddress mcastaddr = InetAddress.getByName("230.0.0.1");
            // Grupo multicast, combinación de dirección IP y puerto
            InetSocketAddress group = new InetSocketAddress(mcastaddr, port);
            // Crear un socket multicast que se usará para enviar el mensaje
            MulticastSocket s = new MulticastSocket(port);

            // Unir el socket al grupo multicast en la interfaz de red especificada
            s.joinGroup(group, netIf);

            /* Código de envío */
            // Convertir el mensaje a bytes
            byte[] msgBytes = msg.getBytes();
            // Crear un DatagramPacket con el mensaje, su longitud, y el grupo destino
            DatagramPacket hi = new DatagramPacket(msgBytes, msgBytes.length, group);
            // Enviar el paquete
            s.send(hi);

            // Aquí se podría salir del grupo multicast si se quisiera, pero está comentado
            // s.leaveGroup(group, netIf);

        } catch (IOException e) {
            // Manejar las posibles excepciones de IO
            e.printStackTrace();
        }
    }
}
