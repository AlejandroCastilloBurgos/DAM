
/**
 * Javier Abelln, 25 Mayo 2006
 * 
 * Para Cliente de chat.
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel para mostrar la conversacin y pedir al usuario el texto que quiere
 * enviar.
 * 
 * @author Chuidiang
 */
public class PanelCliente {
    /** Scroll */
    private JScrollPane scroll;

    /** Area para mostrar la conversaci */
    private JTextArea textArea;

    /** Para pedir el texto al usuario */
    private JTextField textField;

    /** Botn para enviar el texto */
    private JButton boton;

    /**
     * Crea el panel con todos sus componentes. Un Area de texto para ver la
     * conversacin, un textField para escribir el texto que queremos enviar y
     * un botn de enviar.
     * 
     * @param contenedor
     *                   Contenedor en el que aadir todos los componentes
     */
    public PanelCliente(Container contenedor) {
        contenedor.setLayout(new BorderLayout());
        textArea = new JTextArea();
        scroll = new JScrollPane(textArea);

        JPanel panel = new JPanel(new FlowLayout());
        textField = new JTextField(50);
        boton = new JButton("Enviar");
        panel.add(textField);
        panel.add(boton);

        contenedor.add(scroll, BorderLayout.CENTER);
        contenedor.add(panel, BorderLayout.SOUTH);
    }

    /**
     * Aade el actionListener que se le pasa tanto a pulsar <intro> en el
     * textField como al botn.
     * 
     * @param accion ActionListener a aadir.
     */
    public void addActionListener(ActionListener accion) {
        textField.addActionListener(accion);
        boton.addActionListener(accion);

    }

    /**
     * Aade el texto que se le pasa al textArea.
     * 
     * @param texto Texto a anadir
     */
    public void addTexto(String texto) {
        textArea.append(texto);
    }

    /**
     * Devuelve el texto que hay en el textfield y lo borra.
     * 
     * @return El texto del textfield.
     */
    public String getTexto() {
        String texto = textField.getText();
        textField.setText("");
        return texto;
    }
}
