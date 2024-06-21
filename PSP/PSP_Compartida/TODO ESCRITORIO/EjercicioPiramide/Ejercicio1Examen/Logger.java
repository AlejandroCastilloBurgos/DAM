package ut04;

import java.io.FileWriter;
import java.io.IOException;

import ut04.PrimosHTTP.Observer;

public class Logger implements Observer {

    private String fichero;

    public Logger(String name) {
        this.fichero = name;
    }

    @Override
    public void primoEncontrado(Integer numero) {
        try {
            FileWriter escritor = new FileWriter(fichero, true);
            escritor.append(Integer.toString(numero) + "\n");
            escritor.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
