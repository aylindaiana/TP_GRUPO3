package presentacion.vista;

import javax.swing.*;

public class FrmMenuPrincipal extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuPersona;
    private JMenuItem itemAgregar;
    private JMenuItem itemEliminar;

    public FrmMenuPrincipal() {
        setTitle("Programa");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        menuBar = new JMenuBar();
        menuPersona = new JMenu("Persona");
        itemAgregar = new JMenuItem("Agregar");

        menuPersona.add(itemAgregar);
        menuBar.add(menuPersona);
        
        itemEliminar = new JMenuItem("Eliminar");
        menuPersona.add(itemEliminar);
        setJMenuBar(menuBar);
    }

    // Metodo getter que necesita el controlador
    public JMenuItem getItemAgregar() {
        return itemAgregar;
    }
}
