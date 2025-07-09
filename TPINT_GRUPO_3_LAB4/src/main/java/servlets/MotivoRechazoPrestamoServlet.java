package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioPrestamo;
import negocioImpl.NegocioPrestamoImpl;

/**
 * Servlet implementation class MotivoRechazoPrestamoServlet
 */
@WebServlet("/MotivoRechazoPrestamoServlet")
public class MotivoRechazoPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NegocioPrestamo prestamoNegocio = new NegocioPrestamoImpl();
       
    public MotivoRechazoPrestamoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btn-confirmar") != null){
			
			/*
				 explicacion de esta precarga de datos.
				 dado que el SP que los manipula actua en base a el estado del prestamo 
				 pasado por parametro.
				 no se necesita que todos los parametros tengan un valor real,
				 porque como podemos apreciar estamos dentro del metodos doPost de rechazar
				 
				 por ende los unicos datos necesarios para este SP, son IDPrestamo, Estado y MotivoRechazo.
			 */
			int IDPrestamo = Integer.parseInt(request.getParameter("IDPrestamo"));
			int IDCuentaDestino = 0;
			int estadoPrestamo = 3;
			int cantidadCuotas = 0;
			double importeSolicitado = 0;
			int IDCuentaOrigen = 0;
            LocalDate fechaActual = LocalDate.now();
            Date fechaprestamo = Date.valueOf(fechaActual);
            String comentarioPrestamo = "";
            String motivoRechazo = request.getParameter("motivoRechazo");
			
			
			boolean actualizado = prestamoNegocio.actualizacionDeTablasEnBase_EstadoPrestamo
				(
				IDPrestamo, IDCuentaDestino, estadoPrestamo, cantidadCuotas, 
				importeSolicitado, IDCuentaOrigen, fechaprestamo,
				comentarioPrestamo, motivoRechazo
				);
			
			
			request.getSession().setAttribute("actualizado", actualizado);
			
			response.sendRedirect("PrestamosAdminServlet");
		}
		
		if(request.getParameter("btn-regresar") != null){
			
			response.sendRedirect("PrestamosAdminServlet");
		}
	}

}
