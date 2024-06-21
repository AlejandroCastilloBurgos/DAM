package Ejercicio9;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservaHandler implements HttpHandler {
    private final ReservaManager reservaManager;

    public ReservaHandler(ReservaManager reservaManager) {
        this.reservaManager = reservaManager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response;
        String method = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery();
        String[] params = query != null ? query.split("&") : new String[0];

        if ("GET".equalsIgnoreCase(method)) {
            response = manejarGet(params);
        } else if ("POST".equalsIgnoreCase(method)) {
            response = manejarPost(params);
        } else if ("DELETE".equalsIgnoreCase(method)) {
            response = manejarDelete(params);
        } else {
            response = "Método no soportado";
        }

        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String manejarGet(String[] params) {
        StringBuilder response = new StringBuilder("Reservas actuales:\n");
        for (Reserva reserva : reservaManager.getReservas()) {
            response.append(reserva.toString()).append("\n");
        }
        return response.toString();
    }

    private String manejarPost(String[] params) throws IOException {
        String nombre = null;
        LocalDateTime fechaHora = null;

        for (String param : params) {
            String[] keyValue = param.split("=");
            if ("nombre".equals(keyValue[0])) {
                nombre = keyValue[1];
            } else if ("fechaHora".equals(keyValue[0])) {
                fechaHora = LocalDateTime.parse(keyValue[1], DateTimeFormatter.ISO_DATE_TIME);
            }
        }

        if (nombre != null && fechaHora != null && reservaManager.hacerReserva(nombre, fechaHora)) {
            return "Reserva realizada con éxito";
        } else {
            return "No se pudo realizar la reserva. Fecha y hora ya reservadas o parámetros inválidos";
        }
    }

    private String manejarDelete(String[] params) throws IOException {
        String nombre = null;
        LocalDateTime fechaHora = null;

        for (String param : params) {
            String[] keyValue = param.split("=");
            if ("nombre".equals(keyValue[0])) {
                nombre = keyValue[1];
            } else if ("fechaHora".equals(keyValue[0])) {
                fechaHora = LocalDateTime.parse(keyValue[1], DateTimeFormatter.ISO_DATE_TIME);
            }
        }

        if (nombre != null && fechaHora != null && reservaManager.cancelarReserva(nombre, fechaHora)) {
            return "Reserva cancelada con éxito";
        } else {
            return "No se pudo cancelar la reserva. Parámetros inválidos";
        }
    }
}
