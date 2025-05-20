package presentacion.vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;

public class frmListarPersona extends JFrame{
	private static final long serialVersionUID = 1L;

	private JTable tablePersonas; 
	private ArrayList<Persona> listaPersonas;
	private DefaultTableModel modelPersonas; 
	private String[] titulos = {"Nombre", "Apellido", "Dni"};
	
	public frmListarPersona() {

        setTitle("listado Personas");
        setSize(400, 300);
        setLocationRelativeTo(null);
		

		modelPersonas = new DefaultTableModel(titulos,0);
		tablePersonas = new JTable(modelPersonas);
        
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		tablePersonas = new JTable();
		getContentPane().add(tablePersonas, BorderLayout.CENTER);
		setListaPersonas();
		llenarTabla(listaPersonas);
	}


	public void llenarTabla(List<Persona> personasEnTabla) {
		modelPersonas.setRowCount(0);
		modelPersonas.setColumnCount(0);
		modelPersonas.setColumnIdentifiers(titulos);

		for (Persona p : personasEnTabla)
		{
			String nombre = p.getNombre();
			String apellido = p.getApellido(); 
			String dni = p.getDni();
			Object[] fila = {nombre, apellido, dni};
			modelPersonas.addRow(fila);
		}
		tablePersonas.setModel(modelPersonas);
	}
	
	public void setListaPersonas() {
		PersonaNegocio negocio = new PersonaNegocioImpl();
		listaPersonas = (ArrayList<Persona>)negocio.obtenerTodas();
	}
}
