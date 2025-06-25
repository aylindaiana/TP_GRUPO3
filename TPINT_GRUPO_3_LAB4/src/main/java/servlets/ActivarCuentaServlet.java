package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.NegocioCuenta;
import negocioImpl.NegocioCuentaImpl;

@WebServlet("/ActivarCuentaServlet")
public class ActivarCuentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioCuenta negocioCuenta = new NegocioCuentaImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            boolean activar = negocioCuenta.activarCuenta(id);

            if (activar) {
                response.sendRedirect("CuentaAdminServlet?status=activado");
            } else {
                response.sendRedirect("CuentaAdminServlet?status=errorActivacion");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CuentaAdminServlet?status=errorActivacion");
        }
    }
}