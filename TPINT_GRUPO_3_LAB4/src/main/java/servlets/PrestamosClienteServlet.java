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
import dao.PrestamoDao;
import daoImpl.CuentaDaoImpl;
import daoImpl.PrestamoDaoImpl;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.NegocioCuenta;
import negocio.NegocioPrestamo;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioPrestamoImpl;

/**
 * Servlet implementation class PrestamosClienteServlet
 */
@WebServlet("/PrestamosClienteServlet")
public class PrestamosClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    NegocioPrestamo pNegocio = new NegocioPrestamoImpl();
	NegocioCuenta cuentaNegocio = new NegocioCuentaImpl(); 
    
    public PrestamosClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String id = precargarIDCliente(request.getSession());
        
	    List<Cuenta> cuentas = cuentaNegocio.listarCuentasActivasPorCliente(Integer.parseInt(id));
	    List<Prestamo> prestamos = pNegocio.obtenerPorIdCliente(Integer.parseInt(id));
	    
	    request.setAttribute("listaCuentas", cuentas);
	    request.setAttribute("listaPrestamos", prestamos);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/prestamos.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btn-solicitarPrestamo") != null && request.getParameter("btnradio") != null && request.getParameter("txtMontoSolicitado") != null && !request.getParameter("txtMontoSolicitado").isEmpty()) {
						
			if(Double.parseDouble(request.getParameter("txtMontoSolicitado")) <= 0) {
				boolean bandera = false;
				
	            request.setAttribute("valorErroneo", bandera);

	    		String id = precargarIDCliente(request.getSession());
	    		
	            NegocioPrestamo cNegocio = new NegocioPrestamoImpl();
	            
	    	    List<Cuenta> cuentas = cuentaNegocio.listarCuentasActivasPorCliente(Integer.parseInt(id));
	    	    List<Prestamo> prestamos = cNegocio.obtenerPorIdCliente(Integer.parseInt(id));
	    	    
	    	    request.setAttribute("listaCuentas", cuentas);
	    	    request.setAttribute("listaPrestamos", prestamos);
	    	    
				RequestDispatcher rd = request.getRequestDispatcher("/cliente/prestamos.jsp");
			    rd.forward(request, response);
			}
			
			double montoTotal = 0;
            double montoSolicitado = Double.parseDouble(request.getParameter("txtMontoSolicitado"));
            int cuotas = Integer.parseInt(request.getParameter("btnradio"));
            int IDCuenta = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
            
            montoTotal = montoSolicitado * 1.5;

            request.setAttribute("montoSolicitado", montoSolicitado);
            request.setAttribute("montoTotal", montoTotal);
            request.setAttribute("cuotas", cuotas);
            request.setAttribute("cuentaSeleccionada", IDCuenta);
            

			RequestDispatcher rd = request.getRequestDispatcher("/cliente/solicitarPrestamo.jsp");
		    rd.forward(request, response);
		    
		    
		}
		else {
			boolean bandera = false;

            request.setAttribute("incompleto", bandera);
			

    		HttpSession session = request.getSession();
            int idCliente = (int) session.getAttribute("id");
            String id = String.valueOf(idCliente);
            NegocioPrestamo cNegocio = new NegocioPrestamoImpl();
            
    	    List<Cuenta> cuentas = cuentaNegocio.listarCuentasActivasPorCliente(Integer.parseInt(id));
    	    List<Prestamo> prestamos = cNegocio.obtenerPorIdCliente(idCliente);
    	    
    	    request.setAttribute("listaCuentas", cuentas);
    	    request.setAttribute("listaPrestamos", prestamos);
            
			RequestDispatcher rd = request.getRequestDispatcher("/cliente/prestamos.jsp");
		    rd.forward(request, response);
		}
	}

	private String precargarIDCliente(HttpSession session) {
        int idCliente = (int) session.getAttribute("id");
        return String.valueOf(idCliente);
	}
}
