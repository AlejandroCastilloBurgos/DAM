import java.net.Socket;
import java.util.ArrayList;

public class ParesHTTP {

    Socket socket;
    int inicial;
    int cantidad;
    int vidas;

    ArrayList<ParesListener> listeners;

    ParesHTTP(Socket socket, int inicial, int cantidad, int vidas) {
        this.socket = socket;
        this.inicial = inicial;
        this.cantidad = cantidad;
        this.vidas = vidas;
        listeners = new ArrayList<>();
    }

    public interface ParesListener {
        void parEncontrado(int par);
    }

    public void addListener(ParesListener listener) {
        listeners.add(listener);
    }

    public void generarPares() {
        int contador = 0;
        int numero = inicial;

        // Ajustar el inicio al próximo número par si es impar
        if (numero % 2 != 0) {
            numero++;
        }

        while (contador < cantidad) {
            // Notificar a todos los listeners que se ha encontrado un número par
            for (ParesListener listener : listeners) {
                listener.parEncontrado(numero);
            }
            numero += 2; // Incrementar para obtener el siguiente número par
            contador++;
        }
    }
}
