package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import entidad.Usuario;
import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/procesarAltaCliente")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UsuarioServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Paso 1: Obtener parámetros
        String dni = request.getParameter("dni");
        String cuil = request.getParameter("cuil");
        String telefono = request.getParameter("telefono");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");

        boolean hayError = false;

        // Validar numéricos
        if (dni == null || !dni.matches("\\d+")) {
            request.setAttribute("errorDni", "Sólo se permiten números en DNI");
            hayError = true;
        }
        if (cuil == null || !cuil.matches("\\d+")) {
            request.setAttribute("errorCuil", "Sólo se permiten números en CUIL");
            hayError = true;
        }
        if (telefono == null || !telefono.matches("\\d+")) {
            request.setAttribute("errorTelefono", "Sólo se permiten números en Teléfono");
            hayError = true;
        }
        if (password == null || !password.equals(confirmPassword)) {
            request.setAttribute("errorPassword", "Las contraseñas no coinciden.");
            hayError = true;
        }

        // Validar edad mínima
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            try {
                LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                LocalDate hoy = LocalDate.now();
                int edad = Period.between(fechaNacimiento, hoy).getYears();

                if (edad < 18) {
                    request.setAttribute("errorEdad", "Se debe tener al menos 18 años para registrarse.");
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

        // Paso 2: Setear todos los atributos
        request.setAttribute("dni", dni);
        request.setAttribute("cuil", cuil);
        request.setAttribute("telefono", telefono);
        request.setAttribute("nombre", request.getParameter("nombre"));
        request.setAttribute("apellido", request.getParameter("apellido"));
        request.setAttribute("sexo", request.getParameter("sexo"));
        request.setAttribute("nacionalidad", request.getParameter("nacionalidad"));
        request.setAttribute("fechaNacimiento", fechaNacimientoStr);
        request.setAttribute("direccion", request.getParameter("direccion"));
        request.setAttribute("localidad", request.getParameter("localidad"));
        request.setAttribute("provincia", request.getParameter("provincia"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("usuario", usuario);

        // Si hay errores de validación, volver al formulario
        if (hayError) {
            request.getRequestDispatcher("FormularioClienteServlet").forward(request, response);
            return;
        }

        try {
            NegocioUsuario negocio = new NegocioUsuarioImpl();

            // Validar DNI duplicado
            int dniInt = Integer.parseInt(dni);
            if (negocio.existeDni(dniInt)) {
                request.setAttribute("errorDni", "El DNI ya está registrado.");
                request.getRequestDispatcher("FormularioClienteServlet").forward(request, response);
                return;
            }

            // Validar nombre de usuario duplicado
            if (negocio.existeNombreUsuario(usuario)) {
                request.setAttribute("errorNombreUsuario", "El nombre de usuario ya está registrado.");
                request.getRequestDispatcher("FormularioClienteServlet").forward(request, response);
                return;
            }

            // Validar CUIL duplicado
            long cuilLong = Long.parseLong(cuil);
            if (negocio.existeCuil(cuilLong)) {
                request.setAttribute("errorCuil", "El CUIL ya está registrado.");
                request.getRequestDispatcher("FormularioClienteServlet").forward(request, response);
                return;
            }

            // Paso 3: Crear objeto Usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setDni(dniInt);
            nuevoUsuario.setCuil(cuilLong);
            nuevoUsuario.setNombre(request.getParameter("nombre"));
            nuevoUsuario.setApellido(request.getParameter("apellido"));
            nuevoUsuario.setSexo(request.getParameter("sexo"));
            nuevoUsuario.setNacionalidad(request.getParameter("nacionalidad"));
            nuevoUsuario.setFechaDeNacimiento(Date.valueOf(fechaNacimientoStr));
            nuevoUsuario.setDireccion(request.getParameter("direccion"));
            nuevoUsuario.setIdProvincia(Integer.parseInt(request.getParameter("provincia")));
            nuevoUsuario.setIdLocalidad(Integer.parseInt(request.getParameter("localidad")));
            nuevoUsuario.setCorreoElectronico(request.getParameter("email"));
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setIdUsuario(2); // Cliente

            // Paso 4: Insertar
            boolean insertadoOK = negocio.agregarUsuarioConCredenciales(nuevoUsuario, usuario, password);

            if (insertadoOK) {
                response.sendRedirect("FormularioClienteServlet?status=success");
            } else {
                response.sendRedirect("FormularioClienteServlet?status=error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("FormularioClienteServlet?status=error");
        }
    }
}
