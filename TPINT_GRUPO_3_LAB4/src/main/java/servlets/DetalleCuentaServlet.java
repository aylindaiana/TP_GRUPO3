package servlets;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;
import negocio.NegocioCuenta;
import negocio.NegocioUsuario;
import negocioImpl.NegocioCuentaImpl;
import negocioImpl.NegocioMovimientoImpl;
import negocioImpl.NegocioUsuarioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
                    NegocioMovimientoImpl movimiento = new NegocioMovimientoImpl();
                    List<Movimiento> movimientos = movimiento.listarMovimientosPorCuenta(idCuenta);
                    request.setAttribute("movimientos", movimientos);
                    
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

        int random1 = (int)(Math.random() * 1000000 + 1);
        int random2 = (int)(Math.random() * 10000000 + 1);
        int random3 = (int)(Math.random() * 1000000 + 1);
        String randomCbu = Integer.toString(random1) + Integer.toString(random2) + Integer.toString(random3);
        request.setAttribute("cbu", randomCbu);
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
	        String fechaCreacionStr = request.getParameter("fechaCreacion");
	        java.util.Date fechaCreacion = null;
	        
	        /* Para identificar que exista el id de cliente indicado*/
	        NegocioUsuario negocioUsuario = new NegocioUsuarioImpl();
	        Usuario usuario = negocioUsuario.obtenerPorId(idCliente);
	        if(usuario == null) {
	              request.setAttribute("error", "No se encontro el ID de Cliente indicado. Verifique que el cliente exista.");
	              request.setAttribute("modo", modo);
	              request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
	              return;
	        }
	        
	        /* */
	        
	        try {
	            if (fechaCreacionStr != null && !fechaCreacionStr.isEmpty()) {
	                fechaCreacion = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(fechaCreacionStr);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	
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
	          if (fechaCreacion != null) {
	        	    cuenta.setFechaDeCreacion(new java.sql.Date(fechaCreacion.getTime()));
	          }
	          
	          if ("crear".equalsIgnoreCase(modo) && negocioCuenta.existeCBU(cbu)) {
	        	    request.setAttribute("error", "Ya existe una cuenta con ese CBU.");
	        	    request.setAttribute("cuenta", cuenta); 
	        	    request.setAttribute("modo", "crear");
	        	    request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
	        	    return;
	          }
	          if ("editar".equalsIgnoreCase(modo) && negocioCuenta.existeCBUExceptoId(cbu, idCuenta)) {
	        	    request.setAttribute("error", "Ya existe otra cuenta con ese CBU.");
	        	    request.setAttribute("cuenta", cuenta); 
	        	    request.setAttribute("modo", "editar");
	        	    request.getRequestDispatcher("/admin/detalleCuenta.jsp").forward(request, response);
	        	    return;
	          }

	
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
