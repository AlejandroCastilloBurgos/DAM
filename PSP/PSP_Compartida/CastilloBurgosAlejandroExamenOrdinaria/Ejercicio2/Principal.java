package Ejercicio2;

public class Principal {
    public static void main(String args[]) {
        // Crear una instancia de la clase WC que será compartida entre los hilos Ravero
        WC wc = new WC();

        // Iniciar tres hilos de Ravero, cada uno asociado con la misma instancia de WC
        new Thread(new Persona(1, wc)).start();
        new Thread(new Persona(2, wc)).start();
        new Thread(new Persona(3, wc)).start();
        new Thread(new Persona(4, wc)).start();
        new Thread(new Persona(5, wc)).start();
        new Thread(new Persona(6, wc)).start();
        new Thread(new Persona(7, wc)).start();
        new Thread(new Persona(8, wc)).start();
        new Thread(new Persona(9, wc)).start();
        new Thread(new Persona(10, wc)).start();
        new Thread(new Persona(11, wc)).start();
        new Thread(new Persona(12, wc)).start();
        new Thread(new Persona(13, wc)).start();
        new Thread(new Persona(14, wc)).start();
        new Thread(new Persona(15, wc)).start();
        new Thread(new Persona(16, wc)).start();
        new Thread(new Persona(17, wc)).start();
        new Thread(new Persona(18, wc)).start();
        new Thread(new Persona(19, wc)).start();
        new Thread(new Persona(20, wc)).start();

    }
}