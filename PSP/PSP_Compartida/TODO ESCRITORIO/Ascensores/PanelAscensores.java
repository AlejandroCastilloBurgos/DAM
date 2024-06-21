import javax.swing.*;
import java.awt.*;

public class PanelAscensores extends JFrame {
    private JTextArea areaTramas;

    public PanelAscensores() {
        super("Tramas de Control de Ascensores");
        inicializarUI();
    }

    private void inicializarUI() {
        areaTramas = new JTextArea(15, 30);
        areaTramas.setEditable(false); // No permitir edición
        JScrollPane scrollPane = new JScrollPane(areaTramas);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Añade una trama de control al área de texto.
     *
     * @param trama La trama de control recibida.
     */
    public void agregarTramaControl(String trama) {
        SwingUtilities.invokeLater(() -> {
            areaTramas.append(trama + "\n");
            areaTramas.setCaretPosition(areaTramas.getDocument().getLength()); // Auto-scroll al final
        });
    }
}
