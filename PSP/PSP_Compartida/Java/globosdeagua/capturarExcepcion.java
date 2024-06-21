package globosdeagua;

public class capturarExcepcion {
    public static void main(String[] args) {
        // Crear el hilo que lanza la excepción
        Thread hilo = new Thread(() -> {
            throw new RuntimeException("¡Esta es una excepción no capturada!");
        });

        // Establecer el UncaughtExceptionHandler para el hilo
        hilo.setUncaughtExceptionHandler((thread, throwable) -> {
            System.out.println("Excepción capturada en el hilo: " + thread.getName());
            System.out.println("Mensaje de la excepción: " + throwable.getMessage());
        });

        // Iniciar el hilo
        hilo.start();
    }
}
