package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.HashMap;
import java.util.Map;

import entidad.Usuario;
import entidad.Cuenta;
import negocio.NegocioUsuario;
import negocioImpl.NegocioUsuarioImpl;
import negocio.NegocioCuenta;
import negocioImpl.NegocioCuentaImpl;

@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();
    private NegocioCuenta negocioCuenta = new NegocioCuentaImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pagina = 1;               // Página por defecto
        int cantidadPorPagina = 10;   // Siempre mostrar 10 usuarios por página

        // Leer el número de página de la request
        String paramPagina = request.getParameter("page");

        try {
            if (paramPagina != null) {
                pagina = Integer.parseInt(paramPagina);
            }
        } catch (NumberFormatException e) {
            pagina = 1; // Valor por defecto si hay error
        }

        if (pagina < 1) pagina = 1;

        // Obtener usuarios de la página actual
        List<Usuario> usuarios = negocioUsuario.obtenerUsuariosPaginados(pagina, cantidadPorPagina);
        
        Map<Integer, Integer> cuentasPorUsuario = new HashMap<>();
        for (Usuario usuario : usuarios) {
            int cantidad = negocioCuenta.contarCuentasPorUsuario(usuario.getId());
            cuentasPorUsuario.put(usuario.getId(), cantidad);
        }
        
        
        int totalUsuarios = negocioUsuario.contarUsuarios();
        int totalPaginas = (int) Math.ceil((double) totalUsuarios / cantidadPorPagina);

        // Atributos para el JSP
        request.setAttribute("obtenerTodos", usuarios);
        request.setAttribute("cuentasPorUsuario", cuentasPorUsuario);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("cantidadPorPagina", cantidadPorPagina);

        // Redirigir a la vista
        request.getRequestDispatcher("/admin/clientes.jsp").forward(request, response);
    }
}
