import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ascensor {
    private int numeroAscensor;
    private String direccionIP;
    private int puerto = 1234; // Asumiendo que el servidor escucha en el puerto 1234.
    private String pisoActual = "PB";
    private char direccion = 'U'; // Dirección inicial Up (U) o Down (D).
    private Set<String> pisosValidos;

    public Ascensor(int numeroAscensor, String direccionIP) {
        this.numeroAscensor = numeroAscensor;
        this.direccionIP = direccionIP;
        inicializarPisosValidos();
    }

    private void inicializarPisosValidos() {
        // Aquí defines los pisos válidos para tu edificio.
        pisosValidos = new HashSet<>();
        pisosValidos.add("PB");
        // Añade aquí cualquier otro piso válido, por ejemplo, sótanos o pisos
        // especiales.
        for (int i = 1; i <= 10; i++) { // Asumiendo pisos del 1 al 10 como válidos.
            pisosValidos.add(String.valueOf(i));
        }
    }

    public void iniciar() {
        try (Socket socket = new Socket(direccionIP, puerto);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Ascensor " + numeroAscensor + " conectado.");

            // Iniciar envío periódico de tramas de control
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> enviarTramaControl(out), 0, 100, TimeUnit.MILLISECONDS);

            while (true) {
                System.out.println("Ingrese el piso destino:");
                String pisoDestino = scanner.nextLine();
                if (validarPisoDestino(pisoDestino)) {
                    actualizarEstado(pisoDestino);
                } else {
                    System.out.println("Piso destino no válido. Intente nuevamente.");
                }
                // La trama de control se enviará automáticamente cada 100ms por el scheduler
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validarPisoDestino(String pisoDestino) {
        return pisosValidos.contains(pisoDestino);
    }

    private void enviarTramaControl(PrintWriter out) {
        try {
            String tramaControl = numeroAscensor + ";" + pisoActual + ";" + direccion;
            out.println(tramaControl);
            out.flush(); // Asegurarse de que los datos se envían inmediatamente.
            // System.out.println("Enviando trama de control: " + tramaControl);
        } catch (Exception e) {
            System.out.println("Error al enviar trama de control: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void actualizarEstado(String pisoDestino) {
        this.pisoActual = pisoDestino;
        direccion = calcularDireccion(pisoDestino);
    }

    private char calcularDireccion(String pisoDestino) {
        try {
            int pisoDestinoNumero = "PB".equals(pisoDestino) ? 0 : Integer.parseInt(pisoDestino);
            int pisoActualNumero = "PB".equals(pisoActual) ? 0 : Integer.parseInt(pisoActual);
            return pisoDestinoNumero > pisoActualNumero ? 'U' : 'D';
        } catch (NumberFormatException e) {
            System.out.println("Error al calcular dirección: formato de piso no válido.");
            return 'U'; // Valor predeterminado en caso de error
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Ascensor <numeroAscensor> <direccionIP>");
            System.exit(1);
        }
        int numeroAscensor = Integer.parseInt(args[0]);
        String direccionIP = args[1];
        Ascensor ascensor = new Ascensor(numeroAscensor, direccionIP);
        ascensor.iniciar();
    }
}
