package servlets;

import entidad.Cuenta;
import negocio.NegocioCuenta;
import negocioImpl.NegocioCuentaImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CuentasClienteServlet")
public class CuentaClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioCuenta negocioCuenta = new NegocioCuentaImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object idObj = session.getAttribute("id");

        if (idObj != null) {
            int id = (int) idObj;

            List<Cuenta> cuentas = negocioCuenta.listarCliente(id);

            request.setAttribute("cuentasUsuario", cuentas);

            request.getRequestDispatcher("/cliente/cuentas.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/public/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}