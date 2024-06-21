import java.net.Socket;
import java.util.ArrayList;

public class PrimosHTTPInverso {

    Socket socket;
    int inicial;
    int cantidad;
    ArrayList<PrimosListener> listeners;

    public PrimosHTTPInverso(Socket socket, int inicial, int cantidad) {
        this.socket = socket;
        this.inicial = inicial;
        this.cantidad = cantidad;
        this.listeners = new ArrayList<>();
    }

    public interface PrimosListener {
        void primoEncontrado(int primo);
    }

    public void addListener(PrimosListener listener) {
        listeners.add(listener);
    }

    public void generarPrimos() {
        int n = inicial - 1;
        int restantes = cantidad;
        while (restantes > 0 && n > 1) {
            if (esPrimo(n)) {
                for (PrimosListener listener : listeners) {
                    listener.primoEncontrado(n);
                }
                restantes--;
            }
            n--;
        }
    }

    private boolean esPrimo(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
