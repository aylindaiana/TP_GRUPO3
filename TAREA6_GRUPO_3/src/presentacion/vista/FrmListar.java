package presentacion.vista;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import entidad.Persona;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class FrmListar extends JFrame {

    private List<Persona> listaPersonas;
    private DefaultListModel<String> modeloLista;
    private JList<String> jListPersonas;
    
    public FrmListar() {
    
        setTitle("Listar Personas");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        PersonaNegocio negocio = new PersonaNegocioImpl();
        listaPersonas = negocio.obtenerTodas();
        
        modeloLista = new DefaultListModel<>();
        for (Persona p : listaPersonas) {
            modeloLista.addElement(p.getNombre() + " " + p.getApellido() + " " + p.getDni());
        }
       
        jListPersonas = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(jListPersonas);
        scrollPane.setBounds(10, 40, 260, 70);
        add(scrollPane);
    }     
}
