import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(Integer.parseInt(args[0]))) {

            Logger logger = new Logger();

            while (true) {
                Socket connCliente = server.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connCliente.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connCliente.getOutputStream()));

                String peticion = reader.readLine();

                int inicial = 0;
                int cantidad = 0;
                int vidas = 0;
                try {
                    String[] argumentosRecurso = extraeRecurso(peticion).split("/");
                    inicial = Integer.parseInt(argumentosRecurso[1]); // Ajustado índices para ser consistentes
                    cantidad = Integer.parseInt(argumentosRecurso[2]);
                    vidas = Integer.parseInt(argumentosRecurso[3]);
                } catch (Exception e) {
                    // Opcionalmente, enviar una respuesta de error aquí
                    continue;
                }

                ParesHTTP paresHTTP = new ParesHTTP(connCliente, inicial, cantidad, vidas);
                paresHTTP.addListener(logger);

                String html = generaPagina(paresHTTP, vidas); // Ajuste para pasar 'vidas'
                writer.write("HTTP/1.1 200 OK\n");
                writer.write("\n");
                writer.write(html);
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

    public static String generaPagina(ParesHTTP paresHTTP, int vidas) { // Ajuste para aceptar 'vidas'
        final StringBuilder builder = new StringBuilder();
        builder.append("<ul>\n");

        // Esta lógica no funcionará como se espera ya que el listener se ejecutará
        // fuera del flujo principal
        // Considerar una estrategia diferente para generar HTML basado en eventos
        // asincrónicos
        paresHTTP.addListener(par -> {
            builder.append(String.format("<li>%d</li>\n", par));
            System.out.println("Par encontrado: " + par);
            System.out.println("Te quedan " + vidas + " vidas");
        });

        paresHTTP.generarPares();

        builder.append("</ul>\n");
        return builder.toString();
    }
}
