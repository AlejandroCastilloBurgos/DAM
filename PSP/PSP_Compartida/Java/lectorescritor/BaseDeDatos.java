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