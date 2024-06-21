import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Enviador implements Receptor.mensajeListener {
    int puertoEnvio;

    Enviador(int puertoEnvio) {
        this.puertoEnvio = puertoEnvio;
    }

    @Override
    public void tramaControl(char caracter1, char caracter2, char caracter3, char caracter4, char caracter5) {
        System.out.print(caracter1);
        System.out.print(caracter2);
        System.out.print(caracter3);
        System.out.print(caracter4);
        System.out.print(caracter5);

        String trama = ConvertirTrama.contarCaracteres(caracter1, caracter2, caracter3, caracter4, caracter5);
        enviarTrama(trama);
        System.out.println(trama);

    }

    private void enviarTrama(String trama) {
        try {
            DatagramSocket ds = new DatagramSocket();
            byte[] buffer = trama.getBytes();
            ds.setBroadcast(true);
            DatagramPacket p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("10.0.2.255"),
                    puertoEnvio);
            ds.send(p);
            ds.close();
        } catch (Exception e) {
            System.out.println("Error enviando trama: " + e.getMessage());
        }
    }

}
