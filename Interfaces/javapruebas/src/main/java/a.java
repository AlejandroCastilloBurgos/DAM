/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Castillo
 */

    
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class a {
    public static void main(String[] args) {
        // Crear checkbox y campo de texto
        JCheckBox checkboxPatata = new JCheckBox("Patata");
        JTextField campoTexto = new JTextField(20);

        // Crear botón
        JButton botonAlehop = new JButton("Alehop");

        // Crear panel
        JPanel panel = new JPanel();
        panel.add(checkboxPatata);
        panel.add(campoTexto);
        panel.add(botonAlehop);

        // Crear ventana
        JFrame ventana = new JFrame("Cambiar Nombre CheckBox");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().add(panel);
        ventana.setSize(300, 100);
        ventana.setVisible(true);

        // Agregar ActionListener al botón
        botonAlehop.addActionListener((ActionEvent e) -> {
            String nuevoNombre = campoTexto.getText();
            checkboxPatata.setText(nuevoNombre);
        });
    }
}

    

