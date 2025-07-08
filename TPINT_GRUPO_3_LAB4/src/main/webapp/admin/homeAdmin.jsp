<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Admin</title>
<link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" 
        crossorigin="anonymous">
<link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/homeAdmin.css">
<link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/cuentasAdmin.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">	
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
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a></li>
				</ul>
				<span class="navbar-text d-flex flex-row align-items-center gap-2">
					<a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
					<a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
				</span>
			</div>
		</div>
	</nav>
	
	<div class="container my-5">
		<h2 class="mb-4">Panel de Control</h2>
		<div class="row g-4">
			<div class="col-md-4">
				<div class="card text-center shadow-sm">
					<div class="card-body">
						<h5 class="card-title">Cuentas activas</h5>
						<p class="display-6 text-primary"><%= request.getAttribute("cantCuentas") %></p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card text-center shadow-sm">
					<div class="card-body">
						<h5 class="card-title">Clientes activos</h5>
						<p class="display-6 text-success"><%= request.getAttribute("cantUser") %></p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card text-center shadow-sm">
					<div class="card-body">
						<h5 class="card-title">Total dinero en cuentas del banco</h5>
						<p class="display-6 text-info">$<%= request.getAttribute("cantPlataBanco") %></p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card text-center shadow-sm">
					<div class="card-body">
						<h5 class="card-title">Valor Dolar HOY:</h5>
						<p class="display-6 text-danger">$1200</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
<script 
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" 
	crossorigin="anonymous"></script>
</body>
</html>
