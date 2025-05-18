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


public class FrmEliminar extends JFrame {

    private JButton btnEliminar;
    private List<Persona> listaPersonas;
    private DefaultListModel<String> modeloLista;
    private JList<String> jListPersonas;
    
    public FrmEliminar() {
    
        setTitle("Eliminar Persona");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel label = new JLabel("Eliminar personas");
        label.setBounds(10, 10, 200, 20);
        add(label);
        
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
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(90, 120, 100, 30);
        add(btnEliminar);
        
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListPersonas.getSelectedIndex();
                if (index >= 0) {
                    String seleccionado = modeloLista.get(index);
                    String dni = seleccionado.substring(seleccionado.lastIndexOf(" ") + 1);

                    if (negocio.eliminarPersona(dni)) {
                        modeloLista.remove(index);
                        JOptionPane.showMessageDialog(null, "Persona eliminada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar la persona.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una persona para poder eliminarla.");
                }
            }
        });    
    }     
}
