import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorAscensores {
    private int puerto = 1234;

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor de Ascensores iniciado en el puerto " + puerto);
            ExecutorService pool = Executors.newFixedThreadPool(4); // Soporta hasta 4 ascensores concurrentemente

            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new HiloAscensores(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServidorAscensores().iniciar();
    }
}
