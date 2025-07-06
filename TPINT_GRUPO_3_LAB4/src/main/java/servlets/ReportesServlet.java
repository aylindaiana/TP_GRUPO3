package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Reporte;
import negocio.NegocioCuota;
import negocio.NegocioMovimiento;
import negocio.NegocioPrestamo;
import negocio.NegocioPrestamoRechazado;
import negocio.NegocioUsuario;
import negocioImpl.NegocioCuotaImpl;
import negocioImpl.NegocioMovimientoImpl;
import negocioImpl.NegocioPrestamoImpl;
import negocioImpl.NegocioPrestamoRechazadoImpl;
import negocioImpl.NegocioUsuarioImpl;

@WebServlet("/ReportesServlet")
public class ReportesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String fechaInicio = request.getParameter("fechaInicio");
            String fechaFin = request.getParameter("fechaFin");

            if (fechaInicio == null || fechaFin == null || fechaInicio.isEmpty() || fechaFin.isEmpty()) {
                request.setAttribute("error", "Debe ingresar ambas fechas.");
                request.getRequestDispatcher("/admin/reportes.jsp").forward(request, response);
                return;
            }

            LocalDate desde = LocalDate.parse(fechaInicio);
            LocalDate hasta = LocalDate.parse(fechaFin);

            NegocioMovimiento movimientoNegocio = new NegocioMovimientoImpl();
            NegocioPrestamo prestamoNegocio = new NegocioPrestamoImpl();
            NegocioPrestamoRechazado rechazadoNegocio = new NegocioPrestamoRechazadoImpl();
            NegocioCuota cuotaNegocio = new NegocioCuotaImpl();
            NegocioUsuario usuarioNegocio = new NegocioUsuarioImpl();

           
            Reporte reporte = new Reporte(desde, hasta);

            reporte.setTotalMovimientosIngresos(movimientoNegocio.obtenerTotalxTipo(1, desde, hasta));
            reporte.setTotalMovimientosEgresos(movimientoNegocio.obtenerTotalxTipo(2, desde, hasta));

            reporte.setPrestamosAprobados(prestamoNegocio.contarPrestamosAprobados(desde, hasta));
            reporte.setPrestamosRechazados(rechazadoNegocio.contarRechazos(desde,hasta));

            reporte.setCuotasPagadas(cuotaNegocio.contarCuotasPagadas(desde, hasta));
            reporte.setTotalRecaudado(cuotaNegocio.totalRecaudadoEnCuotas(desde, hasta));

            reporte.setNuevosClientes(usuarioNegocio.contarNuevosClientes(desde, hasta));
 
            request.setAttribute("reporte", reporte);
            request.getRequestDispatcher("/admin/reportes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al generar el reporte: " + e.getMessage());
            request.getRequestDispatcher("/admin/reportes.jsp").forward(request, response);
        }
    }
}
