package servlets;

import java.io.IOException;
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
	

	private double montoSolicitadoTotal = 0;
	private double montoTotalAPagar = 0;
	private List<Usuario> clientes;
	private List<Cuenta> cuentas;
	private List<Prestamo> prestamosPorCliente;
	private List<Prestamo> prestamosPorCuenta;
	private int IDClienteSeleccionado = 2;
	private int IDCuentaSeleccionada = 1;
	
    public PrestamosAdminServlet() {
        super();
    	clientes = new ArrayList<Usuario>();
    	cuentas = new ArrayList<Cuenta>();
    	prestamosPorCliente = new ArrayList<Prestamo>();
    	prestamosPorCuenta = new ArrayList<Prestamo>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.clientes = usuarioNegocio.listarClientes();
		
		this.montoSolicitadoTotal = 0;
		this.montoTotalAPagar = 0;
		
		precargarDatos(clientes);
		
	    request.setAttribute("listaClientes", clientes);
	    request.setAttribute("listaCuentas", cuentas);
	    request.setAttribute("listaPrestamos", prestamosPorCliente);
	    request.setAttribute("montoSolicitadoTotal", montoSolicitadoTotal);
	    request.setAttribute("montoTotalAPagar", montoTotalAPagar);
	    request.setAttribute("idClienteSeleccionado", IDClienteSeleccionado);
	    request.setAttribute("idCuentaSeleccionada", IDCuentaSeleccionada);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/prestamosAdmin.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btn-buscar") != null) {
			this.IDClienteSeleccionado = Integer.parseInt(request.getParameter("clienteSeleccionado") == null ? "0" : request.getParameter("clienteSeleccionado"));
			this.IDCuentaSeleccionada = Integer.parseInt(request.getParameter("cuentaSeleccionada") == null ? "0" : request.getParameter("cuentaSeleccionada"));

			precargarDatos(clientes, IDClienteSeleccionado, IDCuentaSeleccionada);
			
		    request.setAttribute("listaClientes", clientes);
		    request.setAttribute("listaCuentas", cuentas);
		    request.setAttribute("listaPrestamos", prestamosPorCliente);
		    request.setAttribute("montoSolicitadoTotal", montoSolicitadoTotal);
		    request.setAttribute("montoTotalAPagar", montoTotalAPagar);
		    request.setAttribute("idClienteSeleccionado", IDClienteSeleccionado);
		    request.setAttribute("idCuentaSeleccionada", IDCuentaSeleccionada);
		    
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

	private void precargarDatos(List<Usuario> clientes) {
		if ((clientes == null) || clientes.isEmpty()) 
		{
		    clientes = new ArrayList<>();
 
		    manejoNulos();
		}
		else
		{
			int idCliente = clientes.get(0).getId();
			
			List<Cuenta> cAux = cuentaNegocio.listarCuentasActivasPorCliente(idCliente);
			this.cuentas = cAux == null || cAux.isEmpty() ? new ArrayList<Cuenta>() : cAux;
			
			if(cuentaNegocio.cuentasActivasPorCliente(idCliente) > 0)
			{

				List<Prestamo> pAux = prestamoNegocio.obtenerPorIdCliente(idCliente);
				this.prestamosPorCliente = pAux == null || pAux.isEmpty() ? new ArrayList<Prestamo>() : pAux;
				
				int idCuenta = cuentas.get(0).getId();
				List<Prestamo> pAux2 = prestamoNegocio.obtenerPorIdCuenta(idCuenta);
				this.prestamosPorCuenta = pAux2 == null || pAux2.isEmpty() ? new ArrayList<Prestamo>() : pAux2;

				//tienen que volver a ponerse en cero, dado que ahora se trata de variables miembro.
				this.montoSolicitadoTotal = 0;
				this.montoTotalAPagar = 0;
				 
				for(Prestamo aux : this.prestamosPorCuenta) {
					// se debe dividir el importe porque el prestamo guarda el monto  ->a pagar<-,
					// el monto  ->solicitado<-  se guarda en la cuenta del cliente al momento de aceptado el prestamo
					this.montoSolicitadoTotal += aux.getImporte()/1.5;
					this.montoTotalAPagar += aux.getImporte();
				}
			}
			else 
			{
				manejoNulos();
			}

		}
		return;
	}
	
	private void precargarDatos(List<Usuario> clientes, int IDClienteSeleccionado, int IDCuentaSeleccionada) {
		if ((clientes == null) || clientes.isEmpty()) 
		{
			
			clientes = new ArrayList<>();
			manejoNulos();
		}
		else
		{

			List<Cuenta> cAux = cuentaNegocio.listarCuentasActivasPorCliente(IDClienteSeleccionado);
			this.cuentas = cAux.isEmpty() || cAux == null ? new ArrayList<Cuenta>() : cAux;
			
			if(cuentaNegocio.cuentasActivasPorCliente(IDClienteSeleccionado) > 0)
			{

				List<Prestamo> pAux = prestamoNegocio.obtenerPorIdCliente(IDClienteSeleccionado);
				this.prestamosPorCliente = pAux == null || pAux.isEmpty() ? new ArrayList<Prestamo>() : pAux;

				List<Prestamo> pAux2 = prestamoNegocio.obtenerPorIdCuenta(IDCuentaSeleccionada);
				this.prestamosPorCuenta =  pAux2 == null || pAux2.isEmpty() ? new ArrayList<Prestamo>() : pAux2;

				//tienen que volver a ponerse en cero, dado que ahora se trata de variables miembro.
				this.montoSolicitadoTotal = 0;
				this.montoTotalAPagar = 0;
				
				for(Prestamo aux : this.prestamosPorCuenta) {
					// se debe dividir el importe porque el prestamo guarda el monto  ->a pagar<-,
					// el monto  ->solicitado<-  se guarda en la cuenta del cliente al momento de aceptado el prestamo
					this.montoSolicitadoTotal += aux.getImporte()/1.5;
					this.montoTotalAPagar += aux.getImporte();
				}
			}
			else 
			{
				manejoNulos();
			}
			
		}
		return;
	}
	
	private void manejoNulos() {
		this.cuentas = new ArrayList<Cuenta>();
		this.prestamosPorCliente = new ArrayList<Prestamo>();
		this.prestamosPorCuenta = new ArrayList<Prestamo>();

		this.montoSolicitadoTotal = 0;
		this.montoTotalAPagar = 0;
	}
}









