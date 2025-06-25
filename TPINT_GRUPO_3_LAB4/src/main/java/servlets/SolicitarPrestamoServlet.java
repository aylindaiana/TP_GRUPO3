package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.NegocioPrestamo;
import negocioImpl.NegocioPrestamoImpl;
import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Prestamo;

/**
 * Servlet implementation class SolicitarPrestamoServlet
 */
@WebServlet("/SolicitarPrestamoServlet")
public class SolicitarPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SolicitarPrestamoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btn-confirmar") != null) { 
			CuentaDao daoCuentas = new CuentaDaoImpl(); 
			NegocioPrestamo negocio = new NegocioPrestamoImpl();
			HttpSession session = request.getSession();
			
			double montoTotal = Double.parseDouble(request.getParameter("total"));
            double montoSolicitado = Double.parseDouble(request.getParameter("solicitado"));
            int cuotas = Integer.parseInt(request.getParameter("cuotas"));
            int IDCuenta = Integer.parseInt(request.getParameter("cuenta"));
            
            LocalDate fechaActual = LocalDate.now();
            Date fecha = Date.valueOf(fechaActual);
            
            Prestamo prestamo = new Prestamo();
            int idCliente = (int) session.getAttribute("id");
            String id = String.valueOf(idCliente);
            
            prestamo.setIDCliente(id);
            prestamo.setIDCuenta(IDCuenta);
            prestamo.setFechaDeAlta(fecha);
            prestamo.setImporte(montoTotal);
            prestamo.setImporteMensual(montoTotal/cuotas);
            //prestamo.setPlazoPago(fechaActual.plusMonths(cuotas));
            // esto genera que al dia de solicitado el prestamo se le sumen la cantidad de cuotas
            // generaria el dia de finalizacion del prestamo, pero el plazo es int.
            prestamo.setPlazoPago(cuotas);
            prestamo.setCantidadCuotas(cuotas);
            //el estado de autorizacion del prestamo es 2 el cual hace referencia a pendiente.
            // cuando se realice el apartado del admin, 
            // el sera el encargado de aceptar o denegar el mismo.
            prestamo.setAutorizacion(2);
            
            
            negocio.solicitarPrestamo(prestamo);
            daoCuentas.recargarCuenta(IDCuenta, montoSolicitado);
            
            
            //se tiene que volver a cargar las cuentas
    	    List<Cuenta> cuentas = daoCuentas.listarCuentas();

    	    request.setAttribute("listaCuentas", cuentas);

			RequestDispatcher rd = request.getRequestDispatcher("/cliente/prestamos.jsp");
		    rd.forward(request, response);
		}
		else {
			
			RequestDispatcher rd = request.getRequestDispatcher("/cliente/prestamos.jsp");
		    rd.forward(request, response);
		}
	}

}
