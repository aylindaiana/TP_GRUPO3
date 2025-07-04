package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuota;
import entidad.Prestamo;
import entidad.PrestamoRechazado;
import negocio.NegocioCuota;
import negocio.NegocioPrestamo;
import negocio.NegocioPrestamoRechazado;
import negocioImpl.NegocioCuotaImpl;
import negocioImpl.NegocioPrestamoImpl;
import negocioImpl.NegocioPrestamoRechazadoImpl;

/**
 * Servlet implementation class VerPrestamoServlet
 */
@WebServlet("/VerPrestamoServlet")
public class VerPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NegocioCuota cNegocio = new NegocioCuotaImpl();
	private NegocioPrestamoRechazado prNegocio = new NegocioPrestamoRechazadoImpl();
	private NegocioPrestamo pNegocio = new NegocioPrestamoImpl();
	
	
    public VerPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("verCuotas") != null) {

			int IDPrestamo = Integer.parseInt(request.getParameter("verCuotas"));
			
			List<Cuota> cuotas = cNegocio.listarCuotasPorPrestamo(IDPrestamo);
			
			
		    request.setAttribute("listaCuotas", cuotas);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/verPrestamo.jsp");
		    dispatcher.forward(request, response);
		    
		}

		if(request.getParameter("verRechazo") != null) {

			int IDPrestamo = Integer.parseInt(request.getParameter("verRechazo"));
			
			PrestamoRechazado pRechazado = prNegocio.selectMotivoRechazoPrestamo(IDPrestamo);

			Prestamo prestamo = pNegocio.obtenerPorIDPrestamo(IDPrestamo);
			
		    request.setAttribute("motivoRechazo", pRechazado);
		    request.setAttribute("datosPrestamo", prestamo);
		    
		    
			
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/verPrestamo.jsp");
		    dispatcher.forward(request, response);
		}
		

		if(request.getParameter("IDPrestamo") != null) {

			int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamo"));
			
			List<Cuota> cuotas = cNegocio.listarCuotasPorPrestamo(IDPrestamo);
			
			
		    request.setAttribute("listaCuotas", cuotas);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/verPrestamo.jsp");
		    dispatcher.forward(request, response);
		    
		}
	}

}
