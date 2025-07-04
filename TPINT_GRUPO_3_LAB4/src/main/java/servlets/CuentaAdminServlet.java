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

        List<Cuenta> cuentas = negocioCuenta.listar();
        
        boolean hayFiltroCliente = filtroCliente != null && !filtroCliente.trim().isEmpty();
        boolean hayFiltroCBU = filtroCBU != null && !filtroCBU.trim().isEmpty();
        
        if (hayFiltroCliente || hayFiltroCBU) {
            cuentas = negocioCuenta.buscar(filtroCliente, filtroCBU);
            request.setAttribute("busquedaAnterior", filtroCliente);
            request.setAttribute("cbuAnterior", filtroCBU);
        } else {
            cuentas = negocioCuenta.listar(); 
        }

        request.setAttribute("listaCuentas", cuentas);
        request.getRequestDispatcher("/admin/cuentasAdmin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}