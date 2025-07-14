<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List, entidad.Transferencia, entidad.Cuenta"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Historial de Transferencias</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/homeCliente.css">
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
    rel="stylesheet">
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/nav.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>

<%
// Corregir null para no explotar en parseInt
String fechaDesdeParam = request.getParameter("fechaDesde") != null ? request.getParameter("fechaDesde") : "";
String fechaHastaParam = request.getParameter("fechaHasta") != null ? request.getParameter("fechaHasta") : "";
String montoMinParam = request.getParameter("montoMin") != null ? request.getParameter("montoMin") : "";
String montoMaxParam = request.getParameter("montoMax") != null ? request.getParameter("montoMax") : "";
String cuentaParam = (request.getParameter("cuenta") != null && !"null".equals(request.getParameter("cuenta"))) ? request.getParameter("cuenta") : "0";
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand"
            href="${pageContext.request.contextPath}/HomeClienteServlet">Banco
            G3</a>
        <button class="navbar-toggler" type="button"
            data-bs-toggle="collapse" data-bs-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link"
                    href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a>
                </li>
                <li class="nav-item"><a class="nav-link"
                    href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
                </li>
                <li class="nav-item"><a class="nav-link"
                    href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamos</a>
                </li>
                <li class="nav-item active"><a class="nav-link active"
                    aria-current="page"
                    href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
                </li>
            </ul>
            <span class="navbar-text d-flex flex-row align-items-center gap-2">
                <a class="username-link"
                href="${pageContext.request.contextPath}/VerDatosClienteServlet">
                    <%= (session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null"%>
            </a> <a
                href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar"
                class="logout-btn">Salir</a>
            </span>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="text-center">Historial de Transferencias</h2>

    <form method="get"
        action="${pageContext.request.contextPath}/TransferenciasVerTodoServlet"
        class="row g-3 my-4">

        <div class="col-md-3">
            <label for="fechaDesde" class="form-label">Fecha desde</label>
            <input type="date" class="form-control" name="fechaDesde"
                value="<%=fechaDesdeParam%>">
        </div>
        <div class="col-md-3">
            <label for="fechaHasta" class="form-label">Fecha hasta</label>
            <input type="date" class="form-control" name="fechaHasta"
                value="<%=fechaHastaParam%>">
        </div>
        <div class="col-md-2">
            <label for="montoMin" class="form-label">Monto mínimo</label>
            <input type="number" step="0.01" class="form-control" name="montoMin"
                value="<%=montoMinParam%>">
        </div>
        <div class="col-md-2">
            <label for="montoMax" class="form-label">Monto máximo</label>
            <input type="number" step="0.01" class="form-control" name="montoMax"
                value="<%=montoMaxParam%>">
        </div>
        <div class="col-md-2">
            <label for="cuenta" class="form-label">Cuenta</label>
            <select name="cuenta" class="form-select">
                <option value="0">Todas</option>
                <%
                List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasPropias");
                for (Cuenta c : cuentas) {
                    String selected = Integer.parseInt(cuentaParam) == c.getId() ? "selected" : "";
                %>
                <option value="<%=c.getId()%>" <%=selected%>><%=c.getCbu()%></option>
                <%
                }
                %>
            </select>
        </div>
        <div class="col-12 text-center">
            <button type="submit" class="btn btn-primary px-4">Filtrar</button>
        </div>
    </form>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Fecha</th>
                <th>Tipo</th>
                <th>Cuenta Origen</th>
                <th>Cuenta Destino</th>
                <th>Monto</th>
                <th>Comentario</th>
            </tr>
        </thead>
        <tbody>
            <%
            List<Transferencia> transferencias = (List<Transferencia>) request.getAttribute("transferencias");
            if (transferencias != null && !transferencias.isEmpty()) {
                for (Transferencia t : transferencias) {
                    String claseOperacion = t.getTipoMovimiento().equals("EGRESO") ? "text-danger" : "text-success";
                    String signo = t.getTipoMovimiento().equals("EGRESO") ? "-" : "+";
                    java.sql.Timestamp fecha = t.getFecha();
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd / HH:mm");
                    String fechaFormateada = sdf.format(fecha);
            %>
            <tr>
                <td><%=fechaFormateada%></td>
                <td class="<%=claseOperacion%>"><%=t.getTipoMovimiento()%></td>
                <td><%=t.getNombreOrigen()%> <br> CBU: <%=t.getCbuOrigen()%></td>
                <td><%=t.getNombreDestino()%> <br> CBU: <%=t.getCbuDestino()%></td>
                <td><span class="<%=claseOperacion%>"><%=signo%>$<%=t.getMonto()%></span></td>
                <td><%=t.getComentario()%></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6">No se encontraron transferencias.</td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>

    <div class="d-flex justify-content-center mt-3 gap-2">
        <%
        int paginaActual = (request.getAttribute("paginaActual") != null) ? (int) request.getAttribute("paginaActual") : 1;
        boolean haySiguiente = (request.getAttribute("haySiguiente") != null) ? (boolean) request.getAttribute("haySiguiente") : false;
        String baseUrl = request.getContextPath() + "/TransferenciasVerTodoServlet?";
        String parametros = "fechaDesde=" + fechaDesdeParam + "&fechaHasta=" + fechaHastaParam + "&montoMin=" + montoMinParam + "&montoMax=" + montoMaxParam + "&cuenta=" + cuentaParam;
        %>

        <a href="<%=baseUrl + parametros + "&pagina=" + (paginaActual - 1)%>"
            class="btn btn-secondary <%=(paginaActual > 1) ? "" : "disabled"%>">Anterior</a>

        <a href="<%=baseUrl + parametros + "&pagina=" + (paginaActual + 1)%>"
            class="btn btn-primary <%=(haySiguiente) ? "" : "disabled"%>">Siguiente</a>
    </div>
</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>
