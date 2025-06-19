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
				<a href="${pageContext.request.contextPath}/cliente/homeCliente.jsp">Home</a>
				<a
					href="${pageContext.request.contextPath}/cliente/transferencias.jsp">Transferir</a>
				<a href="${pageContext.request.contextPath}/cliente/cuentas.jsp">Cuentas</a>
				<a href="${pageContext.request.contextPath}/cliente/prestamos.jsp"><u>Préstamo</u></a>
				<span style="margin-left: 20px;">Usuario</span>
				<button class="logout-btn">LOG OUT</button>
			</div>
		</nav>
	</header>

	<div class="container text-center" id="general-container">
		<div class="col-md-6 mx-auto" id="historial-container">
			<div class="row">
				<b>Pagar cuota numero X</b> Seleccione cuenta <select
					class="btn btn-secondary btn-lg dropdown-toggle">
					<option>Cuenta 1</option>
					<option>Cuenta 2</option>
					<option>Cuenta 3</option>
				</select>
			</div>

			<br>
			<div class="row">
				<div class="col" id="montos-container">Monto: $2000</div>
			</div>


			<br>
			<div class="row">
				<a href="verPrestamo.jsp" class="btn btn-success">Confirmar</a>
			</div>
			<br>
			<div class="row">
				<a href="verPrestamo.jsp" class="btn btn-danger">Cancelar</a>
			</div>
		</div>
	</div>

</body>
</html>





















