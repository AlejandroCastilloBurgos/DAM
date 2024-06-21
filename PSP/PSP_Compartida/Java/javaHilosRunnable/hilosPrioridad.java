class CancionThread extends Thread {
    private String animal;
    private String accion;
    private int numeroMaximo;

    public CancionThread(String animal, String accion, int numeroMaximo) {
        this.animal = animal;
        this.accion = accion;
        this.numeroMaximo = numeroMaximo;
    }

    private boolean esPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        for (int i = 1; i <= numeroMaximo; i++) {
            System.out.println("Un " + animal + " " + accion + " sobre la tela de una araña");
            System.out.println("Como veía que resistía, fue a llamar otro " + animal);
            System.out.println("Dos " + animal + " " + accion + " sobre la tela de una araña");
            System.out.println("Como veían que resistía, fueron a llamar otro " + animal);

            int numeroAleatorio = (int) (Math.random() * 200001) + 100000;
            if (esPrimo(numeroAleatorio)) {
                System.out.println("Número aleatorio (" + numeroAleatorio + ") es primo.");
            } else {
                System.out.println("Número aleatorio (" + numeroAleatorio + ") no es primo.");
            }

            try {
                Thread.sleep(1000); // Simula la duración de una estrofa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class hilosPrioridad {
    public static void main(String[] args) {
        CancionThread elefanteThread = new CancionThread("elefante", "se balanceaba", 3);
        CancionThread perroThread = new CancionThread("perro", "ladraba", 3);
        CancionThread gatoThread = new CancionThread("gato", "maullaba", 3);

        elefanteThread.setPriority(Thread.MAX_PRIORITY);
        perroThread.setPriority(Thread.NORM_PRIORITY);
        gatoThread.setPriority(Thread.MIN_PRIORITY);

        elefanteThread.start();
        perroThread.start();
        gatoThread.start();
    }
}

/*
 * Definición de la clase CancionThread:
 * La clase CancionThread extiende la clase Thread, lo que significa que cada
 * instancia de esta clase puede ejecutarse como un hilo independiente.
 * Tiene tres atributos: animal (nombre del animal), accion (acción que realiza
 * el animal sobre la tela de una araña) y numeroMaximo (número máximo de
 * estrofas que el hilo ejecutará).
 * El constructor inicializa estos atributos.
 * 
 * Método esPrimo(int numero):
 * Este método verifica si un número dado es primo. Se utiliza para comprobar si
 * el número aleatorio generado en cada iteración es primo.
 * 
 * Método run():
 * Este método es parte de la interfaz Runnable que se implementa al extender
 * Thread. Contiene el código que se ejecutará cuando el hilo comience.
 * En un bucle que se ejecuta numeroMaximo veces, el hilo imprime varias líneas
 * que forman una estrofa de la canción infantil. Luego, genera un número
 * aleatorio, lo verifica con esPrimo() y imprime si es primo o no.
 * Después de cada iteración, el hilo se duerme durante 1000 milisegundos (1
 * segundo), simulando así la duración de una estrofa.
 * 
 * Clase hilosPrioridad:
 * En el método main, se crean tres objetos CancionThread para representar a un
 * elefante, un perro y un gato. Cada uno se inicializa con un nombre de animal,
 * una acción y el número máximo de estrofas que ejecutará.
 * Se establecen las prioridades de los hilos. El hilo del elefante tiene la
 * máxima prioridad (MAX_PRIORITY), el hilo del perro tiene la prioridad normal
 * (NORM_PRIORITY) y el hilo del gato tiene la mínima prioridad (MIN_PRIORITY).
 * Se inician los hilos llamando al método start() de cada objeto CancionThread.
 * Esto activa la ejecución del método run en cada hilo de manera concurrente.
 */