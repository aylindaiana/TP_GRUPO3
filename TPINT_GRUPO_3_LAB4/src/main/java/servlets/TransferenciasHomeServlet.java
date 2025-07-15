package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import entidad.Transferencia;
import negocio.NegocioTransferencia;
import negocioImpl.NegocioTransferenciaImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/TransferenciasHomeServlet")
public class TransferenciasHomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TransferenciasHomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCliente = (int) request.getSession().getAttribute("idCliente");

        CuentaDao cuentaDao = new CuentaDaoImpl();
        List<Cuenta> cuentasPropias = cuentaDao.listarCuentas(idCliente);
        request.setAttribute("cuentasPropias", cuentasPropias);

        int cuenta1 = cuentasPropias.size() >= 1 ? cuentasPropias.get(0).getId() : 0;
        int cuenta2 = cuentasPropias.size() >= 2 ? cuentasPropias.get(1).getId() : cuenta1;
        int cuenta3 = cuentasPropias.size() >= 3 ? cuentasPropias.get(2).getId() : cuenta2;

        NegocioTransferencia negocioTransf = new NegocioTransferenciaImpl();
        
        Timestamp fechaDesde = Timestamp.valueOf("2000-01-01 00:00:00");
        Timestamp fechaHasta = new Timestamp(System.currentTimeMillis());
        
        List<Transferencia> ultimasTransferencias = negocioTransf.listarTransferencias(
                cuenta1,
                cuenta2,
                cuenta3,
                fechaDesde,
                fechaHasta,
                0,
                99999999,
                0,
                5
        );

        request.setAttribute("ultimasTransferencias", ultimasTransferencias);

        request.getRequestDispatcher("/cliente/transferencias.jsp").forward(request, response);
    }

}
