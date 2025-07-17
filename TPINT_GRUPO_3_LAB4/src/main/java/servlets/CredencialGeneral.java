package servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class CredencialGeneral implements Filter {
    public CredencialGeneral() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
			HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        HttpSession session = req.getSession(false);

	        // Donde permite ingresar sin haberse logueado
	        String path = req.getRequestURI();
	        boolean esPublico = path.contains("ServletLogin") || path.contains("/public/");

	        // Si no está logueado y no es recurso público -> redirigimos al login
	        if ((session == null || session.getAttribute("idTipoUsuario") == null) && !esPublico) {
	            res.sendRedirect(req.getContextPath() + "/public/login.jsp");
	            return;
	        }

	        // Si pasa el filtro, continúa
	        chain.doFilter(request, response);
	    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
