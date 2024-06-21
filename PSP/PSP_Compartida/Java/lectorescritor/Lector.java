package lectorescritor;

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