package com.example;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PrimosHTTP {

    Socket socket;
    int inicial;
    int cantidad;

    ArrayList<PrimosListener> listeners;

    PrimosHTTP(Socket socket, int inicial, int cantidad) {
        this.socket = socket;
        this.inicial = inicial;
        this.cantidad = cantidad;
        listeners = new ArrayList<>();
    }

    public interface PrimosListener {
        public void primoEncontrado(int primo);
    }

    public void addListener(PrimosListener listener) {
        listeners.add(listener);
    }

    private List<Integer> primosEncontrados = new ArrayList<>();

    public void generarPrimos() {
        int n = inicial;
        int restantes = cantidad;
        while (restantes > 0) {
            if (esPrimo(n)) {
                for (PrimosListener listener : listeners) {
                    listener.primoEncontrado(n);
                }
                primosEncontrados.add(n); //
                restantes--;
            }
            n++;
        }
    }

    // Getter para acceder a la lista de primos encontrados
    public List<Integer> getPrimosEncontrados() {
        return primosEncontrados;
    }

    public boolean esPrimo(int n) {
        if (n == 2 || n == 1) {
            return true;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

// mvn exec:java -Dexec.mainClass=com.examen.ejercicio1.Main
// -Dexec.args="'argumento 1' 'argumento 2'"
