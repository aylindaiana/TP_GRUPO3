package servlets;

import entidad.Cuenta;
import entidad.Usuario;
import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/DetalleUsuarioServlet")
public class DetalleUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        //cargar lista de cuentas
        
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Usuario usuario = negocioUsuario.obtenerPorId(id); // Usamos la función que ya tenés creada
                
                if (usuario != null) {
                	CuentaDao dao = new CuentaDaoImpl();
                	
                	List<Cuenta> cuentas = dao.listarCuentas(Integer.parseInt(idParam));
                	

                    request.setAttribute("cuentasUsuario", cuentas);
                    request.setAttribute("usuarioDetalle", usuario);
                    request.getRequestDispatcher("/admin/detalleCliente.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Si no hay ID válido o usuario no encontrado, redireccionamos
        response.sendRedirect("admin/clientes.jsp?status=errorDetalle");
    }
}
