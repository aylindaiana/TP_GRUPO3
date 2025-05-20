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
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FrmModificar extends JFrame {

    private JButton btnModificar;
    private List<Persona> listaPersonas;
    private DefaultListModel<String> modeloLista;
    private JList<String> jListPersonas;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDni;
    private PersonaNegocio negocio;
    
    public FrmModificar() {
    
        setTitle("Modificar Persona");
        setSize(387, 266);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        negocio = new PersonaNegocioImpl();
        
        modeloLista = cargarLista(listaPersonas);
        
        jListPersonas = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(jListPersonas);
        scrollPane.setBounds(10, 10, 285, 90);
        getContentPane().add(scrollPane);
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(201, 142, 100, 30);
        getContentPane().add(btnModificar);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(10, 111, 86, 20);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);
        
        txtApellido = new JTextField();
        txtApellido.setColumns(10);
        txtApellido.setBounds(106, 111, 86, 20);
        getContentPane().add(txtApellido);
        
        txtDni = new JTextField();
        txtDni.setEditable(false);
        txtDni.setColumns(10);
        txtDni.setBounds(202, 111, 86, 20);
        getContentPane().add(txtDni);
        

        jListPersonas.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = jListPersonas.getSelectedIndex();
                if (index >= 0) {
                	
                    String seleccionado = modeloLista.get(index);
                    // separamos dni
                    String dni = seleccionado.substring(seleccionado.lastIndexOf(" ") + 1);
                    // Separamos la cadena de texto hasta el numero de dni
                    String nombreApellido = seleccionado.substring(0, seleccionado.lastIndexOf(" "));

                    // Separamos en nombre y apellido (asumiendo cliente ideal con 1 nombre y 1 apellido
                    String[] partes = nombreApellido.split(" ", 2);

                    String nombre = partes[0];
                    String apellido = partes[1];

                    Persona modificar = new Persona(nombre, apellido, dni);
                    
                    txtNombre.setText(nombre);
                    txtApellido.setText(apellido);
                    txtDni.setText(dni);
                } 
        	}
        });
        
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListPersonas.getSelectedIndex();
                if (index >= 0) {
                	
                    String seleccionado = modeloLista.get(index);
                    // separamos dni
                    String dni = seleccionado.substring(seleccionado.lastIndexOf(" ") + 1);
                    // Separamos la cadena de texto hasta el numero de dni
                    String nombreApellido = seleccionado.substring(0, seleccionado.lastIndexOf(" "));

                    // Separamos en nombre y apellido (asumiendo cliente ideal con 1 nombre y 1 apellido
                    String[] partes = nombreApellido.split(" ", 2);

                    String nombre = txtNombre.getText();
                    String apellido = txtApellido.getText();

                    Persona modificar = new Persona(dni, nombre, apellido);
                    
                    txtNombre.setText(nombre);
                    txtApellido.setText(apellido);
                    txtDni.setText(dni);
                    
                    if (negocio.modificarPersona(modificar)) {
                        JOptionPane.showMessageDialog(null, "Persona modificada correctamente.");
                        modeloLista.clear();
                        modeloLista = cargarLista(listaPersonas);
                        jListPersonas.setModel(modeloLista);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo modificar la persona.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una persona para poder eliminarla.");
                }
            }
        });    
    }     
    
    public DefaultListModel<String> cargarLista(List<Persona> listaPersonas){
    	DefaultListModel<String> modeloLista = new DefaultListModel<>();
        listaPersonas = negocio.obtenerTodas();
        
        for (Persona p : listaPersonas) {
            modeloLista.addElement(p.getNombre() + " " + p.getApellido() + " " + p.getDni());
        }
        return modeloLista;
    };
}
