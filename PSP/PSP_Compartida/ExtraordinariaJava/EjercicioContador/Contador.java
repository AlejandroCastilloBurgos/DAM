package EjercicioContador;

// Contador.java
public class Contador {
    private int valor = 0;

    public synchronized void incrementar() {
        valor++;
        System.out.println(Thread.currentThread().getName() + " incrementó: " + valor);
    }

    public synchronized void decrementar() {
        valor--;
        System.out.println(Thread.currentThread().getName() + " decrementó: " + valor);
    }

    public int getValor() {
        return valor;
    }
}
