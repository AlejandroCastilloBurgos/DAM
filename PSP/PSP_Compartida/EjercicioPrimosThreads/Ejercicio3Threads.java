package EjercicioPrimosThreads;

import java.util.ArrayList;
import java.util.Random;

public class Ejercicio3Threads {
    private static final int[] numeros = new int[32000];

    static class BuscadorDePrimos extends Thread {
        private final ArrayList<String> historico = new ArrayList<>();
        private final Random rand = new Random();

        public BuscadorDePrimos(String name) {
            super(name);
        }

        @Override
        public void run() {
            int primosEncontrados = 0;
            while (primosEncontrados < 10) {
                int pos1 = rand.nextInt(numeros.length);
                int pos2 = rand.nextInt(numeros.length);
                int pos3 = rand.nextInt(numeros.length);

                int suma = numeros[pos1] + numeros[pos2] + numeros[pos3];
                if (esPrimo(suma)) {
                    primosEncontrados++;
                    historico.add(pos1 + ":" + pos2 + ":" + pos3);
                }
            }
        }

        public ArrayList<String> getResultados() {
            return historico;
        }

        private boolean esPrimo(int numero) {
            if (numero <= 1)
                return false;
            for (int i = 2; i * i <= numero; i++) {
                if (numero % i == 0)
                    return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = rand.nextInt(10); // Números entre 0 y 9
        }

        BuscadorDePrimos[] threads = new BuscadorDePrimos[3];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new BuscadorDePrimos("Thread " + (i + 1));
        }

        for (BuscadorDePrimos thread : threads) {
            thread.start();
        }

        try {
            for (BuscadorDePrimos thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Resultados:");
        for (BuscadorDePrimos thread : threads) {
            System.out.println(thread.getName() + ": " + thread.getResultados());
        }
    }
}
