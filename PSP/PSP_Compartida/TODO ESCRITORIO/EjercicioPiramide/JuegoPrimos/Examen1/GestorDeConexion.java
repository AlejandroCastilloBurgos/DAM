package Examen1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class GestorDeConexion implements Runnable {
    private Socket socket;

    public GestorDeConexion(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream outputStream = socket.getOutputStream();

            // Lee la solicitud del cliente
            String solicitud = reader.readLine();

            // Procesa la solicitud y obtiene una respuesta
            String respuesta = procesarSolicitud(solicitud);

            // Envía la respuesta al cliente
            outputStream.write(respuesta.getBytes());

            // Cierra la conexión
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para procesar la solicitud del cliente
    private String procesarSolicitud(String solicitud) {
        if (solicitud != null && !solicitud.isEmpty()) {
            // Divide la línea de solicitud (e.g., "GET /consulta/madrid HTTP/1.1") por
            // espacios
            String[] partes = solicitud.split(" ");
            // Verifica que la solicitud tenga al menos el método, el path y la versión HTTP
            if (partes.length >= 3) {
                String path = partes[1]; // Esto debería ser "/consulta/madrid"
                String[] pathParts = path.split("/");
                // Asegúrate de que el path tenga al menos 3 partes ("/consulta/madrid")
                if (pathParts.length >= 3 && pathParts[1].equalsIgnoreCase("consulta")) {
                    String ciudad = pathParts[2].toUpperCase();
                    return BaseDeDatosCiudades.consultarCiudad(ciudad);
                }
            }
        }
        return "400 - BAD GATEWAY";
    }
}
