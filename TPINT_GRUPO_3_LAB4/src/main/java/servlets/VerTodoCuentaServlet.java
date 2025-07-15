package servlets;

import entidad.Movimiento;
import negocio.NegocioMovimiento;
import negocioImpl.NegocioMovimientoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/VerTodoCuentaServlet")
public class VerTodoCuentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioMovimiento negocioMovimiento = new NegocioMovimientoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));

        String tipo = request.getParameter("tipo");
        String texto = request.getParameter("texto");
        String minMontoStr = request.getParameter("minMonto");
        String maxMontoStr = request.getParameter("maxMonto");
        String fechaDesdeStr = request.getParameter("fechaDesde");
        String fechaHastaStr = request.getParameter("fechaHasta");

        double minMonto = (minMontoStr != null && !minMontoStr.isEmpty()) ? Double.parseDouble(minMontoStr) : 0;
        double maxMonto = (maxMontoStr != null && !maxMontoStr.isEmpty()) ? Double.parseDouble(maxMontoStr) : 999999999;

        Date fechaDesde = (fechaDesdeStr != null && !fechaDesdeStr.isEmpty()) ? Date.valueOf(fechaDesdeStr) : Date.valueOf("2000-01-01");
        Date fechaHasta = (fechaHastaStr != null && !fechaHastaStr.isEmpty()) ? Date.valueOf(fechaHastaStr) : new Date(System.currentTimeMillis());

        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int offset = (page - 1) * recordsPerPage;

        List<Movimiento> movimientos = negocioMovimiento.listarMovimientosConFiltros(
                idCuenta,
                tipo,
                minMonto,
                maxMonto,
                fechaDesde,
                fechaHasta,
                texto,
                offset,
                recordsPerPage
        );

        request.setAttribute("movimientos", movimientos);
        request.setAttribute("idCuenta", idCuenta);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/cliente/verCuenta.jsp").forward(request, response);
    }
}
