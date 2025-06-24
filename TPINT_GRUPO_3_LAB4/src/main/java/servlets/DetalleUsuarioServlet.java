package servlets;

import entidad.Usuario;
import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DetalleUsuarioServlet")
public class DetalleUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Usuario usuario = negocioUsuario.obtenerPorId(id); // Usamos la función que ya tenés creada

                if (usuario != null) {
                    request.setAttribute("usuarioDetalle", usuario);
                    request.getRequestDispatcher("/admin/detalleCliente.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Si no hay ID válido o usuario no encontrado, redireccionamos
        response.sendRedirect("admin/clientes.jsp?status=errorDetalle");
    }
}
