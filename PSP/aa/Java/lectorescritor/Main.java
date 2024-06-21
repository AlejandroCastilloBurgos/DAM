package lectorescritor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class BaseDeDatos {
    private int lectoresActivos;
    private boolean escritorActivo;
    private Lock lock;
    private Condition condicionLectores;
    private Condition condicionEscritor;

    public BaseDeDatos() {
        lectoresActivos = 0;
        escritorActivo = false;
        lock = new ReentrantLock();
        condicionLectores = lock.newCondition();
        condicionEscritor = lock.newCondition();
    }

    public void iniciarLectura(String lector) {
        lock.lock();
        try {
            while (escritorActivo) {
                condicionLectores.await();
            }
            lectoresActivos++;
            System.out.println(lector + " inicia lectura. Lectores activos: " + lectoresActivos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void finalizarLectura(String lector) {
        lock.lock();
        try {
            lectoresActivos--;
            System.out.println(lector + " finaliza lectura. Lectores activos: " + lectoresActivos);
            if (lectoresActivos == 0) {
                condicionEscritor.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void iniciarEscritura(String escritor) {
        lock.lock();
        try {
            while (lectoresActivos > 0 || escritorActivo) {
                condicionEscritor.await();
            }
            escritorActivo = true;
            System.out.println(escritor + " inicia escritura.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void finalizarEscritura(String escritor) {
        lock.lock();
        try {
            escritorActivo = false;
            System.out.println(escritor + " finaliza escritura.");
            condicionLectores.signalAll();
            condicionEscritor.signal();
        } finally {
            lock.unlock();
        }
    }
}

class Lector implements Runnable {
    private BaseDeDatos baseDeDatos;
    private String nombre;

    public Lector(BaseDeDatos baseDeDatos, String nombre) {
        this.baseDeDatos = baseDeDatos;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        baseDeDatos.iniciarLectura(nombre);
        // Realizar operaciones de lectura
        baseDeDatos.finalizarLectura(nombre);
    }
}

class Escritor implements Runnable {
    private BaseDeDatos baseDeDatos;
    private String nombre;

    public Escritor(BaseDeDatos baseDeDatos, String nombre) {
        this.baseDeDatos = baseDeDatos;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        baseDeDatos.iniciarEscritura(nombre);
        // Realizar operaciones de escritura
        baseDeDatos.finalizarEscritura(nombre);
    }
}

public class Main {
    public static void main(String[] args) {
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        Thread[] lectores = new Thread[5];
        Thread[] escritores = new Thread[2];

        for (int i = 0; i < 5; i++) {
            lectores[i] = new Thread(new Lector(baseDeDatos, "Lector " + (i + 1)));
            lectores[i].start();
        }

        for (int i = 0; i < 2; i++) {
            escritores[i] = new Thread(new Escritor(baseDeDatos, "Escritor " + (i + 1)));
            escritores[i].start();
        }

        try {
            for (Thread lector : lectores) {
                lector.join();
            }
            for (Thread escritor : escritores) {
                escritor.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
