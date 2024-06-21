/*Problema:
Generar botellas en threads empleados con tiempos de generación entre 2 y 3 segundos.
Mantener un almacenamiento con capacidad para 50 botellas.
Utilizar UDP para recibir peticiones de botellas de los clientes y responder con la cantidad de botellas entregadas (o -1 si no hay suficientes).
Sincronizar los threads utilizando synchronized, wait, notify o notifyAll. */
package Ejercicio8;

public class Main {
    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica();
        Thread[] empleados = new Thread[5];

        for (int i = 0; i < empleados.length; i++) {
            empleados[i] = new Thread(new Empleado(fabrica), "Empleado" + (i + 1));
            empleados[i].start();
        }

        Thread servidor = new Thread(new Servidor(fabrica), "ServidorUDP");
        servidor.start();
    }

}
