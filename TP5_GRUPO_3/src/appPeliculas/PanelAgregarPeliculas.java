package appPeliculas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAgregarPeliculas extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JLabel lblId;
	private JLabel lblNombre;
	private JLabel lblGenero;
	private JLabel lblGenerateId;
	private JComboBox cbxGenero;
	
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
		
		// ----------------------------
		// Aca dejo el espacio en el que iria el incremento.
		lblGenerateId = new JLabel("");
		lblGenerateId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGenerateId.setBounds(208, 64, 73, 22);
		add(lblGenerateId);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombre.setBounds(208, 108, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		cbxGenero = new JComboBox();
		cbxGenero.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbxGenero.setBounds(208, 150, 86, 22);
		add(cbxGenero);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtNombre.getText().isEmpty()) {
					String nombre = txtNombre.getText();
					Categorias categoria = (Categorias) cbxGenero.getSelectedItem();
					Peliculas peli = new Peliculas(nombre, categoria);
					
					txtNombre.setText("");
				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAceptar.setBounds(109, 203, 107, 23);
		add(btnAceptar);
		
	}


}
