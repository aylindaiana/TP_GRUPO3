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
	private JTextField textField_2;
	private JLabel lblResultado;
	public Ejercicio1() {
		super();
		setTitle("Ejercicio 1");
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 33, 140, 14);
		getContentPane().add(lblNombre);
		
		txtFieldNombre = new JTextField();
		txtFieldNombre.setBounds(39, 46, 140, 20);
		getContentPane().add(txtFieldNombre);
		txtFieldNombre.setColumns(10);
		
		txtFieldApellido = new JTextField();
		txtFieldApellido.setColumns(10);
		txtFieldApellido.setBounds(39, 86, 140, 20);
		getContentPane().add(txtFieldApellido);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(39, 73, 140, 14);
		getContentPane().add(lblApellido);
		
		txtFieldTelefono = new JTextField();
		txtFieldTelefono.setColumns(10);
		txtFieldTelefono.setBounds(39, 130, 140, 20);
		getContentPane().add(txtFieldTelefono);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(39, 117, 140, 14);
		getContentPane().add(lblTelefono);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(39, 174, 140, 20);
		getContentPane().add(textField_2);
		
		JLabel lbl1_3 = new JLabel("Fecha de Nacimiento");
		lbl1_3.setBounds(39, 161, 140, 14);
		getContentPane().add(lbl1_3);
		
		lblResultado = new JLabel("Los datos ingresados fueron: ");
		lblResultado.setBounds(39, 205, 410, 14);
		getContentPane().add(lblResultado);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkFields();
			}
		});
		btnEnviar.setBounds(189, 173, 126, 23);
		getContentPane().add(btnEnviar);
		
		appActiva();
	}
	
	public void checkFields() {
		boolean bandera = true;
		if(txtFieldNombre.getText().equals("")) {
			txtFieldNombre.setBackground(new Color(255, 0, 0));
			bandera = false;
		} else {
			txtFieldNombre.setBackground(new Color(255, 255, 255));
		}
		

		if(txtFieldApellido.getText().equals("")) {
			txtFieldApellido.setBackground(new Color(255, 0, 0));
			bandera = false;
		} else {
			txtFieldApellido.setBackground(new Color(255, 255, 255));
		}
		

		if(txtFieldTelefono.getText().equals("")) {
			txtFieldTelefono.setBackground(new Color(255, 0, 0));
			bandera = false;
		} else {
			txtFieldTelefono.setBackground(new Color(255, 255, 255));
		}
		
		if(bandera) {
			lblResultado.setText("Los datos ingresados son: " + txtFieldNombre.getText() + " " + txtFieldApellido.getText() + ", Telefono: " + txtFieldTelefono.getText());
			System.out.println("hola");
		}
	}
}
