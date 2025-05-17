package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import presentacion.vista.FrmMenuPrincipal;
import presentacion.vista.FrmAgregarPersona;

public class Controlador implements ActionListener {

    private FrmMenuPrincipal menu;
    private FrmAgregarPersona frmAgregar;
    private PersonaNegocio personaNegocio;

    public Controlador(FrmMenuPrincipal menu) {
        this.menu = menu;
        this.personaNegocio = new PersonaNegocioImpl();

        // Escuchador para el ítem "Agregar"
        this.menu.getItemAgregar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menu.getItemAgregar())) {
            abrirVentanaAgregar();
        }
    }

    private void abrirVentanaAgregar() {
        if (frmAgregar == null || !frmAgregar.isDisplayable()) {
            frmAgregar = new FrmAgregarPersona();
            frmAgregar.setVisible(true);
        }
    }
}
