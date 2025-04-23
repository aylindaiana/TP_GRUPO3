package appEscritorio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ejercicio2 extends Ventana {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNota1;
	private JTextField txtNota2;
	private JTextField txtNota3;
	private JButton btnCalcular;
	private JTextField txtPromedio;
	private JTextField txtCondicion;

	
	public Ejercicio2() {
		setTitle("Notas del estudiante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelNotas = new JPanel();
		panelNotas.setBorder(javax.swing.BorderFactory.createTitledBorder("Notas del estudiante"));
		panelNotas.setBounds(30, 30, 258, 150); 
		panelNotas.setLayout(null); 
		contentPane.add(panelNotas);
		
		JLabel lblNota1 = new JLabel("Nota 1:");
		lblNota1.setBounds(10, 25, 46, 14);
		panelNotas.add(lblNota1);
		
		JLabel lblNota2 = new JLabel("Nota 2:");
		lblNota2.setBounds(10, 50, 46, 14);
		panelNotas.add(lblNota2);
		
		JLabel lblNota3 = new JLabel("Nota 3:");
		lblNota3.setBounds(10, 75, 46, 14);
		panelNotas.add(lblNota3);
		
		txtNota1 = new JTextField();
		txtNota1.setBounds(66, 22, 86, 20);
		panelNotas.add(txtNota1);
		txtNota1.setColumns(10);
		
		txtNota2 = new JTextField();
		txtNota2.setBounds(66, 47, 86, 20);
		panelNotas.add(txtNota2);
		txtNota2.setColumns(10);
		
		txtNota3 = new JTextField();
		txtNota3.setBounds(66, 72, 86, 20);
		panelNotas.add(txtNota3);
		txtNota3.setColumns(10);
		
		JComboBox comboTP = new JComboBox();
		comboTP.setModel(new DefaultComboBoxModel(new String[] {"Aprobado", "Desaprobado"}));
		comboTP.setBounds(66, 103, 86, 22);
		panelNotas.add(comboTP);
		
		JLabel lblTps = new JLabel("TPS");
		lblTps.setBounds(10, 107, 46, 14);
		panelNotas.add(lblTps);
		
		JPanel panelResultados = new JPanel();
		panelResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Notas del estudiante"));
		panelResultados.setBounds(30, 191, 258, 128);
		panelResultados.setLayout(null);
		contentPane.add(panelResultados);
		
		JLabel lblPromedio = new JLabel("Promedio:");
		lblPromedio.setBounds(10, 30, 58, 14);
		panelResultados.add(lblPromedio);
		
		JLabel lblCondicion = new JLabel("Condicion:");
		lblCondicion.setBounds(10, 60, 58, 14);
		panelResultados.add(lblCondicion);
		
		txtPromedio = new JTextField();
		txtPromedio.setEditable(false);
		txtPromedio.setBounds(78, 27, 86, 20);
		panelResultados.add(txtPromedio);
		txtPromedio.setColumns(10);
		
		txtCondicion = new JTextField();
		txtCondicion.setEditable(false);
		txtCondicion.setBounds(78, 57, 86, 20);
		panelResultados.add(txtCondicion);
		txtCondicion.setColumns(10);
		
		btnCalcular = new JButton("CALCULAR");
		btnCalcular.setBounds(326, 50, 101, 35);
		contentPane.add(btnCalcular);
		
		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNuevo.setBounds(326, 95, 101, 35);
		contentPane.add(btnNuevo);
		
		btnNuevo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        txtNota1.setText("");
		        txtNota2.setText("");
		        txtNota3.setText("");
		        txtPromedio.setText("");
		        txtCondicion.setText("");
		        comboTP.setSelectedIndex(0);
		    }
		});
		
		btnCalcular.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            double nota1 = Double.parseDouble(txtNota1.getText());
		            double nota2 = Double.parseDouble(txtNota2.getText());
		            double nota3 = Double.parseDouble(txtNota3.getText());
		            
		            if (nota1 < 1 || nota1 > 10 || nota2 < 1 || nota2 > 10 || nota3 < 1 || nota3 > 10) {
		                throw new NumberFormatException("Las notas deben estar entre 1 y 10");
		            }
		            
		            double promedio = (nota1 + nota2 + nota3) / 3;
		            txtPromedio.setText(String.format("%.2f", promedio));
		            
		            String tp = (String) comboTP.getSelectedItem();
		            String condicion = "";

		            if (tp.equals("Desaprobado") || nota1 < 6 || nota2 < 6 || nota3 < 6) {
		                condicion = "Libre";
		            } else if (nota1 >= 8 && nota2 >= 8 && nota3 >= 8 && tp.equals("Aprobado")) {
		                condicion = "Promocionado";
		            } else if (nota1 >= 6 && nota2 >= 6 && nota3 >= 6 && tp.equals("Aprobado")) {
		                condicion = "Regular";
		            }

		            txtCondicion.setText(condicion);
		            
		        } catch (NumberFormatException ex) {
			        txtNota1.setText("Nro Invalido");
			        txtNota2.setText("Nro Invalido");
			        txtNota3.setText("Nro Invalido");
		            txtPromedio.setText("Error");
		            txtCondicion.setText("Error");
		        }
		    }
		});


		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(326, 141, 101, 35);
		contentPane.add(btnSalir);
		
		btnSalir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	dispose();
		    }
		});

		
		appActiva();

	}
}
