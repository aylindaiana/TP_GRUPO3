package servlets;

import java.io.IOException;
import java.sql.Date;

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

        try {
            // Paso 1: Obtener parámetros del formulario
            String dni = request.getParameter("dni");
            String cuil = request.getParameter("cuil");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String sexo = request.getParameter("sexo");
            String nacionalidad = request.getParameter("nacionalidad");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String direccion = request.getParameter("direccion");
            String localidad = request.getParameter("localidad");
            String provincia = request.getParameter("provincia");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            // Paso 2: Crear objeto Usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setDni(Integer.parseInt(dni));
            nuevoUsuario.setCuil(Integer.parseInt(cuil));
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setSexo(sexo);
            nuevoUsuario.setNacionalidad(nacionalidad);
            nuevoUsuario.setFechaDeNacimiento(Date.valueOf(fechaNacimiento));
            nuevoUsuario.setDireccion(direccion);
            nuevoUsuario.setLocalidad(localidad);
            nuevoUsuario.setProvincia(provincia);
            nuevoUsuario.setCorreoElectronico(email);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setIdUsuario(2); // Usuario tipo cliente

            // Paso 3: Lógica de negocio
            NegocioUsuario negocio = new NegocioUsuarioImpl();
            boolean insertadoOK = negocio.agregarUsuarioConCredenciales(nuevoUsuario, usuario, password);

            // Paso 4: Redirección con mensaje
            if (insertadoOK) {
                response.sendRedirect("admin/altaCliente.jsp?status=success");
            } else {
                response.sendRedirect("admin/altaCliente.jsp?status=error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin/altaCliente.jsp?status=error");
        }
    }
}
