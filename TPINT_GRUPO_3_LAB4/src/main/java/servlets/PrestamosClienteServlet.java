package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CuentaDao;
import dao.PrestamoDao;
import daoImpl.CuentaDaoImpl;
import daoImpl.PrestamoDaoImpl;
import entidad.Cuenta;

/**
 * Servlet implementation class PrestamosClienteServlet
 */
@WebServlet("/PrestamosClienteServlet")
public class PrestamosClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrestamosClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CuentaDao daoCuentas = new CuentaDaoImpl(); 
	    List<Cuenta> cuentas = daoCuentas.listarCuentas();

	    request.setAttribute("listaCuentas", cuentas);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/prestamos.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btn-solicitarPrestamo") != null && request.getParameter("btnradio") != null && request.getParameter("txtMontoSolicitado") != null && !request.getParameter("txtMontoSolicitado").isEmpty()) {
						
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/cliente/prestamos.jsp");
		    rd.forward(request, response);
		}
	}

}
