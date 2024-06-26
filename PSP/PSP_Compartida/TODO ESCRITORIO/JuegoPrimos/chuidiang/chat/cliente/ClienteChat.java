
/**
 * Javier Abelln, 25 Mayo 2006
 * Cliente de un chat
 */

import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Clase con el main de un cliente del chat.
 * Establece la conexion y crea la ventana y la clase de control.
 * 
 * @author Chuidiang
 *
 */

public class ClienteChat {
    /** Socket con el servidor del chat */
    private Socket socket;

    /** Panel con la ventana del cliente */
    private PanelCliente panel;

    /**
     * Arranca el Cliente de chat.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new ClienteChat();
    }

    /**
     * Crea la ventana, establece la conexin e instancia al controlador.
     */
    public ClienteChat() {
        try {
            creaYVisualizaVentana();
            socket = new Socket("localhost", 5557);
            ControlCliente control = new ControlCliente(socket, panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea una ventana, le mete dentro el panel para el cliente y la visualiza
     */
    private void creaYVisualizaVentana() {
        JFrame v = new JFrame();
        panel = new PanelCliente(v.getContentPane());
        v.pack();
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
