package servlets;
import java.util.List;
import java.io.IOException;

import entidad.Cuenta;
import negocio.NegocioCuenta; 
import negocioImpl.NegocioCuentaImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CuentaAdminServlet")
public class CuentaAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CuentaAdminServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NegocioCuenta negocioCuenta = new NegocioCuentaImpl();

        String filtroCliente = request.getParameter("filtroCliente");
        String filtroCBU = request.getParameter("filtroCBU");
        
        boolean hayFiltroCliente = filtroCliente != null && !filtroCliente.trim().isEmpty();
        boolean hayFiltroCBU = filtroCBU != null && !filtroCBU.trim().isEmpty();
        
        String status = request.getParameter("status");
        if ("errorMaxCuentas".equalsIgnoreCase(status)) {
            request.setAttribute("error", "No se pudo activar la cuenta: el cliente ya tiene 3 cuentas activas.");
        }
        else if("activado".equalsIgnoreCase(status)) {
        	request.setAttribute("activacion", "Se activo correctamente la cuenta y se vera reflejado automaticamente.");
        }
        else if("eliminado".equalsIgnoreCase(status)) {
        	request.setAttribute("inactivo", "Se dio de baja correctamente la cuenta y se vera reflejado automaticamente.");
        }

        List<Cuenta> cuentas;
        if (hayFiltroCliente || hayFiltroCBU) {
            cuentas = negocioCuenta.buscar(filtroCliente, filtroCBU);
            request.setAttribute("busquedaAnterior", filtroCliente);
            request.setAttribute("cbuAnterior", filtroCBU);
        } else {
            cuentas = negocioCuenta.listar();
        }

        // PAGINACIÓN
        int cuentasPorPagina = 10;
        int paginaActual = 1;

        if (request.getParameter("page") != null) {
            try {
                paginaActual = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                paginaActual = 1;
            }
        }

        int totalCuentas = cuentas.size();
        int totalPaginas = (int) Math.ceil((double) totalCuentas / cuentasPorPagina);

        int inicio = (paginaActual - 1) * cuentasPorPagina;
        int fin = Math.min(inicio + cuentasPorPagina, totalCuentas);
        List<Cuenta> cuentasPaginadas = cuentas.subList(inicio, fin);

        // Envío a JSP
        request.setAttribute("listaCuentas", cuentasPaginadas);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("filtroCliente", filtroCliente);
        request.setAttribute("filtroCBU", filtroCBU);

        request.getRequestDispatcher("/admin/cuentasAdmin.jsp").forward(request, response);
    }

}