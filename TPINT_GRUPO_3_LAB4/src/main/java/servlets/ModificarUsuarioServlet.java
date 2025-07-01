package servlets;

import entidad.Provincia;
import entidad.Localidad;
import entidad.Usuario;
import negocio.NegocioUsuario;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.LocalidadDaoImpl;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

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
                // Cargar provincias y localidades
                ProvinciaDaoImpl provinciaDao = new ProvinciaDaoImpl();
                LocalidadDaoImpl localidadDao = new LocalidadDaoImpl();

                List<Provincia> provincias = provinciaDao.listarProvincias();
                List<Localidad> localidades = localidadDao.listarTodas();

                request.setAttribute("usuarioModificar", usuario);
                request.setAttribute("provincias", provincias);
                request.setAttribute("localidades", localidades);

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
