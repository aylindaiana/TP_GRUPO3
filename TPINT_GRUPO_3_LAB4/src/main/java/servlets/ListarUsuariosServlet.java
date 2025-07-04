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
        int pagina = 1;
        int cantidadPorPagina = 10;

        String paramPagina = request.getParameter("page");
        String busqueda = request.getParameter("busqueda");

        try {
            if (paramPagina != null) {
                pagina = Integer.parseInt(paramPagina);
            }
        } catch (NumberFormatException e) {
            pagina = 1;
        }

        boolean esBusqueda = busqueda != null && !busqueda.trim().isEmpty();
        List<Usuario> usuarios;
        int totalUsuarios;

        if (esBusqueda) {
            usuarios = negocioUsuario.buscarUsuarios(busqueda);
            totalUsuarios = negocioUsuario.contarUsuariosFiltrados(busqueda);
        } else {
            usuarios = negocioUsuario.obtenerUsuariosPaginados(pagina, cantidadPorPagina);
            totalUsuarios = negocioUsuario.contarUsuarios();
        }

        Map<Integer, Integer> cuentasPorUsuario = new HashMap<>();
        for (Usuario usuario : usuarios) {
            int cantidad = negocioCuenta.contarCuentasPorUsuario(usuario.getId());
            cuentasPorUsuario.put(usuario.getId(), cantidad);
        }

        int totalPaginas = (int) Math.ceil((double) totalUsuarios / cantidadPorPagina);

        request.setAttribute("obtenerTodos", usuarios);
        request.setAttribute("cuentasPorUsuario", cuentasPorUsuario);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("cantidadPorPagina", cantidadPorPagina);
        request.setAttribute("busqueda", busqueda);

        request.getRequestDispatcher("/admin/clientes.jsp").forward(request, response);
    }

}
