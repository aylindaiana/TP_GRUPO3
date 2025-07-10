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

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");

		if (accion != null && accion.equals("cerrar")) {
			request.getSession().removeAttribute("id");
			request.getSession().removeAttribute("idCliente");
			request.getSession().removeAttribute("idTipoUsuario");
			RequestDispatcher rd = request.getRequestDispatcher("/public/login.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");

		if (accion != null && accion.equals("iniciar")) {
			String username = !request.getParameter("username").toString().isEmpty()
					? request.getParameter("username").toString()
					: "";
			String password = !request.getParameter("password").toString().isEmpty()
					? request.getParameter("password").toString()
					: "";

			UsuarioCredencialesDao dao = new UsuarioCredencialesImpl();
			int idLogin = dao.iniciarSesion(username, password);
			
			request.getSession().setAttribute("NombreUsuario", username);

			if (idLogin != -1) {
				request.getSession().setAttribute("id", idLogin);
				
				int idCliente = dao.obtenerIDClientePorCredencial(idLogin);
				request.getSession().setAttribute("idCliente", idCliente);							
				
				// check nombre usuario
				UsuarioDao userDao = new UsuarioDaoImpl();
				Usuario user = userDao.obtenerPorId(idCliente);
				String nombre = user.getNombre();
				System.out.println(nombre);
				request.getSession().setAttribute("idNombre", nombre);

				// check tipo usuario
				usuarioTipoDao uDao = new usuarioTipoDaoImpl();
				int idTipoUsuario = uDao.buscarTipoId(idLogin);				

				// guardar id usuario y id tipo de usuario en sesion
				request.getSession().setAttribute("idTipoUsuario", idTipoUsuario);
				// si es admin
				if (idTipoUsuario == 1) {
					RequestDispatcher rd = request.getRequestDispatcher("/HomeAdminServlet");
					rd.forward(request, response);
				} 
				// si es cliente
				else if (idTipoUsuario == 2) {
					RequestDispatcher rd = request.getRequestDispatcher("/HomeClienteServlet");
					rd.forward(request, response);
				}
			} else {
				// mensaje de error: podrías redirigir a login con mensaje
				response.sendRedirect(request.getContextPath() + "/public/login.jsp?error=1");
			}
			
			
		}
	}
}
