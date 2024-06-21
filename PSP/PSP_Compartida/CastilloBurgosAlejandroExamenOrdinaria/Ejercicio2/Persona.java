package Ejercicio2;

public class Persona implements Runnable {
    private WC wc;
    private int id;
    int contador;

    // Constructor que recibe un identificador y una instancia de WC
    public Persona(int id, WC wc) {
        this.wc = wc;
        this.id = id;
    }

    // Método para simular una pausa o espera en milisegundos
    private void duerme(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) { // Queremos ir al baño
            duerme(2500);

            // Verificar si el baño está libre
            if (wc.cont < 5) {
                // Uno de ellos se queda aquí
                wc.entra(); // Entra al baño
                System.out.println(id + " Entro!!");
                duerme(1000);
                System.out.println(id + " Salgo!!");
                wc.sal(); // Sale del baño
                duerme(1000);

            }
        }
    }
}