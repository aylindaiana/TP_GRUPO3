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
			<strong>CLIENTE</strong>
		</div>
		<nav>
			<div>
				<a href="${pageContext.request.contextPath}/cliente/homeCliente.jsp">Home</a>
				<a href="${pageContext.request.contextPath}/cliente/transferencias.jsp">Transferir</a>
				<a href="${pageContext.request.contextPath}/cliente/cuentas.jsp">Cuentas</a>
				<a href="${pageContext.request.contextPath}/cliente/prestamos.jsp"><u>Préstamo</u></a>
				<span style="margin-left: 20px;">Usuario</span>
				<button class="logout-btn">LOG OUT</button>
			</div>
		</nav>
	</header>


	<div class="container text-center" id="general-container">
		<div class="row">
			<div class="col" id="historial-container">

				<div class="row">
					<b>PRESTAMO NUMERO 1</b>
				</div>

				<div class="row">

					<form action="">
						<table class="table table-hover">
							<tr>
								<th>Numero de Cuota</th>
								<th>Monto</th>
								<th>Fecha limite</th>
								<th>Estado cuota</th>
							</tr>
							<tr>
								<td>1</td>
								<td>$2000</td>
								<td>14/06/2025</td>
								<td><button type="submit" class="btn btn-success">Pagado</button></td>
							</tr>

							<tr>
								<td>2</td>
								<td>$2000</td>
								<td>14/07/2025</td>
								<td><button type="submit" class="btn btn-warning">Pagar</button></td>
							</tr>

							<tr>
								<td>3</td>
								<td>$2000</td>
								<td>14/08/2025</td>
								<td><button type="submit" class="btn btn-warning">Pagar</button></td>
							</tr>

							<tr>
								<td>4</td>
								<td>$2000</td>
								<td>14/09/2025</td>
								<td><button type="submit" class="btn btn-warning">Pagar</button></td>
							</tr>
						</table>
					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>