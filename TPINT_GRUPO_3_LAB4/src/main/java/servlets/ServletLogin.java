package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioCredencialesDao;
import daoImpl.UsuarioCredencialesImpl;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = !request.getParameter("username").toString().isEmpty() ? request.getParameter("username").toString() : "";
		String password = !request.getParameter("password").toString().isEmpty() ? request.getParameter("password").toString() : "";
		
		UsuarioCredencialesDao dao = new UsuarioCredencialesImpl();
		Boolean login = dao.iniciarSesion(username, password);

		if(login) {
			// deberia verificar con un usuario tipo que tipo de usuario se logueo, para ver si lo re dirige al home usuario o admin
		    RequestDispatcher rd = request.getRequestDispatcher("/admin/homeAdmin.jsp");
		    rd.forward(request, response);
		} else {
			// mensaje de error
		}
	}

}
