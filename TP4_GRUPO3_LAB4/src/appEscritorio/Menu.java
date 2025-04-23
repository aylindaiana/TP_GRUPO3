package appEscritorio;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class Menu extends Ventana{
	private static final long serialVersionUID = 1L;
	
	public Menu() {
		super();
		setTitle("Menu");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GRUPO NRO° 3");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(29, 41, 165, 14);
		getContentPane().add(lblNewLabel);
		
		JButton btnEj1 = new JButton("Ejercicio 1");
		btnEj1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejercicio1 ej = new Ejercicio1();
			}
		});
		
		btnEj1.setBounds(174, 83, 123, 23);
		getContentPane().add(btnEj1);
		
		JButton btnEj2 = new JButton("Ejercicio 2");
		btnEj2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejercicio2 ej2 = new Ejercicio2();
			}
		});
		btnEj2.setBounds(174, 117, 123, 23);
		getContentPane().add(btnEj2);
		
		JButton btnEj3 = new JButton("Ejercicio 3");
		btnEj3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejercicio3 ej3 = new Ejercicio3();
			}
		});
		btnEj3.setBounds(174, 151, 123, 23);
		getContentPane().add(btnEj3);
		
		appActiva();
	}
}
