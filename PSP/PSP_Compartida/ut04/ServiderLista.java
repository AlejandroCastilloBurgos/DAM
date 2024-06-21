package ut04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.nio.Buffer;

public class ServiderLista {

    /***
     * 
     * @author Jorge Dueñas Lerín
     *         Objetivo: entender cómo se programa un servicio usando de ejemplo el
     *         protocolo HTTP
     */

    private static final int DEFAULT_PORT = 8765;
    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);
        Logger loger = new Logger("primos.txt");
        while (true) {
            Socket connCliente = server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connCliente.getInputStream()));
            String header = reader.readLine();
            // GET ________ HTTP/1.1
            String peticion = extraeInformacion(header);
            String[] parametros = parametros(peticion);
            if (parametros.length == 2) {
                PrimosHTTP operador = new PrimosHTTP(connCliente, parametros);
                operador.addObserver(loger);
                operador.start();
            }
        }
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }

    private static String[] parametros(String cadena) {
        cadena = cadena.substring(1, cadena.length());
        return cadena.split("/");
    }
}
