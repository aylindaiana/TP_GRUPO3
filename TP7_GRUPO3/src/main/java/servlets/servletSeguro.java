package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import Dominio.TipoSeguro;
import Dominio.Seguro;
import Dominio.SeguroDao;

@WebServlet("/servletSeguro")
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public servletSeguro() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnFiltrar") != null) {
			SeguroDao sdao = new SeguroDao();
			int idTipo = Integer.parseInt(request.getParameter("idTipoSeguro").toString());
			ArrayList<Seguro> lista = sdao.obtenerSeguros(idTipo);
		    ArrayList<TipoSeguro> listaTipos = sdao.obtenerTiposSeguro();
			
			request.setAttribute("listaSeguros", lista);
		    request.setAttribute("listaTipos", listaTipos);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
		    rd.forward(request, response);
		}

		String accion = request.getParameter("accion");

		if (accion != null && accion.equals("nuevo")) {
		    SeguroDao sdao = new SeguroDao();
		    int proximoId = sdao.obtenerProximoIdSeguro();
		    ArrayList<TipoSeguro> listaTipos = sdao.obtenerTiposSeguro();

		    request.setAttribute("proximoId", proximoId);
		    request.setAttribute("listaTipos", listaTipos);

		    RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
		    rd.forward(request, response);
		}
		else if(accion != null && accion.equals("listado"))
		{
			SeguroDao sdao = new SeguroDao();
			ArrayList<Seguro> lista = sdao.obtenerSeguros();
		    ArrayList<TipoSeguro> listaTipos = sdao.obtenerTiposSeguro();
		    
			request.setAttribute("listaSeguros", lista);
		    request.setAttribute("listaTipos", listaTipos);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
		    rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String descripcion = request.getParameter("descripcion");
	    int tipo = Integer.parseInt(request.getParameter("tipoSeguro"));
	    double costoContratacion = Double.parseDouble(request.getParameter("costoContratacion"));
	    double costoMaximo = Double.parseDouble(request.getParameter("costoMaximo"));

	    SeguroDao dao = new SeguroDao();

	    int filas = dao.agregarSeguro(descripcion, tipo, costoContratacion, costoMaximo);

	    int proximoId = dao.obtenerProximoIdSeguro();
	    ArrayList<TipoSeguro> listaTipos = dao.obtenerTiposSeguro();

	    request.setAttribute("filasInsertadas", filas);
	    request.setAttribute("proximoId", proximoId);
	    request.setAttribute("listaTipos", listaTipos);

	    RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
	    rd.forward(request, response);
	}


}
