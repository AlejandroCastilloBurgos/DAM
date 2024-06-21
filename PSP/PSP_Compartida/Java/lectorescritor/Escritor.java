package lectorescritor;

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