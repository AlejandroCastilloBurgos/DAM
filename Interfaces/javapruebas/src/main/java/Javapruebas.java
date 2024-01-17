import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Javi
 */
public class Javapruebas {

    public static void main(String[] args) {

        /*JLabel textoSaludo = new JLabel("Adios");

        JTextField introducirTexto = new JTextField(25); //25 es el tamaño de la caja del JTextField

        JButton botonSaludar = new JButton("Hola");
        JButton botonPulsame = new JButton("Pulsame!");
        JButton botonx = new JButton("Si/no/cancelar");
        JButton botonIntroduceTexto = new JButton("Introduce texto");

        JRadioButton botonSeleccion1 = new JRadioButton("eleccion1");
        JRadioButton botonSeleccion2 = new JRadioButton("eleccion2");
        JRadioButton botonSeleccion3 = new JRadioButton("eleccion3");

        JCheckBox checkbox1 = new JCheckBox("tick");
        JCheckBox checkbox2 = new JCheckBox("tack");
        JCheckBox checkbox3 = new JCheckBox("tock");

        JComboBox listado = new JComboBox();
        listado.addItem("");
        listado.addItem("Rojo");
        listado.addItem("Azul");
        listado.addItem("Amarillo");
        listado.addItem("Verde");

        //El buttonGroup es para agrupar los radios button y hacer que solo se pueda seleccionar uno de ellos y no todos como pasaria con las cajitas de los checks (JCheckBox);
        ButtonGroup agruparRadioButtons = new ButtonGroup();
        agruparRadioButtons.add(botonSeleccion1);
        agruparRadioButtons.add(botonSeleccion2);
        agruparRadioButtons.add(botonSeleccion3);
        //creamos panel y añadimos todos los elementos
        JPanel panel = new JPanel();
        panel.add(textoSaludo);
        panel.add(introducirTexto);
        panel.add(botonSaludar);
        panel.add(botonPulsame);
        panel.add(botonIntroduceTexto);
        panel.add(botonx);
        panel.add(botonSeleccion1);
        panel.add(botonSeleccion2);
        panel.add(botonSeleccion3);
        panel.add(checkbox1);
        panel.add(checkbox2);
        panel.add(checkbox3);
        panel.add(listado);
        //creamos el framer y añadimos el panel al frame
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(500, 500);//tamaño predefinido
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Esto sirve para que cuando cierres la pestaña del IDE, tambien se cierre el proceso
        frame.setVisible(true);//para que se haga visible
        frame.setResizable(true);//Para que cuando hagas mas pequeño o mas grande la pantalla del proyecto, que este no se deforme
        frame.setTitle("Repaso Examen");//para poner un titulo en tu frame (el nombre de la ventana)
        
        listado.addActionListener((e) -> {
            String listaSeleccionada = listado.getSelectedItem().toString().toLowerCase();

            switch (listaSeleccionada) {
                case "rojo":
                    panel.setBackground(Color.red);
                    break;
                case "azul":
                    panel.setBackground(Color.blue);
                    break;
                case "amarillo":
                    panel.setBackground(Color.yellow);
                    break;
                case "verde":
                    panel.setBackground(Color.green);
                    break;
                default:
                    panel.setBackground(Color.white);
                    break;
            }
            /*
            if(listaSeleccionada.equals("rojo")){
                panel.setBackground(Color.red);
            }else if(listaSeleccionada.equals("azul")){
                panel.setBackground(Color.blue);
            }else if(listaSeleccionada.equals("amarillo")){
                panel.setBackground(Color.yellow);                
            }else if(listaSeleccionada.equals("verde")){
                panel.setBackground(Color.green);
            }else{
                panel.setBackground(Color.white);
            }
             
        });
        
        botonSaludar.addActionListener((e) -> {
            textoSaludo.setText(introducirTexto.getText().toString());
        });
        botonx.addActionListener((e) -> {
            int opcion = JOptionPane.showConfirmDialog(null, "Lo has entendido?");
            if (opcion == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Lo entendi");
            } else if (opcion == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "No lo entendi");
            }
        });
        botonIntroduceTexto.addActionListener((e) -> {
            String frase = JOptionPane.showInputDialog(null, "Introduce un mensaje");
            introducirTexto.setText(frase);
            String titulo = JOptionPane.showInputDialog(null, "Introduce un titulo para mi querido Frame");
            while (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No vale poner vacio, anda ponme algo");
                titulo = JOptionPane.showInputDialog(null, "Introduce un titulo para mi querido Frame");
            }
            frame.setTitle(titulo);
        });
        botonPulsame.addActionListener((e) -> {
            JOptionPane.showMessageDialog(null, "Me has pulsado!!!!!!");
        });
    }
}/*
        
        */
        



        // Creamos botones
        JFrame miventana = new JFrame("Escribe tu titulo");
        JTextField campo = new JTextField(45);
        JButton cambia = new JButton("Cambia");
        JButton minimizar = new JButton("Minimizar");
        JButton cerrar = new JButton("cerrar");
        JLabel titulobox = new JLabel("Selecciona el color de fondo");
        JComboBox colores = new JComboBox();
        JButton mensaje = new JButton("Mensaje");
        // 
        JCheckBox Cocacola = new JCheckBox("Cocacola");
        JCheckBox fanta = new JCheckBox("Fanta");
        JCheckBox nestea = new JCheckBox("Nestea");
        //
        JRadioButton refresco = new JRadioButton("refresco");
        JRadioButton agua = new JRadioButton("Agua");
        JRadioButton Cubata = new JRadioButton("Cubata");
        //
        JButton Reset = new JButton("Reset");
        JButton Imprime = new JButton("imprime");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(refresco);
        grupo.add(agua);
        grupo.add(Cubata);

        // Creamos panel para añadirlos
        JPanel mipanel = new JPanel();
        mipanel.add(titulobox);
        mipanel.add(campo);
        mipanel.add(cambia);
        mipanel.add(minimizar);
        mipanel.add(cerrar);
        mipanel.add(titulobox);
        mipanel.add(colores);
        mipanel.add(mensaje);
        mipanel.add(Cocacola);
        mipanel.add(fanta);
        mipanel.add(nestea);
        mipanel.add(refresco);
        mipanel.add(agua);
        mipanel.add(Cubata);
        mipanel.add(Reset);
        mipanel.add(Imprime);
        
        

        // Añadimos items al comboBOX
        colores.addItem("Rojo");
        colores.addItem("Azul");
        colores.addItem("Verde");

        // Creamos ventana
        miventana.setVisible(true);
        miventana.add(mipanel);
        miventana.setResizable(true);
        miventana.setSize(500, 500);
        
        

        // Listeners
        
        Cocacola.addActionListener((ActionEvent e) -> {
            if (Cocacola.isSelected()) {
                fanta.setSelected(false);
                nestea.setSelected(false);
                
            }
            
        });
        
        fanta.addActionListener((ActionEvent e) -> {
           if(fanta.isSelected()){
               Cocacola.setSelected(false);
               nestea.setSelected(false);
               
           } 
        });
        
        nestea.addActionListener((ActionEvent e) -> {
            if(nestea.isSelected()) {
                Cocacola.setSelected(false);
                fanta.setSelected(false);
            }
        });
        
        refresco.addActionListener((ActionEvent e) -> {
             if(refresco.isSelected()){
                Cocacola.setEnabled(true);
                fanta.setEnabled(true);
                nestea.setEnabled(true);
                
            }
            
        });
        
        agua.addActionListener((ActionEvent e) -> {
            if(agua.isSelected()){
                Cocacola.setEnabled(false);
                fanta.setEnabled(false);
                nestea.setEnabled(false);
                Cocacola.setSelected(false);
                nestea.setSelected(false);
                fanta.setSelected(false);
                
            }
        });
        
        Cubata.addActionListener((ActionEvent e) -> {
            
             if(Cubata.isSelected()){
                Cocacola.setEnabled(false);
                fanta.setEnabled(false);
                nestea.setEnabled(false);
                Cocacola.setSelected(false);
                nestea.setSelected(false);
                fanta.setSelected(false);
                
            }
            
        });
        
        Reset.addActionListener((ActionEvent e) -> {
            grupo.clearSelection();
            Cocacola.setSelected(false);
            nestea.setSelected(false);
            fanta.setSelected(false);
            Cocacola.setEnabled(true);
            nestea.setEnabled(true);
            fanta.setEnabled(true);
            
        });
        
        Imprime.addActionListener((ActionEvent e) -> {
            String pepe = grupo.getSelection().toString();
            System.out.println("ha Seleccionado" + pepe);
           
            if (Cocacola.isSelected()) {
                System.out.println("cocacola");
            } else if (fanta.isSelected()) {
                System.out.println("fanta");
                
            }else if (nestea.isSelected()){
                
                System.out.println("nestea");
            }
            
                
            
        });
        
        cerrar.addActionListener((ActionEvent e) -> {
            miventana.dispose();
            
        });
        cambia.addActionListener((ActionEvent e) -> {
            String campito = campo.getText();
            Cocacola.setText(campito);
            miventana.setTitle(campito);
            if (campito.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay nada perrrrrrrrrrro");
            }
        });

        minimizar.addActionListener((ActionEvent e) -> {
            miventana.setExtendedState(JFrame.ICONIFIED);
            cambia.setEnabled(false);
        });

        colores.addActionListener((ActionEvent e) -> {
            String colorcito = colores.getSelectedItem().toString();
            switch (colorcito) {
                case "Rojo":
                    mipanel.setBackground(Color.red);
                    break;
                case "Azul":
                    mipanel.setBackground(Color.blue);
                    break;
                case "Verde":
                    mipanel.setBackground(Color.green);
                    break;
                default:
                    mipanel.setBackground(Color.white);
            }
        });

        mensaje.addActionListener((ActionEvent) -> {
            JOptionPane.showMessageDialog(null, "Este examen esta tirao");
        });
    }
}





