package servlets;

import entidad.Usuario;
import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/VerDatosAdminServlet")
public class VerDatosAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object idObj = session.getAttribute("id");

        if (idObj != null) {
            int id = (int) idObj;

            Usuario usuario = negocioUsuario.obtenerPorId(id);

            request.setAttribute("usuarioDetalle", usuario);

            request.getRequestDispatcher("/admin/verUsuarioAdmin.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/public/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}