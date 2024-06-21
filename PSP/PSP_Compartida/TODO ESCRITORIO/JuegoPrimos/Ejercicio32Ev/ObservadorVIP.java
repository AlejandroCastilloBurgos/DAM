public class ObservadorVIP implements Observador {
    @Override
    public void notificar(String codigo) {
        // Implementación específica para ObservadorVIP
        System.out.println("EntradaVIP - " + codigo);
    }
}