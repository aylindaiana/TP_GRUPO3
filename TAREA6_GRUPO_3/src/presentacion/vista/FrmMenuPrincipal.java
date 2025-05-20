package presentacion.vista;

import javax.swing.*;

public class FrmMenuPrincipal extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
    private JMenu menuPersona;
    private JMenuItem itemAgregar;
    private JMenuItem itemEliminar;
    private JMenuItem itemListar;
    private JMenuItem itemModificar;

    public FrmMenuPrincipal() {
        setTitle("Programa");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        menuBar = new JMenuBar();
        menuPersona = new JMenu("Persona");
        itemAgregar = new JMenuItem("Agregar");
        itemListar = new JMenuItem("Listar");

        menuPersona.add(itemAgregar);
        menuBar.add(menuPersona);
        
        itemEliminar = new JMenuItem("Eliminar");
        menuPersona.add(itemEliminar);
        
        itemModificar = new JMenuItem("Modificar");
        menuPersona.add(itemModificar);
        
        itemListar = new JMenuItem("Listar");
        menuPersona.add(itemListar);
        setJMenuBar(menuBar);
    }

    // Metodo getter que necesita el controlador
    public JMenuItem getItemAgregar() {
        return itemAgregar;
    }
    
    public JMenuItem getItemListar() {
        return itemListar;
    }
    
    public JMenuItem getItemEliminar() {
        return itemEliminar;
    }
    
    public JMenuItem getItemModificar() {
        return itemModificar;
    }
}
