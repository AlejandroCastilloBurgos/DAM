import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class ObtenerIPs {

    public static void main(String[] args) {
        try {
            // Obtener todas las interfaces de red
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            System.out.println("Direcciones IP:");

            // Iterar sobre todas las interfaces de red
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();

                // Obtener las direcciones IP asociadas a la interfaz
                Enumeration<InetAddress> addresses = iface.getInetAddresses();

                // Mostrar las direcciones IP
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    System.out.println("  " + iface.getName() + ": " + addr.getHostAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
