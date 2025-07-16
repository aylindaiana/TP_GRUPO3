package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Prestamo;
import negocio.NegocioCuenta;
import negocio.NegocioCuota;
import negocio.NegocioMovimiento;
import negocio.NegocioPrestamo;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioCuotaImpl;
import negocioImpl.NegocioMovimientoImpl;
import negocioImpl.NegocioPrestamoImpl;

/**
 * Servlet implementation class PagarCuotaServlet
 */
@WebServlet("/PagarCuotaServlet")
public class PagarCuotaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NegocioCuota cuotaNegocio = new NegocioCuotaImpl();
    private NegocioCuenta cuentaNegocio = new NegocioCuentaImpl();
    private NegocioPrestamo prestamoNegocio = new NegocioPrestamoImpl();
    private NegocioMovimiento movimientoNegocio = new NegocioMovimientoImpl();
    
    public PagarCuotaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = precargarIDCliente(request.getSession());
		
		List<Cuenta> cuentas = cuentaNegocio.listarCuentasActivasPorCliente(Integer.parseInt(id));

		int IDCuota = Integer.parseInt(request.getParameter("IDCuota"));
		double montoCuota = Double.parseDouble(request.getParameter("montoCuota"));
		
		int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamo"));
		
		
		request.setAttribute("IDCuota", IDCuota);
	    request.setAttribute("montoCuota", montoCuota);
	    request.setAttribute("listaCuentas", cuentas);
		request.setAttribute("IDPrestamo", IDPrestamo);
	    

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/pagarCuota.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("confirmar") != null)
		{

			int IDCuenta = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
			int IDCuota = Integer.parseInt(request.getParameter("IDCuota"));
			double montoCuota = Double.parseDouble(request.getParameter("montoCuota"));
			int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamo"));
			
			String id = precargarIDCliente(request.getSession());
	        
		    List<Cuenta> cuentas = cuentaNegocio.listarCuentasActivasPorCliente(Integer.parseInt(id));
		    List<Prestamo> prestamos = prestamoNegocio.obtenerPorIdCliente(Integer.parseInt(id));
			
		    boolean estadoPagoCuota;
			 
			if(cuentaNegocio.obtenerSaldoCuenta(IDCuenta) >= montoCuota) {
				
				estadoPagoCuota = cuotaNegocio.pagarCuota(IDCuota);
				cuentaNegocio.debitarCuenta(IDCuenta, montoCuota);
				
				Movimiento movimiento = new Movimiento();
	            LocalDate fechaActual = LocalDate.now();
	            Date fecha = Date.valueOf(fechaActual);
				
				
				movimiento.setIDCuentaOrigen(IDCuenta);
				movimiento.setIDCuentaDestino(1111111111);
				movimiento.setMonto(montoCuota);
				movimiento.setFecha(fecha);
				movimiento.setComentario("pago cuota prestamo (id " + IDPrestamo + ")");
				movimiento.setIDTipoDeMovimiento(3);
				
				movimientoNegocio.nuevoMovimiento(movimiento);

			    request.setAttribute("listaCuentas", cuentas);
			    request.setAttribute("listaPrestamos", prestamos);
			    request.setAttribute("estadoPagoCuota", estadoPagoCuota);
			    
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/prestamos.jsp");
			    dispatcher.forward(request, response);
			    
			}
			else 
			{
				estadoPagoCuota = false;
				
				request.setAttribute("IDCuota", IDCuota);
			    request.setAttribute("montoCuota", montoCuota);
			    request.setAttribute("listaCuentas", cuentas);
				request.setAttribute("IDPrestamo", IDPrestamo);
			    request.setAttribute("estadoPagoCuota", estadoPagoCuota);

			    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/pagarCuota.jsp");
			    dispatcher.forward(request, response);
			}
			
		    
		}
			
		if(request.getParameter("cancelar") != null)
		{
			int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamo"));
			
			request.setAttribute("IDPrestamo", IDPrestamo);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("/VerPrestamoServlet");
		    dispatcher.forward(request, response);
		}
		
		
	}


	private String precargarIDCliente(HttpSession session) {
        int idCliente = (int) session.getAttribute("id");
        return String.valueOf(idCliente);
	}
}
