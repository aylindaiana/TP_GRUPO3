package servlets;

import java.io.IOException;
import java.util.List;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Movimiento;
import negocio.NegocioMovimiento;
import negocioImpl.NegocioMovimientoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/TransferenciasHomeServlet")
public class TransferenciasHomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TransferenciasHomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCliente = (int) request.getSession().getAttribute("idCliente");

        // Traer cuentas del cliente
        CuentaDao cuentaDao = new CuentaDaoImpl();
        List<Cuenta> cuentasPropias = cuentaDao.listarCuentas(idCliente);
        request.setAttribute("cuentasPropias", cuentasPropias);

        // Traer últimos movimientos del cliente (limite: 5)
        NegocioMovimiento negocioMov = new NegocioMovimientoImpl();
        List<Movimiento> ultimosMovimientos = negocioMov.listarUltimosMovimientos(idCliente, 5);
        request.setAttribute("ultimosMovimientos", ultimosMovimientos);

        // Redirigir a la página principal de Transferencias
        request.getRequestDispatcher("/cliente/transferencias.jsp").forward(request, response);
    }

}
