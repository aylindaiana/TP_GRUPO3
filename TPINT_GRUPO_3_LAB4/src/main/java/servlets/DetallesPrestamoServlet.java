package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Prestamo;
import negocio.NegocioPrestamo;
import negocioImpl.NegocioPrestamoImpl;

@WebServlet("/DetallesPrestamoServlet")
public class DetallesPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private NegocioPrestamo prestamoNegocio = new NegocioPrestamoImpl();   
	
    public DetallesPrestamoServlet() { 
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/prestamosAdmin.jsp");
	    dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btn-aceptarPrestamo") != null) {

			double interes = 1.5;
			int IDPrestamo = Integer.parseInt(request.getParameter("btn-aceptarPrestamo"));
			Prestamo aux = prestamoNegocio.obtenerPorIDPrestamo(IDPrestamo);
			
			int IDCuentaDestino = aux.getIDCuenta();
			int estadoPrestamo = 1;
			int cantidadCuotas = aux.getCantidadCuotas();
			double importeSolicitado = aux.getImporte();
			int IDCuentaOrigen = 111111111; // puede ser utilizado como la cuenta del banco?
            Date fechaprestamo = aux.getFechaDeAlta();
            String comentarioPrestamo = "bien bien";
            String motivoRechazo = "";
			
			boolean actualizado = prestamoNegocio.actualizacionDeTablasEnBase_EstadoPrestamo
				(
				IDPrestamo, IDCuentaDestino, estadoPrestamo, cantidadCuotas, 
				importeSolicitado, IDCuentaOrigen, fechaprestamo,
				comentarioPrestamo, motivoRechazo
				);

			request.getSession().setAttribute("actualizado", actualizado);
			
			response.sendRedirect("PrestamosAdminServlet");
		}

		if(request.getParameter("btn-rechazarPrestamo") != null) {
			
			int IDPrestamo = Integer.parseInt(request.getParameter("btn-rechazarPrestamo"));
			Prestamo prestamo = prestamoNegocio.obtenerPorIDPrestamo(IDPrestamo);
			
			
			request.setAttribute("prestamo", prestamo);
			
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/motivoRechazoPrestamo.jsp");
		    dispatcher.forward(request, response);
		    
		}
		
	}

}
