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

@WebFilter("/cliente/*")
public class CredencialesCliente implements Filter {
    public CredencialesCliente() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        if (session != null && session.getAttribute("idTipoUsuario") != null) {
        	if ((int)session.getAttribute("idTipoUsuario") == 2) {
                chain.doFilter(request, response);
        	}
        } else if (session != null && session.getAttribute("idTipoUsuario") != null){
        	if ((int)session.getAttribute("idTipoUsuario") == 1) {
                res.sendRedirect(req.getContextPath() + "/admin/homeAdmin.jsp");
        	}
        } else {
            res.sendRedirect(req.getContextPath() + "/public/login.jsp");
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
