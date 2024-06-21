import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EnviaBroadcast {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            byte[] buffer = "Hola mundo\n".getBytes();
            // String ip = "192.168.20.200"; // Dirección específica (si fuera necesario)
            String ip = "10.0.2.255"; // Dirección de broadcast
            ds.setBroadcast(true);
            DatagramPacket p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ip), 4321);
            ds.send(p);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// sudo echo "Hola mundo" | nc -u -b 10.0.2.255 4321
