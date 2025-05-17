package presentacion.vista;

import javax.swing.*;

public class FrmMenuPrincipal extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuPersona;
    private JMenuItem itemAgregar;

    public FrmMenuPrincipal() {
        setTitle("Programa");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        menuBar = new JMenuBar();
        menuPersona = new JMenu("Persona");
        itemAgregar = new JMenuItem("Agregar");

        menuPersona.add(itemAgregar);
        menuBar.add(menuPersona);
        setJMenuBar(menuBar);
    }

    // Método getter que necesita el controlador
    public JMenuItem getItemAgregar() {
        return itemAgregar;
    }
}
