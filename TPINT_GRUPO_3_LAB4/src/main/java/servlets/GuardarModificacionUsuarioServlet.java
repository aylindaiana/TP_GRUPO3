package servlets;

import entidad.Usuario;
import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/GuardarModificacionUsuarioServlet")
public class GuardarModificacionUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            // Obtener parámetros del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            int dni = Integer.parseInt(request.getParameter("dni"));
            int cuil = Integer.parseInt(request.getParameter("cuil"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String sexo = request.getParameter("sexo");
            String nacionalidad = request.getParameter("nacionalidad");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String direccion = request.getParameter("direccion");
            int idLocalidad = Integer.parseInt(request.getParameter("localidad"));
            int idProvincia = Integer.parseInt(request.getParameter("provincia"));
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");

            // Crear objeto Usuario actualizado
            Usuario usuarioModificado = new Usuario();
            usuarioModificado.setId(id);
            usuarioModificado.setDni(dni);
            usuarioModificado.setCuil(cuil);
            usuarioModificado.setNombre(nombre);
            usuarioModificado.setApellido(apellido);
            usuarioModificado.setSexo(sexo);
            usuarioModificado.setNacionalidad(nacionalidad);
            usuarioModificado.setFechaDeNacimiento(Date.valueOf(fechaNacimiento));
            usuarioModificado.setDireccion(direccion);
            usuarioModificado.setIdLocalidad(idLocalidad);
            usuarioModificado.setIdProvincia(idProvincia);
            usuarioModificado.setCorreoElectronico(correo);
            usuarioModificado.setTelefono(telefono);

            // Ejecutar modificación
            boolean modificadoOK = negocioUsuario.modificarUsuario(usuarioModificado);

            if (modificadoOK) {
                response.sendRedirect("ListarUsuariosServlet?status=modificado");
            } else {
                response.sendRedirect("ListarUsuariosServlet?status=error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListarUsuariosServlet?status=error");
        }
    }
}
