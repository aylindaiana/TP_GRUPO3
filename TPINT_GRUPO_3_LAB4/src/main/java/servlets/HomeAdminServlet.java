package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioCredencialesDao;
import dao.UsuarioDao;
import dao.usuarioTipoDao;
import daoImpl.UsuarioCredencialesImpl;
import daoImpl.UsuarioDaoImpl;
import daoImpl.usuarioTipoDaoImpl;
import entidad.Usuario;

@WebServlet("/HomeAdminServlet")
public class HomeAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object idTipoObj = session.getAttribute("idTipoUsuario");

        if (idTipoObj != null) {
        	int idTipo = (int) idTipoObj;
        	if(idTipo == 1)
        	{
        		negocio.NegocioUsuario usuarioNegocio = new negocioImpl.NegocioUsuarioImpl();
    		    negocio.NegocioCuenta cuentaNegocio = new negocioImpl.NegocioCuentaImpl();
    		    negocio.NegocioPrestamo prestamoNegocio = new negocioImpl.NegocioPrestamoImpl();
    		    negocio.NegocioTransferencia transferenciaNegocio = new negocioImpl.NegocioTransferenciaImpl();

    		    int totalUsuariosActivos = usuarioNegocio.contarUsuarios();
    		    int totalCuentasActivas = cuentaNegocio.contarCuentasTotalesImpl();
    		    double saldoTotalActualDelBanco = cuentaNegocio.saldoTotalBancario();
    		    int cantidadPrestamosPorAutorizar = prestamoNegocio.cantidadPrestamosPendientes();
    		    double dineroEnTransfe = transferenciaNegocio.saldoTotalDeTransfe();
//    		    java.time.LocalDate fechaActual = java.time.LocalDate.now();
//    		    java.time.LocalDate desde = fechaActual.withDayOfMonth(1);
    		    request.setAttribute("cantUser", totalUsuariosActivos);
    		    request.setAttribute("cantCuentas", totalCuentasActivas);
    		    request.setAttribute("cantPlataBanco", saldoTotalActualDelBanco);
    		    request.setAttribute("cantPrestamos", cantidadPrestamosPorAutorizar);
    		    request.setAttribute("dineroEnTransfe", dineroEnTransfe);
        		request.getRequestDispatcher("/admin/homeAdmin.jsp").forward(request, response);
        	}
        }   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}