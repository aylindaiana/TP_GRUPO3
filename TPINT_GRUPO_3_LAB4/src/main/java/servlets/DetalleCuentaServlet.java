package servlets;

import entidad.Cuenta;
import negocio.NegocioCuenta;
import negocioImpl.NegocioCuentaImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DetalleCuentaServlet")
public class DetalleCuentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NegocioCuenta negocioCuenta = new NegocioCuentaImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCuentaParam = request.getParameter("idCuenta");

        if (idCuentaParam != null) {
            int idCuenta = Integer.parseInt(idCuentaParam);
            Cuenta cuenta = negocioCuenta.obtenerPorId(idCuenta);

            if (cuenta != null) {
                request.setAttribute("cuenta", cuenta);
                request.setAttribute("modo", "editar");
            } else {
                request.setAttribute("error", "Cuenta no encontrada.");
            }
        } else {
            request.setAttribute("modo", "agregar");
        }

        request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCuenta = request.getParameter("idCuenta") != null ? Integer.parseInt(request.getParameter("idCuenta")) : 0;
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            int tipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
            double saldo = Double.parseDouble(request.getParameter("saldo"));
            String cbu = request.getParameter("cbu");

            Cuenta cuenta = new Cuenta();
            cuenta.setId(idCuenta);
            cuenta.setIdCliente(idCliente);
            cuenta.setIdTipoDeCuenta(tipoCuenta);
            cuenta.setSaldo(saldo);
            cuenta.setCbu(cbu);

            boolean resultado;
            if (idCuenta > 0) {
                resultado = negocioCuenta.modificar(cuenta);
            } else {
                resultado = negocioCuenta.insertar(cuenta);
            }

            if (resultado) {
                response.sendRedirect("ListarCuentasServlet");
            } else {
                request.setAttribute("error", "No se pudo guardar la cuenta.");
                request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar los datos.");
            request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
        }
    }
}
