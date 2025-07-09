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
import entidad.Cuota;
import entidad.Prestamo;
import entidad.PrestamoRechazado;
import negocio.NegocioCuenta;
import negocio.NegocioCuota;
import negocio.NegocioPrestamo;
import negocio.NegocioPrestamoRechazado;
import negocio.NegocioUsuario;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioCuotaImpl;
import negocioImpl.NegocioPrestamoImpl;
import negocioImpl.NegocioPrestamoRechazadoImpl;
import negocioImpl.NegocioUsuarioImpl;

@WebServlet("/PrestamosAdminServlet")
public class PrestamosAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NegocioUsuario usuarioNegocio = new NegocioUsuarioImpl();
	private NegocioCuenta cuentaNegocio = new NegocioCuentaImpl();
	private NegocioPrestamo prestamoNegocio = new NegocioPrestamoImpl();
	private NegocioCuota cuotaNegocio = new NegocioCuotaImpl();
	private NegocioPrestamoRechazado prestamoRechazadoNegocio = new NegocioPrestamoRechazadoImpl();
	
    public PrestamosAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double montoSolicitadoTotal = 0;
		double montoTotalAPagar = 0;
		
		List<Usuario> clientes = usuarioNegocio.listarClientes().isEmpty() ? new ArrayList<Usuario>() : usuarioNegocio.listarClientes();
		List<Cuenta> cuentas = cuentaNegocio.listarCuentas(clientes.get(0).getId()).isEmpty() ? new ArrayList<Cuenta>() : cuentaNegocio.listarCuentas(clientes.get(0).getId());
		List<Prestamo> prestamosPorCliente= prestamoNegocio.obtenerPorIdCliente(clientes.get(0).getId()).isEmpty() ? new ArrayList<Prestamo>() : prestamoNegocio.obtenerPorIdCliente(clientes.get(0).getId());
		List<Prestamo> prestamosPorCuenta = prestamoNegocio.obtenerPorIdCuenta(cuentas.get(0).getId()).isEmpty() ? new ArrayList<Prestamo>() : prestamoNegocio.obtenerPorIdCuenta(cuentas.get(0).getId());

		int IDClienteSeleccionado = clientes.get(0) == null ? 0 : clientes.get(0).getId();
		int IDCuentaSeleccionada = cuentas.get(0) == null ? 0 : cuentas.get(0).getId();
		
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
	    request.setAttribute("idClienteSeleccionado", IDClienteSeleccionado);
	    request.setAttribute("idCuentaSeleccionado", IDCuentaSeleccionada);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/prestamosAdmin.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btn-buscar") != null) {
			int IDClienteSeleccionado = Integer.parseInt(request.getParameter("clienteSeleccionado") == null ? "0" : request.getParameter("clienteSeleccionado"));
			int IDCuentaSeleccionada = Integer.parseInt(request.getParameter("cuentaSeleccionada") == null ? "0" : request.getParameter("cuentaSeleccionada"));
			

			List<Usuario> clientes = usuarioNegocio.listarClientes().isEmpty() ? new ArrayList<Usuario>() : usuarioNegocio.listarClientes();
			List<Cuenta> cuentas = cuentaNegocio.listarCuentas(IDClienteSeleccionado);
			List<Prestamo> prestamosPorCliente= prestamoNegocio.obtenerPorIdCliente(IDClienteSeleccionado);
			List<Prestamo> prestamosPorCuenta = prestamoNegocio.obtenerPorIdCuenta(IDCuentaSeleccionada);
			
			double montoSolicitadoTotal = 0;
			double montoTotalAPagar = 0;
			

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
		    request.setAttribute("idClienteSeleccionado", IDClienteSeleccionado);
		    request.setAttribute("idCuentaSeleccionado", IDCuentaSeleccionada);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/prestamosAdmin.jsp");
		    dispatcher.forward(request, response);
		}

		if(request.getParameter("verCuotas") != null) {
			
			int IDPrestamo = Integer.parseInt(request.getParameter("verCuotas"));
			
			List<Cuota> cuotas = cuotaNegocio.listarCuotasPorPrestamo(IDPrestamo);
			
		    request.setAttribute("listaCuotas", cuotas);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/detallesPrestamo.jsp");
		    dispatcher.forward(request, response);
		    
		}
		
		if(request.getParameter("verPendiente") != null) {

			int IDPrestamo = Integer.parseInt(request.getParameter("verPendiente"));

			Prestamo prestamo = prestamoNegocio.obtenerPorIDPrestamo(IDPrestamo);
			

		    request.setAttribute("prestamoPendiente", prestamo);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/detallesPrestamo.jsp");
		    dispatcher.forward(request, response);
		}
		
		if(request.getParameter("verRechazo") != null) {

			int IDPrestamo = Integer.parseInt(request.getParameter("verRechazo"));
			
			PrestamoRechazado pRechazado = prestamoRechazadoNegocio.selectMotivoRechazoPrestamo(IDPrestamo);

			Prestamo prestamo = prestamoNegocio.obtenerPorIDPrestamo(IDPrestamo);
			
		    request.setAttribute("motivoRechazo", pRechazado);
		    request.setAttribute("datosPrestamo", prestamo);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/detallesPrestamo.jsp");
		    dispatcher.forward(request, response);
		}
	}
}









