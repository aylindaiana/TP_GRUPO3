package servlets;

import java.io.IOException;
import java.util.List;

import entidad.Cuenta;
import negocio.NegocioTransferencia;
import negocioImpl.NegocioTransferenciaImpl;
import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/TransferenciaServlet")
public class TransferenciaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TransferenciaServlet() {
        super();
    }

    // === GET ===
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");

        // Traer cuentas propias del usuario logueado
        int idCliente = (int) request.getSession().getAttribute("idCliente");
        CuentaDao cuentaDao = new CuentaDaoImpl();
        List<Cuenta> cuentasPropias = cuentaDao.listarCuentas(idCliente);

        request.setAttribute("cuentasPropias", cuentasPropias);

        // Redirige según opción elegida
        if ("propia".equals(tipo)) {
            request.getRequestDispatcher("/cliente/transferirPropia.jsp").forward(request, response);
        } else if ("cbu".equals(tipo)) {
            request.getRequestDispatcher("/cliente/transferirCBU.jsp").forward(request, response);
        } else {
            response.sendRedirect("HomeClienteServlet");
        }
    }

    // === POST ===
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int idCliente = (int) request.getSession().getAttribute("idCliente");
        CuentaDao cuentaDao = new CuentaDaoImpl();
        List<Cuenta> cuentasPropias = cuentaDao.listarCuentas(idCliente);
        request.setAttribute("cuentasPropias", cuentasPropias);

        String tipoTransferencia = request.getParameter("tipoTransferencia");
        String strCuentaOrigen = request.getParameter("cuentaOrigen");
        String strCuentaDestino = request.getParameter("cuentaDestino");
        String strCBUDestino = request.getParameter("cbuDestino");
        String strMonto = request.getParameter("monto");
        String comentario = request.getParameter("comentario");

        boolean hayError = false;

        int cuentaOrigen = 0;
        int cuentaDestino = 0;
        double monto = 0;

        try {
            cuentaOrigen = Integer.parseInt(strCuentaOrigen);
            monto = Double.parseDouble(strMonto);

            if (monto <= 0) {
                hayError = true;
                request.setAttribute("errorMonto", "El monto debe ser mayor a cero.");
            }

            if ("propia".equals(tipoTransferencia)) {
                cuentaDestino = Integer.parseInt(strCuentaDestino);
            } else if ("cbu".equals(tipoTransferencia)) {
                cuentaDestino = cuentaDao.obtenerIdCuentaPorCBU(strCBUDestino);
                if (cuentaDestino <= 0) {
                    hayError = true;
                    request.setAttribute("errorGeneral", "El CBU ingresado no existe.");
                }
            } else {
                hayError = true;
                request.setAttribute("errorGeneral", "Tipo de transferencia desconocido.");
            }

        } catch (Exception e) {
            hayError = true;
            request.setAttribute("errorGeneral", "Datos inválidos. Verifique los campos.");
        }

        if (cuentaOrigen == cuentaDestino) {
            hayError = true;
            request.setAttribute("errorGeneral", "No se puede transferir a la misma cuenta.");
        }

        if (hayError) {
            if ("propia".equals(tipoTransferencia)) {
                request.getRequestDispatcher("/cliente/transferirPropia.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/cliente/transferirCBU.jsp").forward(request, response);
            }
            return;
        }

        // === PROCESAR TRANSFERENCIA ===
        NegocioTransferencia negocioTransf = new NegocioTransferenciaImpl();
        boolean resultado = negocioTransf.transferir(cuentaOrigen, cuentaDestino, monto, comentario);

        if (resultado) {
            request.setAttribute("success", "Transferencia realizada con éxito.");
        } else {
            request.setAttribute("errorGeneral", "Saldo insuficiente o error interno.");
        }

        if ("propia".equals(tipoTransferencia)) {
            request.getRequestDispatcher("/cliente/transferirPropia.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/cliente/transferirCBU.jsp").forward(request, response);
        }
    }
}
