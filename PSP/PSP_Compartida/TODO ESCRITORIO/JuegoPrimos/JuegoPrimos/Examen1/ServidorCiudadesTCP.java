package Examen1;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCiudadesTCP {

    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Error: Especifique el puerto como argumento.");
            System.exit(1);
        }

        int puerto = 5555;// Integer.parseInt(args[0]);
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                // Acepta una conexión entrante
                Socket socket = servidor.accept();
                System.out.println("Conexión aceptada desde " + socket);

                // Crea y arranca un nuevo hilo para manejar la conexión
                new Thread(new GestorDeConexion(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
