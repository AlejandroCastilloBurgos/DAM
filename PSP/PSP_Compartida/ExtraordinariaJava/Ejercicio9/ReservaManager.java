package Ejercicio9;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservaManager {
    private List<Reserva> reservas = new ArrayList<>();
    private final File archivo;

    public ReservaManager(String archivo) throws IOException {
        this.archivo = new File(archivo);
        cargarReservas();
    }

    private void cargarReservas() throws IOException {
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(" - ");
                    String nombre = partes[0];
                    LocalDateTime fechaHora = LocalDateTime.parse(partes[1], DateTimeFormatter.ISO_DATE_TIME);
                    reservas.add(new Reserva(nombre, fechaHora));
                }
            }
        }
    }

    public synchronized boolean hacerReserva(String nombre, LocalDateTime fechaHora) throws IOException {
        for (Reserva reserva : reservas) {
            if (reserva.getFechaHora().equals(fechaHora)) {
                return false; // Fecha y hora ya reservada
            }
        }
        Reserva nuevaReserva = new Reserva(nombre, fechaHora);
        reservas.add(nuevaReserva);
        guardarReservas();
        return true;
    }

    public synchronized boolean cancelarReserva(String nombre, LocalDateTime fechaHora) throws IOException {
        Reserva reservaParaEliminar = null;
        for (Reserva reserva : reservas) {
            if (reserva.getNombre().equals(nombre) && reserva.getFechaHora().equals(fechaHora)) {
                reservaParaEliminar = reserva;
                break;
            }
        }
        if (reservaParaEliminar != null) {
            reservas.remove(reservaParaEliminar);
            guardarReservas();
            return true;
        }
        return false;
    }

    public synchronized List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

    private void guardarReservas() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.toString());
                writer.newLine();
            }
        }
    }
}
