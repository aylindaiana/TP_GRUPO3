package servlets;

import entidad.Provincia;
import entidad.Localidad;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.LocalidadDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/FormularioClienteServlet")
public class FormularioClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FormularioClienteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProvinciaDaoImpl provinciaDao = new ProvinciaDaoImpl();
        LocalidadDaoImpl localidadDao = new LocalidadDaoImpl();

        List<Provincia> provincias = provinciaDao.listarProvincias();
        List<Localidad> localidades = localidadDao.listarTodas();

        request.setAttribute("provincias", provincias);
        request.setAttribute("localidades", localidades);

        request.getRequestDispatcher("/admin/altaCliente.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
