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
import negocio.NegocioCuota;
import negocioImpl.NegocioCuotaImpl;

/**
 * Servlet implementation class VerPrestamoServlet
 */
@WebServlet("/VerPrestamoServlet")
public class VerPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NegocioCuota cNegocio = new NegocioCuotaImpl();
	
	
    public VerPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("IDPrestamo") != null) {
			int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamo"));
			
			List<Cuota> cuotas = cNegocio.listarCuotasPorPrestamo(IDPrestamo);
			
			
		    request.setAttribute("listaCuotas", cuotas);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/verPrestamo.jsp");
		    dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
