import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(Integer.parseInt(args[0]))) {

            Logger logger = new Logger();

            while (true) {
                Socket connCliente = server.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connCliente.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connCliente.getOutputStream()));

                String peticion = reader.readLine(); // Lee la primera línea de la petición HTTP.

                int inicial;
                int cantidad;
                try {
                    String[] argumentosRecurso = extraeRecurso(peticion).split("/");
                    inicial = Integer.parseInt(argumentosRecurso[1]);
                    cantidad = Integer.parseInt(argumentosRecurso[2]);
                } catch (Exception e) {
                    writer.write("HTTP/1.1 400 Bad Request\n\nInvalid request format.");
                    writer.flush();
                    connCliente.close();
                    continue;
                }

                PrimosHTTPInverso primosHTTPInverso = new PrimosHTTPInverso(connCliente, inicial, cantidad);
                primosHTTPInverso.addListener(logger);

                String html = generaPagina(primosHTTPInverso);
                writer.write("HTTP/1.1 200 OK\n\n" + html);
                writer.flush();
                connCliente.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String extraeRecurso(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }

    public static String generaPagina(PrimosHTTPInverso primosHTTPInverso) {
        StringBuilder builder = new StringBuilder();
        builder.append("<ul>\n");

        primosHTTPInverso.addListener(primo -> builder.append(String.format("<li>%d</li>\n", primo)));

        primosHTTPInverso.generarPrimos();

        builder.append("</ul>\n");
        return builder.toString();
    }
}
