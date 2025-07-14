<%@page import="entidad.Prestamo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rechazar Prestamo</title>

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
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a></li>
	      	</ul>		  
		  <span class="navbar-text d-flex flex-row">
			  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/VerDatosAdminServlet"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
			  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Salir</a>
		  </span>
	    </div>
	  </div>
	</nav>


<%

if(request.getAttribute("prestamo") != null){
	Prestamo prestamo = (Prestamo)request.getAttribute("prestamo");

%>
	<div class="container text-center" id="general-container">
		<div class="col-md-12 mx-auto" id="historial-container">
			<div class="row">
				<form action="MotivoRechazoPrestamoServlet" method="post">
					<table class="table table-hover">
						<tr class="table-info">
							<th>Cantidad de cuotas</th>
							<th>Monto solicitado</th>
							<th>Fecha de solicitud</th>
							<th>Motivo de rechazo</th>
						</tr>
						<tr>
							<td><%= prestamo.getCantidadCuotas() %></td>
							<td>$<%= prestamo.getImporte()/1.5 %></td>
							<td><%= prestamo.getFechaDeAlta() %></td>
							<td><textarea name="motivoRechazo" rows="8" cols="50" style="resize: none;" maxlength="45"></textarea></td>
						</tr>
					</table>
					<input type="hidden" value="<%= prestamo.getID() %>" name="IDPrestamo">
					<div class="col">
						<div class="row">
							<button name="btn-confirmar" class="btn btn-success">Confirmar</button>
						</div>
					</div>
					<div class="col">
						<div class="row">
							<button name="btn-regresar" class="btn btn-danger">Regresar</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<%
}
%>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>