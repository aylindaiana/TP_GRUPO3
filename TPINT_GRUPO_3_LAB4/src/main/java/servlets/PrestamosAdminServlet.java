package servlets;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.NegocioCuenta;
import negocio.NegocioPrestamo;
import negocio.NegocioUsuario;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioPrestamoImpl;
import negocioImpl.NegocioUsuarioImpl;

@WebServlet("/PrestamosAdminServlet")
public class PrestamosAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NegocioUsuario usuarioNegocio = new NegocioUsuarioImpl();
	private NegocioCuenta cuentaNegocio = new NegocioCuentaImpl();
	private NegocioPrestamo prestamoNegocio = new NegocioPrestamoImpl();
	
    public PrestamosAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double montoSolicitadoTotal = 0;
		double montoTotalAPagar = 0;
		
		List<Usuario> clientes = usuarioNegocio.listarClientes();
		List<Cuenta> cuentas = cuentaNegocio.listarCuentas(clientes.get(0).getId());
		List<Prestamo> prestamosPorCliente= prestamoNegocio.obtenerPorIdCliente(clientes.get(0).getId());
		List<Prestamo> prestamosPorCuenta = prestamoNegocio.obtenerPorIdCuenta(cuentas.get(0).getId());
		
		for(Prestamo aux : prestamosPorCuenta) {
			// se debe dividir el importe porque el prestamo guarda el monto  ->a pagar<-,
			// el monto  ->solicitado<-  se guarda en la cuenta del cliente al momento de aceptado el prestamo
			montoSolicitadoTotal += aux.getImporte()/1.5;
			montoTotalAPagar += aux.getImporte();
		}
		
		
		
	    request.setAttribute("listaClientes", clientes);
	    request.setAttribute("listaCuentas", cuentas);
	    request.setAttribute("listaPrestamos", prestamosPorCliente);
	    request.setAttribute("montoSolicitadoTotal", montoSolicitadoTotal);
	    request.setAttribute("montoTotalAPagar", montoTotalAPagar);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/prestamosAdmin.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		

		RequestDispatcher rd = request.getRequestDispatcher("/admin/prestamosAdmin.jsp");
	    rd.forward(request, response);
		
	}

}
