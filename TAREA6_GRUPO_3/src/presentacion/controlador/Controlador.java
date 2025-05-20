package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import presentacion.vista.FrmMenuPrincipal;
import presentacion.vista.frmListarPersona;
import presentacion.vista.FrmModificar;
import presentacion.vista.FrmAgregarPersona;
import presentacion.vista.FrmEliminar;

public class Controlador implements ActionListener {

    private FrmMenuPrincipal menu;
    private FrmAgregarPersona frmAgregar;
    private FrmEliminar frmEliminar;
    private frmListarPersona frmListar;
    private FrmModificar frmModificar;
    


    private PersonaNegocio personaNegocio;

    public Controlador(FrmMenuPrincipal menu) {
        this.menu = menu;
        this.personaNegocio = new PersonaNegocioImpl();

        // Escuchador para el ítem "Agregar"
        this.menu.getItemAgregar().addActionListener(this);
        this.menu.getItemEliminar().addActionListener(this);
        this.menu.getItemListar().addActionListener(this);
        this.menu.getItemModificar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menu.getItemAgregar())) {
            abrirVentanaAgregar();
        } else if (e.getSource().equals(menu.getItemEliminar())) {
            abrirVentanaEliminar();
        } else if (e.getSource().equals(menu.getItemListar())) {
        	abrirVentanalistar();
        } else if (e.getSource().equals(menu.getItemModificar())) {
	    	abrirVentanaModificar();
	    }
    }

    private void abrirVentanaAgregar() {
        if (frmAgregar == null || !frmAgregar.isDisplayable()) {
            frmAgregar = new FrmAgregarPersona();
            frmAgregar.setVisible(true);
        }
    }
    private void abrirVentanaEliminar() {
        if (frmEliminar == null || !frmEliminar.isDisplayable()) {
        	frmEliminar = new FrmEliminar();
        	frmEliminar.setVisible(true);
        }
    }

    private void abrirVentanalistar() {
        if (frmListar == null || !frmListar.isDisplayable()) {
        	frmListar = new frmListarPersona();
        	frmListar.setVisible(true);
        }
    }
    private void abrirVentanaModificar() {
        if (frmModificar == null || !frmModificar.isDisplayable()) {
        	frmModificar = new FrmModificar();
        	frmModificar.setVisible(true);
        }
    }
}
