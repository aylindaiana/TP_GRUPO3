package appEscritorio;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ejercicio1 extends Ventana {
	private static final long serialVersionUID = 1L;
	private JTextField txtFieldNombre;
	private JTextField txtFieldApellido;
	private JTextField txtFieldTelefono;
	private JTextField txtFieldFechaNacimiento;
	private JLabel lblResultado;
	private JLabel lblVerificadorFormatoFecha;
	private JLabel lblVerificadorFormatoTelefono;
	private JLabel lblVerificadorFormatoApellido;
	private JLabel lblVerificadorFormatoNombre;
	
	public Ejercicio1() {
		super();
		setTitle("Contactos");
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 33, 140, 14);
		getContentPane().add(lblNombre);
		
		txtFieldNombre = new JTextField();
		txtFieldNombre.setBounds(182, 33, 234, 20);
		getContentPane().add(txtFieldNombre);
		txtFieldNombre.setColumns(10);
		
		txtFieldApellido = new JTextField();
		txtFieldApellido.setColumns(10);
		txtFieldApellido.setBounds(182, 73, 234, 20);
		getContentPane().add(txtFieldApellido);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(39, 73, 140, 14);
		getContentPane().add(lblApellido);
		
		txtFieldTelefono = new JTextField();
		txtFieldTelefono.setColumns(10);
		txtFieldTelefono.setBounds(182, 117, 234, 20);
		getContentPane().add(txtFieldTelefono);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(39, 117, 140, 14);
		getContentPane().add(lblTelefono);
		
		txtFieldFechaNacimiento = new JTextField();
		txtFieldFechaNacimiento.setColumns(10);
		txtFieldFechaNacimiento.setBounds(182, 158, 234, 20);
		getContentPane().add(txtFieldFechaNacimiento);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaNacimiento.setBounds(39, 161, 140, 14);
		getContentPane().add(lblFechaNacimiento);
		
		lblResultado = new JLabel("Los datos ingresados fueron: ");
		lblResultado.setBounds(46, 323, 685, 14);
		getContentPane().add(lblResultado);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkFields();
			}
		});
		btnEnviar.setBounds(290, 189, 126, 23);
		getContentPane().add(btnEnviar);
		
		lblVerificadorFormatoFecha = new JLabel(" ");
		lblVerificadorFormatoFecha.setBounds(436, 161, 295, 14);
		getContentPane().add(lblVerificadorFormatoFecha);
		
		lblVerificadorFormatoTelefono = new JLabel(" ");
		lblVerificadorFormatoTelefono.setBounds(436, 120, 295, 14);
		getContentPane().add(lblVerificadorFormatoTelefono);
		
		lblVerificadorFormatoApellido = new JLabel(" ");
		lblVerificadorFormatoApellido.setBounds(436, 76, 295, 14);
		getContentPane().add(lblVerificadorFormatoApellido);
		
		lblVerificadorFormatoNombre = new JLabel(" ");
		lblVerificadorFormatoNombre.setBounds(436, 39, 295, 14);
		getContentPane().add(lblVerificadorFormatoNombre);
		
		appActiva();
	}
	
	public void checkFields() {
		boolean bandera = true;
		if(txtFieldNombre.getText().equals("")) {
			txtFieldNombre.setBackground(new Color(255, 0, 0));
			lblVerificadorFormatoNombre.setText("Campo obligatorio");
			bandera = false;
		} else {
			txtFieldNombre.setBackground(new Color(255, 255, 255));
		}
		

		if(txtFieldApellido.getText().equals("")) {
			txtFieldApellido.setBackground(new Color(255, 0, 0));
			lblVerificadorFormatoApellido.setText("Campo obligatorio");
			bandera = false;
		} else {
			txtFieldApellido.setBackground(new Color(255, 255, 255));
		}
		

		if(txtFieldTelefono.getText().equals("")) {
			txtFieldTelefono.setBackground(new Color(255, 0, 0));
			lblVerificadorFormatoTelefono.setText("Campo obligatorio");
			bandera = false;
		} else {
			txtFieldTelefono.setBackground(new Color(255, 255, 255));
		}
				
		for(int x = 0; x < txtFieldTelefono.getText().length(); x++) {
			if(!(Character.isDigit(txtFieldTelefono.getText().charAt(x)))) {
				txtFieldTelefono.setBackground(new Color(255, 0, 0));
				lblVerificadorFormatoTelefono.setText("Este campo solo acepta numeros");
				bandera = false;
			}
		}
		
		if(txtFieldFechaNacimiento.getText().length() == 8) {
			for(int x = 0; x < txtFieldFechaNacimiento.getText().length(); x++) {
				
				boolean limpiar = true;
				
				if(x == 0 || x == 1 || x == 3 || x == 4 || x == 6 || x == 7) {
					if(!(Character.isDigit(txtFieldFechaNacimiento.getText().charAt(x)))) {
						txtFieldFechaNacimiento.setBackground(new Color(255, 0, 0));
						lblVerificadorFormatoFecha.setText("Formato fecha '11/11/11' ");
						lblVerificadorFormatoFecha.setBackground(Color.red);
						bandera = false;
						limpiar = false;
						break;
					}
				}
				
				if((x == 2 || x == 5) && txtFieldFechaNacimiento.getText().charAt(x) != '/') {
					txtFieldFechaNacimiento.setBackground(new Color(255, 0, 0));
					lblVerificadorFormatoFecha.setText("Formato fecha '11/11/11' ");
					bandera = false;
					limpiar = false;
					break;
				}
				
				if(limpiar) {
					txtFieldFechaNacimiento.setBackground(new Color(255, 255, 255));
					lblVerificadorFormatoFecha.setText("");
				}
			}
		} else if(txtFieldFechaNacimiento.getText().length() < 8 || txtFieldFechaNacimiento.getText().length() > 8){
			txtFieldFechaNacimiento.setBackground(new Color(255, 0, 0));
			lblVerificadorFormatoFecha.setText("Formato fecha '11/11/11' ");
			lblVerificadorFormatoFecha.setBackground(Color.red);
			bandera = false;
			
		} else if (txtFieldFechaNacimiento.getText().equals("")) {
			txtFieldFechaNacimiento.setBackground(new Color(255, 0, 0));
			bandera = false;
			
		} else {
			txtFieldFechaNacimiento.setBackground(new Color(255, 255, 255));
			lblVerificadorFormatoFecha.setText("");
		}
		
		
		
		if(bandera) {
			
			lblResultado.setText(
					"Los datos ingresados son: " 
					+ txtFieldNombre.getText() 
					+ ", " + txtFieldApellido.getText() 
					+ ", Telefono: " + txtFieldTelefono.getText() 
					+ ", Fecha Nacimiento: " + txtFieldFechaNacimiento.getText()
			);
			
			txtFieldNombre.setText("");
			lblVerificadorFormatoNombre.setText("");
			txtFieldApellido.setText("");
			lblVerificadorFormatoApellido.setText("");
			txtFieldTelefono.setBackground(new Color(255, 255, 255));
			lblVerificadorFormatoTelefono.setText("");
			txtFieldTelefono.setText("");
			txtFieldTelefono.setBackground(new Color(255, 255, 255));
			lblVerificadorFormatoTelefono.setText("");
			txtFieldFechaNacimiento.setText("");
			txtFieldFechaNacimiento.setBackground(new Color(255, 255, 255));
			lblVerificadorFormatoFecha.setText("");
		}
	}
}
