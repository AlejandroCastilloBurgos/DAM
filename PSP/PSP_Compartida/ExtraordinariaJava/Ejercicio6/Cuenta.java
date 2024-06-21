package Ejercicio6;

public class Cuenta {
    private int cantidad = 0;
    private final String nombre;

    public Cuenta(String nombre) {
        this.nombre = nombre;
    }

    public Cuenta(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }
}
