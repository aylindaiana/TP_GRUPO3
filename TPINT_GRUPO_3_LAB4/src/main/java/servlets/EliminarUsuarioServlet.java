package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;

@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            boolean eliminado = negocioUsuario.eliminarUsuario(id);

            if (eliminado) {
                response.sendRedirect("ListarUsuariosServlet?status=eliminado");
            } else {
                response.sendRedirect("ListarUsuariosServlet?status=errorEliminacion");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListarUsuariosServlet?status=errorEliminacion");
        }
    }
}

