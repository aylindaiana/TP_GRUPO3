package servlets;

import entidad.Usuario;
import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ModificarUsuarioServlet")
public class ModificarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            Usuario usuario = negocioUsuario.obtenerPorId(id);

            if (usuario != null) {
                request.setAttribute("usuarioModificar", usuario);
                request.getRequestDispatcher("/admin/modificarCliente.jsp").forward(request, response);
            } else {
                response.sendRedirect("ListarUsuariosServlet");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListarUsuariosServlet");
        }
    }
}

