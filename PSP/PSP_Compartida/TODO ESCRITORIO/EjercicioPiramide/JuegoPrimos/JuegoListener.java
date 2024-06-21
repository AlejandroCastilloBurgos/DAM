public interface JuegoListener {
    void numeroGenerado(int numero, boolean esPrimo, int vidasRestantes);

    void juegoTerminado(boolean haGanado);
}
