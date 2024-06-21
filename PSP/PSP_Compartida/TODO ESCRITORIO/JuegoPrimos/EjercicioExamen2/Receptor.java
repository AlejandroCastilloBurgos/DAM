import java.io.BufferedReader;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Receptor {
    int puertoEscucha;
    int puertoEnvio;

    ArrayList<mensajeListener> listeners;

    public interface mensajeListener {
        void tramaControl(char caracter1, char caracter2, char caracter3, char caracter4, char caracter5);
    }

    public void addListener(mensajeListener listener) {
        listeners.add(listener);
    }

    Receptor(int puertoEscucha, int puertoEnvio) {
        this.puertoEscucha = puertoEscucha;
        this.puertoEnvio = puertoEnvio;
        this.listeners = new ArrayList<>();
    }

    public void comenzarLectura() {
        try {
            int port = puertoEscucha;
            DatagramSocket socket = new DatagramSocket(port, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);

            while (true) {
                System.out.println("Esperando mensaje de broadcast en el puerto" + port + "...");
                // preparamos mensaje
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                // espero
                socket.receive(packet);
                // convertir el mensaje
                String received = new String(packet.getData(), 0, packet.getLength());
                // manejamos la trama
                System.out.println(received.split(" "));
                String[] parts = received.split(" ");
                if (parts.length == 5) {

                    System.out.println("ENTRO");

                    char caracter1 = parts[0].charAt(0);
                    char caracter2 = parts[1].charAt(0);
                    char caracter3 = parts[2].charAt(0);
                    char caracter4 = parts[3].charAt(0);
                    char caracter5 = parts[4].charAt(0);
                    System.out.println("notificando a listenes");

                    for (mensajeListener listener : listeners) {
                        listener.tramaControl(caracter1, caracter2, caracter3, caracter4, caracter5);
                    }
                } // System.out.println("caracter1" + caracter1 + "caracter2" );

            }

        } catch (SocketException e) {
            System.out.println("socket" + e.getMessage());
        } catch (Exception e) {
            System.out.println("");
        }
    }

}
