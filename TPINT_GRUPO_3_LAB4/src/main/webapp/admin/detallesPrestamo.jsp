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
</head>
<body>
	<header>
		<div>
			<strong>ADMINISTRADOR</strong>
		</div>
		<nav>
			<div>
				<a href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a>
				<a href="${pageContext.request.contextPath}/admin/altCliente.jsp">Alta
					cliente</a> <a
					href="${pageContext.request.contextPath}/admin/clientes.jsp">Lista
					Clientes</a> <a
					href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
				<a
					href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp"><u>Préstamos</u></a>
				<a href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
				<span style="margin-left: 20px;">Usuario</span>
				<button class="logout-btn">LOG OUT</button>
			</div>
		</nav>
	</header>


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