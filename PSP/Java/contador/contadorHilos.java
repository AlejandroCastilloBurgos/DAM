// Archivo: contadorHilos.java
public class contadorHilos {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread1 = new Thread(new Incrementer(counter));
        Thread thread2 = new Thread(new Incrementer(counter));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}

class Incrementer implements Runnable {
    private Counter counter;

    public Incrementer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}
