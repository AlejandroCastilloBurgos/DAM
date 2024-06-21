package globosdeagua;

class Nino extends Thread {
    private char simbolo;
    private Fuente fuente;

    public Nino(char simbolo, Fuente fuente) {
        this.simbolo = simbolo;
        this.fuente = fuente;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Jugar
                System.out.print(simbolo);
                Thread.sleep((long) (Math.random() * 1000));

                // Recargar en la fuente
                fuente.recargar(simbolo);

                // Descansar antes de volver a jugar
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}