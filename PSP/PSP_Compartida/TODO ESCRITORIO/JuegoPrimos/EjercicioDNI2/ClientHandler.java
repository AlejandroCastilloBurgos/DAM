import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        handleClient();
    }

    private void handleClient() {
        try (InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String request = new String(buffer, 0, bytesRead);
            System.out.println("Petición recibida: " + request);

            String response = processRequest(request);
            System.out.println("Esta es la respuesta: " + response);
            outputStream.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String processRequest(String request) {
        String[] parts = request.split(" ");
        if (parts.length >= 2 && parts[0].equals("GET")) {
            String path = parts[1];
            String[] pathParts = path.split("/");
            if (pathParts.length >= 2) {
                String dni = pathParts[1];
                System.out.println("este es el dni : " + dni);
                return generateResponse(dni);
            }
        }
        return "HTTP/1.1 400 Bad Request\r\n\r\n";
    }

    private String generateResponse(String dni) {
        String[] letrasValidas = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
                "V", "H", "L", "C", "K", "E" };
        try {
            int number = Integer.parseInt(dni.substring(0, dni.length() - 1));
            int resto = number % 23;
            String letter = dni.substring(dni.length() - 1).toUpperCase();
            return "HTTP/1.1 200 OK\r\n\r\n<h1 style=\"color: "
                    + (letter.equals(letrasValidas[resto]) ? "green" : "red") + ";\">"
                    + (letter.equals(letrasValidas[resto]) ? "OK" : "KO") + "</h1>";
        } catch (NumberFormatException e) {
            return "HTTP/1.1 400 Bad Request\r\n\r\n";
        }
    }
}
