package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioCredencialesDao;
import dao.UsuarioDao;
import dao.usuarioTipoDao;
import daoImpl.UsuarioCredencialesImpl;
import daoImpl.UsuarioDaoImpl;
import daoImpl.usuarioTipoDaoImpl;
import entidad.Usuario;
import entidad.UsuarioCredenciales;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("cerrar")) {
            request.getSession().removeAttribute("id");
            request.getSession().removeAttribute("idCliente");
            request.getSession().removeAttribute("idTipoUsuario");
            request.getSession().removeAttribute("idNombre");
            request.getSession().removeAttribute("NombreUsuario");
            RequestDispatcher rd = request.getRequestDispatcher("/public/login.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("iniciar")) {
            String username = request.getParameter("username") != null ? request.getParameter("username") : "";
            String password = request.getParameter("password") != null ? request.getParameter("password") : "";

            UsuarioCredencialesDao dao = new UsuarioCredencialesImpl();
            int idLogin = dao.iniciarSesion(username, password); // ID de usuario_credenciales

            request.getSession().setAttribute("NombreUsuario", username);

            if (idLogin != -1) {
                int idCliente = dao.obtenerIDClientePorCredencial(idLogin); // ID de usuario
                UsuarioCredenciales cred = dao.obtenerPorClienteId(idCliente); // aquí sí usamos el ID del cliente

                if (cred != null && cred.getEstado() == 0) {
                    // Usuario está inactivo
                    response.sendRedirect(request.getContextPath() + "/public/login.jsp?status=inactivo");
                    return;
                }

                request.getSession().setAttribute("id", idLogin);
                request.getSession().setAttribute("idCliente", idCliente);

                UsuarioDao userDao = new UsuarioDaoImpl();
                Usuario user = userDao.obtenerPorId(idCliente);
                request.getSession().setAttribute("idNombre", user.getNombre());

                usuarioTipoDao uDao = new usuarioTipoDaoImpl();
                int idTipoUsuario = uDao.buscarTipoId(idLogin);
                request.getSession().setAttribute("idTipoUsuario", idTipoUsuario);

                if (idTipoUsuario == 1) {
                    RequestDispatcher rd = request.getRequestDispatcher("/HomeAdminServlet");
                    rd.forward(request, response);
                } else if (idTipoUsuario == 2) {
                    RequestDispatcher rd = request.getRequestDispatcher("/HomeClienteServlet");
                    rd.forward(request, response);
                }
            } else {
                // Usuario o contraseña incorrectos
                response.sendRedirect(request.getContextPath() + "/public/login.jsp?status=errorLogin");
            }
        }
    }

}
