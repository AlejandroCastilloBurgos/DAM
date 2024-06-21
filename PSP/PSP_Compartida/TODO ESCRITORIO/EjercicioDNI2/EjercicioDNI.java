import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EjercicioDNI {

    public static void main(String[] args) {
        int port = 8888;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

                // Crear e iniciar un nuevo hilo para manejar la conexión del cliente
                Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket));
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



