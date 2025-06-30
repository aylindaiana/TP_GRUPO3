package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.NegocioCuenta;
import negocio.NegocioCuota;
import negocio.NegocioPrestamo;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioCuotaImpl;
import negocioImpl.NegocioPrestamoImpl;

/**
 * Servlet implementation class PagarCuotaServlet
 */
@WebServlet("/PagarCuotaServlet")
public class PagarCuotaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NegocioCuota cuotaNegocio = new NegocioCuotaImpl();
    private NegocioCuenta cuentaNegocio = new NegocioCuentaImpl();
    private NegocioPrestamo pNegocio = new NegocioPrestamoImpl();
	private CuentaDao daoCuentas = new CuentaDaoImpl(); 
    
    public PagarCuotaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = precargarIDCliente(request.getSession());
		
		List<Cuenta> cuentas = cuentaNegocio.listarCliente(Integer.parseInt(id));

		int IDCuota = Integer.parseInt(request.getParameter("IDCuota"));
		double montoCuota = Double.parseDouble(request.getParameter("montoCuota"));
		
		
		
		request.setAttribute("IDCuota", IDCuota);
	    request.setAttribute("montoCuota", montoCuota);
	    request.setAttribute("listaCuentas", cuentas);
	    

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/pagarCuota.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//agregar funcionalidad para que se realice el debido updte a modo de pagar las cuotas.

		int IDCuenta = Integer.parseInt(request.getParameter("cuentaSeleccionada"));

		int IDCuota = Integer.parseInt(request.getParameter("IDCuota"));
		double montoCuota = Double.parseDouble(request.getParameter("montoCuota"));
		
		cuotaNegocio.pagarCuota(IDCuota);
		cuentaNegocio.debitarCuenta(IDCuenta, montoCuota);
				
		String id = precargarIDCliente(request.getSession());
        
	    List<Cuenta> cuentas = daoCuentas.listarCuentas(Integer.parseInt(id));
	    List<Prestamo> prestamos = pNegocio.obtenerPorIdCliente(Integer.parseInt(id));
	    
	    request.setAttribute("listaCuentas", cuentas);
	    request.setAttribute("listaPrestamos", prestamos);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/prestamos.jsp");
	    dispatcher.forward(request, response);
			
	}


	private String precargarIDCliente(HttpSession session) {
        int idCliente = (int) session.getAttribute("id");
        return String.valueOf(idCliente);
	}
}
