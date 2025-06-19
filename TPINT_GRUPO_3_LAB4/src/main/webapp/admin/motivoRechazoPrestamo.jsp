<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagar Cuota</title>

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
				<a
					href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta cliente</a>
				<a
					href="${pageContext.request.contextPath}/admin/clientes.jsp">Lista Clientes</a>
				<a href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
				<a href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp"><u>Préstamos</u></a>
				<a href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
				<span style="margin-left: 20px;">Usuario</span>
				<button class="logout-btn">LOG OUT</button>
			</div>
		</nav>
	</header>

	<div class="container text-center" id="general-container">
		<div class="col-md-12 mx-auto" id="historial-container">
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
						<td><textarea rows="8" cols="50" style="resize: none;">el monto solicitado excede la cantidad a brindar para alguien con un historial crediticio casi nulo.
								</textarea></td>
					</tr>
				</table>


				<div class="col">
					<div class="row">
						<a href="prestamosAdmin.jsp" class="btn btn-success">Confirmar</a>
					</div>
				</div>
				<div class="col">
					<div class="row">
						<a href="detallesPrestamo.jsp" class="btn btn-danger">Regresar</a>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>