package Ejercicio2;

public class WC {

    private boolean ocu;
    public int cont = 0;

    // Constructor que inicializa el estado del baño como libre
    public WC() {
        ocu = false;
    }

    // Método para verificar si el baño está ocupado
    public boolean ocupado() {
        return ocu;
    }

    // Método para indicar que alguien ha entrado al baño
    public void entra() {
        ocu = true;
        cont++;
    }

    // Método para indicar que alguien ha salido del baño
    public void sal() {
        ocu = false;
        cont--;
    }
}