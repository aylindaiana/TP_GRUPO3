package main;

import presentacion.controlador.Controlador;
import presentacion.vista.FrmMenuPrincipal;

public class Principal {
    public static void main(String[] args) {
        FrmMenuPrincipal menu = new FrmMenuPrincipal();
        Controlador controlador = new Controlador(menu);
        menu.setVisible(true);
    }
}
