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
import java.time.LocalDate;
import java.time.Period;

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
            String fechaNacimientoStr = request.getParameter("fechaNacimiento");

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

            // Validar edad mínima
            if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
                try {
                    LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                    LocalDate hoy = LocalDate.now();
                    int edad = Period.between(fechaNacimiento, hoy).getYears();

                    if (edad < 18) {
                        request.setAttribute("errorEdad", "Se debe tener al menos 18 años.");
                        hayError = true;
                    }
                } catch (Exception e) {
                    request.setAttribute("errorEdad", "Fecha de nacimiento inválida.");
                    hayError = true;
                }
            } else {
                request.setAttribute("errorEdad", "La fecha de nacimiento es obligatoria.");
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
            request.setAttribute("fechaNacimiento", fechaNacimientoStr);
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

            // Validación de CUIL duplicado si fue modificado
            long cuilNuevo = Long.parseLong(cuilStr);
            if (!hayError && usuarioActual.getCuil() != cuilNuevo && negocioUsuario.existeCuil(cuilNuevo)) {
                request.setAttribute("errorCuil", "El CUIL ya está registrado.");
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
            usuarioModificado.setCuil(Long.parseLong(cuilStr));
            usuarioModificado.setNombre(request.getParameter("nombre"));
            usuarioModificado.setApellido(request.getParameter("apellido"));
            usuarioModificado.setSexo(request.getParameter("sexo"));
            usuarioModificado.setNacionalidad(request.getParameter("nacionalidad"));
            usuarioModificado.setFechaDeNacimiento(Date.valueOf(fechaNacimientoStr));
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
