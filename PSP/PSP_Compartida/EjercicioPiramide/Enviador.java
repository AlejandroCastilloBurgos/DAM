import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// Enviador implementa el listener para la recepción de datos para generar la pirámide
public class Enviador implements Receptor.mensajeListener {

    int puertoEnvio;

    Enviador(int puertoEnvio) {
        this.puertoEnvio = puertoEnvio;
    }

    @Override
    public void piramideRecibida(int alto, char caracter) {
        System.out.println("Pirámide recibida: pisos=" + alto + ", caracter=" + caracter);
        String piramide = GenerarFiguras.generarPiramide(alto, caracter);
        enviarPiramide(piramide);
        System.out.println(piramide);
    }

    private void enviarPiramide(String piramide) {
        try {
            DatagramSocket ds = new DatagramSocket();
            byte[] buffer = piramide.getBytes();
            ds.setBroadcast(true);
            DatagramPacket p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"),
                    puertoEnvio);
            ds.send(p);
            ds.close();
        } catch (Exception e) {
            System.out.println("Error enviando la pirámide: " + e.getMessage());
        }
    }
}
