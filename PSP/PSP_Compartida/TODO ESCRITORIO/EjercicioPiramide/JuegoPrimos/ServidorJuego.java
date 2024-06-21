import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringJoiner;

public class ServidorJuego {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4444)) { // Asumiendo que el puerto es 4444
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String requestLine = in.readLine();
            if (requestLine != null && !requestLine.isEmpty()) {
                String[] requestParts = requestLine.split(" ")[1].split("/");
                if (requestParts.length >= 3) {
                    int cantidad = Integer.parseInt(requestParts[1]);
                    int vidas = Integer.parseInt(requestParts[2]);

                    StringBuilder numerosGeneradosStr = new StringBuilder();

                    Juego juego = new Juego(cantidad, vidas);
                    juego.addListener(new JuegoListener() {
                        @Override
                        public void numeroGenerado(int numero, boolean esPrimo, int vidasRestantes) {
                            numerosGeneradosStr.append("Número: ").append(numero).append(esPrimo ? " (primo)" : "")
                                    .append(", Vidas restantes: ").append(vidasRestantes).append("<br>");
                        }

                        @Override
                        public void juegoTerminado(boolean haGanado) {
                            try {
                                out.write("HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=UTF-8\r\n\r\n");
                                out.write("<html><body>");
                                out.write(numerosGeneradosStr.toString());
                                out.write(haGanado ? "Has ganado!" : "Has perdido. Te quedaste sin vidas.");
                                out.write("</body></html>");
                                out.flush();
                                clientSocket.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    juego.iniciar(); // Esto iniciará el juego y los eventos serán manejados por el listener.
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
