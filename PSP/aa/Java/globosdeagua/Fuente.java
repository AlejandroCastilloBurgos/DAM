package globosdeagua;

class Fuente {
    private boolean ocupada = false;
    private char simboloActual;

    synchronized void recargar(char simbolo) {
        while (ocupada) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        ocupada = true;
        simboloActual = simbolo;
        System.out.println("ENTRA(" + simbolo + ")-RECARGA-SALE(" + simbolo + ")");

        ocupada = false;
        notifyAll();
    }
}
