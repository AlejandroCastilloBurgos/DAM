package Java.Ascensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ascensor {
    private int numeroAscensor;
    private ComunicacionAscensores controlador;

    public Ascensor(int numeroAscensor) {
        this.numeroAscensor = numeroAscensor;
        try {
            // Obtener automáticamente la dirección IP del dispositivo
            InetAddress direccion = InetAddress.getLocalHost();
            String direccionIP = direccion.getHostAddress();

            this.controlador = new ComunicacionAscensores(direccionIP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void moverseEntrePlantas(String plantaDestino, String direccion) {
        TramaAscensor trama = new TramaAscensor(numeroAscensor, plantaDestino, direccion);
        controlador.enviarTrama(trama);

        // Simular el tiempo que tarda en moverse entre plantas (1 segundo)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Ascensor <numeroAscensor>");
            System.exit(1);
        }

        int numeroAscensor = Integer.parseInt(args[0]);

        Ascensor simulador = new Ascensor(numeroAscensor);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                try {
                    System.out.print("Ingrese la planta destino (0 para salir): ");
                    String plantaDestino = reader.readLine();

                    if (plantaDestino.equals("0")) {
                        System.out.println("Simulador de ascensor finalizado.");
                        break;
                    }

                    System.out.print("Ingrese la dirección (U para arriba, D para abajo): ");
                    String direccion = reader.readLine();

                    simulador.moverseEntrePlantas(plantaDestino, direccion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                // Cerrar el BufferedReader
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
