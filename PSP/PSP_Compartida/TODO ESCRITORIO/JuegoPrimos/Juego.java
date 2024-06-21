import java.util.ArrayList;
import java.util.List;

public class Juego {
    private int cantidad;
    private int vidas;
    private List<JuegoListener> listeners = new ArrayList<>();

    public Juego(int cantidad, int vidas) {
        this.cantidad = cantidad;
        this.vidas = vidas;
    }

    public void addListener(JuegoListener listener) {
        listeners.add(listener);
    }

    public void iniciar() {
        int numerosGenerados = 0;
        while (numerosGenerados < cantidad && vidas > 0) {
            int numeroAleatorio = (int) (Math.random() * 999) + 1;
            boolean esPrimo = esPrimo(numeroAleatorio);
            if (esPrimo) {
                vidas--;
            }
            numerosGenerados++;
            notificarNumeroGenerado(numeroAleatorio, esPrimo, vidas);
            if (vidas == 0) {
                notificarJuegoTerminado(false);
                return;
            }
        }
        notificarJuegoTerminado(true);
    }

    private void notificarNumeroGenerado(int numero, boolean esPrimo, int vidasRestantes) {
        for (JuegoListener listener : listeners) {
            listener.numeroGenerado(numero, esPrimo, vidasRestantes);
        }
    }

    private void notificarJuegoTerminado(boolean haGanado) {
        for (JuegoListener listener : listeners) {
            listener.juegoTerminado(haGanado);
        }
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
