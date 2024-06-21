package Ejercicio10;

public class Libro {
    private final String titulo;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
