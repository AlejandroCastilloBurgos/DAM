package Ejercicio9;

import java.time.LocalDateTime;

public class Reserva {
    private String nombre;
    private LocalDateTime fechaHora;

    public Reserva(String nombre, LocalDateTime fechaHora) {
        this.nombre = nombre;
        this.fechaHora = fechaHora;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return nombre + " - " + fechaHora.toString();
    }
}
