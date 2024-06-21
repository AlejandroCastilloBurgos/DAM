import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 5555;// Integer.parseInt(args[0]);
        GestorEntradas gestor = new GestorEntradas("/home/casti/Desktop/Ejercicio32Ev/entradas.csv");
        Observador observador = new ObservadorVIP(); // Asumiendo que tienes una clase Observador con un hijo
                                                     // ObservadorVIP

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String codigo = new String(packet.getData(), 0, packet.getLength());
                String resultado = gestor.procesarEntrada(codigo);

                // Notificar al observador si la entrada es válida
                if ("VÁLIDA".equals(resultado)) {
                    observador.notificar(codigo); // Implementar lógica en ObservadorVIP para verificar si es VIP
                }

                System.out.println(resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
