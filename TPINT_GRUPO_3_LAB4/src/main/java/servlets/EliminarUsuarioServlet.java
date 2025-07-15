package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.NegocioUsuario;
import negocio.NegocioCuenta;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioUsuarioImpl;

@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            boolean eliminado = negocioUsuario.eliminarUsuario(id);
            
            NegocioCuenta cNegocio = new NegocioCuentaImpl();
            
            
            if (eliminado) {
                cNegocio.bajaCuentasUsuario(id);
                response.sendRedirect("DetalleUsuarioServlet?id=" + id + "&status=inactivado");

            } else {
            	response.sendRedirect("DetalleUsuarioServlet?id=" + id + "&status=errorInactivacion");
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ListarUsuariosServlet?status=errorEliminacion");
        }
    }
}





















