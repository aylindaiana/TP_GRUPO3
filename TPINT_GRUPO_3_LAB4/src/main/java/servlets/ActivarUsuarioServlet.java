package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;

@WebServlet("/ActivarUsuarioServlet")
public class ActivarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            boolean activar = negocioUsuario.activarUsuario(id);

            if (activar) {
            	response.sendRedirect("DetalleUsuarioServlet?id=" + id + "&status=activado");
            } else {
            	response.sendRedirect("DetalleUsuarioServlet?id=" + id + "&status=errorActivacion");
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListarUsuariosServlet?status=errorActivacion");
        }
    }
}
