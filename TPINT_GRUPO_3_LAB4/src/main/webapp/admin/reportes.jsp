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
        href="${pageContext.request.contextPath}/resources/css/cuentasAdmin.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="${pageContext.request.contextPath}/HomeAdminServlet">Banco G3</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarText">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a></li>
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a></li>
	      </ul>		
		  <span class="navbar-text d-flex flex-row align-items-center gap-2">
			  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/VerDatosAdminServlet"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
			  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
		  </span>
	    </div>
	  </div>
	</nav>

    <div class="container mt-4">
        <h1 class="page-title">Reportes del Sistema</h1>
  
        

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
		    <div class="col-md-2 d-flex align-items-end">
		        <a href="${pageContext.request.contextPath}/ReportesServlet?accion=limpiar" class="btn btn-outline-secondary ms-2">Limpiar</a>
		    </div>
		    
		</form>
		      
        <% if (request.getAttribute("error") != null) { %>
		    <div class="alert alert-danger">
		        <%= request.getAttribute("error") %>
		    </div>
		<% } %>
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
                    <span class="metric-value"><%= (reporte != null) ? reporte.getCuentasCajaAhorro() : 0 %></span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">🏛️ Cuenta corriente:</span>
                    <span class="metric-value"><%= (reporte != null) ? reporte.getCuentasCuentaCorriente() : 0 %></span>
                </div>
                <hr>
                <div class="metric-row">
                    <span class="metric-label">📋 Total cuentas activas:</span>
                    <span class="metric-value metric-primary"><%= (reporte != null) ? reporte.getCuentasCajaAhorro() + reporte.getCuentasCuentaCorriente() : 0 %> </span>
                </div>
            </div>
            

            <div class="info-card">
                <div class="info-card-header">
                    <h6>↔️ Movimientos por tipo</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">➡️ Transferencias:</span>
                    <span class="metric-value"><%= (reporte != null) ? reporte.getTotalTransferencias() : 0 %></span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">⬆️ Altas de cuenta:</span>
                    <span class="metric-value"><%= (reporte != null) ? reporte.getTotalAltasCuenta() : 0 %></span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">💰 Pagos de préstamo:</span>
                    <span class="metric-value"><%= (reporte != null) ? reporte.getTotalPagosPrestamo() : 0 %></span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">📈 Altas de préstamos:</span>
                    <span class="metric-value"><%= (reporte != null) ? reporte.getTotalAltasPrestamo() : 0 %></span>
                </div>
            </div>
        </div>
	</div>
	
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>