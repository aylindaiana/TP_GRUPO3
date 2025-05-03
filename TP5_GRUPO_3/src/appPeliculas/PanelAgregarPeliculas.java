package appPeliculas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAgregarPeliculas extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JLabel lblId;
	private JLabel lblNombre;
	private JLabel lblGenero;
	private JLabel lblGenerateId;
	private JComboBox cbxGenero;
	private int proximoId = 1;
	
	public PanelAgregarPeliculas() {
		setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(57, 64, 73, 22);
		add(lblId);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(57, 107, 73, 22);
		add(lblNombre);
		
		lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenero.setBounds(57, 150, 73, 22);
		add(lblGenero);
		
		lblGenerateId = new JLabel("");
		lblGenerateId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenerateId.setBounds(208, 64, 73, 22);
		add(lblGenerateId);
		
		lblGenerateId.setText(String.valueOf(proximoId));
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombre.setBounds(208, 108, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		cbxGenero = new JComboBox();
		cbxGenero.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbxGenero.setBounds(208, 150, 86, 22);
		add(cbxGenero);
		
		cbxGenero.addItem(new Categorias("Seleccione un genero")); // valor por defecto
	    cbxGenero.addItem(new Categorias("Terror"));
	    cbxGenero.addItem(new Categorias("Accion"));
	    cbxGenero.addItem(new Categorias("Suspenso"));
	    cbxGenero.addItem(new Categorias("Romantica"));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText().trim();
			    Categorias categoria = (Categorias) cbxGenero.getSelectedItem();

			    if (nombre.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para la película.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    if (categoria.getNombre().equalsIgnoreCase("Seleccione un genero")) {
			        JOptionPane.showMessageDialog(null, "Debe seleccionar un género válido.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    Peliculas peli = new Peliculas(nombre, categoria);

			    txtNombre.setText("");
			    cbxGenero.setSelectedIndex(0);
			    proximoId++;
			    lblGenerateId.setText(String.valueOf(proximoId));
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAceptar.setBounds(109, 203, 107, 23);
		add(btnAceptar);
		
	}


}
