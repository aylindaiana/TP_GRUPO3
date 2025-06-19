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
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a>
	        </li>
	    <%
	    	// si es admin
	    	%>
	    	
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/clientes.jsp">Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
	        </li>
	   <%
	    %>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamo</a>
	        </li>
	      </ul>
	      <span class="navbar-text d-flex flex-row">
	      <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp">Nombre Usuario</a>
	        <button class="btn btn-danger">Log Out</button>
	      </span>
	    </div>
	  </div>
	</nav>

	<div class="container text-center"
		id="general-container">
		<div class="row">
			<div class="col-4" id="cuenta-destino-container">

				<div class="col">
					<div class="row">
						<b>CLIENTES</b>
						<select class="btn btn-secondary btn-lg dropdown-toggle">
							<option>Usuario 1</option>
							<option>Usuario 2</option>
							<option>Usuario 3</option>
							<option>Usuario 4</option>
							<option>Usuario 5</option>
							<option>Usuario 6</option>
							<option>Usuario 7</option>
							<option>Usuario 8</option>
							<option>Usuario 9</option>
						</select>
					</div>
					<div class="row">
						<b>CUENTA DESTINO</b>
						<select class="btn btn-secondary btn-lg dropdown-toggle">
							<option>Cuenta 1</option>
							<option>Cuenta 2</option>
							<option>Cuenta 3</option>
						</select>
					</div>	
				</div>
				<div class="row">Monto solicitado total</div>
				<div class="row">
					<div class="col" id="montos-container">
						<div class="row">$500.000,00</div>
						<div class="row">$1.000.000,00</div>
					</div>
				</div>
				<div class="row">Monto total a pagar</div>

			</div>
			<div class="col" id="historial-container">

				<div class="row">
					<b>HISTORIAL</b>
				</div>

				<div class="row">
					<table class="table table-hover">
						<tr>
							<th>Numero de prestamo</th>
							<th>Estado</th>
						</tr>
						<tr>
							<td>prestamo 1</td>
							<td><a href="detallesPrestamo.jsp" class="btn btn-success">ver</a></td>
						</tr>

						<tr>
							<td>prestamo 2</td>
							<td><a href="" class="btn btn-warning">pendiente</a></td>
						</tr>

						<tr>
							<td>prestamo 3</td>
							<td><a href="" class="btn btn-warning">pendiente</a></td>
						</tr>

						<tr>
							<td>prestamo 4</td>
							<td><a href="" class="btn btn-danger">rechazado</a></td>
						</tr>
					</table>
				</div>

			</div>
		</div>
	</div>
	
</body>
</html>