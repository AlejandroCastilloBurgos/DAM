// Principal.java
class MiHilo extends Thread {
    public void run() {
        System.out.println("Hola Mundo desde un hilo que extiende de Thread.");
    }
}

class MiRunnable implements Runnable {
    public void run() {
        System.out.println("Hola Mundo desde un hilo que implementa Runnable.");
    }
}

public class extenderThreads {
    public static void main(String[] args) {
        MiHilo hilo1 = new MiHilo();
        Thread hilo2 = new Thread(new MiRunnable());
        Thread hilo3 = new Thread(() -> System.out.println("Hola Mundo desde un hilo con Lambda."));

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
