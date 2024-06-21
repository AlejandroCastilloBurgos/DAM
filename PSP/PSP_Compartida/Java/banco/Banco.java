public class Banco {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();

        // Sin sincronización
        Thread aliceThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                cuenta.transferir("Alice", 10);
            }
        });

        Thread bobThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                cuenta.transferir("Bob", 10);
            }
        });

        aliceThread.start();
        bobThread.start();

        try {
            aliceThread.join();
            bobThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final sin sincronización: " + cuenta.getSaldo());

        // Con sincronización
        cuenta = new CuentaBancaria(); // Reiniciamos la cuenta

        Thread aliceThreadSync = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (cuenta) {
                    cuenta.transferir("Alice", 10);
                }
            }
        });

        Thread bobThreadSync = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (cuenta) {
                    cuenta.transferir("Bob", 10);
                }
            }
        });

        aliceThreadSync.start();
        bobThreadSync.start();

        try {
            aliceThreadSync.join();
            bobThreadSync.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final con sincronización: " + cuenta.getSaldo());
    }
}

