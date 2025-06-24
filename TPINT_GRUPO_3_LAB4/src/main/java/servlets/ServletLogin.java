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
		
		if(accion != null && accion.equals("cerrar")) {
			request.getSession().removeAttribute("id");
			request.getSession().removeAttribute("idTipoUsuario");
			RequestDispatcher rd = request.getRequestDispatcher("/public/login.jsp");
		    rd.forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		
		if(accion != null && accion.equals("iniciar")) {
			String username = !request.getParameter("username").toString().isEmpty() ? request.getParameter("username").toString() : "";
			String password = !request.getParameter("password").toString().isEmpty() ? request.getParameter("password").toString() : "";
			
			UsuarioCredencialesDao dao = new UsuarioCredencialesImpl();
			int idLogin = dao.iniciarSesion(username, password);
			
			if(idLogin != -1) {
				request.getSession().setAttribute("id", idLogin);
				
				// check nombre usuario
				UsuarioDao userDao = new UsuarioDaoImpl();
				Usuario user = userDao.obtenerPorId(idLogin);
				String nombre = user.getNombre();
				System.out.println(nombre);
				request.getSession().setAttribute("idNombre", nombre);
				
				// check tipo usuario
				usuarioTipoDao uDao = new usuarioTipoDaoImpl();
				int idTipoUsuario = uDao.buscarTipoId(idLogin);
				
				// guardar id usuario y id tipo de usuario en sesion
				// si es admin
				if(idTipoUsuario == 1) {
					request.getSession().setAttribute("idTipoUsuario", idTipoUsuario);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/homeAdmin.jsp");
				    rd.forward(request, response);
				} 
				// si es cliente
				else if(idTipoUsuario == 2) {
					request.getSession().setAttribute("idTipoUsuario", idTipoUsuario);
					RequestDispatcher rd = request.getRequestDispatcher("/cliente/homeCliente.jsp");
				    rd.forward(request, response);
				}
			} else {
				// mensaje de error
			}
		}
	}

}
