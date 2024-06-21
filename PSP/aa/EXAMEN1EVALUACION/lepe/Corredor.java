package EXAMEN1EVALUACION.lepe;

public class Corredor implements Runnable {

    public final int META = 408;

    public final int MINIMO = 50;

    public final int MAXIMO = 100;

    public final int AVANCE = 50;

    int contador1;
    int contador2;

    public Carrera carrera;

    private Aviso aviso;

    private int COHETE;

    private int metros;

    public Corredor(int COHETE, Aviso aviso) {
        this.COHETE = COHETE;
        this.setAviso(aviso);
        this.metros = 0;
    }

    public Aviso getAviso() {
        return aviso;
    }

    public void setAviso(Aviso aviso) {
        this.aviso = aviso;
    }

    public int getCOHETE() {
        return COHETE;
    }

    public void setCOHETE(int COHETE) {
        this.COHETE = COHETE;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public synchronized void avance() {
        try {
            int rando = numAleatorio();
            System.out.println("SOY EL COHETE " + COHETE + " Y HE AVANZADO " + AVANCE + " KILOMETROS");
            setMetros(rando + getMetros());
            int dormirRando = numAleatorio();
            Thread.sleep(dormirRando);
            if (getMetros() > META) {
                System.out.println("HE LLEGADO SANO, SOY EL COHETE: " + COHETE);
                contador2++;
            }
            if (rando * 1 < 100) {
                System.out.println("HE EXPLOTADO SOY EL COHETE: " + COHETE);
                contador1++;
                Thread.interrupted();

            }
            if (contador2 >= 3) {
                System.out.println("LA MISION HA SIDO TODO UN EXITO");
            }
            System.out.println("Han explotado " + contador1 + "Cohetes");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int numAleatorio() {
        return (int) (Math.random() * MAXIMO) + MINIMO;
    }

    @Override
    public void run() {
        while (!aviso.isEmpezado()) {
            synchronized (aviso) {
                try {
                    aviso.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (aviso) {
            aviso.notifyAll();
            if (getMetros() < 1000 || numAleatorio() * 10 < 50) {
                avance();
            } else {
                System.out.println("HE EXPLOTADO SOY EL COHETE: " + COHETE);
                contador1++;
                Thread.interrupted();

            }
        }
    }
}
