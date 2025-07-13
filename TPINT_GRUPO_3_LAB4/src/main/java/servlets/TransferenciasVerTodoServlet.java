package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Transferencia;
import negocio.NegocioTransferencia;
import negocioImpl.NegocioTransferenciaImpl;

@WebServlet("/TransferenciasVerTodoServlet")
public class TransferenciasVerTodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCliente = (int) request.getSession().getAttribute("idCliente");
        CuentaDao cuentaDao = new CuentaDaoImpl();
        List<Cuenta> cuentasPropias = cuentaDao.listarCuentas(idCliente);

        int cuenta1 = cuentasPropias.size() >= 1 ? cuentasPropias.get(0).getId() : 0;
        int cuenta2 = cuentasPropias.size() >= 2 ? cuentasPropias.get(1).getId() : cuenta1;
        int cuenta3 = cuentasPropias.size() >= 3 ? cuentasPropias.get(2).getId() : cuenta2;

        String fechaDesdeStr = request.getParameter("fechaDesde");
        String fechaHastaStr = request.getParameter("fechaHasta");
        String montoMinStr = request.getParameter("montoMin");
        String montoMaxStr = request.getParameter("montoMax");
        String cuentaElegidaStr = request.getParameter("cuenta");

        Timestamp fechaDesde;
        Timestamp fechaHasta;

        if (fechaDesdeStr == null || fechaDesdeStr.isEmpty() || fechaDesdeStr.equals("null")) {
            fechaDesde = Timestamp.valueOf("2000-01-01 00:00:00");
        } else {
            fechaDesde = Timestamp.valueOf(fechaDesdeStr + " 00:00:00");
        }

        if (fechaHastaStr == null || fechaHastaStr.isEmpty() || fechaHastaStr.equals("null")) {
            fechaHasta = new Timestamp(System.currentTimeMillis());
        } else {
            fechaHasta = Timestamp.valueOf(fechaHastaStr + " 23:59:59");
        }

        double montoMin = (montoMinStr == null || montoMinStr.isEmpty() || montoMinStr.equals("null")) ? 0 : Double.parseDouble(montoMinStr);
        double montoMax = (montoMaxStr == null || montoMaxStr.isEmpty() || montoMaxStr.equals("null")) ? 99999999 : Double.parseDouble(montoMaxStr);

        int cuentaElegida = (cuentaElegidaStr != null && !cuentaElegidaStr.isEmpty() && !cuentaElegidaStr.equals("null"))
                ? Integer.parseInt(cuentaElegidaStr) : 0;

        String paginaStr = request.getParameter("pagina");
        int pagina = (paginaStr != null && !paginaStr.isEmpty() && !"null".equals(paginaStr)) ? Integer.parseInt(paginaStr) : 1;
        int limite = 10;
        int offset = (pagina - 1) * limite;

        NegocioTransferencia negocioTransf = new NegocioTransferenciaImpl();

        List<Transferencia> transferencias = negocioTransf.listarTransferencias(
                cuentaElegida != 0 ? cuentaElegida : cuenta1,
                cuentaElegida != 0 ? cuentaElegida : cuenta2,
                cuentaElegida != 0 ? cuentaElegida : cuenta3,
                fechaDesde, fechaHasta, montoMin, montoMax, offset, limite
        );

        request.setAttribute("transferencias", transferencias);
        request.setAttribute("cuentasPropias", cuentasPropias);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("haySiguiente", transferencias.size() == limite);

        request.getRequestDispatcher("/cliente/verTodoTransferencias.jsp").forward(request, response);
    }
}
