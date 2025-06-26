package servlets;

import entidad.Cuenta;
import entidad.Usuario;
import negocio.NegocioUsuario;
import negocio.NegocioCuenta;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.CuentaDao;
import dao.UsuarioDao;
import daoImpl.CuentaDaoImpl;
import daoImpl.UsuarioDaoImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/DetalleUsuarioServlet")
public class DetalleUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");

		// cargar lista de cuentas

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NegocioCuenta cNegocio = new NegocioCuentaImpl();
		NegocioUsuario uNegocio = new NegocioUsuarioImpl();
		CuentaDao dao = new CuentaDaoImpl();

		if (request.getParameter("numeroCuenta") != null) {

			int id = Integer.parseInt(request.getParameter("numeroCuenta"));
			Cuenta cuenta = cNegocio.obtenerPorId(id);
			Usuario usuario = uNegocio.obtenerPorId(cuenta.getIdCliente());

			List<Cuenta> cuentas = dao.listarCuentas(cuenta.getIdCliente());

			if (request.getParameter("verCuenta") != null) {

				request.setAttribute("cuenta", cuenta);

				request.setAttribute("usuarioDetalle", usuario);

				request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("eliminarCuenta") != null) {

				request.setAttribute("cuenta", cuenta);
				request.setAttribute("usuarioDetalle", usuario);
				request.setAttribute("cuentasUsuario", cuentas);

				boolean eliminar = true;

				request.setAttribute("eliminar", eliminar);
				request.setAttribute("cuentaEliminar", cuenta.getId());
				request.getRequestDispatcher("/admin/detalleCliente.jsp").forward(request, response);
				return;
			}

			if (request.getParameter("confirmarEliminar") != null) {

				cNegocio.inactivarCuenta(id);

				request.setAttribute("cuenta", cuenta);
				request.setAttribute("usuarioDetalle", usuario);
				request.setAttribute("cuentasUsuario", cuentas);
				
				boolean eliminar = false;

				request.setAttribute("eliminar", eliminar);
				request.setAttribute("cuentasUsuario", cuentas);

				request.getRequestDispatcher("/admin/detalleCliente.jsp").forward(request, response);
				return;
			}
		}

		if (request.getParameter("agregarCuenta") != null) {
			boolean permitirAgregar;
			int id = Integer.parseInt(request.getParameter("idUser"));

			Usuario usuario = uNegocio.obtenerPorId(id);
			List<Cuenta> cuentas = dao.listarCuentas(id);

			if (cNegocio.cantidadCuentas(id)) {
				permitirAgregar = true;


				request.setAttribute("permitirAgregar", permitirAgregar);
				request.getRequestDispatcher("/admin/verCuentaAdmin.jsp").forward(request, response);
				return;
			} else {
				permitirAgregar = false;
				
				request.setAttribute("usuarioDetalle", usuario);
				request.setAttribute("cuentasUsuario", cuentas);
				request.setAttribute("permitirAgregar", permitirAgregar);
				request.getRequestDispatcher("/admin/detalleCliente.jsp").forward(request, response);
				return;
			}

		}
	}
}
