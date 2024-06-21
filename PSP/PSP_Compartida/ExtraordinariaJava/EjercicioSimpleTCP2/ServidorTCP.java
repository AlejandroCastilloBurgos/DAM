package EjercicioSimpleTCP2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9876)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    // Recibir mensaje del cliente
                    String message = in.readLine();

                    // Contar número de letras
                    int letterCount = message.replaceAll("[^a-zA-Z]", "").length();
                    String response = "Número de letras: " + letterCount;

                    // Enviar respuesta al cliente
                    out.println(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
