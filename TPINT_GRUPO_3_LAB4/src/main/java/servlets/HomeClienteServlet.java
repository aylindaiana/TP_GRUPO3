package servlets;

import entidad.Cuenta;
import entidad.Usuario;
import negocio.NegocioCuenta;
import negocio.NegocioUsuario;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/HomeClienteServlet")
public class HomeClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();
    private NegocioCuenta negocioCuenta = new NegocioCuentaImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object idObj = session.getAttribute("id");

        if (idObj != null) {
            int id = (int) idObj;

            Usuario usuario = negocioUsuario.obtenerPorId(id);
            List<Cuenta> cuentas = negocioCuenta.listarCliente(id);

            request.setAttribute("usuarioDetalle", usuario);
            request.setAttribute("cuentasUsuario", cuentas);

            request.getRequestDispatcher("/cliente/homeCliente.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/public/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
