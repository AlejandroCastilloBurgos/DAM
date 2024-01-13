class MiRunnable implements Runnable {
    private int numero;

    public MiRunnable(int numero) {
        this.numero = numero;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(numero + " x " + i + " = " + (numero * i));
        }
    }
}

public class implementacionRunnable {
    public static void main(String[] args) {
        int N = 5; // Número de hilos
        Thread[] hilos = new Thread[N];

        for (int i = 0; i < N; i++) {
            MiRunnable runnable = new MiRunnable(i + 1);
            hilos[i] = new Thread(runnable);
            hilos[i].setName("Hilo-" + (i + 1));
            hilos[i].start();
        }

        try {
            for (Thread hilo : hilos) {
                hilo.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// java Principal > salida.txt
// sort salida.txt
