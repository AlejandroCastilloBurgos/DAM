package Ejercicio1;

class Producto {
    private final String nombre;

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
