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
        String modo = request.getParameter("modo");

        if ("crear".equalsIgnoreCase(modo)) {
            request.setAttribute("modo", "crear");
            request.setAttribute("cuenta", new Cuenta());
        } else if (idCuentaParam != null) {
            try {
                int idCuenta = Integer.parseInt(idCuentaParam);
                Cuenta cuenta = negocioCuenta.obtenerPorId(idCuenta);

                if (cuenta != null) {
                    request.setAttribute("cuenta", cuenta);
                    request.setAttribute("modo", "editar");
                } else {
                    request.setAttribute("error", "Cuenta no encontrada.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID de cuenta inválido.");
            }
        } else {
            request.setAttribute("error", "No se proporcionó una cuenta.");
        }

        request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String modo = request.getParameter("modo");
            boolean resultado = false;
            Cuenta cuenta = new Cuenta();

            if ("crear".equalsIgnoreCase(modo)) {
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));

                boolean tieneDemasiadasCuentas = negocioCuenta.cantidadCuentas(idCliente);
                if (tieneDemasiadasCuentas) {
                    request.setAttribute("error", "El cliente ya tiene 3 cuentas activas.");
                    request.setAttribute("modo", "crear");
                    request.setAttribute("cuenta", new Cuenta()); 
                    request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
                    return;
                }

                int tipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
                String cbu = request.getParameter("cbu");

                cuenta.setIdCliente(idCliente);
                cuenta.setIdTipoDeCuenta(tipoCuenta);
                cuenta.setCbu(cbu);
                cuenta.setSaldo(10000);
                cuenta.setEstado(true);

                resultado = negocioCuenta.insertar(cuenta);

            } else if ("editar".equalsIgnoreCase(modo)) {
                int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
                int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                int tipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
                double saldo = Double.parseDouble(request.getParameter("saldo"));
                String cbu = request.getParameter("cbu");

                cuenta.setId(idCuenta);
                cuenta.setIdCliente(idCliente);
                cuenta.setIdTipoDeCuenta(tipoCuenta);
                cuenta.setSaldo(saldo);
                cuenta.setCbu(cbu);

                resultado = negocioCuenta.modificar(cuenta);

            } else {
                request.setAttribute("error", "Modo desconocido.");
                request.setAttribute("cuenta", cuenta);
                request.setAttribute("modo", modo);
                request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
                return;
            }

            if (!resultado) {
                request.setAttribute("error", "No se pudo guardar la cuenta.");
                request.setAttribute("cuenta", cuenta);
                request.setAttribute("modo", modo);
                request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
            } else {
                response.sendRedirect("CuentaAdminServlet");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar los datos.");
            request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
        }
    }

}
