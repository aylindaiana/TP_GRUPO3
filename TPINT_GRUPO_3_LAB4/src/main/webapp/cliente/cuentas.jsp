<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.List" %>
<%
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasUsuario");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cuentas - Cliente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/homeCliente.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/HomeClienteServlet">Banco G3</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
                </li>
            </ul>
            <span class="navbar-text d-flex flex-row align-items-center gap-2">
                <a class="username-link"
                   href="${pageContext.request.contextPath}/VerDatosClienteServlet">
                    <%= (session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %>
                </a>
                <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
            </span>
        </div>
    </div>
</nav>


<div class="container">
  <div class="row mt-4">
    <% if (cuentas != null && !cuentas.isEmpty()) {
      for (Cuenta cuenta : cuentas) { %>
    <div class="col-md-4">
      <div class="info-container">
        <h5 class="section-title">Cuenta N°: <%= cuenta.getId() %></h5>

        <div class="info-field">
          <label class="field-label">Tipo:</label>
          <span class="field-value"><%= cuenta.getDescripcionTipoDeCuenta() %></span>
        </div>

        <div class="info-field">
          <label class="field-label">Saldo:</label>
          <span class="saldo-positivo">$<%= cuenta.getSaldo() %></span>
        </div>

        <div class="info-field">
          <label class="field-label">CBU:</label>
          <span class="cbu-text"><%= cuenta.getCbu() %></span>
        </div>

        <div class="mt-3 text-center">
          <a href="${pageContext.request.contextPath}/cliente/verCuenta.jsp?id=<%= cuenta.getId() %>"
             class="btn btn-primary">Ver</a>
        </div>

      </div>
    </div>
    <% } } else { %>
    <div class="col-12">
      <div class="alert alert-info text-center" role="alert">
        No hay cuentas disponibles en este momento.
      </div>
    </div>
    <% } %>
  </div>
</div>

	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>

</body>
</html>

