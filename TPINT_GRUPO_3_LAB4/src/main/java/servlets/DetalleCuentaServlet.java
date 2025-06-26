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
	      	
	        int idCuenta = request.getParameter("idCuenta") != null ? Integer.parseInt(request.getParameter("idCuenta")) : 0;
	        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
	        int tipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta"));
	        double saldo = Double.parseDouble(request.getParameter("saldo"));
	        String cbu = request.getParameter("cbu");
	
	          Cuenta cuenta = new Cuenta();
	          cuenta.setId(idCuenta);
	          cuenta.setIdCliente(idCliente);
	          cuenta.setIdTipoDeCuenta(tipoCuenta);
	          if (idCuenta > 0) {
	              cuenta.setSaldo(saldo); 
	          } else {
	              cuenta.setSaldo(10000); 
	          }
	          cuenta.setCbu(cbu);
	          cuenta.setEstado(true);
	
	          boolean resultado;
	          if ("editar".equalsIgnoreCase(modo)) {
	              resultado = negocioCuenta.modificar(cuenta);
	          } else if ("crear".equalsIgnoreCase(modo)) {
	              resultado = negocioCuenta.insertar(cuenta);
	          } else {
	              request.setAttribute("error", "Modo desconocido.");
	              request.setAttribute("cuenta", cuenta);
	              request.setAttribute("modo", modo);
	              request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
	              return;
	          }

	          if (!resultado) {
	              request.setAttribute("error", "No se pudo guardar la cuenta. Verifique que el cliente no tenga más de 3 cuentas activas.");
	              request.setAttribute("cuenta", cuenta); 
	              request.setAttribute("modo", modo);
	              request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
	              return;
	          }else {
	          	response.sendRedirect("CuentaAdminServlet");
	          }
	
	      } catch (Exception e) {
	          e.printStackTrace();
	          request.setAttribute("error", "Error al procesar los datos.");
	          request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
	      }
    }

}
