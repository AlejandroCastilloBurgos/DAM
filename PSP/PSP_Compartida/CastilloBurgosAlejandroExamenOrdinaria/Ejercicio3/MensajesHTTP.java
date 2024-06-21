package Ejercicio3;

import java.net.Socket;
import java.util.ArrayList;

public class MensajesHTTP {

    Socket socket;
    String mensaje;
    int cantidad;

    ArrayList<MensajeListener> listeners;

    MensajesHTTP(Socket socket, String mensaje, int cantidad) {
        this.socket = socket;
        this.mensaje = mensaje;
        this.cantidad = cantidad;
        listeners = new ArrayList<>();
    }

    public interface MensajeListener {
        public void mensajeEncontrado(String mensaje);
    }

    public void addListener(MensajeListener listener) {
        listeners.add(listener);
    }

    public void generarMensaje() {
        int restantes = cantidad;
        while (restantes > 0) {
            for (MensajeListener listener : listeners) {
                listener.mensajeEncontrado(mensaje);
            }
            restantes--;
        }
    }
}