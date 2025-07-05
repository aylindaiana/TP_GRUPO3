<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prestamos</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
		crossorigin="anonymous">
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
		crossorigin="anonymous"></script>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/prestamos.css">
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
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a>
	        </li>
	        <li class="nav-item">
			  <a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a>
			</li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}//CuentaAdminServlet">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamos</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
	        </li>
	      </ul>			  
		  <span class="navbar-text d-flex flex-row">
			  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
			  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Log Out</a>
		  </span>
	    </div>
	  </div>
	</nav>

	
	<%
	
		List<Usuario> clientes = (List<Usuario>)request.getAttribute("listaClientes");
		List<Cuenta> cuentas = (List<Cuenta>)request.getAttribute("listaCuentas");
		List<Prestamo> prestamosPorCliente = (List<Prestamo>)request.getAttribute("listaPrestamos");
		double montoSolicitadoTotal = (double)request.getAttribute("montoSolicitadoTotal");
		double montoTotalAPagar = (double)request.getAttribute("montoTotalAPagar");
		
	%>
	

	<div class="container text-center"
		id="general-container">
		<div class="row">
			<div class="col-4" id="cuenta-destino-container">
		<form action="">

				<div class="col">
					<div class="row">
						<b>CLIENTES</b>
						<select class="form-select" name="clienteSeleccionada">
							<%
								for(Usuario aux : clientes)
								{
							%>
							
								<option value="<%= aux.getId() %>"><%= aux.getApellido() %> <%= aux.getNombre() %></option>									
									
							<%
								}
							%>
						
						</select>
					</div>
					<div class="row">
						<b>CUENTA DESTINO</b>
						<select class="form-select" name="cuentaSeleccionada">
							<%
								for(Cuenta aux : cuentas)
								{
							%>
							
							    <option value="<%= aux.getId() %>"><%= aux.getCbu() %></option>								
									
							<%
								}
							%>
						
						</select>
					</div>	
				</div>
				<div class="row">Monto solicitado total</div>
				<div class="row">
					<div class="col" id="montos-container">
						<div class="row">$<%= montoSolicitadoTotal %></div>
						<div class="row">$<%= montoTotalAPagar %></div>
					</div>
				</div>
				<div class="row">Monto total a pagar</div>

				<button type="submit" class="btn btn-info" name="btn-buscar">buscar</button>
			</form>
			</div>
			
			
			
			<div class="col" id="historial-container">

				<div class="row">
					<b>HISTORIAL</b>
				</div>

				<div class="row">
					<table class="table table-hover">
						<tr class="table-info">
							<th>Numero de prestamo</th>
							<th>Estado</th>
						</tr>
						
						<%
						List<Prestamo> prestamos = (List<Prestamo>)request.getAttribute("listaPrestamos");
						for(Prestamo aux : prestamos)
						{
						%>
						
						<tr>
							<td>identificacion: <%= aux.getID() %></td>
							
							<%
							switch(aux.getAutorizacion())
							{
								case 1:
									%>
									<td>
										<button name="verCuotas" value="<%=aux.getID() %>" class="btn btn-success">ver</button>
									</td>
									<%
									break;
								case 2:
									%>
									<td><button class="btn btn-warning">pendiente</button></td>
									<%
									break;
								case 3:
									%>
									<td>
										<button name="verRechazo" value="<%=aux.getID() %>" class="btn btn-danger">rechazado</button>
									</td>
									<%
									break;
							}
							%>
							
						</tr>

						<%
						}
						%>
					</table>
				</div>

			</div>
		</div>
	</div>
	
</body>
</html>