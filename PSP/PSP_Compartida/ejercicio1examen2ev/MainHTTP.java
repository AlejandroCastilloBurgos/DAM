package ejercicio1;

public class MainHTTP {
    public static void main(String[] args) {
        int puerto = Integer.parseInt(args[0]);
        ServidorHTTP server = new ServidorHTTP(puerto);
        ObservadorHTTP observer = new ObservadorHTTP();
        server.agregarObservador(observer);
        server.gestionConsultaHTTP();
    }
}
