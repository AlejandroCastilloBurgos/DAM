package ut04;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class PrimosHTTP extends Thread {

    Integer inicio;
    Integer cantidad;
    Socket cliente;

    Observer observador;

    public interface Observer {
        void primoEncontrado(Integer numero);
    }

    public void addObserver(Observer observador) {
        this.observador = observador;
    }

    public PrimosHTTP(Socket cliente, String[] parametros) {
        this.cliente = cliente;
        inicio = Integer.parseInt(parametros[0]);
        cantidad = Integer.parseInt(parametros[1]);
    }

    @Override
    public void run() {
        try {
            int cont = 0;
            Integer primos[] = new Integer[cantidad];
            // calculo de primos
            while (cont < cantidad) {
                if (esPrimo(inicio)) {
                    Thread.sleep(1000);
                    primos[cont] = inicio;
                    observador.primoEncontrado(inicio);
                    cont++;
                }
                inicio++;
            }
            // escribir la web
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            cliente.getOutputStream()));
            writer.write("HTTP/1.1 200 OK\n");
            // writer.write("Content-Type: application/json; charset=utf-8\n");
            writer.write("\n");
            writer.write("<!DOCTYPE html>");
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title> Primos </title>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<ul>");
            // writer.write("{\"primos\":[\n");
            for (Integer integer : primos) {
                // for (int integer = 0; integer < cont; integer++) {
                writer.write(String.format("<li> %d </li><br>", integer));
                // String linea = String.format("{\"primo\":%d}", primos[integer]);
                // if (integer != cont - 1) {
                // linea += ",";
                // }
                // writer.write(linea);
            }
            // writer.write("]}");
            writer.write("</ul>");
            writer.write("</body>");
            writer.write("</body>");
            writer.flush();
            writer.close();
            cliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean esPrimo(int x) {
        boolean res = true;
        int i = 2;
        while (i < Math.sqrt(x) && res) {
            if (x % i == 0) {
                res = false;
            }
            i++;
        }

        return res;
    }

}
