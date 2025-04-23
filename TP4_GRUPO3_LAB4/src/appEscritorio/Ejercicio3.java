package appEscritorio;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class Ejercicio3 extends Ventana {

	private static final long serialVersionUID = 1L;
	private JPanel ContentPane;
	private JTextField textFieldHoras;
	
	public Ejercicio3() {
		setTitle("Selección múltiple");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 445);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		
		// Panel para sistema operativo
		JPanel panelSistemaOperativo = new JPanel();
		panelSistemaOperativo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSistemaOperativo.setBounds(10, 24, 430, 45);
		ContentPane.add(panelSistemaOperativo);
		panelSistemaOperativo.setLayout(null);
		
		JLabel lblSistemaOperativo = new JLabel("Elije un sistema operativo");
		lblSistemaOperativo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSistemaOperativo.setBounds(10, 11, 174, 14);
		panelSistemaOperativo.add(lblSistemaOperativo);
		
		JRadioButton rdbtnWindows = new JRadioButton("Windows");
		rdbtnWindows.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnWindows.setBounds(173, 7, 88, 23);
		panelSistemaOperativo.add(rdbtnWindows);
		
		JRadioButton rdbtnMac = new JRadioButton("Mac");
		rdbtnMac.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnMac.setBounds(263, 7, 53, 23);
		panelSistemaOperativo.add(rdbtnMac);
		
		JRadioButton rdbtnLinux = new JRadioButton("Linux");
		rdbtnLinux.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnLinux.setBounds(321, 8, 66, 23);
		panelSistemaOperativo.add(rdbtnLinux);
		
		 // Grupo para los radio buttons
        ButtonGroup grupoSO = new ButtonGroup();
        grupoSO.add(rdbtnWindows);
        grupoSO.add(rdbtnMac);
        grupoSO.add(rdbtnLinux);
        
        // Panel para especialidades
		JPanel panelEspecialidad = new JPanel();
		panelEspecialidad.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelEspecialidad.setBounds(10, 80, 430, 145);
		ContentPane.add(panelEspecialidad);
		panelEspecialidad.setLayout(null);
		
		JLabel lblEspecialidad = new JLabel("Elije una especialidad");
		lblEspecialidad.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblEspecialidad.setBounds(10, 59, 137, 14);
		panelEspecialidad.add(lblEspecialidad);
		
		JCheckBox chckbxProgramacion = new JCheckBox("Programación");
		chckbxProgramacion.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		chckbxProgramacion.setBounds(202, 7, 119, 23);
		panelEspecialidad.add(chckbxProgramacion);
		
		JCheckBox chckbxAdministracion = new JCheckBox("Administración");
		chckbxAdministracion.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		chckbxAdministracion.setBounds(202, 55, 119, 23);
		panelEspecialidad.add(chckbxAdministracion);
		
		JCheckBox chckbxDisenoGrafico = new JCheckBox("Diseño gráfico");
		chckbxDisenoGrafico.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		chckbxDisenoGrafico.setBounds(202, 102, 119, 23);
		panelEspecialidad.add(chckbxDisenoGrafico);
		
		// Campo para horas
		JLabel lblCantidadHoras = new JLabel("Cantidad de horas en el computador:");
		lblCantidadHoras.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCantidadHoras.setBounds(10, 255, 221, 14);
		ContentPane.add(lblCantidadHoras);
		
		textFieldHoras = new JTextField();
		textFieldHoras.setBounds(241, 253, 120, 20);
		ContentPane.add(textFieldHoras);
		textFieldHoras.setColumns(10);
		
		// Botón Aceptar
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAceptar.setBounds(272, 303, 89, 23);
		ContentPane.add(btnAceptar);
		
		btnAceptar.addActionListener(e -> {
		    String sistemaOperativo = "";
		    if (rdbtnWindows.isSelected()) {
		        sistemaOperativo = "Windows";
		    } else if (rdbtnMac.isSelected()) {
		        sistemaOperativo = "Mac";
		    } else if (rdbtnLinux.isSelected()) {
		        sistemaOperativo = "Linux";
		    }
		    
		    if (sistemaOperativo.isEmpty()) {
		        javax.swing.JOptionPane.showMessageDialog(this, "Por favor seleccione un sistema operativo.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    
		    StringBuilder especialidades = new StringBuilder();
		    if (chckbxProgramacion.isSelected()) {
		        especialidades.append("Programación - ");
		    }
		    if (chckbxAdministracion.isSelected()) {
		        especialidades.append("Administración - ");
		    }
		    if (chckbxDisenoGrafico.isSelected()) {
		        especialidades.append("Diseño Gráfico - ");
		    }
		    
		    if(especialidades.length() == 0)
		    {
		    	javax.swing.JOptionPane.showMessageDialog(this, "Por favor seleccione una especialidad", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    
		    String horas = textFieldHoras.getText().trim();
		    if (horas.isEmpty()) {
		    	javax.swing.JOptionPane.showMessageDialog(this, "Por favor ingrese horas.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
		    	return;
		    }

		    //Carga de mensaje final
		    String mensaje = sistemaOperativo + " - ";
		    if (especialidades.length() > 0) {
		        mensaje += especialidades.substring(0, especialidades.length() - 3);
		    }
		    mensaje += " - " + horas + " Hs";

		    // Mostrar mensaje
		    javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Mensaje", javax.swing.JOptionPane.INFORMATION_MESSAGE);
		});

		appActiva();
	}
}
