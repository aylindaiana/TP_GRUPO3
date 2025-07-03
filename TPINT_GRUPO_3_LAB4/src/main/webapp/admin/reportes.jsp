<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidad.Reporte" %>
<%
    Reporte reporte = (Reporte) request.getAttribute("reporte");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reportes</title>
<link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" 
        crossorigin="anonymous">
    <link rel="stylesheet" 
        href="${pageContext.request.contextPath}/resources/css/reportesAdmin.css">
    <link rel="stylesheet" 
        href="${pageContext.request.contextPath}/resources/css/cuentasAdmin.css">	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Bankame</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarText">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a>
	        </li>
	        <li class="nav-item">
  <a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a>
</li>

	        <li class="nav-item">
	          <a class="nav-link active" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamo</a>
	        </li>
	      </ul>		
		  <span class="navbar-text d-flex flex-row">
			  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
			  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Log Out</a>
		  </span>
	    </div>
	  </div>
	</nav>

    <div class="container mt-4">
        <h1 class="page-title">Reportes del Sistema</h1>
        
        <% if (request.getAttribute("error") != null) { %>
		    <div class="alert alert-danger">
		        <%= request.getAttribute("error") %>
		    </div>
		<% } %>
        

		<form action="ReportesServlet" method="get" class="row mb-4">
		    <div class="col-md-3">
		        <label>Desde:</label>
		        <input type="date" name="fechaInicio" class="form-control" required>
		    </div>
		    <div class="col-md-3">
		        <label>Hasta:</label>
		        <input type="date" name="fechaFin" class="form-control" required>
		    </div>
		    <div class="col-md-2 d-flex align-items-end">
		        <button type="submit" class="btn btn-primary">Generar Reporte</button>
		    </div>
		</form>
		
        <div class="summary-grid">
            <div class="info-card">
                <div class="info-card-header">
                    <h6>📊 Reporte Ingresos / Egresos</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">✓ Ingresos:</span>
                    <span class="metric-value metric-positive">$<%= (reporte != null) ? reporte.getTotalMovimientosIngresos() : 0 %></span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">✗ Egresos:</span>
                    <span class="metric-value metric-negative">$<%= (reporte != null) ? reporte.getTotalMovimientosEgresos() : 0 %></span>
                </div>
                <hr>
                <div class="metric-row">
                    <span class="metric-label">📊 Diferencia:</span>
                    <span class="metric-value metric-primary">$<%= (reporte != null) ? reporte.getTotalMovimientosIngresos() - reporte.getTotalMovimientosEgresos() : 0 %></span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>💳 Préstamos</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">✓ Aprobados:</span>
                    <span class="metric-value metric-positive"><%= (reporte != null) ? reporte.getPrestamosAprobados() : 0 %></span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">✗ Rechazados:</span>
                    <span class="metric-value metric-negative"><%= (reporte != null) ? reporte.getPrestamosRechazados() : 0 %></span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>📅 Cuotas pagadas</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">Total cuotas:</span>
                    <span class="metric-value"><%= (reporte != null) ? reporte.getCuotasPagadas() : 0 %></span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">Total recaudado:</span>
                    <span class="metric-value metric-positive"> $<%= (reporte != null) ? reporte.getTotalRecaudado() : 0 %></span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>🏦 Cuentas activas</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">📦 Caja de ahorro:</span>
                    <span class="metric-value">1</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">🏛️ Cuenta corriente:</span>
                    <span class="metric-value">2</span>
                </div>
                <hr>
                <div class="metric-row">
                    <span class="metric-label">📋 Total cuentas activas:</span>
                    <span class="metric-value metric-primary">3</span>
                </div>
            </div>
            

            <div class="info-card">
                <div class="info-card-header">
                    <h6>👥 Nuevos clientes</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">Total:</span>
                    <span class="metric-value"><%= (reporte != null) ? reporte.getNuevosClientes() : 0 %></span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">🏙️ Provincia con más altas:</span>
                    <span class="metric-value">Buenos Aires (4)</span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>↔️ Movimientos por tipo</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">➡️ Transferencias:</span>
                    <span class="metric-value">1</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">⬆️ Altas de cuenta:</span>
                    <span class="metric-value">2</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">💰 Pagos de préstamo:</span>
                    <span class="metric-value">3</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">📈 Altas de préstamos:</span>
                    <span class="metric-value">4</span>
                </div>
            </div>
        </div>
	</div>
</body>
</html>