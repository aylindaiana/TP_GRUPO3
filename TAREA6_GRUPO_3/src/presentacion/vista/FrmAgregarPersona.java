package presentacion.vista;

import javax.swing.*;
import entidad.Persona;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import java.awt.event.*;

public class FrmAgregarPersona extends JFrame {

    private JTextField txtDni, txtNombre, txtApellido;
    private JButton btnAgregar;

    public FrmAgregarPersona() {
        setTitle("Agregar Persona");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(10, 10, 80, 25);
        add(lblDni);

        txtDni = new JTextField();
        txtDni.setBounds(100, 10, 150, 25);
        add(txtDni);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 40, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 40, 150, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(10, 70, 80, 25);
        add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(100, 70, 150, 25);
        add(txtApellido);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(100, 110, 150, 30);
        add(btnAgregar);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Persona persona = new Persona(
                    txtDni.getText(),
                    txtNombre.getText(),
                    txtApellido.getText()
                );

                PersonaNegocio negocio = new PersonaNegocioImpl();
                if (negocio.agregarPersona(persona)) {
                    Object[] opciones = {"Sí", "No"};
                    int opcion = JOptionPane.showOptionDialog(
                        null,
                        "Persona agregada correctamente. ¿Desea agregar otra?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                        txtDni.setText("");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtDni.requestFocus();
                    } else {
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar persona o el DNI ya existe.");
                }
            }
        });
    }
}
