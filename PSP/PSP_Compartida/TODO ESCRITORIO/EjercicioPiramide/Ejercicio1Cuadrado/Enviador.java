import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Enviador implements Receptor.mensajeListener {

    int puertoEnvio;

    Enviador(int puertoEnvio) {
        this.puertoEnvio = puertoEnvio;
    }

    @Override
    public void cuadradoRecibido(int alto, int ancho, char caracter) {
        System.out.println("Cuadrado recibido: alto=" + alto + ", ancho=" + ancho + ", caracter=" + caracter);

        String cuadrado = GenerarCuadrados.GeneradorCuadrados(alto, ancho, caracter);
        enviarCuadrado(cuadrado);
        System.out.println(cuadrado);
    }

    private void enviarCuadrado(String cuadrado) {
        try {
            DatagramSocket ds = new DatagramSocket();
            byte[] buffer = cuadrado.getBytes();
            ds.setBroadcast(true);
            DatagramPacket p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("10.0.2.255"),
                    puertoEnvio);
            ds.send(p);
            ds.close();
        } catch (Exception e) {
            System.out.println("Error enviando el cuadrado: " + e.getMessage());
        }
    }
}
