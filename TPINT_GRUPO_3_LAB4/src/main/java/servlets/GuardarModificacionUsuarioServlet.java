package servlets;

import entidad.Usuario;
import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.LocalidadDaoImpl;

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

        boolean hayError = false;

        try {
            // Obtener parámetros
            int id = Integer.parseInt(request.getParameter("id"));
            String dniStr = request.getParameter("dni");
            String cuilStr = request.getParameter("cuil");
            String telefono = request.getParameter("telefono");

            // Validaciones numéricas
            if (dniStr == null || !dniStr.matches("\\d+")) {
                request.setAttribute("errorDni", "Sólo se permiten números en DNI");
                hayError = true;
            }
            if (cuilStr == null || !cuilStr.matches("\\d+")) {
                request.setAttribute("errorCuil", "Sólo se permiten números en CUIL");
                hayError = true;
            }
            if (telefono == null || !telefono.matches("\\d+")) {
                request.setAttribute("errorTelefono", "Sólo se permiten números en Teléfono");
                hayError = true;
            }

            // Guardar valores ingresados para repoblar en el JSP si hay errores
            request.setAttribute("dni", dniStr);
            request.setAttribute("cuil", cuilStr);
            request.setAttribute("telefono", telefono);
            request.setAttribute("nombre", request.getParameter("nombre"));
            request.setAttribute("apellido", request.getParameter("apellido"));
            request.setAttribute("sexo", request.getParameter("sexo"));
            request.setAttribute("nacionalidad", request.getParameter("nacionalidad"));
            request.setAttribute("fechaNacimiento", request.getParameter("fechaNacimiento"));
            request.setAttribute("direccion", request.getParameter("direccion"));
            request.setAttribute("localidad", request.getParameter("localidad"));
            request.setAttribute("provincia", request.getParameter("provincia"));
            request.setAttribute("correo", request.getParameter("correo"));

            // Obtener datos actuales
            Usuario usuarioActual = negocioUsuario.obtenerPorId(id);
            if (usuarioActual == null) {
                response.sendRedirect("ListarUsuariosServlet?status=error");
                return;
            }

            // Validación de DNI duplicado si fue modificado
            if (!hayError && usuarioActual.getDni() != Integer.parseInt(dniStr) && negocioUsuario.existeDni(Integer.parseInt(dniStr))) {
                request.setAttribute("errorDni", "El DNI ya está registrado.");
                hayError = true;
            }

            if (hayError) {
                // Cargar provincias y localidades
                ProvinciaDaoImpl provinciaDao = new ProvinciaDaoImpl();
                LocalidadDaoImpl localidadDao = new LocalidadDaoImpl();

                request.setAttribute("usuarioModificar", usuarioActual);
                request.setAttribute("provincias", provinciaDao.listarProvincias());
                request.setAttribute("localidades", localidadDao.listarTodas());

                request.getRequestDispatcher("/admin/modificarCliente.jsp").forward(request, response);
                return;
            }

            // Crear objeto actualizado
            Usuario usuarioModificado = new Usuario();
            usuarioModificado.setId(id);
            usuarioModificado.setDni(Integer.parseInt(dniStr));
            usuarioModificado.setCuil(Integer.parseInt(cuilStr));
            usuarioModificado.setNombre(request.getParameter("nombre"));
            usuarioModificado.setApellido(request.getParameter("apellido"));
            usuarioModificado.setSexo(request.getParameter("sexo"));
            usuarioModificado.setNacionalidad(request.getParameter("nacionalidad"));
            usuarioModificado.setFechaDeNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));
            usuarioModificado.setDireccion(request.getParameter("direccion"));
            usuarioModificado.setIdLocalidad(Integer.parseInt(request.getParameter("localidad")));
            usuarioModificado.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));
            usuarioModificado.setCorreoElectronico(request.getParameter("correo"));
            usuarioModificado.setTelefono(telefono);

            // Guardar
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
