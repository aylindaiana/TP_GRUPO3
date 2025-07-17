<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Cuenta" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cuentas</title>
<link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" 
        crossorigin="anonymous">
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
			<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a></li>
	      </ul>		  
		  <span class="navbar-text d-flex flex-row align-items-center gap-2">
			  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/VerDatosAdminServlet"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
			  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
		  </span>
	    </div>
	  </div>
	</nav>
	
<main class="container my-4">
	  <div class="card mb-4">
	    <div class="card-body">
	      <h5 class="card-title mb-3"><strong>Buscar Cuentas</strong></h5>
	      <form method="get" action="${pageContext.request.contextPath}/CuentaAdminServlet">
	        <div class="row g-3">
	          <div class="col-md-3">
	            <label class="form-label">Cliente por Nombre y apellido</label>
	            <input type="text" class="form-control" name="filtroCliente"
	              value="<%= request.getAttribute("busquedaAnterior") != null ? request.getAttribute("busquedaAnterior") : "" %>">
	          </div>
	          <div class="col-md-3">
	            <label class="form-label">CBU</label>
	            <input type="text" class="form-control" name="filtroCBU"
	              value="<%= request.getAttribute("cbuAnterior") != null ? request.getAttribute("cbuAnterior") : "" %>">
	          </div>
	          <div class="col-md-2">
	            <label class="form-label">Desde</label>
	            <input type="date" class="form-control" name="fechaDesde"
	              value="<%= request.getAttribute("fechaDesdeAnterior") != null ? request.getAttribute("fechaDesdeAnterior") : "" %>">
	          </div>
	          <div class="col-md-2">
	            <label class="form-label">Hasta</label>
	            <input type="date" class="form-control" name="fechaHasta"
	              value="<%= request.getAttribute("fechaHastaAnterior") != null ? request.getAttribute("fechaHastaAnterior") : "" %>">
	          </div>
	          <div class="col-md-2">
	            <label class="form-label">Tipo de Cuenta</label>
	            <select class="form-select" name="tipoCuenta">
	              <option value="">-- Todas --</option>
	              <option value="1" <%= "1".equals(request.getAttribute("tipoCuentaAnterior")) ? "selected" : "" %>>Caja de Ahorro</option>
	              <option value="2" <%= "2".equals(request.getAttribute("tipoCuentaAnterior")) ? "selected" : "" %>>Cuenta Corriente</option>
	            </select>
	          </div>
	        </div>
	        <div class="d-flex justify-content-end gap-2 mt-3">
	          <button type="submit" class="btn btn-primary">Buscar</button>
	          <a href="${pageContext.request.contextPath}/CuentaAdminServlet" class="btn btn-outline-secondary">Limpiar</a>
	        </div>
	      </form>
	    </div>
	  </div>

       <% if (request.getAttribute("error") != null) { %>
		   <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
		<% } %>
		<% if (request.getAttribute("activacion") != null) { %>
		   <div class="alert alert-success"><%= request.getAttribute("activacion") %></div>
		<% } %>
		<% if (request.getAttribute("inactivo") != null) { %>
		   <div class="alert alert-success"><%= request.getAttribute("inactivo") %></div>
		<% } %>

        
	  <div class="card">
	    <div class="card-header d-flex justify-content-between align-items-center">
	      <h5 class="mb-0">Cuentas</h5>
	      <a href="${pageContext.request.contextPath}/DetalleCuentaServlet?modo=crear" class="btn btn-success btn-sm">Crear Nueva Cuenta</a>
	    </div>
	    <div class="card-body p-0">
	      <div class="table-responsive">
	        <table class="table table-striped table-hover table-bordered mb-0">
	          <thead class="table-primary text-center">
	                <tr>
                	<th>Nro Cuenta</th>
                	<th>ID cliente</th>
                	<th>Cliente</th> 
                    <th>CBU</th>
                    <th>Tipo de cuenta</th>
                    <th>Fecha de Creacion</th>
                    <th>Saldo</th>
                    <th>Estado</th>
                    <th>Acción</th>
                </tr>
            </thead>
			<tbody>
			<%
			    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
			    if (cuentas != null) {
			        for (Cuenta cuenta : cuentas) {
			%>
			    <tr>
			    	<td><%= cuenta.getId() %></td>
			    	<td><%= cuenta.getIdCliente() %></td>
			        <td><%= cuenta.getNombreCliente() %></td>
			        <td>..................<%= cuenta.getCbu().substring(cuenta.getCbu().length() - 3) %></td>
			        <td><%= cuenta.getIdTipoDeCuenta() == 1 ? "Caja de ahorro" : "Cuenta corriente" %></td>
			        <td><%= cuenta.getFechaDeCreacion() %></td>
			        <td>$<%= String.format("%.2f", cuenta.getSaldo()) %></td>
			        <td>
			            <span class="badge <%= cuenta.isEstado() ? "bg-success" : "bg-danger" %>">
			                <%= cuenta.isEstado() ? "Activa" : "Inactiva" %>
			            </span>
			        </td>
			        <td>
			            <form method="get" action="DetalleCuentaServlet">
			                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>"/>
			                <input type="hidden" name="modo" value="editar"/>
			                <button type="submit" class="ver-btn">ver</button>
			            </form>
			        </td>
			    </tr>
			<%
			        }
			    } else {
			%>
			    <tr><td colspan="7">No hay cuentas disponibles.</td></tr>
			<%
			    }
			%>
			</tbody>

        </table>
        <%
		    int paginaActual = (request.getAttribute("paginaActual") != null) ? (Integer)request.getAttribute("paginaActual") : 1;
		    int totalPaginas = (request.getAttribute("totalPaginas") != null) ? (Integer)request.getAttribute("totalPaginas") : 1;
		
		    String filtroClienteVal = request.getAttribute("filtroCliente") != null ? request.getAttribute("filtroCliente").toString() : "";
		    String filtroCBUVal = request.getAttribute("filtroCBU") != null ? request.getAttribute("filtroCBU").toString() : "";
		%>

	<nav aria-label="Paginación de cuentas">
	  <ul class="pagination justify-content-center mt-4">
	    <li class="page-item <%= (paginaActual == 1) ? "disabled" : "" %>">
	      <a class="page-link" href="CuentaAdminServlet?page=<%= paginaActual - 1 %>&filtroCliente=<%= filtroClienteVal %>&filtroCBU=<%= filtroCBUVal %>">Anterior</a>
	    </li>
	    <% for (int i = 1; i <= totalPaginas; i++) { %>
	      <li class="page-item <%= (i == paginaActual) ? "active" : "" %>">
	        <a class="page-link" href="CuentaAdminServlet?page=<%= i %>&filtroCliente=<%= filtroClienteVal %>&filtroCBU=<%= filtroCBUVal %>"><%= i %></a>
	      </li>
	    <% } %>
	    <li class="page-item <%= (paginaActual == totalPaginas) ? "disabled" : "" %>">
	      <a class="page-link" href="CuentaAdminServlet?page=<%= paginaActual + 1 %>&filtroCliente=<%= filtroClienteVal %>&filtroCBU=<%= filtroCBUVal %>">Siguiente</a>
	    </li>
	  </ul>
	</nav>
	        
	    	</div>
		</div>
	</div>
</main>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>