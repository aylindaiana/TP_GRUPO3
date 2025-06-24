<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitar Prestamo</title>

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
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Bankame</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarText">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/cliente/homeCliente.jsp">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/cliente/cuentas.jsp">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="${pageContext.request.contextPath}/cliente/prestamos.jsp">Prestamo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/cliente/transferencias.jsp">Transferir</a>
	        </li>
	      </ul>	  
	      <span class="navbar-text d-flex flex-row">
		    <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/cliente/verUsuarioCliente.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
		    <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Log Out</a>
		  </span>
	    </div>
	  </div>
	</nav>

	<div class="container text-center" id="general-container">
		<div class="col" id="historial-container">
			<div class="col">
				<form action="">
					<div class="row">
						<table class="table table-hover">
							<tr class="table-info">
								<th>Cuenta</th>
								<th>Cantidad de cuotas</th>
								<th>Tasa de interés</th>
								<th>Monto solicitado</th>
								<th>Monto final a pagar</th>
							</tr>

							<tr>
								<th>Cuenta 1</th>
								<th>9</th>
								<th>50%</th>
								<th>1.000.000,00</th>
								<th>1.500.000,00</th>
							</tr>
						</table>
					</div>
					<div class="row">
						<div class="col">
							<div class="row">
								<a href="prestamos.jsp" class="btn btn-success">Confirmar</a>
							</div>
						</div>
						<div class="col">
							<div class="row">
								<a href="prestamos.jsp" class="btn btn-danger">Cancelar</a>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>