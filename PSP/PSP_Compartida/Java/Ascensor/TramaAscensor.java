package Java.Ascensor;

public class TramaAscensor {
    private int numeroAscensor;
    private String plantaDestino;
    private String direccion;

    // constructor que castea y recibe los parametros
    public TramaAscensor(int numeroAscensor, String plantaDestino, String direccion) {
        this.numeroAscensor = numeroAscensor;
        this.plantaDestino = plantaDestino;
        this.direccion = direccion;
    }

    // metodo para devolver la trama en formato especificado
    public String obtenerTrama() {
        return String.format("%02d;%s;%s", numeroAscensor, plantaDestino, direccion);
    }
}

// En trama ascensor me encargo de crear el mensaje que va a mandar como trama
// de control