package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidad.Cuenta;
import negocio.NegocioCuenta;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioMovimientoImpl;

@WebServlet("/InactivarCuentaServlet")
public class InactivarCuentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioCuenta negocioCuenta = new NegocioCuentaImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            boolean eliminado = negocioCuenta.inactivarCuenta(id);

            if (eliminado) {
                response.sendRedirect("CuentaAdminServlet?status=eliminado");
            } else {
            	request.setAttribute("error", "No se puede inactivar la cuenta porque tiene préstamos activos.");
            	Cuenta cuenta = negocioCuenta.obtenerPorId(id);
            	request.setAttribute("cuenta", cuenta);
            	request.setAttribute("modo", "editar");

            	NegocioMovimientoImpl movimiento = new NegocioMovimientoImpl();
            	request.setAttribute("movimientos", movimiento.listarMovimientosPorCuenta(id));

            	request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);

            }
            

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CuentaAdminServlet?status=errorEliminacion");
        }
    }
}