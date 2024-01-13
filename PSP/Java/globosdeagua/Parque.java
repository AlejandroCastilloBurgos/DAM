package globosdeagua;

public class Parque {
    public static void main(String[] args) {
        Fuente fuente = new Fuente();

        // Crear niños
        Nino ninoA = new Nino('A', fuente);
        Nino ninoB = new Nino('B', fuente);

        // Iniciar niños
        ninoA.start();
        ninoB.start();
    }
}