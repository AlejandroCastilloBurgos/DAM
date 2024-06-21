/* Programa HTTP para un Servicio de Reservas de Restaurantes
Descripción:
Un servicio de reservas de restaurantes permite a los usuarios hacer reservas a través de la web. Desarrolla un sistema en Java que utilice HTTP para manejar solicitudes de los usuarios, permitiendo buscar disponibilidad y hacer reservas. Las reservas se guardan en un archivo que se especifica por línea de comandos.

Implementa un servidor web en Java multithread que maneje solicitudes HTTP de clientes buscando disponibilidad en el restaurante. El servidor debe poder recibir solicitudes que contengan la fecha y hora de la reserva. El servidor responderá con la disponibilidad y permitirá hacer la reserva.

El servidor también permite cancelar una reserva. */
package Ejercicio9;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Uso: java Main <archivo_de_reservas>");
            System.exit(1);
        }

        String archivoReservas = args[0];
        ReservaManager reservaManager = new ReservaManager(archivoReservas);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/reservas", new ReservaHandler(reservaManager));
        server.setExecutor(null);
        server.start();

        System.out.println("Servidor iniciado en el puerto 8000...");
    }
}
// java -cp out Ejercicio3.Main reservas.txt
/*
 * Ejecuta el siguiente comando desde el directorio que contiene el directorio
 * Ejercicio3:
 * 
 * sh
 * Copiar código
 * javac -d out Ejercicio3/*.java
 * Ejecutar el programa:
 * 
 * Utiliza el classpath para especificar el directorio de salida de la
 * compilación y el nombre completo de la clase Main incluyendo el paquete.
 * 
 * sh
 * Copiar código
 * java -cp out Ejercicio3.Main reservas.txt
 */

/*
 * 2. Ejecutar el servidor
 * Ejecuta la clase Main con el archivo de reservas como argumento. Este archivo
 * almacenará las reservas.
 * 
 * sh
 * Copiar código
 * java -cp out Ejercicio3.Main reservas.txt
 * 3. Realizar solicitudes HTTP
 * Usando curl
 * Consultar reservas (GET):
 * sh
 * Copiar código
 * curl -X GET "http://localhost:8000/reservas"
 * Hacer una reserva (POST):
 * sh
 * Copiar código
 * curl -X POST
 * "http://localhost:8000/reservas?nombre=Juan&fechaHora=2024-06-08T18:30:00"
 * Cancelar una reserva (DELETE):
 * sh
 * Copiar código
 * curl -X DELETE
 * "http://localhost:8000/reservas?nombre=Juan&fechaHora=2024-06-08T18:30:00"
 */