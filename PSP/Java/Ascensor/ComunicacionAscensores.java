package Java.Ascensor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ComunicacionAscensores {
    private String direccionIP;

    // castea y construye IP
    public ComunicacionAscensores(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    // abre la conexion del socket, en el printwriter al final tenemos un true esto
    // es para que al escribir vacie y no repita
    public void enviarTrama(TramaAscensor trama) {
        try (Socket socket = new Socket(direccionIP, 986);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(trama.obtenerTrama());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
