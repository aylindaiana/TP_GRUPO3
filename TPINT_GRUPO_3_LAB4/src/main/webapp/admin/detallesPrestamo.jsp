<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver prestamo</title>
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
	    <a class="navbar-brand" href="#">Banco G3</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarText">
	    	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Prestamos</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a></li>
	      	</ul>		  
		  <span class="navbar-text d-flex flex-row">
			  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
			  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Log Out</a>
		  </span>
	    </div>
	  </div>
	</nav>


	<div class="container text-center" id="general-container">
		<div class="col" id="historial-container">

			<div class="row">
				<b>PRESTAMO NUMERO 1</b>
			</div>

			<div class="row">

				<form action="">
					<table class="table table-hover">
						<tr class="table-info">
							<th>Numero de Cuota</th>
							<th>Monto</th>
							<th>Fecha limite</th>
							<th>Estado cuota</th>
						</tr>
						<tr>
							<td>1</td>
							<td>$2000</td>
							<td>14/06/2025</td>
							<td><A class="btn btn-success">Pagado</a></td>
						</tr>

						<tr>
							<td>2</td>
							<td>$2000</td>
							<td>14/07/2025</td>
							<td><a class="btn btn-warning">Pendiente</a></td>
						</tr>

						<tr>
							<td>3</td>
							<td>$2000</td>
							<td>14/08/2025</td>
							<td><a class="btn btn-warning">Pendiente</a></td>
						</tr>

						<tr>
							<td>4</td>
							<td>$2000</td>
							<td>14/09/2025</td>
							<td><a class="btn btn-warning">Pendiente</a></td>
						</tr>
					</table>
				</form>
			</div>

		</div>
	</div>
	<div class="container text-center" id="general-container">
		<div class="col" id="historial-container">
			<table class="table table-hover">
				<tr class="table-info">
					<th>Cantidad de cuotas</th>
					<th>Monto solicitado</th>
					<th>Fecha de solicitud</th>
					<th>Accion</th>
				</tr>
				<tr>
					<td>36</td>
					<td>$2000000</td>
					<td>18/06/2025</td>
					<td>
						<a href="prestamosAdmin.jsp" class="btn btn-success">Aceptar</a>
						<a href="motivoRechazoPrestamo.jsp" class="btn btn-danger">Rechazar</a>
					</td>
				</tr>

			</table>
		</div>
	</div>
	<div class="container text-center" id="general-container">
		<div class="col" id="historial-container">

			<div class="row">
				<b>PRESTAMO PRESTAMO RECHAZADO</b>
			</div>

			<div class="row">
				<table class="table table-hover">
					<tr class="table-info">
						<th>Cantidad de cuotas</th>
						<th>Monto solicitado</th>
						<th>Fecha de solicitud</th>
						<th>Motivo de rechazo</th>
					</tr>
					<tr>
						<td>36</td>
						<td>$2000000</td>
						<td>18/06/2025</td>
						<td><textarea rows="8" cols="50" readonly
								style="resize: none;">el monto solicitado excede la cantidad a brindar para alguien con un historial crediticio casi nulo.
								</textarea></td>
					</tr>

				</table>
				<a href="prestamosAdmin.jsp" class="btn btn-primary">Regresar</a>
			</div>

		</div>
	</div>

</body>
</html>